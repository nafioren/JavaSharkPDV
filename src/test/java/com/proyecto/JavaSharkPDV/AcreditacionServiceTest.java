package com.proyecto.JavaSharkPDV;

import com.proyecto.JavaSharkPDV.model.Acreditacion;
import com.proyecto.JavaSharkPDV.model.PuntoDeVenta;
import com.proyecto.JavaSharkPDV.repositories.AcreditacionRepository;
import com.proyecto.JavaSharkPDV.repositories.PuntoDeVentaRepository;
import com.proyecto.JavaSharkPDV.dto.AcreditacionRequest;
import com.proyecto.JavaSharkPDV.services.AcreditacionService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.Optional;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class AcreditacionServiceTest {

    @Mock
    private AcreditacionRepository acreditacionRepository;

    @Mock
    private PuntoDeVentaRepository puntoDeVentaRepository;

    @InjectMocks
    private AcreditacionService acreditacionService;

    @Test
    public void testRegistrar() {
        // Datos de entrada
        Long puntoDeVentaId = 1L;
        AcreditacionRequest request = new AcreditacionRequest(puntoDeVentaId, 100, LocalDateTime.now());
        PuntoDeVenta puntoDeVenta = new PuntoDeVenta();
        puntoDeVenta.setId(puntoDeVentaId);
        puntoDeVenta.setNombre("Punto 1");

        // Mock comportamiento

        when(puntoDeVentaRepository.findById(puntoDeVentaId)).thenReturn(Optional.empty());

        when(acreditacionRepository.save(any(Acreditacion.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Ejecutar método
        Acreditacion result = acreditacionService.registrar(request);

        // Verificaciones
        assertNotNull(result);
        assertEquals(request.getImporte(), result.getImporte());
        assertEquals(puntoDeVenta.getNombre(), result.getNombrePuntoVenta());
        verify(acreditacionRepository).save(any(Acreditacion.class));
    }


}
