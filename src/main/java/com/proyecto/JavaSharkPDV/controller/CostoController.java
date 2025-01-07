package com.proyecto.JavaSharkPDV.controller;

import com.proyecto.JavaSharkPDV.dto.CostoResponse;
import com.proyecto.JavaSharkPDV.model.Costo;
import com.proyecto.JavaSharkPDV.dto.CostoRequest;
import com.proyecto.JavaSharkPDV.services.CostoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/costos")
@Tag(name = "Costos", description = "Gestión de costos entre puntos de venta")
public class CostoController {

    private final CostoService costoService;

    public CostoController(CostoService costoService) {
        this.costoService = costoService;
    }

    @Operation(summary = "Agregar un costo entre puntos de venta")
    @ApiResponse(responseCode = "200", description = "Costo agregado exitosamente")
    @PostMapping
    public ResponseEntity<CostoResponse> agregarCosto(@Valid @RequestBody CostoRequest request) {
        Costo costo = costoService.agregarCosto(request.getIdOrigen(), request.getIdDestino(), request.getCosto());

        // Crear la respuesta basándose en el objeto costo
        CostoResponse response = new CostoResponse(
                costo.getOrigen().getId(),
                costo.getDestino().getId(),
                costo.getCosto()
        );
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Eliminar un costo entre puntos de venta")
    @ApiResponse(responseCode = "204", description = "Costo eliminado exitosamente")
    @DeleteMapping
    public ResponseEntity<Void> eliminarCosto(@RequestParam Long idOrigen, @RequestParam Long idDestino) {

        costoService.eliminarCosto(idOrigen, idDestino);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Registrar un costo")
    @ApiResponse(responseCode = "200", description = "Costo registrado exitosamente")
    @PostMapping("/registrar")
    public ResponseEntity<CostoResponse> registrar(@Valid @RequestBody CostoRequest request) {
        Costo costo = costoService.registrar(request);
        CostoResponse response = new CostoResponse(
                costo.getOrigen().getId(),
                costo.getDestino().getId(),
                costo.getCosto()
        );
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Obtener costo directo entre puntos de venta")
    @ApiResponse(responseCode = "200", description = "Costo obtenido exitosamente")
    @GetMapping("/costo-directo")
    public ResponseEntity<Integer> obtenerCostoDirecto(
            @RequestParam Long idOrigen,
            @RequestParam Long idDestino) {
        return ResponseEntity.ok(costoService.obtenerCosto(idOrigen, idDestino));  // Ahora devuelve Integer
    }

    @Operation(summary = "Calcular el camino mínimo entre dos puntos de venta")
    @ApiResponse(responseCode = "200", description = "Camino mínimo calculado exitosamente")
    @GetMapping("/camino-minimo")
    public ResponseEntity<Object> calcularCaminoMinimo(@RequestParam Long idOrigen, @RequestParam Long idDestino) {
        try {
            return ResponseEntity.ok(costoService.calcularCaminoMinimo(idOrigen, idDestino));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Ocurrió un error inesperado."));
        }
    }


}



