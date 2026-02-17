package com.fabiansoft.service.db;

import com.fabiansoft.model.Types;
import com.fabiansoft.repository.TypesRepository;
import com.fabiansoft.service.ITypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TypesServiceJpa implements ITypesService {

    @Autowired
    private TypesRepository repo;

    @Override
    public List<Types> buscarTodas() {
        return repo.findAll();
    }

    @Override
    public void guardar(Types obj) {
        repo.save(obj);
    }

    @Override
    public void eliminar(Integer id) {
        repo.deleteById(id);
    }
}
