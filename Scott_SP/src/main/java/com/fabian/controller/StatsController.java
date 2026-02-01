package com.fabian.controller;

import com.fabian.dto.EstadisticaDTO;
import com.fabian.repository.DeptRepository;
import com.fabian.repository.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/estadisticas")
public class StatsController {

    @Autowired
    private EmpRepository  empRepo;
    @Autowired
    private DeptRepository deptRepo;

    @GetMapping("/index")
    public String mostrarDashboard(Model model){
        model.addAttribute("departamentos",deptRepo.findAll());
        return "estadisticas/dashboard";
    }

    @GetMapping("/api/salarios")
    @ResponseBody
    public List<EstadisticaDTO> getSalarios(
                @RequestParam(defaultValue = "10")int limite,
                @RequestParam(required = false) Integer deptId){
        return empRepo.obtenerSalarios(deptId, PageRequest.of(0,limite));

    }

    @GetMapping("/api/composicion")
    @ResponseBody
    public List<EstadisticaDTO> getComposicion(){
        return empRepo.obtenerEmpleadosPorDepartamento();
    }

    @GetMapping("/api/historico")
    @ResponseBody
    public List<EstadisticaDTO> getHistorico(){
        return empRepo.obtenerContratacionesPorMes();
    }
}
