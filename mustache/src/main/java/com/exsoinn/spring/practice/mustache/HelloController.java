package com.exsoinn.spring.practice.mustache;

import com.exsoinn.spring.practice.mustache.model.Greeting;
import com.exsoinn.spring.practice.mustache.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller for handling greeting-related HTTP requests.
 * This is the "C" in MVC pattern - it delegates business logic to the service layer.
 */
@Controller
public class HelloController {

    private final GreetingService greetingService;

    @Autowired
    public HelloController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    /**
     * Displays the default greeting page.
     *
     * @param model Spring MVC model for passing data to the view
     * @return the view name
     */
    @GetMapping("/")
    public String hello(Model model) {
        Greeting greeting = greetingService.getDefaultGreeting();
        model.addAttribute("message", greeting.getFullGreeting());
        return "hello";
    }

    /**
     * Displays a personalized greeting page.
     *
     * @param name the name to greet (optional)
     * @param model Spring MVC model for passing data to the view
     * @return the view name
     */
    @GetMapping("/greet")
    public String greet(@RequestParam(required = false) String name, Model model) {
        Greeting greeting = greetingService.getPersonalizedGreeting(name);
        model.addAttribute("message", greeting.getFullGreeting());
        return "hello";
    }

    /**
     * Displays a custom greeting page with custom message and recipient.
     *
     * @param message custom greeting message (optional)
     * @param name the name to greet (optional)
     * @param model Spring MVC model for passing data to the view
     * @return the view name
     */
    @GetMapping("/custom")
    public String customGreeting(
            @RequestParam(required = false) String message, @RequestParam(required = false) String name, Model model) {
        Greeting greeting = greetingService.getCustomGreeting(message, name);
        model.addAttribute("message", greeting.getFullGreeting());
        return "hello";
    }
}
