package com.fabian.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
// CORRECCIÓN 1: Importar el Pageable correcto
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.fabian.repository.DeptRepository;
import com.fabian.model.Dept;
import com.fabian.service.IDeptsService;

@Service
public class DepasServiceJpa implements IDeptsService {

	@Autowired
	private DeptRepository depasRepo;

	@Override
	public void guardar(Dept dept) {
		depasRepo.save(dept);
	}

	@Override
	public void eliminar(Integer idDept) {
		depasRepo.deleteById(idDept);
	}

	@Override
	public List<Dept> buscarTodas() {
		return depasRepo.findAll();
	}

	@Override
	public Dept buscarPorId(Integer idDept) {
		Optional<Dept> optional = depasRepo.findById(idDept);
		return optional.orElse(null);
	}

	@Override
	public List<Dept> buscarDestacadas() {
		return null;
	}

	// CORRECCIÓN 2: Ahora "page" es del tipo correcto y el repositorio lo aceptará
	@Override
	public Page<Dept> buscarTodas(Pageable page) {
		return depasRepo.findAll(page);
	}

	@Override
	public List<Dept> buscarByExample(Example<Dept> example) {
		return depasRepo.findAll(example); // Sugerencia: Implementa esto también si el repo lo soporta
	}
}