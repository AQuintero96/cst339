package com.gcu.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gcu.business.PartServiceInterface;
import com.gcu.model.PartModel;

/**
 * Handles validated updates and explicit deletion confirmation.
 */
@Controller
@RequestMapping("/parts")
public class PartMaintenanceController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(PartMaintenanceController.class);

    private final PartServiceInterface partService;

    /**
     * Receives the inventory business service.
     * @param partService part maintenance service
     */
    public PartMaintenanceController(PartServiceInterface partService) {
        this.partService = partService;
    }

    /**
     * Prevents form submissions from changing system-managed identifiers.
     * @param binder form binder
     */
    @InitBinder("partModel")
    public void configureBinding(WebDataBinder binder) {
        binder.setAllowedFields(
                "partName", "category", "manufacturer", "model",
                "quantity", "unitCost", "storageLocation", "description");
    }

    /**
     * Supplies the permitted categories.
     * @return category choices
     */
    @ModelAttribute("categories")
    public List<String> categories() {
        return Arrays.asList(
                "RAM", "SSD", "Hard Drive", "Processor",
                "Motherboard", "Power Supply", "Graphics Card", "Cooling");
    }

    /**
     * Displays an edit form populated from the database.
     * @param id requested part identifier
     * @param request current request
     * @param model view attributes
     * @param redirectAttributes redirect messages
     * @return edit page or redirect
     */
    @GetMapping("/{id}/edit")
    public String displayEdit(
            @PathVariable("id") Long id,
            HttpServletRequest request,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (!isLoggedIn(request)) {
            return "redirect:/login";
        }

        try {
            Optional<PartModel> part = partService.getPartById(id);

            if (!part.isPresent()) {
                return missingPart(redirectAttributes);
            }

            model.addAttribute("title", "Edit Part");
            model.addAttribute("partId", id);
            model.addAttribute("partModel", part.get());
            return "part-edit";
        } catch (DataAccessException exception) {
            return databaseFailure(exception, redirectAttributes);
        }
    }

    /**
     * Validates and saves edited values.
     * @param id identifier from the route
     * @param partModel submitted values
     * @param bindingResult validation results
     * @param request current request
     * @param model view attributes
     * @param redirectAttributes redirect messages
     * @return edit page or redirect
     */
    @PostMapping("/{id}/edit")
    public String updatePart(
            @PathVariable("id") Long id,
            @Valid @ModelAttribute("partModel") PartModel partModel,
            BindingResult bindingResult,
            HttpServletRequest request,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (!isLoggedIn(request)) {
            return "redirect:/login";
        }

        model.addAttribute("title", "Edit Part");
        model.addAttribute("partId", id);

        if (bindingResult.hasErrors()) {
            return "part-edit";
        }

        try {
            if (!partService.updatePart(id, partModel)) {
                return missingPart(redirectAttributes);
            }

            redirectAttributes.addFlashAttribute(
                    "successMessage", "The part was updated successfully.");
            return "redirect:/parts/" + id;
        } catch (DataAccessException exception) {
            LOGGER.error("Part update failed: {}",
                    exception.getClass().getSimpleName());

            bindingResult.reject("part.updateFailed",
                    "Unable to confirm the update. Check the saved part "
                    + "before trying again.");
            return "part-edit";
        }
    }

    /**
     * Displays a confirmation page without deleting anything.
     * @param id requested identifier
     * @param request current request
     * @param model view attributes
     * @param redirectAttributes redirect messages
     * @return confirmation page or redirect
     */
    @GetMapping("/{id}/delete")
    public String displayDelete(
            @PathVariable("id") Long id,
            HttpServletRequest request,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (!isLoggedIn(request)) {
            return "redirect:/login";
        }

        try {
            Optional<PartModel> part = partService.getPartById(id);

            if (!part.isPresent()) {
                return missingPart(redirectAttributes);
            }

            model.addAttribute("title", "Delete Part");
            model.addAttribute("part", part.get());
            return "part-delete";
        } catch (DataAccessException exception) {
            return databaseFailure(exception, redirectAttributes);
        }
    }

    /**
     * Deletes a part only after the confirmation form is submitted.
     * @param id requested identifier
     * @param request current request
     * @param redirectAttributes redirect messages
     * @return inventory redirect
     */
    @PostMapping("/{id}/delete")
    public String deletePart(
            @PathVariable("id") Long id,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes) {

        if (!isLoggedIn(request)) {
            return "redirect:/login";
        }

        try {
            if (!partService.deletePart(id)) {
                return missingPart(redirectAttributes);
            }

            redirectAttributes.addFlashAttribute(
                    "successMessage", "The part was deleted successfully.");
            return "redirect:/inventory";
        } catch (DataAccessException exception) {
            return databaseFailure(exception, redirectAttributes);
        }
    }

    /**
     * Checks the existing login session without creating a new one.
     * @param request current request
     * @return whether the session contains an authenticated user
     */
    private boolean isLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null
                && session.getAttribute("currentUser") != null;
    }

    /**
     * Reports a missing or previously deleted record.
     * @param redirectAttributes redirect messages
     * @return inventory redirect
     */
    private String missingPart(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(
                "errorMessage", "That part no longer exists.");
        return "redirect:/inventory";
    }

    /**
     * Reports database failures without exposing connection details.
     * @param exception database failure
     * @param redirectAttributes redirect messages
     * @return inventory redirect
     */
    private String databaseFailure(
            DataAccessException exception,
            RedirectAttributes redirectAttributes) {
        LOGGER.error("Part maintenance failed: {}",
                exception.getClass().getSimpleName());
        redirectAttributes.addFlashAttribute(
                "errorMessage",
                "Unable to complete the request. Check the inventory "
                + "before trying again.");
        return "redirect:/inventory";
    }
}