package com.proyecto.JavaSharkPDV.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class CostoId implements Serializable {

    private Long origen;
    private Long destino;


    public CostoId() {
    }

    public CostoId(Long origen, Long destino) {
        this.origen = origen;
        this.destino = destino;
    }

    public Long getOrigen() {
        return origen;
    }

    public void setOrigen(Long origen) {
        this.origen = origen;
    }

    public Long getDestino() {
        return destino;
    }

    public void setDestino(Long destino) {
        this.destino = destino;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CostoId costoId = (CostoId) o;
        return Objects.equals(origen, costoId.origen) &&
                Objects.equals(destino, costoId.destino);
    }

    @Override
    public int hashCode() {
        return Objects.hash(origen, destino);
    }
}
