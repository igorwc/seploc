SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;

SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';



CREATE SCHEMA IF NOT EXISTS `seploc2` ;

SHOW WARNINGS;

USE `seploc2` ;



-- -----------------------------------------------------

-- Table `seploc2`.`tbl_entrega`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `seploc2`.`tbl_entrega` ;



SHOW WARNINGS;

CREATE  TABLE IF NOT EXISTS `seploc2`.`tbl_entrega` (

  `intCodEnt` INT(4) NOT NULL DEFAULT '0' ,

  `vcrLocal` VARCHAR(20) NOT NULL DEFAULT '' ,

  `dblPreco` DOUBLE NULL DEFAULT NULL ,

  `tspVersao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ,

  PRIMARY KEY (`intCodEnt`) )

ENGINE = InnoDB

DEFAULT CHARACTER SET = latin1;



SHOW WARNINGS;



-- -----------------------------------------------------

-- Table `seploc2`.`tbl_papel`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `seploc2`.`tbl_papel` ;



SHOW WARNINGS;

CREATE  TABLE IF NOT EXISTS `seploc2`.`tbl_papel` (

  `intCodPap` INT(4) NOT NULL DEFAULT '0' ,

  `vcrNome` VARCHAR(20) NOT NULL DEFAULT '' ,

  `dblImpMono` DOUBLE NULL DEFAULT NULL ,

  `dblImpColor` DOUBLE NULL DEFAULT NULL ,

  `dblImpShade` DOUBLE NULL DEFAULT NULL ,

  `tspVersao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ,

  PRIMARY KEY (`intCodPap`) )

ENGINE = InnoDB

DEFAULT CHARACTER SET = latin1;



SHOW WARNINGS;



-- -----------------------------------------------------

-- Table `seploc2`.`tbl_clientes`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `seploc2`.`tbl_clientes` ;



SHOW WARNINGS;

CREATE  TABLE IF NOT EXISTS `seploc2`.`tbl_clientes` (

  `intClienteId` INT NOT NULL ,

  `vcrCnpj` VARCHAR(20) NOT NULL ,

  `vcrRazao` VARCHAR(60) NOT NULL ,

  `vcrCpf` VARCHAR(20) NULL DEFAULT NULL ,

  `vcrEnder` VARCHAR(60) NULL DEFAULT NULL ,

  `vcrBairro` VARCHAR(30) NULL DEFAULT NULL ,

  `vcrCidade` VARCHAR(30) NULL DEFAULT NULL ,

  `vcrEstado` CHAR(2) NULL DEFAULT NULL ,

  `vcrCep` VARCHAR(10) NULL DEFAULT NULL ,

  `vcrEmail` VARCHAR(80) NULL DEFAULT NULL ,

  `vcrInscricao` VARCHAR(20) NULL DEFAULT NULL ,

  `intBalcao` INT(1) NULL DEFAULT NULL ,

  `vcrFantasia` VARCHAR(60) NOT NULL ,

  `txtobs` TEXT NULL DEFAULT NULL ,

  `vcrMapa` VARCHAR(100) NULL DEFAULT NULL ,

  `intEntregaPadrao` INT(4) NULL DEFAULT NULL ,

  `intPapelPadrao` INT(4) NULL DEFAULT NULL ,

  `tspVersao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ,

  PRIMARY KEY (`intClienteId`) ,

  CONSTRAINT `fk_tbl_clientes_tbl_entrega1`

    FOREIGN KEY (`intEntregaPadrao` )

    REFERENCES `seploc2`.`tbl_entrega` (`intCodEnt` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION,

  CONSTRAINT `fk_tbl_clientes_tbl_papel1`

    FOREIGN KEY (`intPapelPadrao` )

    REFERENCES `seploc2`.`tbl_papel` (`intCodPap` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION)

ENGINE = InnoDB

DEFAULT CHARACTER SET = latin1;



SHOW WARNINGS;

CREATE INDEX `fk_tbl_clientes_tbl_entrega1` ON `seploc2`.`tbl_clientes` (`intEntregaPadrao` ASC) ;



SHOW WARNINGS;

CREATE INDEX `fk_tbl_clientes_tbl_papel1` ON `seploc2`.`tbl_clientes` (`intPapelPadrao` ASC) ;



SHOW WARNINGS;



-- -----------------------------------------------------

-- Table `seploc2`.`tbl_cobrador`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `seploc2`.`tbl_cobrador` ;



SHOW WARNINGS;

CREATE  TABLE IF NOT EXISTS `seploc2`.`tbl_cobrador` (

  `intCodCobr` INT(2) NOT NULL DEFAULT '0' ,

  `vcrNome` VARCHAR(60) NOT NULL DEFAULT '' ,

  `vcrFoneCon` VARCHAR(20) NULL DEFAULT NULL ,
  
  `chrAtivo` CHAR(1) NOT NULL DEFAULT 'S' ,  

  `tspVersao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ,

  PRIMARY KEY (`intCodCobr`) )

