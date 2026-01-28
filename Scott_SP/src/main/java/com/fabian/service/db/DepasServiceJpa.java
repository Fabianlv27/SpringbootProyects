package com.fabian.service.db;

import java.util.List;

import org.springframework.boot.data.autoconfigure.web.DataWebProperties.Pageable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;

import com.fabian.model.Dept;
import com.fabian.service.IDepasService;

public class DepasServiceJpa implements IDepasService {

	@Override
	public void guardar(Dept dept) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminar(Integer idDept) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Dept> buscarTodas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dept buscarPorId(Integer idDept) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dept> buscarDestacadas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Dept> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dept> buscarByExample(Example<Dept> example) {
		// TODO Auto-generated method stub
		return null;
	}

}
