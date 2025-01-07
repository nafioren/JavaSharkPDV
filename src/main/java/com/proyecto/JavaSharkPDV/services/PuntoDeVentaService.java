package com.proyecto.JavaSharkPDV.services;


import org.springframework.stereotype.Service;
import com.proyecto.JavaSharkPDV.model.PuntoDeVenta;
import com.proyecto.JavaSharkPDV.repositories.PuntoDeVentaRepository;
import java.util.List;
import java.util.Optional;

@Service
public class PuntoDeVentaService {

        private final PuntoDeVentaRepository puntoDeVentaRepository;

        public PuntoDeVentaService(PuntoDeVentaRepository puntoDeVentaRepository) {
            this.puntoDeVentaRepository = puntoDeVentaRepository;
        }

        public List<PuntoDeVenta> obtenerTodos() {
            return puntoDeVentaRepository.findAll();
        }

        public PuntoDeVenta agregar(PuntoDeVenta puntoVenta) {
            return puntoDeVentaRepository.save(puntoVenta);
        }

        public PuntoDeVenta actualizar(Long id, PuntoDeVenta puntoDeVenta) {
            Optional<PuntoDeVenta> existente = puntoDeVentaRepository.findById(id);
            if (existente.isPresent()) {
                PuntoDeVenta puntoDeVentaExistente = existente.get();
                puntoDeVentaExistente.setNombre(puntoDeVenta.getNombre());
                return puntoDeVentaRepository.save(puntoDeVentaExistente);
            } else {
                throw new RuntimeException("Punto de Venta con ID " + id + " no encontrado");
            }
        }

        public void eliminar(Long id) {
            if (puntoDeVentaRepository.existsById(id)) {
                puntoDeVentaRepository.deleteById(id);
            } else {
                throw new RuntimeException("Punto de Venta con ID " + id + " no encontrado");
            }
        }


}






