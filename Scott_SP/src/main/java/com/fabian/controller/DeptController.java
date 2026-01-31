package com.fabian.controller;

import com.fabian.model.Dept;
import com.fabian.service.IDeptsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/departamentos")
public class DeptController {
    //Conectamos la interfaz para utilizar los metodos de depts
    @Autowired
    private IDeptsService deptsService;

    @GetMapping("/index")
    public String mostrarIndex(Model model , Pageable page){
        Page<Dept> lista = deptsService.buscarTodas(page);
        model.addAttribute("departamentos",lista);
        return "departamentos/listDepartamento";
    }

    @GetMapping("/create")
    public String crear(Dept dept, Model model){
        return "departamentos/formDepartamento";
    }

    @PostMapping("save")
    public  String guardar(Dept dept, BindingResult result, RedirectAttributes attributes,Model model){
        if (result.hasErrors()){
          return "departamentos/formDepartamento";
        }
        deptsService.guardar(dept);
        attributes.addFlashAttribute("msg","Empleado guardado con éxito!");
        return  "redirect:/depts/index";
    }

    @GetMapping("/edit/{id}")
    public  String editar(@PathVariable("id") Integer id,Model model){
        Dept dept=deptsService.buscarPorId(id);
        model.addAttribute("dept",dept);
        return "departamentos/formDepartamento";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int deptId,RedirectAttributes attributes){
        deptsService.eliminar(deptId);
        attributes.addFlashAttribute("msg","Departamento eliminado");
        return  "redirect:/departamentos/index";
    }
}
