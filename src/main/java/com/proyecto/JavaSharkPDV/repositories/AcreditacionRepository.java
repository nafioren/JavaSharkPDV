package com.proyecto.JavaSharkPDV.repositories;

import com.proyecto.JavaSharkPDV.model.Acreditacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AcreditacionRepository extends JpaRepository<Acreditacion, Long> {
    List<Acreditacion> findByNombrePuntoVenta(String nombrePuntoVenta);
}
