package com.eventms.eventms.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    // This method maps to the root URL ("/") and returns the landing page.
    @GetMapping("/")
    public String showLandingPage() {
        // Returning the name of the HTML template (e.g., landing.html)
        return "landing";  // "landing" is the name of the view you will create
    }

}
