package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;

@Controller
//@RequestMapping("")
public class AdminController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/admin")
    public String usersManager(ModelMap model) {
        model.addAttribute("tableHeader", "All users page");
        model.addAttribute("allUsersList", this.userService.getAllUsers());
        return "index";
    }

    @GetMapping(value = "/admin/edit/{id}") // add - /
    public ModelAndView editUser(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        User user = userService.getUserById(id);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping(value = "/admin/merge")
    public String updateUser(@ModelAttribute @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "edit";
        }
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/delete/{id}") // add - /
    public String deleteUser(@PathVariable Long id) {
        userService.removeUserById(id);
        return "redirect:/admin";
    }
}
