package com.rayosoft.service.db;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rayosoft.model.Solicitude;
import com.rayosoft.repository.SolicitudesRepository;
import com.rayosoft.service.ISolicitudesService;

@Service
public class SolicitudesServiceJpa implements ISolicitudesService {

	@Autowired
	private SolicitudesRepository solicitudesRepo;
	
	@Override
	public void guardar(Solicitude solicitud) {
		solicitudesRepo.save(solicitud);
	}

	@Override
	public void eliminar(Integer idSolicitud) {
		solicitudesRepo.deleteById(idSolicitud);
	}

	@Override
	public List<Solicitude> buscarTodas() {
		return solicitudesRepo.findAll();
	}

	@Override
	public Solicitude buscarPorId(Integer idSolicitud) {
		Optional<Solicitude> optional = solicitudesRepo.findById(idSolicitud);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}


	@Override
	public Page<Solicitude> buscarTodas(Pageable page){
		
		// TODO Auto-generated method stub
		return 	solicitudesRepo.findAll(page);
	}

}
