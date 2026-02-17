package com.fabiansoft.model;

import jakarta.persistence.*;

@Entity
@Table(name = "words")
public class Words {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // TODO: Agrega aqui los atributos exactos de tu tabla 'words'
    // private String nombre;
    // private String descripcion;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
}
