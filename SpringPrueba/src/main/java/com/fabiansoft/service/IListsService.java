package com.fabiansoft.service;

import com.fabiansoft.model.Lists;
import java.util.List;

public interface IListsService {
    List<Lists> buscarTodas();
    void guardar(Lists obj);
    void eliminar(Integer id);
}
