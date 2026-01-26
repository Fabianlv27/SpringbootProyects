package com.rayosoft.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import com.rayosoft.model.Solicitude;

public interface ISolicitudesService {
	void guardar(Solicitude solicitud);
	void eliminar(Integer idSolicitud);
	List<Solicitude> buscarTodas();
	Solicitude buscarPorId(Integer idSolicitud);
	Page<Solicitude> buscarTodas(Pageable page);
}
