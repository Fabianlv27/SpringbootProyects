package com.fabiansoft.model;

import jakarta.persistence.*;

@Entity
@Table(name = "types")
public class Types {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // TODO: Agrega aqui los atributos exactos de tu tabla 'types'
    // private String nombre;
    // private String descripcion;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
}
