package com.fabiansoft.repository;

import com.fabiansoft.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    // Metodos de consulta personalizados
}
