package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserDetailsServiceAdded;
import web.service.UserService;

import javax.validation.Valid;

@Controller
@Transactional
public class AdminController {

    private UserDetailsServiceAdded userDetailsServiceAdded;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserDetailsServiceAdded(UserDetailsServiceAdded userDetailsServiceAdded) {
        this.userDetailsServiceAdded = userDetailsServiceAdded;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(value = "/admin")
    public String usersManager(ModelMap model) {
        model.addAttribute("tableHeader", "Admin panel");
        model.addAttribute("allusers", "All users");
        model.addAttribute("allUsersList", userDetailsServiceAdded.getAllUsers());
        model.addAttribute("email", userDetailsServiceAdded.findById(1L).getEmail() + " with roles: ADMIN USER");
        return "index";
    }

    @GetMapping(value = "/admin/edit/{id}")
    public ModelAndView editUser(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("user", userDetailsServiceAdded.findById(id));
        modelAndView.toString();
        return modelAndView;
    }

    @PostMapping(value = "/admin/edit")
    public String updateUser(@ModelAttribute @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "edit";
        }
        userDetailsServiceAdded.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userDetailsServiceAdded.removeUserById(id);
        return "redirect:/admin";
    }
}
