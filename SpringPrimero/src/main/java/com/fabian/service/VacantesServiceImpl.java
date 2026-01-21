package com.fabian.service;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fabian.demo.model.vacante;

@Service
public class VacantesServiceImpl implements IVacantesService {


	
	private List<vacante> lista=null;
	
	public VacantesServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		List<vacante> lista=new LinkedList<vacante>();
		try {
			vacante vacante2 = new vacante();
			vacante2.setId(2);
			vacante2.setNombre("Desarrollador Java");
			vacante2.setDescripcion("Desarrollo y mantenimiento de aplicaciones Java");
			vacante2.setSalario(15000.0);
			vacante2.setFecha(sdf.parse("10-02-2025"));
			vacante2.setDestacado(0);
			vacante2.setImagen("empresa1.png");
			
			vacante vacante3 = new vacante();
			vacante3.setId(3);
			vacante3.setNombre("Analista de Sistemas");
			vacante3.setDescripcion("Análisis de requerimientos y documentación técnica");
			vacante3.setSalario(14000.0);
			vacante3.setFecha(sdf.parse("12-02-2025"));
			vacante3.setDestacado(1);
			vacante3.setImagen("empresa3.png");
			
			vacante vacante1 = new vacante();
			vacante1.setId(4);
			vacante1.setNombre("Diseñador Web");
			vacante1.setDescripcion("Diseño de interfaces web responsivas");
			vacante1.setSalario(11000.0);
			vacante1.setFecha(sdf.parse("15-02-2025"));
			vacante1.setDestacado(0);
			vacante1.setImagen("empresa1.png");
			
			lista.add(vacante1);
			lista.add(vacante2);
			lista.add(vacante3);
			
		}catch(Exception e) {
			System.out.println(e);
		}
		this.lista=lista;

	}

	@Override
	public List<vacante> buscarTodas() {
		return lista;
	}

	@Override
	public vacante buscarPorId(Integer idVacante) {
		// TODO Auto-generated method stub
		for (vacante v : lista)if(v.getId()==idVacante) return v;
		return null;
	}

	@Override
	public List<vacante> buscarPorAño(Integer año) {
		// TODO Auto-generated method stub
		List<vacante>ListaFiltrada=new LinkedList<>();
		for(vacante v:lista)System.out.println(v.getFecha().getYear());
		for(vacante v:lista)if(v.getFecha().getYear()+1900==año) ListaFiltrada.add(v);
		return ListaFiltrada;
	}

	@Override
	public boolean borrarId(Integer idVacante) {
		// TODO Auto-generated method stub
		for (vacante v : lista) {
			if(v.getId()==idVacante) {
				lista.remove(v);
				return true;
			}

		}
		return false;
	}
	
	

}
