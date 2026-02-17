package com.fabiansoft.service.db;

import com.fabiansoft.model.Lists;
import com.fabiansoft.repository.ListsRepository;
import com.fabiansoft.service.IListsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ListsServiceJpa implements IListsService {

    @Autowired
    private ListsRepository repo;

    @Override
    public List<Lists> buscarTodas() {
        return repo.findAll();
    }

    @Override
    public void guardar(Lists obj) {
        repo.save(obj);
    }

    @Override
    public void eliminar(Integer id) {
        repo.deleteById(id);
    }
}
