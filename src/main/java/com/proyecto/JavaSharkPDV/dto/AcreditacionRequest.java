package com.proyecto.JavaSharkPDV.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AcreditacionRequest {

    @NotNull
    private Long puntoDeVentaId;

    @Positive
    private int importe;

    @NotNull
    private LocalDateTime fechaRecepcion;

    private String detalle;


    public AcreditacionRequest(Long puntoDeVentaId, int importe, LocalDateTime fechaRecepcion) {
        this.puntoDeVentaId = puntoDeVentaId;
        this.importe = importe;
        this.fechaRecepcion = fechaRecepcion;
    }
}


