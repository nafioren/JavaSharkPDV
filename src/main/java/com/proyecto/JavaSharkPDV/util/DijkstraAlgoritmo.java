package com.proyecto.JavaSharkPDV.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.proyecto.JavaSharkPDV.dto.CaminoMinimoResponse;
import com.proyecto.JavaSharkPDV.model.Costo;
import com.proyecto.JavaSharkPDV.repositories.CostoRepository;

import java.util.PriorityQueue;

import com.proyecto.JavaSharkPDV.repositories.PuntoDeVentaRepository;
import org.apache.commons.lang3.tuple.Pair;
import java.util.*;
import java.util.stream.Collectors;

public class DijkstraAlgoritmo {


        private final PuntoDeVentaRepository puntoDeVentaRepository;
        private final CostoRepository costoRepository;

        public DijkstraAlgoritmo(PuntoDeVentaRepository puntoDeVentaRepository, CostoRepository costoRepository) {
            this.puntoDeVentaRepository = puntoDeVentaRepository;
            this.costoRepository = costoRepository;
        }

        public CaminoMinimoResponse calcularCaminoMinimo(Long inicio, Long fin) {
            // Verificación de existencia de los puntos de venta
            if (!puntoDeVentaRepository.existsById(inicio) || !puntoDeVentaRepository.existsById(fin)) {
                throw new IllegalArgumentException("Uno o ambos puntos de venta no existen.");
            }

            Map<Long, Integer> distancias = new HashMap<>();
            Map<Long, Long> predecesores = new HashMap<>();
            PriorityQueue<Pair<Long, Integer>> cola = new PriorityQueue<>(Comparator.comparingInt(Pair::getRight));


            puntoDeVentaRepository.findAll().forEach(p -> distancias.put(p.getId(), Integer.MAX_VALUE));
            distancias.put(inicio, 0); // Distancia al nodo de inicio es 0
            cola.add(Pair.of(inicio, 0));

            // Algoritmo de Dijkstra
            while (!cola.isEmpty()) {
                Pair<Long, Integer> actual = cola.poll();
                Long nodoActual = actual.getLeft();

                // Obtener los costos directos hacia los vecinos
                for (Costo costo : obtenerCostosDirectos(nodoActual)) {
                    Long vecino = costo.getDestino().getId(); // Asumiendo que 'Destino' es el punto de venta destino
                    int nuevoCosto = distancias.get(nodoActual) + costo.getCosto();

                    // Si se encuentra un camino más corto, actualizar distancias y predecesores
                    if (nuevoCosto < distancias.get(vecino)) {
                        distancias.put(vecino, nuevoCosto);
                        predecesores.put(vecino, nodoActual);
                        cola.add(Pair.of(vecino, nuevoCosto));
                    }
                }
            }

            // Si no se ha encontrado un camino al destino, lanzar excepción
            if (!distancias.containsKey(fin) || distancias.get(fin).equals(Integer.MAX_VALUE)) {
                throw new IllegalArgumentException("No hay camino disponible entre los puntos de venta especificados.");
            }

            // Reconstrucción del camino más corto
            List<Long> camino = reconstruirCamino(predecesores, inicio, fin);

            // Conversión del camino de Long a String para la respuesta
            List<String> caminoString = camino.stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList());

            return new CaminoMinimoResponse(distancias.get(fin), caminoString);
        }

        // Obtener los costos directos desde el punto de venta con id 'idPuntoDeVenta'
        private List<Costo> obtenerCostosDirectos(Long idPuntoDeVenta) {
            // Aquí, primero encuentras todos los costos donde el origen es idPuntoDeVenta
            return costoRepository.findByOrigen_Id(idPuntoDeVenta); // Usando solo el id del origen
        }

        // Reconstrucción del camino a partir de los predecesores
        private List<Long> reconstruirCamino(Map<Long, Long> predecesores, Long inicio, Long fin) {
            List<Long> camino = new ArrayList<>();
            for (Long at = fin; at != null; at = predecesores.get(at)) {
                camino.add(at);
            }
            Collections.reverse(camino);
            return camino;
        }


}
