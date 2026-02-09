package com.fabian.service.db;
import java.util.List;
import java.util.Optional;

import com.fabian.repository.DeptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.fabian.model.Emp;
import com.fabian.repository.EmpRepository;
import com.fabian.service.IEmpService;

@Service
public class EmpServiceJpa implements IEmpService {

    @Autowired
    private EmpRepository empRepo;

    @Autowired
    private DeptRepository deptRepo;

    public List<Emp> buscarGerentes() {
        return empRepo.findByJob("MANAGER");
    }

    @Override
    public List<Emp> buscarTodos() {
        return empRepo.findAll();
    }

    @Override
    public void guardar(Emp empleado) {
        empRepo.save(empleado);
    }

    @Override
    public void eliminar(Integer empno) {
        empRepo.deleteById(empno);
    }

    @Override
    public Emp buscarPorId(Integer empno) {
        Optional<Emp> optional = empRepo.findById(empno);
        return optional.orElse(null);
    }

    @Override
    public Page<Emp> buscarTodos(Pageable page) {
        return empRepo.findAll(page);
    }
}
