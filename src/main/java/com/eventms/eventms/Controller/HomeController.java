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

    // This method maps to the "/login" URL and returns the login page.
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // Corresponds to 'login.html'
    }

    // This method processes the login form submission.
    @PostMapping("/process-login")
    public String processLogin(
        @RequestParam("username") String username,
        @RequestParam("password") String password,
        Model model
    ) {
        // Simulate login validation (replace with real authentication logic)
        if ("admin".equals(username) && "password".equals(password)) {
            model.addAttribute("message", "Login successful!");
            return "welcome"; // Redirect to a welcome page (you'll create this next)
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login"; // Stay on the login page with an error message
        }
    }
}
