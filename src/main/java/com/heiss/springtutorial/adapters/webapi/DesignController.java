package com.heiss.springtutorial.adapters.webapi;

import com.heiss.springtutorial.adapters.peristence.memory.IngredientRepository;
import com.heiss.springtutorial.adapters.peristence.memory.MemoryRepository;
import com.heiss.springtutorial.domain.Ingredient;
import com.heiss.springtutorial.domain.Ingredient.IngredientType;
import com.heiss.springtutorial.domain.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignController {

    @GetMapping
    public String showDesignForm(Model model) {
        IngredientType[] ingredientTypes = IngredientType.values();

        for (IngredientType ingredientType : ingredientTypes) {
            List<Ingredient> ingredientsByType = IngredientRepository.byType(ingredientType);
            model.addAttribute(ingredientType.toString().toLowerCase(), ingredientsByType);
        }
        model.addAttribute("tacoDesign", new Taco());
        return "design";
    }

    @PostMapping
    public String processDesign(Taco taco) {
        MemoryRepository.CurrentOrder = taco;
        return "redirect:/order/current";
    }
}
