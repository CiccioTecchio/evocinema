-- MySQL Script generated by MySQL Workbench
-- Thu Dec 14 18:35:02 2017
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema evo_cinema
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `evo_cinema` ;

-- -----------------------------------------------------
-- Schema evo_cinema
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `evo_cinema` DEFAULT CHARACTER SET utf8 ;
USE `evo_cinema` ;

-- -----------------------------------------------------
-- Table `evo_cinema`.`Utente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `evo_cinema`.`Utente` ;

CREATE TABLE IF NOT EXISTS `evo_cinema`.`Utente` (
  `email` VARCHAR(50) NOT NULL,
  `nome_utente` VARCHAR(45) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `ruolo` ENUM('utente', 'operatore', 'gestore') CHARACTER SET 'big5' NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `cognome` VARCHAR(45) NOT NULL,
  `data_nascita` DATE NOT NULL,
  `sesso` ENUM('M', 'F') NOT NULL DEFAULT 'M',
  `cellulare` CHAR(14) NULL,
  `città` VARCHAR(45) NULL,
  `indirizzo` VARCHAR(100) NULL,
  `saldo` DECIMAL(5,2) NULL DEFAULT 0,
  PRIMARY KEY (`email`),
  UNIQUE INDEX `nome_utente_UNIQUE` (`nome_utente` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `evo_cinema`.`Sala`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `evo_cinema`.`Sala` ;

CREATE TABLE IF NOT EXISTS `evo_cinema`.`Sala` (
  `id_sala` INT NOT NULL AUTO_INCREMENT,
  `numero_posti` SMALLINT(4) UNSIGNED NOT NULL,
  `configurazione_posti` VARCHAR(900) NOT NULL,
  PRIMARY KEY (`id_sala`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `evo_cinema`.`Sconto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `evo_cinema`.`Sconto` ;

