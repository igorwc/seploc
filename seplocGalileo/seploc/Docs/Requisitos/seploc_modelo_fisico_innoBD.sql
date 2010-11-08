CREATE DATABASE  IF NOT EXISTS `seploc2` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `seploc2`;
-- MySQL dump 10.13  Distrib 5.1.40, for Win32 (ia32)
--
-- Host: 127.0.0.1    Database: seploc2
-- ------------------------------------------------------
-- Server version	5.1.41

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tbl_parametroprog`
--

DROP TABLE IF EXISTS `tbl_parametroprog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_parametroprog` (
  `vcrCodParametro` varchar(100) NOT NULL DEFAULT '',
  `vcrDescricao` varchar(200) NOT NULL DEFAULT '',
  `vcrValor` varchar(200) NOT NULL DEFAULT '',
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`vcrCodParametro`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_parametroprog`
--

LOCK TABLES `tbl_parametroprog` WRITE;
/*!40000 ALTER TABLE `tbl_parametroprog` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_parametroprog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_entrega`
--

DROP TABLE IF EXISTS `tbl_entrega`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_entrega` (
  `intCodEnt` int(4) NOT NULL DEFAULT '0',
  `vcrLocal` varchar(20) NOT NULL DEFAULT '',
  `dblPreco` double DEFAULT NULL,
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intCodEnt`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_entrega`
--

LOCK TABLES `tbl_entrega` WRITE;
/*!40000 ALTER TABLE `tbl_entrega` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_entrega` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_cobrador`
--

DROP TABLE IF EXISTS `tbl_cobrador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_cobrador` (
  `intCodCobr` int(2) NOT NULL DEFAULT '0',
  `vcrNome` varchar(60) NOT NULL DEFAULT '',
  `vcrFoneCon` varchar(20) DEFAULT NULL,
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intCodCobr`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_cobrador`
--

LOCK TABLES `tbl_cobrador` WRITE;
/*!40000 ALTER TABLE `tbl_cobrador` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_cobrador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_menu`
--

DROP TABLE IF EXISTS `tbl_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_menu` (
  `intMenu` int(2) NOT NULL,
  `vcrMenu` varchar(100) NOT NULL DEFAULT '',
  `vcrAcao` varchar(100) DEFAULT NULL,
  `intMenupai` int(2) DEFAULT NULL,
  `chrHabilitado` char(1) NOT NULL DEFAULT 'S',
  `intNivelX` int(2) NOT NULL,
  `intNivelY` int(2) NOT NULL,
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intMenu`),
  KEY `menu_pai_fk` (`intMenupai`),
  CONSTRAINT `menu_pai_fk` FOREIGN KEY (`intMenupai`) REFERENCES `tbl_menu` (`intMenu`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_menu`
--

LOCK TABLES `tbl_menu` WRITE;
/*!40000 ALTER TABLE `tbl_menu` DISABLE KEYS */;
INSERT INTO `tbl_menu` VALUES (1,'Seploc','#',NULL,'S',1,0,'2010-07-01 00:02:14'),(2,'Cadastros','#',NULL,'S',2,0,'2010-07-01 00:02:14'),(3,'Atendimento','#',NULL,'S',3,0,'2010-07-01 00:02:14'),(4,'Relatórios','#',NULL,'S',4,0,'2010-07-01 00:02:14'),(5,'Sair','#',NULL,'S',5,0,'2010-07-01 00:02:14'),(6,'Papeis','#',2,'S',0,1,'2010-07-01 00:02:14'),(7,'Projetos','#',2,'S',0,2,'2010-07-01 00:02:14'),(8,'Clientes','#',2,'S',0,3,'2010-07-01 00:02:14'),(9,'Entregas','#',2,'S',0,4,'2010-07-01 00:02:14'),(10,'Opcionais','#',2,'S',0,5,'2010-07-01 00:02:14'),(11,'Entregadores','#',2,'S',0,6,'2010-07-01 00:02:14'),(12,'Usuários','#',2,'S',0,7,'2010-07-01 00:02:14'),(13,'Balcão','#',3,'S',0,1,'2010-07-01 00:02:14'),(14,'Cliente','#',3,'S',0,2,'2010-07-01 00:02:14'),(15,'Grupos Acesso','#',1,'S',0,1,'2010-07-01 00:02:14'),(16,'Associar Usuário','#',1,'S',0,2,'2010-07-01 00:02:14');
/*!40000 ALTER TABLE `tbl_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_notaiss`
--

DROP TABLE IF EXISTS `tbl_notaiss`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_notaiss` (
  `intNumNota` int(8) NOT NULL DEFAULT '0',
  `intValorIss` int(2) NOT NULL DEFAULT '0',
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intNumNota`),
  CONSTRAINT `NOTAISS_NOTAF_FK` FOREIGN KEY (`intNumNota`) REFERENCES `tbl_notafiscal` (`intNumNota`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_notaiss`
--

LOCK TABLES `tbl_notaiss` WRITE;
/*!40000 ALTER TABLE `tbl_notaiss` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_notaiss` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_clientes`
--

DROP TABLE IF EXISTS `tbl_clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_clientes` (
  `vcrCnpj` varchar(20) NOT NULL DEFAULT '',
  `vcrRazao` varchar(60) NOT NULL DEFAULT '',
  `vcrCpf` varchar(20) DEFAULT NULL,
  `vcrEnder` varchar(60) DEFAULT NULL,
  `vcrBairro` varchar(30) DEFAULT NULL,
  `vcrCidade` varchar(30) DEFAULT NULL,
  `vcrEstado` char(1) DEFAULT NULL,
  `vcrCep` varchar(10) DEFAULT NULL,
  `vcrEmail` varchar(80) DEFAULT NULL,
  `vcrInscricao` varchar(20) DEFAULT NULL,
  `intBalcao` int(1) DEFAULT NULL,
  `vcrFantasia` varchar(60) NOT NULL DEFAULT '',
  `txtobs` text,
  `vcrMapa` varchar(100) DEFAULT NULL,
  `intEntregaPadrao` int(4) DEFAULT NULL,
  `intPapelPadrao` int(4) DEFAULT NULL,
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`vcrCnpj`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_clientes`
--

LOCK TABLES `tbl_clientes` WRITE;
/*!40000 ALTER TABLE `tbl_clientes` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_notafiscal`
--

DROP TABLE IF EXISTS `tbl_notafiscal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_notafiscal` (
  `intNumNota` int(8) NOT NULL DEFAULT '0',
  `vcrCnpj` varchar(20) NOT NULL DEFAULT '',
  `datData` date DEFAULT NULL,
  `dblValor` double NOT NULL DEFAULT '0',
  `dblSubValor` double NOT NULL DEFAULT '0',
  `intCodEnt` int(4) DEFAULT NULL,
  `vcrDescricao` varchar(200) DEFAULT NULL,
  `vcrObservacao` varchar(50) DEFAULT NULL,
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intNumNota`),
  KEY `NOTAFISCAL_CLIE_FK` (`vcrCnpj`),
  KEY `NOTAFISCAL_ENT_FK` (`intCodEnt`),
  CONSTRAINT `NOTAFISCAL_CLIE_FK` FOREIGN KEY (`vcrCnpj`) REFERENCES `tbl_clientes` (`vcrCnpj`),
  CONSTRAINT `NOTAFISCAL_ENT_FK` FOREIGN KEY (`intCodEnt`) REFERENCES `tbl_entrega` (`intCodEnt`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_notafiscal`
--

LOCK TABLES `tbl_notafiscal` WRITE;
/*!40000 ALTER TABLE `tbl_notafiscal` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_notafiscal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_statuscobranca`
--

DROP TABLE IF EXISTS `tbl_statuscobranca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_statuscobranca` (
  `intNumreq` int(8) NOT NULL DEFAULT '0',
  `intCodCobr` int(2) NOT NULL DEFAULT '0',
  `datDataCobr` date DEFAULT NULL,
  `datDataPag` date DEFAULT NULL,
  `horCobranca` time DEFAULT NULL,
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intCodCobr`,`intNumreq`),
  KEY `STSCOBR_REQSERV_FK` (`intNumreq`),
  KEY `STSCOBR_COBR_FK` (`intCodCobr`),
  CONSTRAINT `STSCOBR_COBR_FK` FOREIGN KEY (`intCodCobr`) REFERENCES `tbl_cobrador` (`intCodCobr`),
  CONSTRAINT `STSCOBR_REQSERV_FK` FOREIGN KEY (`intNumreq`) REFERENCES `tbl_reqserv` (`intNumreq`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_statuscobranca`
--

LOCK TABLES `tbl_statuscobranca` WRITE;
/*!40000 ALTER TABLE `tbl_statuscobranca` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_statuscobranca` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_reqservusuario`
--

DROP TABLE IF EXISTS `tbl_reqservusuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_reqservusuario` (
  `vcrLogin` varchar(30) NOT NULL DEFAULT '',
  `intNumReq` int(8) NOT NULL DEFAULT '0',
  `datData` date DEFAULT NULL,
  `vcrLoginAlter` varchar(30) DEFAULT NULL,
  `datdataAlter` date DEFAULT NULL,
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`vcrLogin`,`intNumReq`),
  KEY `REQSERUS_REQSERV_FK` (`intNumReq`),
  KEY `REQSERUS_USUA_FK` (`vcrLogin`),
  CONSTRAINT `REQSERUS_USUA_FK` FOREIGN KEY (`vcrLogin`) REFERENCES `tbl_usuario` (`vcrLogin`),
  CONSTRAINT `REQSERUS_REQSERV_FK` FOREIGN KEY (`intNumReq`) REFERENCES `tbl_reqserv` (`intNumreq`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_reqservusuario`
--

LOCK TABLES `tbl_reqservusuario` WRITE;
/*!40000 ALTER TABLE `tbl_reqservusuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_reqservusuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_grupomenu`
--

DROP TABLE IF EXISTS `tbl_grupomenu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_grupomenu` (
  `intGrupo` int(2) NOT NULL,
  `intMenu` int(2) NOT NULL,
  `chrEscrita` char(1) NOT NULL DEFAULT 'S',
  PRIMARY KEY (`intGrupo`,`intMenu`),
  KEY `GRMN_MENU_FK` (`intMenu`),
  KEY `GRMN_GRUPO_FK` (`intGrupo`),
  CONSTRAINT `GRMN_GRUPO_FK00` FOREIGN KEY (`intGrupo`) REFERENCES `tbl_grupo` (`intGrupo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `GRMN_MENU_FK00` FOREIGN KEY (`intMenu`) REFERENCES `tbl_menu` (`intMenu`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_grupomenu`
--

LOCK TABLES `tbl_grupomenu` WRITE;
/*!40000 ALTER TABLE `tbl_grupomenu` DISABLE KEYS */;
INSERT INTO `tbl_grupomenu` VALUES (1,1,'S'),(1,2,'S'),(1,3,'S'),(1,4,'S'),(1,5,'S'),(1,6,'S'),(1,7,'S'),(1,8,'S'),(1,9,'S'),(1,10,'S'),(1,11,'S'),(1,12,'S'),(1,13,'S'),(1,14,'S'),(1,15,'S'),(1,16,'S'),(2,1,'S'),(2,2,'S'),(2,3,'S'),(2,4,'S'),(2,5,'S'),(2,6,'S'),(2,7,'S'),(2,8,'S'),(2,9,'S'),(2,10,'S'),(2,11,'S'),(2,12,'S'),(2,13,'S'),(2,14,'S'),(2,15,'S'),(2,16,'S');
/*!40000 ALTER TABLE `tbl_grupomenu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_reqserv`
--

DROP TABLE IF EXISTS `tbl_reqserv`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_reqserv` (
  `intNumreq` int(8) NOT NULL DEFAULT '0',
  `datData` date DEFAULT NULL,
  `vcrCnpj` varchar(20) NOT NULL DEFAULT '',
  `intCodProj` int(8) NOT NULL DEFAULT '0',
  `intCodEnt` int(4) DEFAULT NULL,
  `dblValorTotal` double DEFAULT NULL,
  `intStatus` int(1) DEFAULT NULL,
  `intCodCobr` int(2) DEFAULT NULL,
  `intVisivelNf` int(1) DEFAULT NULL,
  `intVisivelReq` int(1) DEFAULT NULL,
  `dblValorEnt` double DEFAULT NULL,
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intNumreq`),
  KEY `REQSERV_CLIE_FK` (`vcrCnpj`),
  KEY `REQSERV_PROJ_FK` (`intCodProj`),
  KEY `REQSERV_ENTREGA_FK` (`intCodEnt`),
  KEY `REQSERV_COBR_FK` (`intCodCobr`),
  CONSTRAINT `REQSERV_CLIE_FK` FOREIGN KEY (`vcrCnpj`) REFERENCES `tbl_clientes` (`vcrCnpj`),
  CONSTRAINT `REQSERV_COBR_FK` FOREIGN KEY (`intCodCobr`) REFERENCES `tbl_cobrador` (`intCodCobr`),
  CONSTRAINT `REQSERV_ENTREGA_FK` FOREIGN KEY (`intCodEnt`) REFERENCES `tbl_entrega` (`intCodEnt`),
  CONSTRAINT `REQSERV_PROJ_FK` FOREIGN KEY (`intCodProj`) REFERENCES `tbl_projetos` (`intCodProj`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_reqserv`
--

LOCK TABLES `tbl_reqserv` WRITE;
/*!40000 ALTER TABLE `tbl_reqserv` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_reqserv` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_papel`
--

DROP TABLE IF EXISTS `tbl_papel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_papel` (
  `intCodPap` int(4) NOT NULL DEFAULT '0',
  `vcrNome` varchar(20) NOT NULL DEFAULT '',
  `dblImpMono` double DEFAULT NULL,
  `dblImpColor` double DEFAULT NULL,
  `dblImpShade` double DEFAULT NULL,
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intCodPap`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_papel`
--

LOCK TABLES `tbl_papel` WRITE;
/*!40000 ALTER TABLE `tbl_papel` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_papel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_usuario`
--

DROP TABLE IF EXISTS `tbl_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_usuario` (
  `vcrLogin` varchar(30) NOT NULL DEFAULT '',
  `vcrPassword` varchar(30) DEFAULT NULL,
  `vcrCpf` varchar(20) NOT NULL DEFAULT '',
  `intPermissao` int(1) NOT NULL DEFAULT '0',
  `vcrIpMaquina` varchar(20) DEFAULT NULL,
  `intGrupo` int(1) DEFAULT NULL,
  `vcrNome` varchar(100) DEFAULT NULL,
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`vcrLogin`),
  KEY `USUA_GRP_FK` (`intGrupo`),
  CONSTRAINT `USUA_GRP_FK` FOREIGN KEY (`intGrupo`) REFERENCES `tbl_grupo` (`intGrupo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_usuario`
--

LOCK TABLES `tbl_usuario` WRITE;
/*!40000 ALTER TABLE `tbl_usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_fonecli`
--

DROP TABLE IF EXISTS `tbl_fonecli`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_fonecli` (
  `vcrCnpj` varchar(20) NOT NULL DEFAULT '',
  `vcrFoneRes` varchar(60) DEFAULT NULL,
  `vcrFoneCom` varchar(60) DEFAULT NULL,
  `vcrFax` varchar(60) DEFAULT NULL,
  `vcrCelular` varchar(30) DEFAULT NULL,
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`vcrCnpj`),
  CONSTRAINT `FONECLI_CLIE_FK` FOREIGN KEY (`vcrCnpj`) REFERENCES `tbl_clientes` (`vcrCnpj`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_fonecli`
--

LOCK TABLES `tbl_fonecli` WRITE;
/*!40000 ALTER TABLE `tbl_fonecli` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_fonecli` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_saidamotoqueiro`
--

DROP TABLE IF EXISTS `tbl_saidamotoqueiro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_saidamotoqueiro` (
  `intNumSaida` int(8) NOT NULL DEFAULT '0',
  `intCodCobr` int(2) DEFAULT NULL,
  `intNumReq` int(8) DEFAULT NULL,
  `datDataCobr` date DEFAULT NULL,
  `datDataPag` date DEFAULT NULL,
  `horCobranca` time DEFAULT NULL,
  `horPagamen` time DEFAULT NULL,
  `vcrCliente` varchar(150) DEFAULT NULL,
  `vcrObs` varchar(200) DEFAULT NULL,
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intNumSaida`),
  KEY `MOTOQ_REQSERV_FK` (`intNumReq`),
  KEY `MOTOQ_COBR_FK` (`intCodCobr`),
  CONSTRAINT `MOTOQ_COBR_FK` FOREIGN KEY (`intCodCobr`) REFERENCES `tbl_cobrador` (`intCodCobr`),
  CONSTRAINT `MOTOQ_REQSERV_FK` FOREIGN KEY (`intNumReq`) REFERENCES `tbl_reqserv` (`intNumreq`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_saidamotoqueiro`
--

LOCK TABLES `tbl_saidamotoqueiro` WRITE;
/*!40000 ALTER TABLE `tbl_saidamotoqueiro` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_saidamotoqueiro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_opcionaisreqserv`
--

DROP TABLE IF EXISTS `tbl_opcionaisreqserv`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_opcionaisreqserv` (
  `vcrCod` varchar(4) NOT NULL DEFAULT '',
  `vcrNomeItem` varchar(40) DEFAULT NULL,
  `dblValorItem` double NOT NULL DEFAULT '0',
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`vcrCod`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_opcionaisreqserv`
--

LOCK TABLES `tbl_opcionaisreqserv` WRITE;
/*!40000 ALTER TABLE `tbl_opcionaisreqserv` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_opcionaisreqserv` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_linhareq`
--

DROP TABLE IF EXISTS `tbl_linhareq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_linhareq` (
  `intNumreq` int(8) NOT NULL DEFAULT '0',
  `intNumLin` int(2) NOT NULL DEFAULT '0',
  `vcrNomeArq` varchar(60) NOT NULL DEFAULT '',
  `dblFormato` double DEFAULT NULL,
  `dblDimensao` double DEFAULT NULL,
  `intCodPap` int(4) NOT NULL DEFAULT '0',
  `vcrImpressao` varchar(30) DEFAULT NULL,
  `intQuant` int(2) DEFAULT NULL,
  `dblValorUnit` double DEFAULT NULL,
  `dblValorSubUnit` double DEFAULT NULL,
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intNumLin`,`intNumreq`),
  KEY `LINREQ_REQSRV_FK` (`intNumreq`),
  KEY `LINREQ_PAP_FK` (`intCodPap`),
  CONSTRAINT `LINREQ_PAP_FK` FOREIGN KEY (`intCodPap`) REFERENCES `tbl_papel` (`intCodPap`),
  CONSTRAINT `LINREQ_REQSRV_FK` FOREIGN KEY (`intNumreq`) REFERENCES `tbl_reqserv` (`intNumreq`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_linhareq`
--

LOCK TABLES `tbl_linhareq` WRITE;
/*!40000 ALTER TABLE `tbl_linhareq` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_linhareq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_projetos`
--

DROP TABLE IF EXISTS `tbl_projetos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_projetos` (
  `vcrCnpj` varchar(20) NOT NULL DEFAULT '',
  `intCodProj` int(8) NOT NULL DEFAULT '0',
  `vcrProjeto` varchar(100) DEFAULT NULL,
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intCodProj`),
  KEY `PROJ_CLIE_FK` (`vcrCnpj`),
  CONSTRAINT `PROJ_CLIE_FK` FOREIGN KEY (`vcrCnpj`) REFERENCES `tbl_clientes` (`vcrCnpj`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_projetos`
--

LOCK TABLES `tbl_projetos` WRITE;
/*!40000 ALTER TABLE `tbl_projetos` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_projetos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_reqservopcionais`
--

DROP TABLE IF EXISTS `tbl_reqservopcionais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_reqservopcionais` (
  `intNumReq` int(8) NOT NULL,
  `intCodOp` int(8) NOT NULL DEFAULT '0',
  `vcrCod` varchar(4) NOT NULL DEFAULT '',
  `intQuant` int(4) NOT NULL DEFAULT '0',
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intNumReq`,`intCodOp`),
  KEY `vcrCod` (`vcrCod`),
  KEY `tbl_reqservopcionais_ibfk_1` (`intNumReq`),
  CONSTRAINT `tbl_reqservopcionais_ibfk_1` FOREIGN KEY (`intNumReq`) REFERENCES `tbl_reqserv` (`intNumreq`),
  CONSTRAINT `tbl_reqservopcionais_ibfk_2` FOREIGN KEY (`vcrCod`) REFERENCES `tbl_opcionaisreqserv` (`vcrCod`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_reqservopcionais`
--

LOCK TABLES `tbl_reqservopcionais` WRITE;
/*!40000 ALTER TABLE `tbl_reqservopcionais` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_reqservopcionais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_reqnotafiscal`
--

DROP TABLE IF EXISTS `tbl_reqnotafiscal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_reqnotafiscal` (
  `intNumNota` int(8) NOT NULL DEFAULT '0',
  `intNumReq` int(8) NOT NULL DEFAULT '0',
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intNumNota`,`intNumReq`),
  KEY `REQNOTFISCAL_REQSERV_FK` (`intNumReq`),
  KEY `REQNOTFISCAL_NOTF_FK` (`intNumNota`),
  CONSTRAINT `REQNOTFISCAL_NOTF_FK` FOREIGN KEY (`intNumNota`) REFERENCES `tbl_notafiscal` (`intNumNota`),
  CONSTRAINT `REQNOTFISCAL_REQSERV_FK` FOREIGN KEY (`intNumReq`) REFERENCES `tbl_reqserv` (`intNumreq`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_reqnotafiscal`
--

LOCK TABLES `tbl_reqnotafiscal` WRITE;
/*!40000 ALTER TABLE `tbl_reqnotafiscal` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_reqnotafiscal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_grupo`
--

DROP TABLE IF EXISTS `tbl_grupo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_grupo` (
  `intGrupo` int(2) NOT NULL DEFAULT '0',
  `vcrGrupo` varchar(100) NOT NULL DEFAULT '',
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intGrupo`,`vcrGrupo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_grupo`
--

LOCK TABLES `tbl_grupo` WRITE;
/*!40000 ALTER TABLE `tbl_grupo` DISABLE KEYS */;
INSERT INTO `tbl_grupo` VALUES (1,'Sistema','2010-07-01 00:10:59'),(2,'Gestor','2010-07-01 00:10:59');
/*!40000 ALTER TABLE `tbl_grupo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2010-06-30 22:33:39
