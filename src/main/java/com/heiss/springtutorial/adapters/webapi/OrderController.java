package com.heiss.springtutorial.adapters.webapi;

import com.heiss.springtutorial.application.IngredientRepository;
import com.heiss.springtutorial.application.OrderRepository;
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
    private IngredientRepository ingredientRepository;
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("current")
    public String shorCurrentDesign(Model model) {
        Taco currentOrder = orderRepository.findCurrentOrder();
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
        Iterable<TacoOrder> all = orderRepository.getAll();
        model.addAttribute("orders", all);

        return "order";
    }

    @PostMapping
    public String processOrder(TacoOrder order) {
        orderRepository.save(order);
        return "redirect:/order";
    }
}
