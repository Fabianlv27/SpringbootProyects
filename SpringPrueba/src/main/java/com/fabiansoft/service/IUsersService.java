package com.fabiansoft.service;

import com.fabiansoft.model.Users;
import java.util.List;

public interface IUsersService {
    List<Users> buscarTodas();
    void guardar(Users obj);
    void eliminar(Integer id);
}
