package com.proyecto.JavaSharkPDV.dto;


import java.util.List;

public class CaminoMinimoResponse {


    private int costo;
    private List<String> camino;

    public CaminoMinimoResponse(int costo, List<String> camino) {
        this.costo = costo;
        this.camino = camino;
    }

    public int getCosto() {
        return costo;
    }

    public List<String> getCamino() {
        return camino;
    }
    @Override
    public String toString() {
        return "CaminoMinimoResponse{" +
                "costo=" + costo +
                ", camino=" + camino +
                '}';
    }


}





