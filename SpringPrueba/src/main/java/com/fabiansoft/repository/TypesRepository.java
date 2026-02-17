package com.fabiansoft.repository;

import com.fabiansoft.model.Types;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypesRepository extends JpaRepository<Types, Integer> {
    // Metodos de consulta personalizados
}
