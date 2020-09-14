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
import web.repositories.RoleRepository;
import web.service.UserDetailsServiceAdded;
import web.service.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@Transactional
public class AdminController {

    private UserDetailsServiceAdded userDetailsServiceAdded;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setUserDetailsServiceAdded(UserDetailsServiceAdded userDetailsServiceAdded) {
        this.userDetailsServiceAdded = userDetailsServiceAdded;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(value = "/admin")
    public String usersManager(ModelMap model, Principal principal) {
        model.addAttribute("tableHeader", "Admin panel");
        model.addAttribute("allusersheader", "All users");
        model.addAttribute("registrationheader", "Add new user");
        model.addAttribute("user", new User());
        model.addAttribute("allUsersList", userDetailsServiceAdded.getAllUsers());
        model.addAttribute("email", principal.getName() + " with roles: ADMIN USER");
        return "index";
    }

/*    @GetMapping(value = "/admin/edit/{id}")
    public ModelAndView editUser(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("user", userDetailsServiceAdded.findById(id));
        modelAndView.toString();
        return modelAndView;
    }*/

    @GetMapping(value = "/admin/getOne/{id}")
    //@ResponseBody
    //@RequestMapping("/admin/getOne")
    public User getOne(@PathVariable Long id) {
        User user = userDetailsServiceAdded.findById(id);
        System.out.println(user.toString());
        return user;
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
