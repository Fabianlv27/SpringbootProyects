package com.fabian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fabian.service.IVacantesService;

@Controller
@RequestMapping("/vacantes")
public class VacantesController {

	@Autowired
	private IVacantesService serviceVacantes;

	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id")int idVacante,Model model) {
		String mensaje="No se pudo Borrar vacante con id: "+ idVacante;
		if(serviceVacantes.borrarId(idVacante))mensaje="Borrando vacante con id: "+ idVacante;
		System.out.println(mensaje);
		model.addAttribute("mensaje",mensaje);
		return "vacante/mensaje";
	}
}
