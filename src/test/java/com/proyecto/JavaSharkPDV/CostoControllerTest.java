package com.proyecto.JavaSharkPDV;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.JavaSharkPDV.controller.CostoController;
import com.proyecto.JavaSharkPDV.dto.CaminoMinimoResponse;
import com.proyecto.JavaSharkPDV.model.Costo;
import com.proyecto.JavaSharkPDV.model.PuntoDeVenta;
import com.proyecto.JavaSharkPDV.dto.CostoRequest;
import com.proyecto.JavaSharkPDV.services.CostoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class CostoControllerTest {

    @InjectMocks
    private CostoController costoController;

    @Mock
    private CostoService costoService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {

        mockMvc = MockMvcBuilders.standaloneSetup(costoController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void agregarCostoTest() throws Exception {
        // Crear objetos relacionados con valores válidos
        PuntoDeVenta origen = new PuntoDeVenta(1L, "Punto de Venta Origen");
        PuntoDeVenta destino = new PuntoDeVenta(2L, "Punto de Venta Destino");
        Costo costo = new Costo(origen, destino, 100);

        CostoRequest costoRequest = new CostoRequest(1L, 2L, 100);

        // Simula el comportamiento del servicio
        when(costoService.agregarCosto(1L, 2L, 100)).thenReturn(costo);

        // Realiza la solicitud POST para agregar el costo
        mockMvc.perform(post("/api/costos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(costoRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idOrigen").value(1L))
                .andExpect(jsonPath("$.idDestino").value(2L))
                .andExpect(jsonPath("$.costo").value(100));

        verify(costoService, times(1)).agregarCosto(1L, 2L, 100);
    }


    @Test
    void eliminarCostoTest() throws Exception {

        doNothing().when(costoService).eliminarCosto(1L, 2L);

        // Realiza la solicitud DELETE para eliminar el costo
        mockMvc.perform(delete("/api/costos")
                        .param("idOrigen", "1")
                        .param("idDestino", "2"))
                .andExpect(status().isNoContent());

        verify(costoService, times(1)).eliminarCosto(1L, 2L);
    }

    @Test
    void registrarCostoTest() throws Exception {
        CostoRequest costoRequest = new CostoRequest(1L, 2L, 150);


        when(costoService.registrar(any(CostoRequest.class)))
                .thenReturn(new Costo(
                        new PuntoDeVenta(1L, "Punto A"),
                        new PuntoDeVenta(2L, "Punto B"),
                        150
                ));

        // Realiza la solicitud POST para registrar el costo
        mockMvc.perform(post("/api/costos/registrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(costoRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idOrigen").value(1L))
                .andExpect(jsonPath("$.idDestino").value(2L))
                .andExpect(jsonPath("$.costo").value(150));

        verify(costoService, times(1)).registrar(any(CostoRequest.class));
    }


    @Test
    void obtenerCostoDirectoTest() throws Exception {

        when(costoService.obtenerCosto(1L, 2L)).thenReturn(100);

        // Realiza la solicitud GET para obtener el costo directo
        mockMvc.perform(get("/api/costos/costo-directo")
                        .param("idOrigen", "1")
                        .param("idDestino", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(100));

        verify(costoService, times(1)).obtenerCosto(1L, 2L);
    }

    @Test
    void calcularCaminoMinimoTest() throws Exception {
        CaminoMinimoResponse response = new CaminoMinimoResponse(100, List.of("1", "2"));


        when(costoService.calcularCaminoMinimo(1L, 2L)).thenReturn(response);

        // Realiza la solicitud GET para calcular el camino mínimo
        mockMvc.perform(get("/api/costos/camino-minimo")
                        .param("idOrigen", "1")
                        .param("idDestino", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.costo").value(100))
                .andExpect(jsonPath("$.camino").isArray())
                .andExpect(jsonPath("$.camino[0]").value("1"))
                .andExpect(jsonPath("$.camino[1]").value("2"));

        verify(costoService, times(1)).calcularCaminoMinimo(1L, 2L);
    }

}
