package com.fabian.service;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.fabian.model.Emp;

public interface IEmpService {
	List<Emp> buscarTodos();
	void guardar(Emp empleado);
	void eliminar(Integer empno);
	Emp buscarPorId(Integer empno);
	Page<Emp> buscarTodos(Pageable page);
}
