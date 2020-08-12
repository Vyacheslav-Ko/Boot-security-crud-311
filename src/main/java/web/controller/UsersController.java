package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;

@Controller
public class UsersController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String usersManager(ModelMap model) {
        model.addAttribute("tableHeader", "Users manager page");
        model.addAttribute("allUsersList", this.userService.getAllUsers());
        return "index";
    }

    @GetMapping(value = "/new")
    public String newUser(ModelMap model) {
        User user = new User();
        model.addAttribute("header", "New user adding!");
        model.addAttribute("user", user);
        return "new";
    }

    @PostMapping(value = "/save")
        public String saveNewUser(@ModelAttribute User user) {
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping(value = "edit/{id}")
    public ModelAndView editUser(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        User user = userService.getUserById(id);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping(value = "/merge")
    public String updateUser(@ModelAttribute User user) {
        userService.updateUser(user);
        return "redirect:/";
    }

    @GetMapping(value = "delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.removeUserById(id);
        return "redirect:/";
    }
}
