package com.rayosoft.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rayosoft.model.Categoria;
import com.rayosoft.repository.CategoriasRepository;
import com.rayosoft.service.ICategoriasService;

@Service
@Primary //Porque hay mas de un servicio con el mismo implemento
public class CategoriasServiceJpa implements ICategoriasService {
	
	@Autowired
	private CategoriasRepository categoriasRepo;

	@Override
	public void guardar(Categoria categoria) {
		categoriasRepo.save(categoria);
	}

	@Override
	public void eliminar(Integer idCategoria) {
		// TODO Auto-generated method stub
		categoriasRepo.deleteById(idCategoria);
	}

	@Override
	public List<Categoria> buscarTodas() {
		// TODO Auto-generated method stub
		return categoriasRepo.findAll();
	}

	@Override
	public Categoria buscarPorId(Integer idCategoria) {
		// TODO Auto-generated method stub
		Optional<Categoria> optional=categoriasRepo.findById(idCategoria);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public Page<Categoria> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return categoriasRepo.findAll(page);
	}

}
