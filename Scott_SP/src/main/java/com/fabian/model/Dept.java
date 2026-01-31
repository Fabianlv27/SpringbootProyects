package com.fabian.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "dept")
public class Dept {

    @Id
    private Integer deptno;

    private String dname; // El nombre exacto es importante
    private String loc;

    @OneToMany(mappedBy = "dept", fetch = FetchType.LAZY)
    private List<Emp> empleados;

    // --- CONSTRUCTOR VACÍO (Requerido por JPA) ---
    public Dept() { }

    // --- GETTERS Y SETTERS (Cruciales para Thymeleaf) ---

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    // OJO AQUÍ: Thymeleaf busca getDname() cuando escribes .dname
    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public List<Emp> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Emp> empleados) {
        this.empleados = empleados;
    }
}