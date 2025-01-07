package com.proyecto.JavaSharkPDV.dto;

public class AcreditacionDTO {

    private int idPuntoDeVenta;
    private int importe;
    private String fechaRecepcion;
    private String nombrePuntoDeVenta;


    public AcreditacionDTO() {}


    public AcreditacionDTO(int idPuntoDeVenta, double importe, String fechaRecepcion, String nombrePuntoDeVenta) {
        this.idPuntoDeVenta = idPuntoDeVenta;
        this.importe = (int) importe;
        this.fechaRecepcion = fechaRecepcion;
        this.nombrePuntoDeVenta = nombrePuntoDeVenta;
    }


    public int getIdPuntoDeVenta() {
        return idPuntoDeVenta;
    }

    public void setIdPuntoDeVenta(int idPuntoDeVenta) {
        this.idPuntoDeVenta = idPuntoDeVenta;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = (int) importe;
    }

    public String getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(String fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public String getNombrePuntoDeVenta() {
        return nombrePuntoDeVenta;
    }

    public void setNombrePuntoDeVenta(String nombrePuntoDeVenta) {
        this.nombrePuntoDeVenta = nombrePuntoDeVenta;
    }
}

