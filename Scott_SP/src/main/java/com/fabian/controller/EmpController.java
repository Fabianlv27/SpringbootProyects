package com.fabian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fabian.model.Emp;
import com.fabian.service.IEmpService;
import com.fabian.repository.DeptRepository; // O su Service correspondiente

@Controller
@RequestMapping("/empleados")
public class EmpController {

    @Autowired
    private IEmpService empService;

    @Autowired
    private DeptRepository deptRepo; // Para llenar el select de departamentos

    @GetMapping("/index")
    public String mostrarIndex(Model model, Pageable page) {
        Page<Emp> lista = empService.buscarTodos(page);
        model.addAttribute("empleados", lista);
        return "empleados/listEmpleados";
    }

    @GetMapping("/create")
    public String crear(Emp emp, Model model) {
        // Pasamos la lista de departamentos para el formulario
        model.addAttribute("departamentos", deptRepo.findAll());
        return "empleados/formEmpleado";
    }

    @PostMapping("/save")
    public String guardar(Emp emp, BindingResult result,
                          @RequestParam(value = "origin",required = false) String origin,
                          RedirectAttributes attributes, Model model) {

        if (result.hasErrors()) {
            // Si hay error, recargamos la lista de deptos y volvemos al form
            model.addAttribute("departamentos", deptRepo.findAll());
            model.addAttribute("origin",origin);
            return "empleados/formEmpleado";
        }

        // Guardamos el empleado
        empService.guardar(emp);
        attributes.addFlashAttribute("msg", "Empleado guardado con éxito!");
        if (origin !=null && !origin.isEmpty()){
            if (emp.getDept() !=null){
                return "redirect:/departamentos/empleados/"+emp.getDept().getDeptno();
            }

        }
        return "redirect:/empleados/index";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int empno, RedirectAttributes attributes) {
        empService.eliminar(empno);
        attributes.addFlashAttribute("msg", "Empleado eliminado.");
        return "redirect:/empleados/index";
    }

    @GetMapping("/edit/{id}")
    public String editar(@PathVariable("id") Integer id,
                         @RequestParam(value = "origin",required = false) String origin,
                         Model model) {

        Emp emp = empService.buscarPorId(id);
        model.addAttribute("emp", emp);
        model.addAttribute("dname",origin);
        model.addAttribute("departamentos", deptRepo.findAll());
        return "empleados/formEmpleado";
    }
}