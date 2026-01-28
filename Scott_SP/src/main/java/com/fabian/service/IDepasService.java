package com.fabian.service;

import java.util.List;

import org.springframework.boot.data.autoconfigure.web.DataWebProperties.Pageable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;

import com.fabian.model.Dept;

public interface IDepasService {
	void guardar(Dept dept);
	void eliminar(Integer idDept);
	List<Dept> buscarTodas();
	Dept buscarPorId(Integer idDept);
	List<Dept> buscarDestacadas();
	Page<Dept> buscarTodas(Pageable page);
	List<Dept> buscarByExample(Example<Dept> example);
}
