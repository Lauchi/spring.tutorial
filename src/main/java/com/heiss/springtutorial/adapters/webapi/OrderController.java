package com.heiss.springtutorial.adapters.webapi;

import com.heiss.springtutorial.adapters.peristence.memory.IngredientRepository;
import com.heiss.springtutorial.adapters.peristence.memory.MemoryRepository;
import com.heiss.springtutorial.application.IIngredientRepository;
import com.heiss.springtutorial.domain.Ingredient;
import com.heiss.springtutorial.domain.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private IIngredientRepository ingredientRepository;

    @GetMapping("current")
    public String shorCurrentDesign(Model model) {
        Taco currentOrder = MemoryRepository.CurrentOrder;
        model.addAttribute("tacoName", currentOrder.getTacoName());
        List<Ingredient> ingredientList = new ArrayList<>();
        for (String ingredientId : currentOrder.getTacoIngredients()) {
            Ingredient ingredient = ingredientRepository.findOne(ingredientId);
            ingredientList.add(ingredient);
        }

        model.addAttribute("ingredients", ingredientList);
        model.addAttribute("order", new TacoOrder());

        return "order/orderForm";
    }

    @GetMapping()
    public String showOrders(Model model) {
        List<TacoOrder> all = OrderRepository.getAll();
        model.addAttribute("orders", all);

        return "order";
    }

    @PostMapping
    public String processOrder(TacoOrder order) {
        OrderRepository.add(order);
        return "redirect:/order";
    }
}
