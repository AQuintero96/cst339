package com.gcu.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

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
 * Displays the part creation form and handles submitted part information.
 */
@Controller
@RequestMapping("/parts")
public class PartController {

    private final PartServiceInterface partService;

    /**
     * Supplies the part service through constructor injection.
     *
     * @param partService the part creation service
     */
    public PartController(PartServiceInterface partService) {
        this.partService = partService;
    }

    /**
     * Limits form binding to fields users are allowed to enter.
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
     * Supplies the categories displayed in the form.
     *
     * @return the supported component categories
     */
    @ModelAttribute("categories")
    public List<String> categories() {
        return Arrays.asList(
                "RAM", "SSD", "Hard Drive", "Processor",
                "Motherboard", "Power Supply", "Graphics Card", "Cooling");
    }

    /**
     * Displays an empty part creation form.
     *
     * @param model the data supplied to the view
     * @return the part form template
     */
    @GetMapping("/new")
    public String displayForm(Model model) {
        model.addAttribute("title", "Add Part");
        model.addAttribute("partModel", new PartModel());
        return "part-form";
    }

    /**
     * Validates the submitted fields and requests part creation.
     *
     * @param partModel the submitted part
     * @param bindingResult the binding and validation results
     * @param model the data supplied to the view
     * @param redirectAttributes the confirmation data
     * @return the form with errors or a redirect to confirmation
     */
    @PostMapping
    public String createPart(
            @Valid @ModelAttribute("partModel") PartModel partModel,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Add Part");
            return "part-form";
        }

        PartModel createdPart = partService.createPart(partModel);

        // Carries the result through one redirect without resubmitting the form.
        redirectAttributes.addFlashAttribute("createdPart", createdPart);
        return "redirect:/parts/created";
    }

    /**
     * Displays the result of a successful part submission.
     *
     * @param model the data supplied to the view
     * @return the confirmation template or a redirect to a new form
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