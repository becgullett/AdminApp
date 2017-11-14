-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`card`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`card` (
  `id_card` INT(11) NOT NULL,
  `id_user` INT(11) NOT NULL,
  `id_order` INT(11) NOT NULL,
  `card_number` VARCHAR(200) NOT NULL,
  `card_type` VARCHAR(2) NOT NULL,
  `card_expiration` VARCHAR(45) NOT NULL,
  `card_name` VARCHAR(150) NOT NULL,
  `card_address` VARCHAR(250) NOT NULL,
  PRIMARY KEY (`id_card`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`order` (
  `id_order` INT(11) NOT NULL,
  `id_user` INT(11) NOT NULL,
  `shipping_address` VARCHAR(250) NULL DEFAULT NULL,
  `shipping_name` VARCHAR(150) NULL DEFAULT NULL,
  `has_shipped` TINYINT(1) NULL DEFAULT '0',
  `created_on` DATETIME NULL DEFAULT NULL,
  `created_by` VARCHAR(45) NULL DEFAULT NULL,
  `is_active` TINYINT(1) NULL DEFAULT '1',
  `shipping_type` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id_order`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`orderline`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`orderline` (
  `id_orderline` INT(11) NOT NULL,
  `id_product` INT(11) NOT NULL,
  `id_order` INT(11) NOT NULL,
  `quantity` INT(11) NOT NULL,
  `is_active` TINYINT(1) NULL DEFAULT '1',
  PRIMARY KEY (`id_orderline`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`product` (
  `id_product` INT(11) NOT NULL AUTO_INCREMENT,
  `artist` VARCHAR(150) NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `genre` VARCHAR(45) NOT NULL,
  `price` FLOAT NOT NULL,
  `stock` INT(11) NOT NULL,
  `url` VARCHAR(45) NOT NULL,
  `is_active` TINYINT(1) NULL DEFAULT '1',
  `created_on` DATETIME NULL DEFAULT NULL,
  `created_by` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id_product`))
ENGINE = InnoDB
AUTO_INCREMENT = 27
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`role` (
  `id_role` INT(11) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_role`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`shipping_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`shipping_type` (
  `id_shipping_type` INT(11) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `price` FLOAT NOT NULL,
  PRIMARY KEY (`id_shipping_type`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`user` (
  `id_user` INT(11) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(75) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `is_active` TINYINT(1) NULL DEFAULT '1',
  `created_on` DATETIME NULL DEFAULT NULL,
  `created_by` VARCHAR(45) NULL DEFAULT NULL,
  `is_locked` TINYINT(1) NULL DEFAULT '0',
  `security` INT(11) NOT NULL,
  PRIMARY KEY (`id_user`))
ENGINE = InnoDB
AUTO_INCREMENT = 23
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