CREATE TABLE IF NOT EXISTS `evo_cinema`.`Sconto` (
  `idSconto` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `tipo` ENUM('percentuale', 'fisso') NOT NULL DEFAULT 'percentuale',
  `percentuale` TINYINT(3) NULL,
  `prezzo` DECIMAL(5,2) NULL,
  `verificabile` ENUM('true', 'false') NOT NULL,
  `disponibile` ENUM('true', 'false') NOT NULL,
  `tipologia` ENUM('cat_Persone', 'giorno_settimana', 'termine', 'data') NOT NULL,
  `parametro_tipologia` VARCHAR(255) NULL,
  PRIMARY KEY (`idSconto`),
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `evo_cinema`.`Opera`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `evo_cinema`.`Opera` ;

CREATE TABLE IF NOT EXISTS `evo_cinema`.`Opera` (
  `idOpera` INT NOT NULL AUTO_INCREMENT,
  `tipo` ENUM('film', 'teatro', 'altro') NOT NULL,
  `titolo` VARCHAR(255) NOT NULL,
  `locandina` VARCHAR(255) NULL,
  `regia` VARCHAR(255) NULL,
  `cast` VARCHAR(255) NULL,
  `genere` VARCHAR(255) NULL,
  `durata` TIME(0) NULL,
  `data_uscita` DATE NULL,
  `visto_censura` ENUM('T', 'VM14', 'VM16', 'VM18') NULL DEFAULT 'T',
  `distribuzione` VARCHAR(255) NULL,
  `produzione` VARCHAR(255) NULL,
  `trama` TEXT NULL,
  `trailer` VARCHAR(155) NULL,
  PRIMARY KEY (`idOpera`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `evo_cinema`.`Recensioni`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `evo_cinema`.`Recensioni` ;

CREATE TABLE IF NOT EXISTS `evo_cinema`.`Recensioni` (
  `email` VARCHAR(50) NOT NULL,
  `id_opera` INT NOT NULL,
  `valutazione` DECIMAL(2,1) NOT NULL,
  `testo` TEXT NULL,
  `data_recensione` DATE NULL,
  PRIMARY KEY (`email`, `id_opera`),
  INDEX `fk_id_opera_idx` (`id_opera` ASC),
  CONSTRAINT `fk_email`
    FOREIGN KEY (`email`)
    REFERENCES `evo_cinema`.`Utente` (`email`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_id_opera`
    FOREIGN KEY (`id_opera`)
    REFERENCES `evo_cinema`.`Opera` (`idOpera`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `evo_cinema`.`Spettacolo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `evo_cinema`.`Spettacolo` ;

CREATE TABLE IF NOT EXISTS `evo_cinema`.`Spettacolo` (
  `idSpettacolo` INT NOT NULL AUTO_INCREMENT,
  `id_sala` INT NOT NULL,
  `idOpera` INT NULL,
  `data_inizio` DATE NOT NULL,
  `data_fine` DATE NOT NULL,
  `prezzo` DECIMAL(5,2) NOT NULL,
  `ora_inizio` TIME(0) NOT NULL,
  `ora_fine` TIME(0) NOT NULL,
  `matrice_posti` TEXT NOT NULL,
  PRIMARY KEY (`idSpettacolo`),
  INDEX `fk_id_sala_idx` (`id_sala` ASC),
  INDEX `fk_idOpera_idx` (`idOpera` ASC),
  CONSTRAINT `fk_id_sala`
    FOREIGN KEY (`id_sala`)
    REFERENCES `evo_cinema`.`Sala` (`id_sala`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_idOpera`
    FOREIGN KEY (`idOpera`)
    REFERENCES `evo_cinema`.`Opera` (`idOpera`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `evo_cinema`.`Applicabile`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `evo_cinema`.`Applicabile` ;

CREATE TABLE IF NOT EXISTS `evo_cinema`.`Applicabile` (
  `idSconto` INT NOT NULL,
  `idSpettacolo` INT NOT NULL,
  PRIMARY KEY (`idSconto`, `idSpettacolo`),
  INDEX `fk_idSpettacolo_idx` (`idSpettacolo` ASC),
  INDEX `fk_idSconto_idx` (`idSconto` ASC),
  CONSTRAINT `fk_idSconto`
    FOREIGN KEY (`idSconto`)
    REFERENCES `evo_cinema`.`Sconto` (`idSconto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_idSpettacolo`
    FOREIGN KEY (`idSpettacolo`)
    REFERENCES `evo_cinema`.`Spettacolo` (`idSpettacolo`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `evo_cinema`.`Posto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `evo_cinema`.`Posto` ;

CREATE TABLE IF NOT EXISTS `evo_cinema`.`Posto` (
  `riga` TINYINT(2) NOT NULL,
  `colonna` TINYINT(2) NOT NULL,
  `idSala` INT NOT NULL,
  `stato` ENUM('Occupato', 'NonDisponibile', 'Prenotato', 'Disponibile') NOT NULL,
  PRIMARY KEY (`riga`, `colonna`, `idSala`),
  INDEX `fk_idSala` (`idSala` ASC),
  CONSTRAINT `fk1_idSala`
    FOREIGN KEY (`idSala`)
    REFERENCES `evo_cinema`.`Sala` (`id_sala`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `evo_cinema`.`Operazione`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `evo_cinema`.`Operazione` ;

CREATE TABLE IF NOT EXISTS `evo_cinema`.`Operazione` (
  `id_Operazione` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(50) NOT NULL,
  `idSpettacolo` INT NOT NULL,
  `posto_colonna` TINYINT(2) NOT NULL,
  `posto_riga` TINYINT(2) NOT NULL,
  `idSala` INT NOT NULL,
  `prenotato` ENUM('TRUE', 'FALSE') NOT NULL,
  `acquistato` ENUM('TRUE', 'FALSE') NOT NULL,
  `prezzo_finale` DECIMAL(5,2) NULL,
  `data` DATE NOT NULL,
  `sconto_applicato` VARCHAR(45) NULL,
  PRIMARY KEY (`id_Operazione`),
  INDEX `FK_id Spettacolo_idx` (`idSpettacolo` ASC),
  INDEX `FK_posto_idx` (`posto_colonna` ASC, `posto_riga` ASC, `idSala` ASC),
  INDEX `FK_sconto_idx` (`sconto_applicato` ASC),
  CONSTRAINT `FK_utente`
    FOREIGN KEY (`email`)
    REFERENCES `evo_cinema`.`Utente` (`email`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `FK_id Spettacolo`
    FOREIGN KEY (`idSpettacolo`)
    REFERENCES `evo_cinema`.`Spettacolo` (`idSpettacolo`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `FK_posto`
    FOREIGN KEY ( `posto_riga` , `posto_colonna` , `idSala`)
    REFERENCES `evo_cinema`.`Posto` (`riga` ,`colonna`, `idSala`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `FK_sconto`
    FOREIGN KEY (`sconto_applicato`)
    REFERENCES `evo_cinema`.`Sconto` (`nome`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;