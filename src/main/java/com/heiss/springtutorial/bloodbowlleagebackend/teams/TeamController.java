package com.heiss.springtutorial.bloodbowlleagebackend.teams;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TeamController {

    @GetMapping("/")
    public String home() {
        return "homeView";
    }
}
