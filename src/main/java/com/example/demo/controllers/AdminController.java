package com.example.demo.controllers;

import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.services.RoleService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
private final UserService us;
private final RoleService rs;
    @Autowired
    public AdminController(UserService us, RoleService rs) {
        this.us = us;
        this.rs = rs;
    }

        @GetMapping()
        public String index(Model model){
            model.addAttribute("users", us.findAll());
            model.addAttribute("user", us.getAuthUser());
            return "admin";
        }
    @GetMapping(value = "/new")
    public String newUser(ModelMap model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String newUser(@ModelAttribute User user,
                          @ModelAttribute("roles") String role) {
        Set<Role> r= new HashSet<>();
        r.add(rs.getRoleByName(role));
        user.setRoles(r);
        us.save(user);
        return "redirect:/admin";
    }
    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id){
        model.addAttribute("user", us.getById(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @ModelAttribute("role") String role){
        us.save(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        us.deleteById(id);
        return "redirect:/admin";}

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            request.getSession().invalidate();
        }
        return "redirect:/login";
    }
}
