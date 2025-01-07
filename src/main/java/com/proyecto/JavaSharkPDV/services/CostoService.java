package com.proyecto.JavaSharkPDV.services;

import com.proyecto.JavaSharkPDV.dto.CostoRequest;
import com.proyecto.JavaSharkPDV.dto.CaminoMinimoResponse;
import com.proyecto.JavaSharkPDV.model.Costo;
import com.proyecto.JavaSharkPDV.model.PuntoDeVenta;
import com.proyecto.JavaSharkPDV.repositories.CostoRepository;
import com.proyecto.JavaSharkPDV.repositories.PuntoDeVentaRepository;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.*;

@Service
public class CostoService {

    private final CostoRepository costoRepository;
    private final PuntoDeVentaRepository puntoDeVentaRepository;

    public CostoService(CostoRepository costoRepository, PuntoDeVentaRepository puntoDeVentaRepository) {
        this.costoRepository = costoRepository;
        this.puntoDeVentaRepository = puntoDeVentaRepository;
    }

    public CaminoMinimoResponse calcularCaminoMinimo(Long idOrigen, Long idDestino) {
        if (!puntoDeVentaRepository.existsById(idOrigen) || !puntoDeVentaRepository.existsById(idDestino)) {
            throw new IllegalArgumentException("Uno o ambos puntos de venta no existen.");
        }


        Map<Long, Integer> distancias = new HashMap<>();
        Map<Long, Long> predecesores = new HashMap<>();
        PriorityQueue<Pair<Long, Integer>> cola = new PriorityQueue<>(Comparator.comparingInt(Pair::getRight));


        puntoDeVentaRepository.findAll().forEach(p -> distancias.put(p.getId(), Integer.MAX_VALUE));
        distancias.put(idOrigen, 0); // Asignar distancia 0 al nodo de origen
        cola.add(Pair.of(idOrigen, 0));

        while (!cola.isEmpty()) {
            Pair<Long, Integer> actual = cola.poll();
            Long nodoActual = actual.getLeft();

            for (Costo costo : obtenerCostosDirectos(nodoActual)) {
                Long vecino = costo.getDestino().getId();
                int nuevoCosto = distancias.get(nodoActual) + costo.getCosto();

                // Verificar si el nuevo costo es menor que el actual
                if (nuevoCosto < distancias.get(vecino)) {
                    distancias.put(vecino, nuevoCosto);
                    predecesores.put(vecino, nodoActual);
                    cola.add(Pair.of(vecino, nuevoCosto));
                }
            }
        }

        // Recupera el camino más corto
        List<String> camino = new ArrayList<>();
        Long nodoActual = idDestino;
        while (nodoActual != null) {
            camino.add(nodoActual.toString());
            nodoActual = predecesores.get(nodoActual);
        }
        Collections.reverse(camino);

        // Retorna el resultado como una respuesta de tipo CaminoMinimoResponse
        return new CaminoMinimoResponse(distancias.get(idDestino), camino);
    }


    private List<Costo> obtenerCostosDirectos(Long idPuntoDeVenta) {
        return costoRepository.findByOrigen_Id(idPuntoDeVenta);
    }

    private List<Long> reconstruirCamino(Map<Long, Long> predecesores, Long idOrigen, Long idDestino) {
        List<Long> camino = new ArrayList<>();
        for (Long at = idDestino; at != null; at = predecesores.get(at)) {
            camino.add(at);
        }
        Collections.reverse(camino);
        return camino;
    }

    public Costo agregarCosto(Long idOrigen, Long idDestino, int costo) {
        // Buscar las entidades PuntoDeVenta asociadas
        PuntoDeVenta origen = puntoDeVentaRepository.findById(idOrigen)
                .orElseThrow(() -> new IllegalArgumentException("Punto de venta de origen no existe"));
        PuntoDeVenta destino = puntoDeVentaRepository.findById(idDestino)
                .orElseThrow(() -> new IllegalArgumentException("Punto de venta de destino no existe"));

        // Crear y retornar el objeto Costo
        return costoRepository.save(new Costo(origen, destino, costo));
    }




    public Integer obtenerCosto(Long idOrigen, Long idDestino) {
        // Asumimos que el costo se devuelve como Integer en la base de datos
        return costoRepository.findByOrigen_IdAndDestino_Id(idOrigen, idDestino)
                .map(Costo::getCosto)  // No hace falta mapear a Double, se deja como Integer
                .orElse(Integer.MAX_VALUE);  // Devolvemos Integer.MAX_VALUE en caso de no encontrar el costo
    }

    public void eliminarCosto(Long idOrigen, Long idDestino) {
        Costo costos = costoRepository.findByOrigen_IdAndDestino_Id(idOrigen, idDestino)
                .orElseThrow(() -> new IllegalArgumentException("El costo entre los puntos de venta no existe"));

        costoRepository.delete(costos);
    }

    public Costo registrar(CostoRequest request) {
        PuntoDeVenta origen = puntoDeVentaRepository.findById(request.getIdOrigen())
                .orElseThrow(() -> new IllegalArgumentException("Punto de venta de origen no existe"));
        PuntoDeVenta destino = puntoDeVentaRepository.findById(request.getIdDestino())
                .orElseThrow(() -> new IllegalArgumentException("Punto de venta de destino no existe"));

        // Si request.getCosto() ya es de tipo int, no es necesario convertirlo a intValue()
        Costo costo = new Costo(origen, destino, request.getCosto());
        return costoRepository.save(costo);
    }


}










