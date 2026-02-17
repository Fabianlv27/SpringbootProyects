package com.fabiansoft.controller;

import com.fabiansoft.model.Words;
import com.fabiansoft.service.IWordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/wordss")
public class WordsController {

    @Autowired
    private IWordsService service;

    @GetMapping("/index")
    public String mostrarIndex(Model model) {
        model.addAttribute("wordss", service.buscarTodas());
        return "wordss/listWordss";
    }
}
