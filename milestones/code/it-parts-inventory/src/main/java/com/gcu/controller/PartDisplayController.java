package com.gcu.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gcu.business.PartServiceInterface;
import com.gcu.model.PartModel;

/**
 * Displays the saved details of an individual inventory part.
 */
@Controller
@RequestMapping("/parts")
public class PartDisplayController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(PartDisplayController.class);

    private final PartServiceInterface partService;

    /**
     * Receives the inventory business service.
     * @param partService part retrieval service
     */
    public PartDisplayController(PartServiceInterface partService) {
        this.partService = partService;
    }

    /**
     * Retrieves a part and handles missing records or database failures.
     * @param id requested identifier
     * @param request current request
     * @param model view attributes
     * @param redirectAttributes messages for inventory
     * @return details template or redirect
     */
    @GetMapping("/{id}")
    public String displayPart(
            @PathVariable("id") Long id,
            HttpServletRequest request,
            Model model,
            RedirectAttributes redirectAttributes) {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("currentUser") == null) {
            return "redirect:/login";
        }

        try {
            Optional<PartModel> part = partService.getPartById(id);

            if (!part.isPresent()) {
                redirectAttributes.addFlashAttribute(
                        "errorMessage", "That part no longer exists.");
                return "redirect:/inventory";
            }

            model.addAttribute("title", "Part Details");
            model.addAttribute("part", part.get());
            return "part-details";
        } catch (DataAccessException exception) {
            LOGGER.error("Part retrieval failed: {}",
                    exception.getClass().getSimpleName());
            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "Unable to load the part. Please try again.");
            return "redirect:/inventory";
        }
    }
}