ENGINE = InnoDB

DEFAULT CHARACTER SET = latin1;



SHOW WARNINGS;



-- -----------------------------------------------------

-- Table `seploc2`.`tbl_fonecli`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `seploc2`.`tbl_fonecli` ;



SHOW WARNINGS;

CREATE  TABLE IF NOT EXISTS `seploc2`.`tbl_fonecli` (

  `intClienteId` INT NOT NULL ,

  `vcrFoneRes` VARCHAR(60) NULL DEFAULT NULL ,

  `vcrFoneCom` VARCHAR(60) NULL DEFAULT NULL ,

  `vcrFax` VARCHAR(60) NULL DEFAULT NULL ,

  `vcrCelular` VARCHAR(30) NULL DEFAULT NULL ,

  `tspVersao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ,

  PRIMARY KEY (`intClienteId`) ,

  CONSTRAINT `FONECLI_CLIE_FK`

    FOREIGN KEY (`intClienteId` )

    REFERENCES `seploc2`.`tbl_clientes` (`intClienteId` ))

ENGINE = InnoDB

DEFAULT CHARACTER SET = latin1;



SHOW WARNINGS;



-- -----------------------------------------------------

-- Table `seploc2`.`tbl_grupo`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `seploc2`.`tbl_grupo` ;



SHOW WARNINGS;

CREATE  TABLE IF NOT EXISTS `seploc2`.`tbl_grupo` (

  `intGrupo` INT(2) NOT NULL DEFAULT '0' ,

  `vcrGrupo` VARCHAR(100) NOT NULL DEFAULT '' ,

  `tspVersao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ,

  PRIMARY KEY (`intGrupo`) )

ENGINE = InnoDB

DEFAULT CHARACTER SET = latin1;



SHOW WARNINGS;



-- -----------------------------------------------------

-- Table `seploc2`.`tbl_projetos`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `seploc2`.`tbl_projetos` ;



SHOW WARNINGS;

CREATE  TABLE IF NOT EXISTS `seploc2`.`tbl_projetos` (

  `intCodProj` INT(8) NOT NULL DEFAULT 0 ,

  `intClienteId` INT NOT NULL ,

  `vcrProjeto` VARCHAR(100) NULL DEFAULT NULL ,

  `tspVersao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ,

  PRIMARY KEY (`intCodProj`) ,

  CONSTRAINT `PROJ_CLIE_FK`

    FOREIGN KEY (`intClienteId` )

    REFERENCES `seploc2`.`tbl_clientes` (`intClienteId` ))

ENGINE = InnoDB

DEFAULT CHARACTER SET = latin1;



SHOW WARNINGS;

CREATE INDEX `PROJ_CLIE_FK` ON `seploc2`.`tbl_projetos` (`intClienteId` ASC) ;



SHOW WARNINGS;



-- -----------------------------------------------------

-- Table `seploc2`.`tbl_reqserv`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `seploc2`.`tbl_reqserv` ;



SHOW WARNINGS;

CREATE  TABLE IF NOT EXISTS `seploc2`.`tbl_reqserv` (

  `intNumreq` INT(8) NOT NULL DEFAULT '0' ,

  `datData` DATE NULL DEFAULT NULL ,

  `intCodProj` INT(8) NOT NULL DEFAULT '0' ,

  `intCodEnt` INT(4) NULL DEFAULT NULL ,

  `dblValorTotal` DOUBLE NULL DEFAULT NULL ,

  `intStatus` INT(1) NULL DEFAULT NULL ,

  `intVisivelNf` INT(1) NULL DEFAULT NULL ,

  `intVisivelReq` INT(1) NULL DEFAULT NULL ,

  `dblValorEnt` DOUBLE NULL DEFAULT NULL ,

  `tspVersao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ,

  PRIMARY KEY (`intNumreq`) ,

  CONSTRAINT `REQSERV_PROJ_FK`

    FOREIGN KEY (`intCodProj` )

    REFERENCES `seploc2`.`tbl_projetos` (`intCodProj` ),

  CONSTRAINT `REQSERV_ENTREGA_FK`

    FOREIGN KEY (`intCodEnt` )

    REFERENCES `seploc2`.`tbl_entrega` (`intCodEnt` ))

