package com.proyecto.JavaSharkPDV.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PuntoDeVentaDTO {

    @NotNull(message = "El ID no puede ser nulo")
    private Integer id;

    @NotNull(message = "El nombre no puede ser nulo")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;

    public PuntoDeVentaDTO() {}

    public PuntoDeVentaDTO(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


}
