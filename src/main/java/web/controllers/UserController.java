package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
@Transactional
public class UserController {

	private UserDetailsServiceAdded userDetailsServiceAdded;

	@Autowired
	public void setUserDetailsServiceAdded(UserDetailsServiceAdded userDetailsServiceAdded) {
		this.userDetailsServiceAdded = userDetailsServiceAdded;
	}

	@GetMapping(value = "hello")
	public String printWelcome(ModelMap model, Principal principal) {
		List<String> messages = new ArrayList<>();
		messages.add("Hello!");
		messages.add("I'm your 1st Spring MVC-SECURITY application");
		messages.add("5.2.8 version by aug'20 ");
		messages.add("---------------------------------------------");
		messages.add("Вы вошли под именем: " + principal.getName());
		messages.add("---------------------------------------------");
		messages.add("You're logged as: " + SecurityContextHolder.getContext().getAuthentication().getName());
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

	@GetMapping("/user")
	public String userPage(ModelMap model, Principal principal){
		List<String> messages = new ArrayList<>();
		messages.add("Hi!");
		messages.add("You're logged as: " + principal.getName() + " and you can do is nothing :)");
		messages.add("---------------------------------------------------------------------------------");
		model.addAttribute("messages", messages);
		return "user";
	}
}