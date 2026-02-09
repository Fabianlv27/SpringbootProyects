package com.fabian.repository;
import java.util.List;

import com.fabian.dto.EstadisticaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.fabian.model.Emp;

public interface EmpRepository extends JpaRepository<Emp, Integer> {

    List<Emp> findByJob(String job);

    List<Emp> findBySalGreaterThan(Float salario);

    Page<Emp> findByDept_Deptno(Integer deptno, Pageable page);

    @Modifying
    @Transactional
    @Query("UPDATE Emp e SET e.sal = e.sal * :porcentaje WHERE e.empno = :idEmpleado")
    void aumentarSalario(@Param("idEmpleado") Integer idEmpleado, @Param("porcentaje") Double porcentaje);

    @Query("SELECT e.ename as etiqueta, e.sal as valor FROM Emp e " +
            "WHERE (:idDept IS NULL OR e.dept.deptno = :idDept) " +
            "ORDER BY e.sal DESC")
    List<EstadisticaDTO> obtenerSalarios(@Param("idDept") Integer idDept, Pageable pageable);

    @Query("SELECT d.dname as etiqueta, COUNT(e) as valor FROM Emp e JOIN e.dept d " +
            "GROUP BY d.dname")
    List<EstadisticaDTO> obtenerEmpleadosPorDepartamento();

    @Query(value = "SELECT DATE_FORMAT(hiredate, '%Y-%m') as etiqueta, COUNT(*) as valor FROM emp " +
            "GROUP BY DATE_FORMAT(hiredate, '%Y-%m') " +
            "ORDER BY etiqueta ASC", nativeQuery = true)
        List<EstadisticaDTO> obtenerContratacionesPorMes();
}