package com.fabiansoft.controller;

import com.fabiansoft.model.Lists;
import com.fabiansoft.service.IListsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/listss")
public class ListsController {

    @Autowired
    private IListsService service;

    @GetMapping("/index")
    public String mostrarIndex(Model model) {
        model.addAttribute("listss", service.buscarTodas());
        return "listss/listListss";
    }
}
