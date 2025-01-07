package com.proyecto.JavaSharkPDV.repositories;
import com.proyecto.JavaSharkPDV.model.PuntoDeVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PuntoDeVentaRepository extends JpaRepository<PuntoDeVenta, Long> {

}

