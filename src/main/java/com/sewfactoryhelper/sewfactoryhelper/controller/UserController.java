package com.sewfactoryhelper.sewfactoryhelper.controller;



import com.sewfactoryhelper.sewfactoryhelper.Service.RoleService;
import com.sewfactoryhelper.sewfactoryhelper.Service.UserService;
import com.sewfactoryhelper.sewfactoryhelper.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
public class UserController {

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage(@RequestParam(value = "error", required = false) String error,
                                  @RequestParam(value = "logout",	required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Почта или пароль неверны");
            model.setViewName("/login");
        }
        if (logout != null) {
            model.addObject("logout", "Logged out successfully.");
            model.setViewName("/login");
        }
        return model;
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "hello";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView("registration");
        modelAndView.addObject("user", new UserEntity());
        return modelAndView;
    }


    @PostMapping(value = "/registration")
    public String registration(@RequestBody UserEntity user) {
        user.setRoles(this.roleService.getRoleByName("USER"));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        this.userService.save(user);
        return "Зарегистрировать";
    }
}

