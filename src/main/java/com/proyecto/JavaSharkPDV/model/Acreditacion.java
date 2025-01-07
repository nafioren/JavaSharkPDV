package com.proyecto.JavaSharkPDV.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "acreditaciones")
public class Acreditacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int importe;

    @ManyToOne(fetch = FetchType.LAZY)  // Relación con la entidad PuntoDeVenta
    @JoinColumn(name = "id_punto_venta")
    private PuntoDeVenta puntoDeVenta;

    @Column(name = "nombre_punto_venta", nullable = false)
    private String nombrePuntoVenta;

    @Column(name = "fecha_recepcion", nullable = false)
    private LocalDateTime fechaRecepcion;

    public Acreditacion() {
    }


    public Acreditacion(Long id, int importe, PuntoDeVenta puntoDeVenta, String nombrePuntoVenta, LocalDateTime fechaRecepcion) {
        this.id = id;
        this.importe = importe;
        this.puntoDeVenta = puntoDeVenta;
        this.nombrePuntoVenta = nombrePuntoVenta;
        this.fechaRecepcion = fechaRecepcion;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getImporte() {
        return importe;
    }

    public void setImporte(int importe) {
        this.importe = importe;
    }

    public PuntoDeVenta getPuntoDeVenta() {
        return puntoDeVenta;
    }

    public void setPuntoDeVenta(PuntoDeVenta puntoDeVenta) {
        this.puntoDeVenta = puntoDeVenta;
    }

    public String getNombrePuntoVenta() {
        return nombrePuntoVenta;
    }

    public void setNombrePuntoVenta(String nombrePuntoVenta) {
        this.nombrePuntoVenta = nombrePuntoVenta;
    }

    public LocalDateTime getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(LocalDateTime fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    @Override
    public String toString() {
        return "Acreditacion{" +
                "id=" + id +
                ", importe=" + importe +
                ", puntoDeVenta=" + puntoDeVenta +
                ", nombrePuntoVenta='" + nombrePuntoVenta + '\'' +
                ", fechaRecepcion=" + fechaRecepcion +
                '}';
    }
}





