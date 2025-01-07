package com.proyecto.JavaSharkPDV;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.proyecto.JavaSharkPDV.dto.CaminoMinimoResponse;
import com.proyecto.JavaSharkPDV.model.Costo;
import com.proyecto.JavaSharkPDV.model.PuntoDeVenta;
import com.proyecto.JavaSharkPDV.repositories.CostoRepository;
import com.proyecto.JavaSharkPDV.repositories.PuntoDeVentaRepository;
import com.proyecto.JavaSharkPDV.util.DijkstraAlgoritmo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

class DijkstraAlgoritmoTest {


        @Mock
        private PuntoDeVentaRepository puntoDeVentaRepository;

        @Mock
        private CostoRepository costoRepository;

        private DijkstraAlgoritmo dijkstraAlgoritmo;

        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);
            dijkstraAlgoritmo = new DijkstraAlgoritmo(puntoDeVentaRepository, costoRepository);
        }

        @Test
        void calcularCaminoMinimoTest() {

            when(puntoDeVentaRepository.existsById(1L)).thenReturn(true);
            when(puntoDeVentaRepository.existsById(2L)).thenReturn(true);

            // Simular la existencia de puntos de venta
            PuntoDeVenta puntoA = new PuntoDeVenta(1L, "A");
            PuntoDeVenta puntoB = new PuntoDeVenta(2L, "B");

            when(puntoDeVentaRepository.findAll()).thenReturn(List.of(puntoA, puntoB));


            Costo costoAB = new Costo(puntoA, puntoB, 50);
            when(costoRepository.findByOrigen_Id(1L)).thenReturn(List.of(costoAB));

            // Llamada al método de calcular camino mínimo
            CaminoMinimoResponse response = dijkstraAlgoritmo.calcularCaminoMinimo(1L, 2L);

            // Verificar el costo total y el camino calculado
            assertEquals(50, response.getCosto());
            assertEquals(List.of("1", "2"), response.getCamino());
        }

        @Test
        void calcularCaminoMinimoSinCaminoTest() {
            // Simular que los puntos de venta existen
            when(puntoDeVentaRepository.existsById(1L)).thenReturn(true);
            when(puntoDeVentaRepository.existsById(2L)).thenReturn(true);

            // Simular la existencia de puntos de venta
            PuntoDeVenta puntoA = new PuntoDeVenta(1L, "A");
            PuntoDeVenta puntoB = new PuntoDeVenta(2L, "B");

            when(puntoDeVentaRepository.findAll()).thenReturn(List.of(puntoA, puntoB));

            // Simular que no hay costos entre los puntos de venta
            when(costoRepository.findByOrigen_Id(1L)).thenReturn(Collections.emptyList());

            // Verificar que se lance la excepción si no hay camino
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                dijkstraAlgoritmo.calcularCaminoMinimo(1L, 2L);
            });

            assertEquals("No hay camino disponible entre los puntos de venta especificados.", exception.getMessage());
        }

        @Test
        void calcularCaminoMinimoConMultiplesCostosTest() {
            // Simular que los puntos de venta existen
            when(puntoDeVentaRepository.existsById(1L)).thenReturn(true);
            when(puntoDeVentaRepository.existsById(3L)).thenReturn(true);

            // Simular la existencia de puntos de venta
            PuntoDeVenta puntoA = new PuntoDeVenta(1L, "A");
            PuntoDeVenta puntoB = new PuntoDeVenta(3L, "C");

            when(puntoDeVentaRepository.findAll()).thenReturn(List.of(puntoA, new PuntoDeVenta(2L, "B"), puntoB));


            Costo costoAB = new Costo(puntoA, new PuntoDeVenta(2L, "B"), 10);
            Costo costoBC = new Costo(new PuntoDeVenta(2L, "B"), puntoB, 15);

            when(costoRepository.findByOrigen_Id(1L)).thenReturn(List.of(costoAB));
            when(costoRepository.findByOrigen_Id(2L)).thenReturn(List.of(costoBC));


            CaminoMinimoResponse response = dijkstraAlgoritmo.calcularCaminoMinimo(1L, 3L);


            assertEquals(25, response.getCosto());
            assertEquals(List.of("1", "2", "3"), response.getCamino());
        }
}



