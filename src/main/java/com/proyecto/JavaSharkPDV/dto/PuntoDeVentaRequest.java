package com.proyecto.JavaSharkPDV.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PuntoDeVentaRequest {

    @NotNull(message = "El nombre del punto de venta es obligatorio")
    @Size(min = 2, max = 255, message = "El nombre debe tener entre 2 y 255 caracteres")
    private String nombre;

    // Getters y setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
