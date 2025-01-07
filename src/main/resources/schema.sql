CREATE DATABASE IF NOT EXISTS PDV;

USE PDV;

-- Crear tablas
CREATE TABLE `punto_de_venta` (
                                  `id` bigint NOT NULL AUTO_INCREMENT,
                                  `nombre` varchar(255) NOT NULL,
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `acreditaciones` (
                                  `id` bigint NOT NULL AUTO_INCREMENT,
                                  `importe` int NOT NULL,
                                  `id_punto_venta` bigint DEFAULT NULL,
                                  `nombre_punto_venta` varchar(255) NOT NULL,
                                  `fecha_recepcion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                  PRIMARY KEY (`id`),
                                  KEY `id_punto_venta` (`id_punto_venta`),
                                  CONSTRAINT `acreditaciones_ibfk_1` FOREIGN KEY (`id_punto_venta`) REFERENCES `punto_de_venta` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `costos` (
                          `id_origen` bigint NOT NULL,
                          `id_destino` bigint NOT NULL,
                          `costo` int NOT NULL,
                          PRIMARY KEY (`id_origen`, `id_destino`),
                          KEY `id_origen` (`id_origen`),
                          KEY `id_destino` (`id_destino`),
                          CONSTRAINT `costos_ibfk_1` FOREIGN KEY (`id_origen`) REFERENCES `punto_de_venta` (`id`),
                          CONSTRAINT `costos_ibfk_2` FOREIGN KEY (`id_destino`) REFERENCES `punto_de_venta` (`id`),
                          CONSTRAINT `costos_chk_1` CHECK (`costo` >= 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

