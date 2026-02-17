package com.fabiansoft.controller;

import com.fabiansoft.model.Types;
import com.fabiansoft.service.ITypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/typess")
public class TypesController {

    @Autowired
    private ITypesService service;

    @GetMapping("/index")
    public String mostrarIndex(Model model) {
        model.addAttribute("typess", service.buscarTodas());
        return "typess/listTypess";
    }
}
