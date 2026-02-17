package com.fabiansoft.service;

import com.fabiansoft.model.Types;
import java.util.List;

public interface ITypesService {
    List<Types> buscarTodas();
    void guardar(Types obj);
    void eliminar(Integer id);
}
