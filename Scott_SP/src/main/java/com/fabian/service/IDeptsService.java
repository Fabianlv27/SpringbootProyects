package com.fabian.service;

import java.util.List;


import com.fabian.model.Emp;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;

import com.fabian.model.Dept;
import org.springframework.data.domain.Pageable;

public interface IDeptsService {
	void guardar(Dept dept);
	void eliminar(Integer idDept);
	List<Dept> buscarTodas();
	Dept buscarPorId(Integer idDept);
	Page<Emp> buscarEmpleados(Integer idDept,Pageable page);
	Page<Dept> buscarTodas(Pageable page);
	List<Dept> buscarByExample(Example<Dept> example);
}
