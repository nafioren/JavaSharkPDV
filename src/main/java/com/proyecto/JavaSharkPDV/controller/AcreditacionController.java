package com.proyecto.JavaSharkPDV.controller;

import com.proyecto.JavaSharkPDV.model.Acreditacion;
import com.proyecto.JavaSharkPDV.dto.AcreditacionRequest;
import com.proyecto.JavaSharkPDV.services.AcreditacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/acreditaciones")
@Tag(name = "Acreditaciones", description = "Gestión de acreditaciones entre puntos de venta")
public class AcreditacionController {


    private final AcreditacionService acreditacionService;

    @Autowired
    public AcreditacionController(AcreditacionService acreditacionService) {
        this.acreditacionService = acreditacionService;
    }

    @GetMapping
    @Operation(summary = "Obtener todas las acreditaciones", description = "Devuelve una lista de todas las acreditaciones registradas")
    public ResponseEntity<List<Acreditacion>> obtenerTodas() {
        return ResponseEntity.ok(acreditacionService.obtenerTodas());
    }

    @DeleteMapping("/{nombrePuntoDeVenta}")
    @Operation(summary = "Eliminar acreditación", description = "Elimina acreditaciones relacionadas con un punto de venta específico")
    public ResponseEntity<Void> eliminarAcreditacion(
            @PathVariable String nombrePuntoDeVenta) {
        boolean eliminada = acreditacionService.eliminarAcreditacionPorNombre(nombrePuntoDeVenta);
        return eliminada ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }


    @PostMapping
    @Operation(summary = "Registrar una acreditación", description = "Registra una nueva acreditación en el sistema.")
    @ApiResponse(responseCode = "200", description = "Acreditación registrada con éxito")
    @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud")
    public Acreditacion registrar(@Valid @RequestBody AcreditacionRequest request) {
        return acreditacionService.registrar(request);
    }

}




