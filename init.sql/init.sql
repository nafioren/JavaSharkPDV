CREATE DATABASE IF NOT EXISTS PDV;

USE PDV;

-- Crear tablas
CREATE TABLE `punto_de_venta` (
                                  `id` bigint NOT NULL,
                                  `nombre` varchar(255) NOT NULL,
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `acreditaciones` (
                                  `id` bigint NOT NULL,
                                  `importe` int NOT NULL,
                                  `id_punto_venta` bigint DEFAULT NULL,
                                  `nombre_punto_venta` varchar(255) NOT NULL,
                                  `fecha_recepcion` timestamp NOT NULL,
                                  PRIMARY KEY (`id`),
                                  KEY `id_punto_venta` (`id_punto_venta`),
                                  CONSTRAINT `acreditaciones_ibfk_1` FOREIGN KEY (`id_punto_venta`) REFERENCES `punto_de_venta` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `costos` (
                          `id_origen` bigint NOT NULL,
                          `id_destino` bigint NOT NULL,
                          `costo` int NOT NULL,
                          KEY `id_origen` (`id_origen`),
                          KEY `id_destino` (`id_destino`),
                          CONSTRAINT `costos_ibfk_1` FOREIGN KEY (`id_origen`) REFERENCES `punto_de_venta` (`id`),
                          CONSTRAINT `costos_ibfk_2` FOREIGN KEY (`id_destino`) REFERENCES `punto_de_venta` (`id`),
                          CONSTRAINT `costos_chk_1` CHECK (`costo` >= 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Insertar datos
INSERT INTO `punto_de_venta` (id, nombre) VALUES
                                              (1, 'CABA'),
                                              (2, 'GBA_1'),
                                              (3, 'GBA_2'),
                                              (4, 'Santa Fe'),
                                              (5, 'Córdoba'),
                                              (6, 'Misiones'),
                                              (7, 'Salta'),
                                              (8, 'Chubut'),
                                              (9, 'Santa Cruz'),
                                              (10, 'Catamarca');

INSERT INTO `costos` (id_origen, id_destino, costo) VALUES
                                                        (1, 2, 2),
                                                        (1, 3, 3),
                                                        (2, 3, 5),
                                                        (2, 4, 10),
                                                        (1, 4, 11),
                                                        (4, 5, 5),
                                                        (2, 5, 14),
                                                        (6, 7, 32),
                                                        (8, 9, 11),
                                                        (10, 7, 5),
                                                        (3, 8, 10),
                                                        (5, 8, 30),
                                                        (10, 5, 5),
                                                        (4, 6, 6);


