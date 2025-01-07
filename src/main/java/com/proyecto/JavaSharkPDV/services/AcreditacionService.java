package com.proyecto.JavaSharkPDV.services;

import com.proyecto.JavaSharkPDV.dto.AcreditacionRequest;
import com.proyecto.JavaSharkPDV.model.Acreditacion;
import com.proyecto.JavaSharkPDV.model.PuntoDeVenta;
import com.proyecto.JavaSharkPDV.repositories.AcreditacionRepository;
import com.proyecto.JavaSharkPDV.repositories.PuntoDeVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AcreditacionService {

    private final AcreditacionRepository acreditacionRepository;
    private final PuntoDeVentaRepository puntoDeVentaRepository;

    @Autowired
    public AcreditacionService(AcreditacionRepository acreditacionRepository, PuntoDeVentaRepository puntoDeVentaRepository) {
        this.acreditacionRepository = acreditacionRepository;
        this.puntoDeVentaRepository = puntoDeVentaRepository;
    }

    public Acreditacion registrar(AcreditacionRequest request) {
        if (request.getImporte() <= 0) {
            throw new IllegalArgumentException("El importe debe ser mayor a 0.");
        }

        PuntoDeVenta puntoDeVenta = puntoDeVentaRepository.findById(request.getPuntoDeVentaId())
                .orElseThrow(() -> new IllegalArgumentException("Punto de venta no encontrado"));

        Acreditacion acreditacion = new Acreditacion();
        acreditacion.setPuntoDeVenta(puntoDeVenta);
        acreditacion.setImporte(request.getImporte());
        acreditacion.setFechaRecepcion(request.getFechaRecepcion());
        acreditacion.setNombrePuntoVenta(puntoDeVenta.getNombre());

        return acreditacionRepository.save(acreditacion);
    }


    // Obtener todas las acreditaciones
    public List<Acreditacion> obtenerTodas() {
        return acreditacionRepository.findAll();
    }

    // Eliminar una acreditación específica
    public boolean eliminarAcreditacionPorNombre(String nombrePuntoVenta) {

        List<Acreditacion> acreditaciones = acreditacionRepository.findByNombrePuntoVenta(nombrePuntoVenta);

        // Si existen acreditaciones con ese nombre, eliminarlas
        if (!acreditaciones.isEmpty()) {
            acreditacionRepository.deleteAll(acreditaciones);
            return true;  // Indicamos que la eliminación fue exitosa
        }

        return false;  // No se encontraron acreditaciones para eliminar
    }

    public List<String> listarAcreditaciones(Long idPuntoVenta) {
        // Devuelve datos simulados o realiza consultas en el repositorio.
        return List.of("Acreditación 1", "Acreditación 2");
    }


}



