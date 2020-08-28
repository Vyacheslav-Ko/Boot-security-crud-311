package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserDetailsServiceAdded;
import web.service.UserService;

import javax.validation.Valid;

@Controller
public class AdminController {

    private UserDetailsServiceAdded userDetailsServiceAdded;

    @Autowired
    public AdminController(UserDetailsServiceAdded userDetailsServiceAdded) {
        this.userDetailsServiceAdded = userDetailsServiceAdded;
    }

    @GetMapping(value = "/admin")
    public String usersManager(ModelMap model) {
        model.addAttribute("tableHeader", "All users page");
        model.addAttribute("allUsersList", this.userDetailsServiceAdded.getAllUsers());
        return "index";
    }

    @GetMapping(value = "/admin/edit/{id}") // add - /
    public ModelAndView editUser(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        User user = userDetailsServiceAdded.findById(id);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping(value = "/admin/merge")
    public String updateUser(@ModelAttribute @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "edit";
        }
        userDetailsServiceAdded.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/delete/{id}") // add - /
    public String deleteUser(@PathVariable Long id) {
        userDetailsServiceAdded.removeUserById(id);
        return "redirect:/admin";
    }
}