ENGINE = InnoDB

DEFAULT CHARACTER SET = latin1;



SHOW WARNINGS;

CREATE INDEX `REQSERV_PROJ_FK` ON `seploc2`.`tbl_reqserv` (`intCodProj` ASC) ;



SHOW WARNINGS;

CREATE INDEX `REQSERV_ENTREGA_FK` ON `seploc2`.`tbl_reqserv` (`intCodEnt` ASC) ;



SHOW WARNINGS;



-- -----------------------------------------------------

-- Table `seploc2`.`tbl_linhareq`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `seploc2`.`tbl_linhareq` ;



SHOW WARNINGS;

CREATE  TABLE IF NOT EXISTS `seploc2`.`tbl_linhareq` (

  `intNumreq` INT(8) NOT NULL DEFAULT '0' ,

  `intNumLin` INT(2) NOT NULL DEFAULT '0' ,

  `vcrNomeArq` VARCHAR(60) NOT NULL DEFAULT '' ,

  `dblFormato` DOUBLE NULL DEFAULT NULL ,

  `dblDimensao` DOUBLE NULL DEFAULT NULL ,

  `intCodPap` INT(4) NOT NULL DEFAULT '0' ,

  `vcrImpressao` VARCHAR(30) NULL DEFAULT NULL ,

  `intQuant` INT(2) NULL DEFAULT NULL ,

  `dblValorUnit` DOUBLE NULL DEFAULT NULL ,

  `dblValorSubUnit` DOUBLE NULL DEFAULT NULL ,

  `tspVersao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ,

  PRIMARY KEY (`intNumLin`, `intNumreq`) ,

  CONSTRAINT `LINREQ_REQSRV_FK`

    FOREIGN KEY (`intNumreq` )

    REFERENCES `seploc2`.`tbl_reqserv` (`intNumreq` ),

  CONSTRAINT `LINREQ_PAP_FK`

    FOREIGN KEY (`intCodPap` )

    REFERENCES `seploc2`.`tbl_papel` (`intCodPap` ))

ENGINE = InnoDB

DEFAULT CHARACTER SET = latin1;



SHOW WARNINGS;

CREATE INDEX `LINREQ_REQSRV_FK` ON `seploc2`.`tbl_linhareq` (`intNumreq` ASC) ;



SHOW WARNINGS;

CREATE INDEX `LINREQ_PAP_FK` ON `seploc2`.`tbl_linhareq` (`intCodPap` ASC) ;



SHOW WARNINGS;



-- -----------------------------------------------------

-- Table `seploc2`.`tbl_menu`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `seploc2`.`tbl_menu` ;



SHOW WARNINGS;

CREATE  TABLE IF NOT EXISTS `seploc2`.`tbl_menu` (

  `intMenu` INT(2) NOT NULL ,

  `vcrMenu` VARCHAR(100) NOT NULL DEFAULT '' ,

  `vcrAcao` VARCHAR(100) NULL DEFAULT NULL ,

  `intMenupai` INT(2) NULL DEFAULT NULL ,

  `chrHabilitado` CHAR NOT NULL DEFAULT 'S' ,

  `intNivelX` INT(2) NOT NULL ,

  `intNivelY` INT(2) NOT NULL ,

  `tspVersao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ,

  PRIMARY KEY (`intMenu`) ,

  CONSTRAINT `menu_pai_fk`

    FOREIGN KEY (`intMenupai` )

    REFERENCES `seploc2`.`tbl_menu` (`intMenu` ))

