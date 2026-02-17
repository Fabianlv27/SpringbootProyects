package com.fabiansoft.service.db;

import com.fabiansoft.model.Words;
import com.fabiansoft.repository.WordsRepository;
import com.fabiansoft.service.IWordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WordsServiceJpa implements IWordsService {

    @Autowired
    private WordsRepository repo;

    @Override
    public List<Words> buscarTodas() {
        return repo.findAll();
    }

    @Override
    public void guardar(Words obj) {
        repo.save(obj);
    }

    @Override
    public void eliminar(Integer id) {
        repo.deleteById(id);
    }
}
