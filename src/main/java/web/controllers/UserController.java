package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.UserDetailsServiceAdded;
import web.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
//@RequestMapping("/")
public class UserController {

	private UserDetailsServiceAdded userDetailsServiceAdded;

	@Autowired
	public UserController(UserDetailsServiceAdded userDetailsServiceAdded) {
		this.userDetailsServiceAdded = userDetailsServiceAdded;
	}

	@GetMapping(value = "hello")
	public String printWelcome(ModelMap model, Principal principal) {
		List<String> messages = new ArrayList<>();
		messages.add("Hello!");
		messages.add("I'm your 1st Spring MVC-SECURITY application");
		messages.add("5.2.8 version by aug'20 ");
		messages.add("---------------------------------------------");
		messages.add("You are logged as: " + principal.getName());
		messages.add("---------------------------------------------");
		messages.add("The same thing - " + SecurityContextHolder.getContext().getAuthentication().getName());
		model.addAttribute("messages", messages);
		return "/hello";
	}

    @GetMapping(value = "/login")
    public String loginPage() {
        return "login";
    }

	@GetMapping(value = "/registration")
	public String newUser(ModelMap model) {
		User user = new User();//сократить
		model.addAttribute("header", "User registration");
		model.addAttribute("user", user);
		return "registration";
	}

	@PostMapping(value = "/save")
	public String saveNewUser(@ModelAttribute @Valid User user, BindingResult result) {
		if (result.hasErrors()) {
			return "registration";
		}
		userDetailsServiceAdded.addUser(user);
		return "redirect:/login";
	}
}