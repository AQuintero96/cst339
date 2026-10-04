package com.gcu.controller;

import java.util.Arrays;
import java.util.List;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gcu.business.PartServiceInterface;
import com.gcu.model.PartModel;

/**
 * Handles part creation forms and displays saved submission details.
 */
@Controller
@RequestMapping("/parts")
public class PartController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(PartController.class);

    private final PartServiceInterface partService;

    /**
     * Receives the part business service.
     *
     * @param partService the part creation service
     */
    public PartController(PartServiceInterface partService) {
        this.partService = partService;
    }

    /**
     * Allows binding only to user-editable fields.
     *
     * @param binder the form data binder
     */
    @InitBinder("partModel")
    public void configureBinding(WebDataBinder binder) {
        binder.setAllowedFields(
                "partName", "category", "manufacturer", "model",
                "quantity", "unitCost", "storageLocation", "description");
    }

    /**
     * Supplies the supported component categories.
     *
     * @return the category choices
     */
    @ModelAttribute("categories")
    public List<String> categories() {
        return Arrays.asList(
                "RAM", "SSD", "Hard Drive", "Processor",
                "Motherboard", "Power Supply", "Graphics Card", "Cooling");
    }

    /**
     * Displays an empty creation form.
     *
     * @param model the view attributes
     * @return the form template
     */
    @GetMapping("/new")
    public String displayForm(Model model) {
        model.addAttribute("title", "Add Part");
        model.addAttribute("partModel", new PartModel());
        return "part-form";
    }

    /**
     * Validates the submission and requests database persistence.
     *
     * @param partModel the submitted part
     * @param bindingResult the validation results
     * @param model the view attributes
     * @param redirectAttributes the confirmation data
     * @return the form or a redirect to confirmation
     */
    @PostMapping
    public String createPart(
            @Valid @ModelAttribute("partModel") PartModel partModel,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        model.addAttribute("title", "Add Part");

        if (bindingResult.hasErrors()) {
            return "part-form";
        }

        PartModel createdPart;

        try {
            createdPart = partService.createPart(partModel);
        } catch (DataAccessException exception) {
            LOGGER.error("Database operation failed during part creation: {}",
                    exception.getClass().getSimpleName());

            bindingResult.reject(
                    "part.unavailable",
                    "Unable to confirm that the part was saved. "
                    + "Please try again later.");
            return "part-form";
        }

        // Redirects only after the service successfully saves the part.
        redirectAttributes.addFlashAttribute("createdPart", createdPart);
        return "redirect:/parts/created";
    }

    /**
     * Displays the result carried through the successful redirect.
     *
     * @param model the view attributes
     * @return the confirmation page or a new form
     */
    @GetMapping("/created")
    public String displayConfirmation(Model model) {
        if (!model.containsAttribute("createdPart")) {
            return "redirect:/parts/new";
        }

        model.addAttribute("title", "Part Created");
        return "part-created";
    }
}