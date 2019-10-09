package com.heiss.springtutorial.adapters.webapi;

import com.heiss.springtutorial.application.IngredientRepository;
import com.heiss.springtutorial.application.TacoRepository;
import com.heiss.springtutorial.domain.Ingredient;
import com.heiss.springtutorial.domain.Ingredient.IngredientType;
import com.heiss.springtutorial.domain.Taco;
import com.heiss.springtutorial.domain.TacoOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignController {

    @ModelAttribute(name = "order")
    public TacoOrder order() {
        return new TacoOrder();
    }
    @ModelAttribute(name = "tacoDesign")
    public Taco taco() {
        return new Taco();
    }

    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private TacoRepository tacoRepository;

    @GetMapping
    public String showDesignForm(Model model) {
        IngredientType[] ingredientTypes = IngredientType.values();

        for (IngredientType ingredientType : ingredientTypes) {
            Iterable<Ingredient> ingredientsByType = ingredientRepository.byType(ingredientType);
            model.addAttribute(ingredientType.toString().toLowerCase(), ingredientsByType);
        }
        model.addAttribute("tacoDesign", new Taco());
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Taco taco, Errors errors) {
        if (errors.hasErrors()) {
            return "redirect:/design";
        }
        tacoRepository.save(taco);
        return "redirect:/order/current";
    }
}
