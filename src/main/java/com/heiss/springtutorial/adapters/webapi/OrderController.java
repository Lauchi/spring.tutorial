package com.heiss.springtutorial.adapters.webapi;

import com.heiss.springtutorial.adapters.peristence.memory.IngredientRepository;
import com.heiss.springtutorial.adapters.peristence.memory.MemoryRepository;
import com.heiss.springtutorial.domain.Ingredient;
import com.heiss.springtutorial.domain.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/order")
public class OrderController {

    @GetMapping("current")
    public String showDesignForm(Model model) {
        Taco currentOrder = MemoryRepository.CurrentOrder;
        model.addAttribute("tacoName", currentOrder.getTacoName());
        List<Ingredient> ingredientList = new ArrayList<>();
        for (String ingredientId : currentOrder.getTacoIngredients()) {
            Ingredient ingredient = IngredientRepository.byId(ingredientId);
            ingredientList.add(ingredient);
        }

        model.addAttribute("ingredients", ingredientList);

        return "order/current";
    }

    @PostMapping
    public String processDesign(TacoOrder order) {
        log.info("Processing order: " + order);
        return "redirect:/design";
    }
}
