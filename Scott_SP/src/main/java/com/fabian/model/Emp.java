package com.fabian.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat; // Importante para la fecha
import java.util.Date;

@Entity
@Table(name = "emp")
public class Emp {

    @Id
    @Column(name = "EMPNO")
    private Integer empno;

    private String ename;
    private String job;
    private Integer mgr;
    private Double sal;
    private Double comm;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date hiredate;


    @ManyToOne
    @JoinColumn(name = "DEPTNO")
    private Dept dept;

    public Emp() {}

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public Integer getEmpno() { return empno; }
    public void setEmpno(Integer empno) { this.empno = empno; }

    public String getEname() { return ename; }
    public void setEname(String ename) { this.ename = ename; }

    public String getJob() { return job; }
    public void setJob(String job) { this.job = job; }

    public Integer getMgr() { return mgr; }
    public void setMgr(Integer mgr) { this.mgr = mgr; }

    public Double getSal() { return sal; }
    public void setSal(Double sal) { this.sal = sal; }

    public Double getComm() { return comm; }
    public void setComm(Double comm) { this.comm = comm; }

    public Date getHiredate() { return hiredate; }
    public void setHiredate(Date hiredate) { this.hiredate = hiredate; }
}