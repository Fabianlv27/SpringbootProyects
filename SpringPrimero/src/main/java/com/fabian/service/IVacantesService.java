package com.fabian.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fabian.demo.model.vacante;


public interface IVacantesService {
List<vacante> buscarTodas();
vacante buscarPorId(Integer idVacante);
List<vacante> buscarPorAño(Integer año);
boolean borrarId(Integer idVacante);
//vacantesAño -> pasar año, crear imp.devolver lista de vacantes en el año dado
}
