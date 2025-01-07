package com.proyecto.JavaSharkPDV;


import com.proyecto.JavaSharkPDV.repositories.PuntoDeVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.containers.MySQLContainer;


//@Testcontainers
@SpringBootTest
public class PuntoVentaRepositoryTest {


        private static final MySQLContainer<?> mysql = new MySQLContainer<>(DockerImageName.parse("mysql:8.0"))
                .withDatabaseName("pdv")
                .withUsername("test")
                .withPassword("test");

        @DynamicPropertySource
        static void configureProperties(DynamicPropertyRegistry registry) {
            registry.add("spring.datasource.url", mysql::getJdbcUrl);
            registry.add("spring.datasource.username", mysql::getUsername);
            registry.add("spring.datasource.password", mysql::getPassword);
        }

        @Autowired
        private PuntoDeVentaRepository puntoDeVentaRepository;

       /* @Test
        public void testGuardarPuntoVenta() {
            PuntoDeVenta punto = new PuntoDeVenta(1L, "CABA");
            puntoDeVentaRepository.save(punto);
            Assertions.assertTrue(puntoDeVentaRepository.findById(1L).isPresent());
        }*/


}
