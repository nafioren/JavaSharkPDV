package com.proyecto.JavaSharkPDV.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
@Entity
@Table(name = "costos")
@IdClass(CostoId.class)
public class Costo {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_origen", nullable = false)
    private PuntoDeVenta origen;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_destino", nullable = false)
    private PuntoDeVenta destino;

    @Column(nullable = false)
    @Min(0)
    private int costo;


    public Costo(long l, long l1, int costo) {
    }

    public Costo(PuntoDeVenta origen, PuntoDeVenta destino, int costo) {
        this.origen = origen;
        this.destino = destino;
        this.costo = costo;
    }

    public Costo() {

    }


    public PuntoDeVenta getOrigen() {
        return origen;
    }

    public void setOrigen(PuntoDeVenta origen) {
        this.origen = origen;
    }

    public PuntoDeVenta getDestino() {
        return destino;
    }

    public void setDestino(PuntoDeVenta destino) {
        this.destino = destino;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    @Override
    public String toString() {
        return "Costo{" +
                "origen=" + origen +
                ", destino=" + destino +
                ", costo=" + costo +
                '}';
    }
}
