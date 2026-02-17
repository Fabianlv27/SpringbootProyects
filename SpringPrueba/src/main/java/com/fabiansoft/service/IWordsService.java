package com.fabiansoft.service;

import com.fabiansoft.model.Words;
import java.util.List;

public interface IWordsService {
    List<Words> buscarTodas();
    void guardar(Words obj);
    void eliminar(Integer id);
}
