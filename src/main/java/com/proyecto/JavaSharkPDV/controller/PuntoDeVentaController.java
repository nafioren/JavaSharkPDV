package com.proyecto.JavaSharkPDV.controller;

import com.proyecto.JavaSharkPDV.dto.CostoDTO;
import com.proyecto.JavaSharkPDV.dto.PuntoDeVentaDTO;
import com.proyecto.JavaSharkPDV.model.Acreditacion;
import com.proyecto.JavaSharkPDV.model.PuntoDeVenta;
import com.proyecto.JavaSharkPDV.services.AcreditacionService;
import com.proyecto.JavaSharkPDV.services.PuntoDeVentaService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Map;


@Tag(name = "Punto de Venta", description = "Operaciones CRUD para Puntos de Venta")
@RestController
@RequestMapping("/api/puntos-venta")
public class PuntoDeVentaController {

        private final PuntoDeVentaService puntoDeVentaService;

        public PuntoDeVentaController(PuntoDeVentaService puntoDeVentaService) {
                this.puntoDeVentaService = puntoDeVentaService;
        }

        @GetMapping
        @Operation(summary = "Obtener todos los puntos de venta", description = "Devuelve una lista de todos los puntos de venta registrados")
        public List<PuntoDeVenta> obtenerTodos() {
                return puntoDeVentaService.obtenerTodos();
        }

        @PostMapping
        @Operation(summary = "Registrar un punto de venta", description = "Registra un nuevo punto de venta en el sistema.")
        @ApiResponse(responseCode = "200", description = "Punto de venta registrado con éxito")
        @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud")
        public PuntoDeVenta agregar(@RequestBody PuntoDeVenta puntoVenta) {
                return puntoDeVentaService.agregar(puntoVenta);
        }

}



















  /*  @PostMapping
    public String agregar(@ModelAttribute PuntoDeVenta puntoDeVenta) {
        puntoDeVentaService.guardar(puntoDeVenta);
        return "redirect:/puntos-venta";
    }

    @GetMapping("/{id}")
    public String editar(@PathVariable int id, Model model) {
        puntoDeVentaService.obtenerPorId(id).ifPresent(pv -> model.addAttribute("puntoDeVenta", pv));
        return "editarPuntoDeVenta"; // Template Thymeleaf
    }

    @PostMapping("/{id}")
    public String actualizar(@PathVariable int id, @ModelAttribute PuntoDeVenta puntoDeVenta) {
        puntoDeVentaService.guardar(puntoDeVenta);
        return "redirect:/puntos-venta";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id) {
        puntoDeVentaService.eliminar(id);
        return "redirect:/puntos-venta";
    }
    @Autowired
    private AcreditacionService acreditacionService;

    @GetMapping("/{id}/acreditaciones")
    public String listarAcreditaciones(@PathVariable int id, Model model) {
        List<Acreditacion> acreditaciones = acreditacionService.obtenerAcreditacionesPorPunto(id);
        model.addAttribute("acreditaciones", acreditaciones);
        return "acreditaciones"; // Nueva plantilla para mostrar acreditaciones
    }

    @PostMapping("/{id}/acreditaciones")
    public String agregarAcreditacion(@PathVariable int id, @ModelAttribute Acreditacion acreditacion) {
        acreditacionService.agregarAcreditacion(id, acreditacion);
        return "redirect:/puntos-venta/" + id + "/acreditaciones";
    }

    @PostMapping("/{id}/acreditaciones/{acreditacionId}/eliminar")
    public String eliminarAcreditacion(@PathVariable int id, @PathVariable int acreditacionId) {
        acreditacionService.eliminarAcreditacion(id, acreditacionId);
        return "redirect:/puntos-venta/" + id + "/acreditaciones";
    }
    @Autowired
    private CostoServicio costoService;

    @PostMapping("/costos")
    public String agregarCosto(@ModelAttribute CostoDTO costoDTO) {
        costoService.agregarConexion(costoDTO);
        return "redirect:/puntos-venta";
    }

    @GetMapping("/costos/{id}")
    public String calcularCostos(@PathVariable int id, Model model) {
        Map<Integer, Double> costos = costoService.calcularCostosMinimos(id);
        model.addAttribute("costos", costos);
        return "costos"; // Nueva plantilla para mostrar los costos
    }
*/














