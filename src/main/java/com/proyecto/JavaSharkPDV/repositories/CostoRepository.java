package com.proyecto.JavaSharkPDV.repositories;

import com.proyecto.JavaSharkPDV.model.Costo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CostoRepository extends JpaRepository<Costo, Long> {

    // Este método buscaría todos los costos donde el origen es el punto de venta con id 'idPuntoDeVenta'
    List<Costo> findByOrigen_Id(Long idPuntoDeVenta);

    // Este método buscaría un costo específico entre dos puntos de venta (origen y destino)
    Optional<Costo> findByOrigen_IdAndDestino_Id(Long idOrigen, Long idDestino);
}
