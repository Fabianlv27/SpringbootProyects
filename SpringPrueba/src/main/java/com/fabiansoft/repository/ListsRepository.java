package com.fabiansoft.repository;

import com.fabiansoft.model.Lists;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListsRepository extends JpaRepository<Lists, Integer> {
    // Metodos de consulta personalizados
}
