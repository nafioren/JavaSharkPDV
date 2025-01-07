package com.proyecto.JavaSharkPDV.dto;


public class CostoRequest {
    private Long idOrigen;
    private Long idDestino;
    private int costo;


    public CostoRequest(Long idOrigen, Long idDestino, int costo) {
        this.idOrigen = idOrigen;
        this.idDestino = idDestino;
        this.costo = costo;
    }


    public Long getIdOrigen() {
        return idOrigen;
    }

    public void setIdOrigen(Long idOrigen) {
        this.idOrigen = idOrigen;
    }

    public Long getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(Long idDestino) {
        this.idDestino = idDestino;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }
}
