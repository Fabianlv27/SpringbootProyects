package com.fabiansoft.controller;

import com.fabiansoft.model.Users;
import com.fabiansoft.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/userss")
public class UsersController {

    @Autowired
    private IUsersService service;

    @GetMapping("/index")
    public String mostrarIndex(Model model) {
        model.addAttribute("userss", service.buscarTodas());
        return "userss/listUserss";
    }
}
