package com.fabiansoft.service.db;

import com.fabiansoft.model.Users;
import com.fabiansoft.repository.UsersRepository;
import com.fabiansoft.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsersServiceJpa implements IUsersService {

    @Autowired
    private UsersRepository repo;

    @Override
    public List<Users> buscarTodas() {
        return repo.findAll();
    }

    @Override
    public void guardar(Users obj) {
        repo.save(obj);
    }

    @Override
    public void eliminar(Integer id) {
        repo.deleteById(id);
    }
}