ENGINE = InnoDB

DEFAULT CHARACTER SET = latin1

COLLATE = latin1_swedish_ci;



SHOW WARNINGS;

CREATE INDEX `menu_pai_fk` ON `seploc2`.`tbl_menu` (`intMenupai` ASC) ;



SHOW WARNINGS;



-- -----------------------------------------------------

-- Table `seploc2`.`tbl_opcionaisreqserv`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `seploc2`.`tbl_opcionaisreqserv` ;



SHOW WARNINGS;

CREATE  TABLE IF NOT EXISTS `seploc2`.`tbl_opcionaisreqserv` (

  `intCod` INT(8) NOT NULL DEFAULT 0 ,

  `vcrNomeItem` VARCHAR(40) NULL DEFAULT NULL ,

  `dblValorItem` DOUBLE NOT NULL DEFAULT 0 ,

  `tspVersao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ,

  PRIMARY KEY (`intCod`) )

ENGINE = InnoDB

DEFAULT CHARACTER SET = latin1

COLLATE = latin1_swedish_ci;



SHOW WARNINGS;



-- -----------------------------------------------------

-- Table `seploc2`.`tbl_parametroprog`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `seploc2`.`tbl_parametroprog` ;



SHOW WARNINGS;

CREATE  TABLE IF NOT EXISTS `seploc2`.`tbl_parametroprog` (

  `vcrCodParametro` VARCHAR(100) NOT NULL DEFAULT '' ,

  `vcrDescricao` VARCHAR(200) NOT NULL DEFAULT '' ,

  `vcrValor` VARCHAR(200) NOT NULL DEFAULT '' ,

  `tspVersao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ,

  PRIMARY KEY (`vcrCodParametro`) )

ENGINE = InnoDB

DEFAULT CHARACTER SET = latin1;



SHOW WARNINGS;



-- -----------------------------------------------------

-- Table `seploc2`.`tbl_reqservopcionais`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `seploc2`.`tbl_reqservopcionais` ;



SHOW WARNINGS;

CREATE  TABLE IF NOT EXISTS `seploc2`.`tbl_reqservopcionais` (

  `intNumReq` INT(8) NOT NULL ,

  `intCodOp` INT(8) NOT NULL DEFAULT 0 ,

  `intQuant` INT(4) NOT NULL DEFAULT 0 ,

  `tspVersao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ,

  PRIMARY KEY (`intNumReq`, `intCodOp`) ,

  CONSTRAINT `tbl_reqservopcionais_ibfk_1`

    FOREIGN KEY (`intNumReq` )

    REFERENCES `seploc2`.`tbl_reqserv` (`intNumreq` ),

  CONSTRAINT `tbl_reqservopcionais_ibfk_2`

    FOREIGN KEY (`intCodOp` )

    REFERENCES `seploc2`.`tbl_opcionaisreqserv` (`intCod` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION)

ENGINE = InnoDB

DEFAULT CHARACTER SET = latin1;



SHOW WARNINGS;

CREATE INDEX `tbl_reqservopcionais_ibfk_1` ON `seploc2`.`tbl_reqservopcionais` (`intNumReq` ASC) ;



SHOW WARNINGS;

CREATE INDEX `tbl_reqservopcionais_ibfk_2` ON `seploc2`.`tbl_reqservopcionais` (`intCodOp` ASC) ;



SHOW WARNINGS;



-- -----------------------------------------------------

-- Table `seploc2`.`tbl_usuario`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `seploc2`.`tbl_usuario` ;



SHOW WARNINGS;

CREATE  TABLE IF NOT EXISTS `seploc2`.`tbl_usuario` (

  `vcrLogin` VARCHAR(30) NOT NULL DEFAULT '' ,

  `vcrPassword` VARCHAR(30) NULL DEFAULT NULL ,

  `vcrCpf` VARCHAR(20) NOT NULL DEFAULT '' ,

  `intPermissao` INT(1) NOT NULL DEFAULT '0' ,

  `vcrIpMaquina` VARCHAR(20) NULL DEFAULT NULL ,

  `intGrupo` INT(1) NULL DEFAULT NULL ,

  `vcrNome` VARCHAR(100) NULL DEFAULT NULL ,

  `tspVersao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ,

  PRIMARY KEY (`vcrLogin`) ,

  CONSTRAINT `USUA_GRP_FK`

    FOREIGN KEY (`intGrupo` )

    REFERENCES `seploc2`.`tbl_grupo` (`intGrupo` ))

