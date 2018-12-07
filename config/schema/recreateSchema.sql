-- MySQL Script generated by MySQL Workbench
-- jue 06 dic 2018 19:24:08 -03
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema group152
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `group152` ;

-- -----------------------------------------------------
-- Schema group152
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `group152` DEFAULT CHARACTER SET utf8 ;
USE `group152` ;

-- -----------------------------------------------------
-- Table `group152`.`auth_token`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `group152`.`auth_token` ;

CREATE TABLE IF NOT EXISTS `group152`.`auth_token` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(50) NOT NULL,
  `token` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  UNIQUE INDEX `token_UNIQUE` (`token` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `group152`.`combi`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `group152`.`combi` ;

CREATE TABLE IF NOT EXISTS `group152`.`combi` (
  `id_combi` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `patente` VARCHAR(7) NOT NULL,
  `modelo` VARCHAR(45) NULL DEFAULT NULL,
  `marca` VARCHAR(45) NULL DEFAULT NULL,
  `asientos` INT(11) NOT NULL,
  PRIMARY KEY (`id_combi`),
  UNIQUE INDEX `patente_UNIQUE` (`patente` ASC),
  UNIQUE INDEX `id_combi_UNIQUE` (`id_combi` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 18
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `group152`.`parada`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `group152`.`parada` ;

CREATE TABLE IF NOT EXISTS `group152`.`parada` (
  `id_parada` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `calle` VARCHAR(45) NOT NULL,
  `numero` VARCHAR(8) NOT NULL,
  `ciudad` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_parada`),
  UNIQUE INDEX `id_parada_UNIQUE` (`id_parada` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 18
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `group152`.`pasajero`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `group152`.`pasajero` ;

CREATE TABLE IF NOT EXISTS `group152`.`pasajero` (
  `id_pasajero` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `dni` VARCHAR(10) NOT NULL,
  `nombre` VARCHAR(30) NULL DEFAULT NULL,
  `apellido` VARCHAR(30) NULL DEFAULT NULL,
  `email` VARCHAR(50) NOT NULL,
  `salt` VARCHAR(255) NOT NULL,
  `password_hash` VARCHAR(255) NOT NULL,
  `isTerminal` TINYINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id_pasajero`),
  UNIQUE INDEX `id_pasajero_UNIQUE` (`id_pasajero` ASC),
  UNIQUE INDEX `dni_UNIQUE` (`dni` ASC),
  UNIQUE INDEX `salt_UNIQUE` (`salt` ASC),
  UNIQUE INDEX `password_hash_UNIQUE` (`password_hash` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 26
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `group152`.`viaje`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `group152`.`viaje` ;

CREATE TABLE IF NOT EXISTS `group152`.`viaje` (
  `id_viaje` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `destino` VARCHAR(45) NOT NULL,
  `origen` VARCHAR(45) NOT NULL,
  `fecha` DATETIME NOT NULL,
  `combi_id_combi` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id_viaje`, `combi_id_combi`),
  UNIQUE INDEX `id_viaje_UNIQUE` (`id_viaje` ASC),
  UNIQUE INDEX `fecha_UNIQUE` (`fecha` ASC),
  INDEX `fk_viaje_combi1_idx` (`combi_id_combi` ASC),
  CONSTRAINT `fk_viaje_combi1`
    FOREIGN KEY (`combi_id_combi`)
    REFERENCES `group152`.`combi` (`id_combi`))
ENGINE = InnoDB
AUTO_INCREMENT = 22
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `group152`.`pasaje`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `group152`.`pasaje` ;

CREATE TABLE IF NOT EXISTS `group152`.`pasaje` (
  `id_pasaje` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `pasajero_id_pasajero` BIGINT(20) NOT NULL,
  `viaje_id_viaje` BIGINT(20) NOT NULL,
  `used` TINYINT(1) NOT NULL,
  `parada_id_parada` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id_pasaje`),
  UNIQUE INDEX `id_pasaje_UNIQUE` (`id_pasaje` ASC),
  INDEX `fk_pasaje_pasajero_idx` (`pasajero_id_pasajero` ASC),
  INDEX `fk_pasaje_viaje1_idx` (`viaje_id_viaje` ASC),
  INDEX `fk_pasaje_parada1_idx` (`parada_id_parada` ASC),
  CONSTRAINT `fk_pasaje_parada1`
    FOREIGN KEY (`parada_id_parada`)
    REFERENCES `group152`.`parada` (`id_parada`),
  CONSTRAINT `fk_pasaje_pasajero`
    FOREIGN KEY (`pasajero_id_pasajero`)
    REFERENCES `group152`.`pasajero` (`id_pasajero`),
  CONSTRAINT `fk_pasaje_viaje1`
    FOREIGN KEY (`viaje_id_viaje`)
    REFERENCES `group152`.`viaje` (`id_viaje`))
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `group152`.`viaje_has_parada`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `group152`.`viaje_has_parada` ;

CREATE TABLE IF NOT EXISTS `group152`.`viaje_has_parada` (
  `viaje_id_viaje` BIGINT(20) NOT NULL,
  `parada_id_parada` BIGINT(20) NOT NULL,
  `hora` TIME NOT NULL,
  PRIMARY KEY (`viaje_id_viaje`, `parada_id_parada`, `hora`),
  INDEX `fk_viaje_has_parada_parada1_idx` (`parada_id_parada` ASC),
  INDEX `fk_viaje_has_parada_viaje1_idx` (`viaje_id_viaje` ASC),
  CONSTRAINT `fk_viaje_has_parada_parada1`
    FOREIGN KEY (`parada_id_parada`)
    REFERENCES `group152`.`parada` (`id_parada`),
  CONSTRAINT `fk_viaje_has_parada_viaje1`
    FOREIGN KEY (`viaje_id_viaje`)
    REFERENCES `group152`.`viaje` (`id_viaje`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
