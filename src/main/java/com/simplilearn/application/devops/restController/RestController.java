package com.simplilearn.application.devops.restController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RestController {
    @GetMapping("/homer")
    public String homer() {
        return "homer.html";
    }

}