ENGINE = InnoDB

DEFAULT CHARACTER SET = latin1;



SHOW WARNINGS;

CREATE INDEX `USUA_GRP_FK` ON `seploc2`.`tbl_usuario` (`intGrupo` ASC) ;



SHOW WARNINGS;



-- -----------------------------------------------------

-- Table `seploc2`.`tbl_reqservusuario`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `seploc2`.`tbl_reqservusuario` ;



SHOW WARNINGS;

CREATE  TABLE IF NOT EXISTS `seploc2`.`tbl_reqservusuario` (

  `vcrLogin` VARCHAR(30) NOT NULL DEFAULT '' ,

  `intNumReq` INT(8) NOT NULL DEFAULT '0' ,

  `datData` DATE NULL DEFAULT NULL ,

  `vcrLoginAlter` VARCHAR(30) NULL DEFAULT NULL ,

  `datdataAlter` DATE NULL DEFAULT NULL ,

  `tspVersao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ,

  PRIMARY KEY (`vcrLogin`, `intNumReq`) ,

  CONSTRAINT `REQSERUS_REQSERV_FK`

    FOREIGN KEY (`intNumReq` )

    REFERENCES `seploc2`.`tbl_reqserv` (`intNumreq` ),

  CONSTRAINT `REQSERUS_USUA_FK`

    FOREIGN KEY (`vcrLogin` )

    REFERENCES `seploc2`.`tbl_usuario` (`vcrLogin` ))

ENGINE = InnoDB

DEFAULT CHARACTER SET = latin1;



SHOW WARNINGS;

CREATE INDEX `REQSERUS_REQSERV_FK` ON `seploc2`.`tbl_reqservusuario` (`intNumReq` ASC) ;



SHOW WARNINGS;

CREATE INDEX `REQSERUS_USUA_FK` ON `seploc2`.`tbl_reqservusuario` (`vcrLogin` ASC) ;



SHOW WARNINGS;



-- -----------------------------------------------------

-- Table `seploc2`.`tbl_saidamotoqueiro`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `seploc2`.`tbl_saidamotoqueiro` ;



SHOW WARNINGS;

CREATE  TABLE IF NOT EXISTS `seploc2`.`tbl_saidamotoqueiro` (

  `intNumSaida` INT(8) NOT NULL DEFAULT '0' ,

  `intCodCobr` INT(2) NULL DEFAULT NULL ,

  `intNumReq` INT(8) NULL DEFAULT NULL ,

  `datDataCobr` DATE NULL DEFAULT NULL ,

  `datDataPag` DATE NULL DEFAULT NULL ,

  `horCobranca` TIME NULL DEFAULT NULL ,

  `horPagamen` TIME NULL DEFAULT NULL ,

  `vcrCliente` VARCHAR(150) NULL DEFAULT NULL ,

  `vcrObs` VARCHAR(200) NULL DEFAULT NULL ,

  `tspVersao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ,

  PRIMARY KEY (`intNumSaida`) ,

  CONSTRAINT `MOTOQ_REQSERV_FK`

    FOREIGN KEY (`intNumReq` )

    REFERENCES `seploc2`.`tbl_reqserv` (`intNumreq` ),

  CONSTRAINT `MOTOQ_COBR_FK`

    FOREIGN KEY (`intCodCobr` )

    REFERENCES `seploc2`.`tbl_cobrador` (`intCodCobr` ))

ENGINE = InnoDB

