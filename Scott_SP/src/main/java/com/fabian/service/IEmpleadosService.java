package com.fabian.service;

import com.fabian.model.Empleado;

public interface IEmpleadosService {
	void guardar(Empleado categoria);
	void eliminar(Integer idCategoria);
	List<Categoria> buscarTodas();
	Categoria buscarPorId(Integer idCategoria);
	Page<Categoria> buscarTodas(Pageable page);
}
