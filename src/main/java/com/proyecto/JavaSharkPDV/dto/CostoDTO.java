package com.proyecto.JavaSharkPDV.dto;

public class CostoDTO {
    private int idA;
    private int idB;
    private double costo;


    public CostoDTO() {}


    public CostoDTO(int idA, int idB, double costo) {
        this.idA = idA;
        this.idB = idB;
        this.costo = costo;
    }


    public int getIdA() {
        return idA;
    }

    public void setIdA(int idA) {
        this.idA = idA;
    }

    public int getIdB() {
        return idB;
    }

    public void setIdB(int idB) {
        this.idB = idB;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
}

