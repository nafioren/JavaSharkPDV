package com.proyecto.JavaSharkPDV.model;

import jakarta.persistence.*;

@Entity
@Table(name = "punto_de_venta")
public class PuntoDeVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    public PuntoDeVenta() {}

    public PuntoDeVenta(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public PuntoDeVenta(String test) {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
