package com.fabian.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fabian.demo.model.vacante;
import com.fabian.service.IVacantesService;


@Controller
public class HomeController {
	
	//Cuando se crea HomeController se crea en memoria el implemento
	@Autowired
	private IVacantesService serviceVacantes;
	
	@GetMapping("/")//Ruta raiz 
	public String mostrarHome(Model model) {
		//A esto es lo que el cliente accede cuando accede a la ruta
		
		//Solo definimos variables para pasar al cliente
		
		String nombre="Programador";
		Date fecha=new Date();
		double salario=9000;
		boolean vigente=true;
		
		//Y aqui los pasamos mediante el metodo addAttribute:
		model.addAttribute("nombre",nombre);
		model.addAttribute("fecha",fecha);
		model.addAttribute("salario",salario);
		model.addAttribute("vigente",vigente);
		
		return "home";
	}
	
	@GetMapping("/buscar/{id}")
	public String BuscarporId(@PathVariable("id") int n,Model model) {
		
		model.addAttribute("vacante",serviceVacantes.buscarPorId(n));
		return "buscar";
		
	}
	
	@GetMapping("/buscarporaño/{year}")
	public String BuscarporFecha(@PathVariable("year") int n,Model model) {
		
		model.addAttribute("año",n);
		model.addAttribute("vacantes",serviceVacantes.buscarPorAño(n));
		return "buscaraño";
		
	}
	
	@GetMapping("/tabla")
	public String mostrarTabla(Model model) {
		model.addAttribute("vacantes",serviceVacantes.buscarTodas());
		return "tabla";
	}
	
	@GetMapping("/listado")
	public String mostrarListado(Model model) {
		List<String> lista = new LinkedList<String>();
		lista.add("Ingeniero de Sistemas");
		lista.add("Auxiliar de Contablilidad");
		lista.add("Vendedor");
		lista.add("Arquitecto");
	
		model.addAttribute("empleos",lista);
		return "listado";
	}
	
}