DEFAULT CHARACTER SET = latin1;



SHOW WARNINGS;

CREATE INDEX `MOTOQ_REQSERV_FK` ON `seploc2`.`tbl_saidamotoqueiro` (`intNumReq` ASC) ;



SHOW WARNINGS;

CREATE INDEX `MOTOQ_COBR_FK` ON `seploc2`.`tbl_saidamotoqueiro` (`intCodCobr` ASC) ;



SHOW WARNINGS;



-- -----------------------------------------------------

-- Table `seploc2`.`tbl_statuscobranca`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `seploc2`.`tbl_statuscobranca` ;



SHOW WARNINGS;

CREATE  TABLE IF NOT EXISTS `seploc2`.`tbl_statuscobranca` (

  `intNumreq` INT(8) NOT NULL DEFAULT '0' ,

  `intCodCobr` INT(2) NOT NULL DEFAULT '0' ,

  `datDataCobr` DATE NULL DEFAULT NULL ,

  `datDataPag` DATE NULL DEFAULT NULL ,

  `horCobranca` TIME NULL DEFAULT NULL ,

  `tspVersao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ,

  PRIMARY KEY (`intCodCobr`, `intNumreq`) ,

  CONSTRAINT `STSCOBR_REQSERV_FK`

    FOREIGN KEY (`intNumreq` )

    REFERENCES `seploc2`.`tbl_reqserv` (`intNumreq` ),

  CONSTRAINT `STSCOBR_COBR_FK`

    FOREIGN KEY (`intCodCobr` )

    REFERENCES `seploc2`.`tbl_cobrador` (`intCodCobr` ))

ENGINE = InnoDB

DEFAULT CHARACTER SET = latin1;



SHOW WARNINGS;

CREATE INDEX `STSCOBR_REQSERV_FK` ON `seploc2`.`tbl_statuscobranca` (`intNumreq` ASC) ;



SHOW WARNINGS;

CREATE INDEX `STSCOBR_COBR_FK` ON `seploc2`.`tbl_statuscobranca` (`intCodCobr` ASC) ;



SHOW WARNINGS;



-- -----------------------------------------------------

-- Table `seploc2`.`tbl_grupomenu`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `seploc2`.`tbl_grupomenu` ;



SHOW WARNINGS;

CREATE  TABLE IF NOT EXISTS `seploc2`.`tbl_grupomenu` (

  `intGrupo` INT(2) NULL ,

  `intMenu` INT(2) NOT NULL ,

  `chrEscrita` CHAR NOT NULL DEFAULT 'S' ,

  `tspVersao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ,

  PRIMARY KEY (`intGrupo`) ,

  CONSTRAINT `GRMN_MENU_FK00`

    FOREIGN KEY (`intMenu` )

    REFERENCES `seploc2`.`tbl_menu` (`intMenu` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION,

  CONSTRAINT `GRMN_GRUPO_FK00`

    FOREIGN KEY (`intGrupo` )

    REFERENCES `seploc2`.`tbl_grupo` (`intGrupo` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION)

ENGINE = InnoDB

DEFAULT CHARACTER SET = latin1

COLLATE = latin1_swedish_ci;



SHOW WARNINGS;

CREATE INDEX `GRMN_MENU_FK` ON `seploc2`.`tbl_grupomenu` (`intMenu` ASC) ;



SHOW WARNINGS;

CREATE INDEX `GRMN_GRUPO_FK` ON `seploc2`.`tbl_grupomenu` (`intGrupo` ASC) ;



SHOW WARNINGS;



-- -----------------------------------------------------

-- Table `seploc2`.`id_gen`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `seploc2`.`id_gen` ;



SHOW WARNINGS;

CREATE  TABLE IF NOT EXISTS `seploc2`.`id_gen` (

  `NOME_ID` VARCHAR(100) NOT NULL ,

  `VAL_ID` INT(11) NOT NULL )

ENGINE = InnoDB

DEFAULT CHARACTER SET = latin1

COLLATE = latin1_swedish_ci;



SHOW WARNINGS;





SET SQL_MODE=@OLD_SQL_MODE;

SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;

SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

