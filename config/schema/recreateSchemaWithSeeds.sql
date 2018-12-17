-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: 192.168.100.2    Database: group152
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `auth_token`
--

DROP TABLE IF EXISTS `auth_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `auth_token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `token` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `token_UNIQUE` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_token`
--

LOCK TABLES `auth_token` WRITE;
/*!40000 ALTER TABLE `auth_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `auth_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `combi`
--

DROP TABLE IF EXISTS `combi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `combi` (
  `id_combi` bigint(20) NOT NULL AUTO_INCREMENT,
  `patente` varchar(7) NOT NULL,
  `modelo` varchar(45) DEFAULT NULL,
  `marca` varchar(45) DEFAULT NULL,
  `asientos` int(11) NOT NULL,
  PRIMARY KEY (`id_combi`),
  UNIQUE KEY `patente_UNIQUE` (`patente`),
  UNIQUE KEY `id_combi_UNIQUE` (`id_combi`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `combi`
--

LOCK TABLES `combi` WRITE;
/*!40000 ALTER TABLE `combi` DISABLE KEYS */;
INSERT INTO `combi` VALUES (1,'DTK-177','Boxer','Peugeot',40),(2,'DTK-200','Boxer','Renault',40),(3,'DTK-300','Boxer','Citroen',40);
/*!40000 ALTER TABLE `combi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parada`
--

DROP TABLE IF EXISTS `parada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `parada` (
  `id_parada` bigint(20) NOT NULL AUTO_INCREMENT,
  `calle` varchar(45) NOT NULL,
  `numero` varchar(8) NOT NULL,
  `ciudad` varchar(45) NOT NULL,
  PRIMARY KEY (`id_parada`),
  UNIQUE KEY `id_parada_UNIQUE` (`id_parada`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parada`
--

LOCK TABLES `parada` WRITE;
/*!40000 ALTER TABLE `parada` DISABLE KEYS */;
INSERT INTO `parada` VALUES (1,'Av 7','1100','La Plata'),(2,'Av 13','1100','La Plata'),(3,'Av 9 de Julio','0','Buenos Aires');
/*!40000 ALTER TABLE `parada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pasaje`
--

DROP TABLE IF EXISTS `pasaje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `pasaje` (
  `id_pasaje` bigint(20) NOT NULL AUTO_INCREMENT,
  `pasajero_id_pasajero` bigint(20) NOT NULL,
  `viaje_id_viaje` bigint(20) NOT NULL,
  `used` tinyint(1) NOT NULL,
  `parada_id_parada` bigint(20) NOT NULL,
  PRIMARY KEY (`id_pasaje`),
  UNIQUE KEY `id_pasaje_UNIQUE` (`id_pasaje`),
  KEY `fk_pasaje_pasajero_idx` (`pasajero_id_pasajero`),
  KEY `fk_pasaje_viaje1_idx` (`viaje_id_viaje`),
  KEY `fk_pasaje_parada1_idx` (`parada_id_parada`),
  CONSTRAINT `fk_pasaje_parada1` FOREIGN KEY (`parada_id_parada`) REFERENCES `parada` (`id_parada`),
  CONSTRAINT `fk_pasaje_pasajero` FOREIGN KEY (`pasajero_id_pasajero`) REFERENCES `pasajero` (`id_pasajero`),
  CONSTRAINT `fk_pasaje_viaje1` FOREIGN KEY (`viaje_id_viaje`) REFERENCES `viaje` (`id_viaje`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pasaje`
--

LOCK TABLES `pasaje` WRITE;
/*!40000 ALTER TABLE `pasaje` DISABLE KEYS */;
INSERT INTO `pasaje` VALUES (1,1,1,0,1);
/*!40000 ALTER TABLE `pasaje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pasajero`
--

DROP TABLE IF EXISTS `pasajero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `pasajero` (
  `id_pasajero` bigint(20) NOT NULL AUTO_INCREMENT,
  `dni` varchar(10) NOT NULL,
  `nombre` varchar(30) DEFAULT NULL,
  `apellido` varchar(30) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `salt` varchar(255) NOT NULL,
  `password_hash` varchar(255) NOT NULL,
  `isTerminal` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id_pasajero`),
  UNIQUE KEY `id_pasajero_UNIQUE` (`id_pasajero`),
  UNIQUE KEY `dni_UNIQUE` (`dni`),
  UNIQUE KEY `salt_UNIQUE` (`salt`),
  UNIQUE KEY `password_hash_UNIQUE` (`password_hash`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pasajero`
--

LOCK TABLES `pasajero` WRITE;
/*!40000 ALTER TABLE `pasajero` DISABLE KEYS */;
INSERT INTO `pasajero` VALUES (1,'38468109','Brandon','Russell','asd@asd.com','$2a$10$7tXQ5KS5.Zzo8Y49LGpGO.','$2a$10$7tXQ5KS5.Zzo8Y49LGpGO.rbfQkeegtFF1l7H5EIPSvXHjWB2jkwO',0),(2,'0','RaspBerry Pi','3 Model B','terminalone@combiLP.com','$2a$10$q2u5E2wDfVyavUWgm3taC.','$2a$10$q2u5E2wDfVyavUWgm3taC.1l51JV3.StEuMMgFa1YdgSlkjfYYBGm',1);
/*!40000 ALTER TABLE `pasajero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `viaje`
--

DROP TABLE IF EXISTS `viaje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `viaje` (
  `id_viaje` bigint(20) NOT NULL AUTO_INCREMENT,
  `destino` varchar(45) NOT NULL,
  `origen` varchar(45) NOT NULL,
  `fecha` date NOT NULL,
  `combi_id_combi` bigint(20) NOT NULL,
  PRIMARY KEY (`id_viaje`,`combi_id_combi`),
  UNIQUE KEY `id_viaje_UNIQUE` (`id_viaje`),
  KEY `fk_viaje_combi1_idx` (`combi_id_combi`),
  CONSTRAINT `fk_viaje_combi1` FOREIGN KEY (`combi_id_combi`) REFERENCES `combi` (`id_combi`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `viaje`
--

LOCK TABLES `viaje` WRITE;
/*!40000 ALTER TABLE `viaje` DISABLE KEYS */;
INSERT INTO `viaje` VALUES (1,'Buenos Aires','La Plata','2018-12-15',1),(2,'Buenos Aires','La Plata','2018-12-15',2),(3,'Buenos Aires','La Plata','2018-12-16',3),(4,'La Plata','Buenos Aires','2018-12-17',1);
/*!40000 ALTER TABLE `viaje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `viaje_has_parada`
--

DROP TABLE IF EXISTS `viaje_has_parada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `viaje_has_parada` (
  `viaje_id_viaje` bigint(20) NOT NULL,
  `parada_id_parada` bigint(20) NOT NULL,
  `hora` time NOT NULL,
  PRIMARY KEY (`viaje_id_viaje`,`parada_id_parada`,`hora`),
  KEY `fk_viaje_has_parada_parada1_idx` (`parada_id_parada`),
  KEY `fk_viaje_has_parada_viaje1_idx` (`viaje_id_viaje`),
  CONSTRAINT `fk_viaje_has_parada_parada1` FOREIGN KEY (`parada_id_parada`) REFERENCES `parada` (`id_parada`),
  CONSTRAINT `fk_viaje_has_parada_viaje1` FOREIGN KEY (`viaje_id_viaje`) REFERENCES `viaje` (`id_viaje`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `viaje_has_parada`
--

LOCK TABLES `viaje_has_parada` WRITE;
/*!40000 ALTER TABLE `viaje_has_parada` DISABLE KEYS */;
INSERT INTO `viaje_has_parada` VALUES (1,1,'00:00:00'),(2,1,'10:00:00'),(4,1,'18:00:00'),(1,2,'00:15:00'),(2,2,'10:15:00'),(3,2,'14:00:00'),(4,2,'17:30:00'),(1,3,'01:00:00'),(3,3,'14:30:00'),(4,3,'17:00:00');
/*!40000 ALTER TABLE `viaje_has_parada` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-16 23:09:16
