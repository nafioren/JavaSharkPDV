package com.proyecto.JavaSharkPDV;

import com.proyecto.JavaSharkPDV.model.PuntoDeVenta;
import com.proyecto.JavaSharkPDV.repositories.PuntoDeVentaRepository;
import com.proyecto.JavaSharkPDV.services.PuntoDeVentaService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import static org.mockito.Mockito.*;


import static org.hamcrest.Matchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PuntoVentaServiceTest {


        @Mock
        private PuntoDeVentaRepository puntoDeVentaRepository;

        @InjectMocks
        private PuntoDeVentaService puntoDeVentaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Inicializar un objeto de prueba de PuntoDeVenta
        PuntoDeVenta puntoDeVenta = new PuntoDeVenta();
        puntoDeVenta.setId(1L);
        puntoDeVenta.setNombre("Punto de Venta 1");
    }

        @Test
        public void testAgregarPuntoVenta() {
            // Crear un punto de venta simulado
            PuntoDeVenta puntoDeVenta = new PuntoDeVenta("Test");

            // Configurar el comportamiento del mock
            when(puntoDeVentaRepository.save(Mockito.any(PuntoDeVenta.class)))
                    .thenAnswer(invocation -> {
                        PuntoDeVenta pv = invocation.getArgument(0);
                        pv.setId(1L); // Simular que la base de datos asigna un ID
                        return pv;
                    });

            // Ejecutar el método del servicio
            PuntoDeVenta resultado = puntoDeVentaService.agregar(puntoDeVenta);

            // Validar que el resultado no sea nulo y tenga un ID asignado
            assertNotNull(resultado.getId());
        }





        @Test
        void testActualizar_Existente() {
            // Preparación
            PuntoDeVenta puntoActualizado = new PuntoDeVenta();
            puntoActualizado.setId(1L);
            puntoActualizado.setNombre("Punto de Venta Actualizado");

            PuntoDeVenta puntoDeVenta = new PuntoDeVenta();
            when(puntoDeVentaRepository.findById(1L)).thenReturn(Optional.of(puntoDeVenta));
            when(puntoDeVentaRepository.save(Mockito.any(PuntoDeVenta.class))).thenReturn(puntoActualizado);


            // Ejecución
            PuntoDeVenta puntoDeVentaResultado = puntoDeVentaService.actualizar(1L, puntoActualizado);

            // Verificación
            assertNotNull(puntoDeVentaResultado);
            assertEquals("Punto de Venta Actualizado", puntoDeVentaResultado.getNombre());
        }

        @Test
        void testActualizar_NoExistente() {
            // Preparación
            PuntoDeVenta puntoActualizado = new PuntoDeVenta();
            puntoActualizado.setId(1L);
            puntoActualizado.setNombre("Punto de Venta Actualizado");

            // Simula que no se encuentra el punto de venta
            when(puntoDeVentaRepository.findById(1L)).thenReturn(Optional.empty());

            // Ejecución y verificación
            RuntimeException exception = assertThrows(RuntimeException.class, () -> {
                puntoDeVentaService.actualizar(1L, puntoActualizado);
            });

            assertEquals("Punto de Venta con ID 1 no encontrado", exception.getMessage());
        }

        @Test
        void testEliminar_Existente() {
            // Preparación
            when(puntoDeVentaRepository.existsById(1L)).thenReturn(true);

            // Ejecución
            puntoDeVentaService.eliminar(1L);

            // Verificación
            verify(puntoDeVentaRepository, times(1)).deleteById(1L);
        }

        @Test
        void testEliminar_NoExistente() {
            // Simula que el punto de venta no existe
            when(puntoDeVentaRepository.existsById(1L)).thenReturn(false);

            // Ejecución y verificación
            RuntimeException exception = assertThrows(RuntimeException.class, () -> {
                puntoDeVentaService.eliminar(1L);
            });

            assertEquals("Punto de Venta con ID 1 no encontrado", exception.getMessage());
        }



}


