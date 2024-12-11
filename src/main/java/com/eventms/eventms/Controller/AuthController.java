package com.eventms.eventms.Controller;

import com.eventms.eventms.model.User;
import com.eventms.eventms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/process-login")
    public String processLogin(
        @RequestParam("username") String email,
        @RequestParam("password") String password,
        Model model,
        HttpSession session
    ) {
        Optional<User> optionalUser = userService.findByEmail(email);

        if (optionalUser.isPresent() && optionalUser.get().getPassword().equals(password)) {
            // Store the user object in session
            session.setAttribute("loggedInUser", optionalUser.get());
            return "redirect:/dashboard"; // Redirect to dashboard
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "login"; // Stay on the login page with an error message
        }
    }

    @GetMapping("/dashboard")
    public String showDashboard(HttpSession session, Model model) {
        // Check if a user is logged in
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login"; // Redirect to login if no user is logged in
        }

        // Add user details to the model for display in the dashboard
        model.addAttribute("user", loggedInUser);
        return "dashboard"; // Load dashboard view
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // Invalidate the session to log the user out
        session.invalidate();
        return "redirect:/login"; // Redirect to login page
    }
}
