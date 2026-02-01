package com.fabian.repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.fabian.model.Emp;

public interface EmpRepository extends JpaRepository<Emp, Integer> {

    // 1. Buscar empleados por su puesto de trabajo (JOB)
    List<Emp> findByJob(String job);

    // 2. Buscar empleados que ganen más de cierto salario
    List<Emp> findBySalGreaterThan(Float salario);

    // 3. Buscar todos los empleados de un departamento específico usando la relación
    // Spring Data interpreta el guion bajo "_" para navegar en la propiedad 'dept' hacia 'deptno'
    Page<Emp> findByDept_Deptno(Integer deptno, Pageable page);

    /**
     * Ejemplo de @Query personalizada (JPQL)
     * Aumentar el salario de un empleado en un porcentaje específico.
     * Requiere @Modifying y @Transactional porque es un UPDATE.
     */
    @Modifying
    @Transactional
    @Query("UPDATE Emp e SET e.sal = e.sal * :porcentaje WHERE e.empno = :idEmpleado")
    void aumentarSalario(@Param("idEmpleado") Integer idEmpleado, @Param("porcentaje") Double porcentaje);
}