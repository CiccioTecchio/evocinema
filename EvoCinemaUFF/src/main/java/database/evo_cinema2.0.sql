-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema evo_cinema
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema evo_cinema
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `evo_cinema` DEFAULT CHARACTER SET utf8 ;
USE `evo_cinema` ;

-- -----------------------------------------------------
-- Table `evo_cinema`.`opera`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `evo_cinema`.`Opera` (
  `idOpera` INT(11) NOT NULL AUTO_INCREMENT,
  `tipo` ENUM('FILM', 'TEATRO', 'ALTRO') NOT NULL,
  `titolo` VARCHAR(255) NOT NULL,
  `locandina` VARCHAR(255) NULL DEFAULT NULL,
  `regia` VARCHAR(255) NULL DEFAULT NULL,
  `cast` VARCHAR(255) NULL DEFAULT NULL,
  `genere` VARCHAR(255) NULL DEFAULT NULL,
  `durata` TIME NULL DEFAULT NULL,
  `data_uscita` DATE NULL DEFAULT NULL,
  `visto_censura` ENUM('T', 'VM14', 'VM16', 'VM18') NULL DEFAULT 'T',
  `distribuzione` VARCHAR(255) NULL DEFAULT NULL,
  `produzione` VARCHAR(255) NULL DEFAULT NULL,
  `trama` TEXT NULL DEFAULT NULL,
  `trailer` VARCHAR(400) NULL DEFAULT NULL,
  `eliminato` ENUM('TRUE', 'FALSE') DEFAULT 'FALSE',
  PRIMARY KEY (`idOpera`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `evo_cinema`.`sala`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `evo_cinema`.`Sala` (
  `id_sala` INT(11) NOT NULL AUTO_INCREMENT,
  `numero_posti` SMALLINT(4) UNSIGNED NOT NULL,
  `configurazione_posti` VARCHAR(900) NOT NULL,
  PRIMARY KEY (`id_sala`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `evo_cinema`.`spettacolo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `evo_cinema`.`Spettacolo` (
  `idSpettacolo` INT(11) NOT NULL AUTO_INCREMENT,
  `id_sala` INT(11) NOT NULL,
  `idOpera` INT(11) NULL DEFAULT NULL,
  `titolo` VARCHAR(255) NOT NULL,
  `data_inizio` DATE NOT NULL,
  `data_fine` DATE NOT NULL,
  `prezzo` DECIMAL(5,2) NOT NULL,
  `ora_inizio` TIME NOT NULL,
  `ora_fine` TIME NOT NULL,
  `matrice_posti` TEXT NOT NULL,
  PRIMARY KEY (`idSpettacolo`),
  INDEX `fk_id_sala_idx` (`id_sala` ASC),
  INDEX `fk_idOpera_idx` (`idOpera` ASC),
  CONSTRAINT `fk_idOpera`
    FOREIGN KEY (`idOpera`)
    REFERENCES `evo_cinema`.`opera` (`idOpera`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_id_sala`
    FOREIGN KEY (`id_sala`)
    REFERENCES `evo_cinema`.`sala` (`id_sala`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `evo_cinema`.`sconto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `evo_cinema`.`Sconto` (
  `idSconto` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `tipo` ENUM('PERCENTUALE', 'FISSO') NOT NULL DEFAULT 'PERCENTUALE',
  `percentuale` TINYINT(3) NULL DEFAULT NULL,
  `prezzo` DECIMAL(5,2) NULL DEFAULT NULL,
  `verificabile` ENUM('TRUE', 'FALSE') NOT NULL,
  `disponibile` ENUM('TRUE', 'FALSE') NOT NULL,
  `tipologia` ENUM('GIORNO_SETTIMANA', 'GENERE', 'FILM', 'SPETTACOLO', 'DATA', 'ETA', 'SESSO', 'ALTRO') NOT NULL,
  `parametro_tipologia` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`idSconto`),
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `evo_cinema`.`utente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `evo_cinema`.`Utente` (
  `email` VARCHAR(50) NOT NULL,
  `nome_utente` VARCHAR(45) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `ruolo` ENUM('UTENTE', 'OPERATORE', 'GESTORE') CHARACTER SET 'big5' NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `cognome` VARCHAR(45) NOT NULL,
  `data_nascita` DATE NOT NULL,
  `sesso` ENUM('M', 'F') NOT NULL DEFAULT 'M',
  `cellulare` CHAR(14) NULL DEFAULT NULL,
  `città` VARCHAR(45) NULL DEFAULT NULL,
  `indirizzo` VARCHAR(100) NULL DEFAULT NULL,
  `saldo` DECIMAL(5,2) NULL DEFAULT '0.00',
  `dataIscrizione` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`email`),
  UNIQUE INDEX `nome_utente_UNIQUE` (`nome_utente` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `evo_cinema`.`operazione`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `evo_cinema`.`Operazione` (
  `id_Operazione` INT(11) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(50) NOT NULL,
  `idSpettacolo` INT(11) NOT NULL,
  `posto` INT(11) NOT NULL,
  `offset` INT(11) NOT NULL,
  `idSala` INT(11) NOT NULL,
  `prenotato` ENUM('TRUE', 'FALSE') NOT NULL,
  `acquistato` ENUM('TRUE', 'FALSE') NOT NULL,
  `prezzo_finale` DECIMAL(5,2) NULL DEFAULT NULL,
  `data` DATE NOT NULL,
  `sconto_applicato` INT(11) NULL,
  PRIMARY KEY (`id_Operazione`),
  INDEX `FK_id Spettacolo_idx` (`idSpettacolo` ASC),
  INDEX `FK_sconto_idx` (`sconto_applicato` ASC),
  INDEX `FK_utente` (`email` ASC),
  CONSTRAINT `FK_id Spettacolo`
    FOREIGN KEY (`idSpettacolo`)
    REFERENCES `evo_cinema`.`spettacolo` (`idSpettacolo`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `FK_sconto`
    FOREIGN KEY (`sconto_applicato`)
    REFERENCES `evo_cinema`.`sconto` (`idSconto`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `FK_utente`
    FOREIGN KEY (`email`)
    REFERENCES `evo_cinema`.`utente` (`email`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `evo_cinema`.`recensioni`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `evo_cinema`.`Recensioni` (
  `email` VARCHAR(50) NOT NULL,
  `id_opera` INT(11) NOT NULL,
  `valutazione` DECIMAL(2,1) NOT NULL,
  `testo` TEXT NULL DEFAULT NULL,
  `data_recensione` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`email`, `id_opera`),
  INDEX `fk_id_opera_idx` (`id_opera` ASC),
  CONSTRAINT `fk_email`
    FOREIGN KEY (`email`)
    REFERENCES `evo_cinema`.`utente` (`email`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_id_opera`
    FOREIGN KEY (`id_opera`)
    REFERENCES `evo_cinema`.`opera` (`idOpera`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
