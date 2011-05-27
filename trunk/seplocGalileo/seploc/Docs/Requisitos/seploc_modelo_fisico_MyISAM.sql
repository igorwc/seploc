-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tempo de Geração: Mai 27, 2011 as 04:40 
-- Versão do Servidor: 5.5.8
-- Versão do PHP: 5.3.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Banco de Dados: `dbseploc`
--
CREATE DATABASE `dbseploc` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `dbseploc`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `id_gen`
--

CREATE TABLE IF NOT EXISTS `id_gen` (
  `NOME_ID` varchar(100) NOT NULL,
  `VAL_ID` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_bairros`
--

CREATE TABLE IF NOT EXISTS `tbl_bairros` (
  `intCod` int(11) NOT NULL AUTO_INCREMENT,
  `vcrNome` varchar(150) NOT NULL,
  `intCodCidade` int(11) NOT NULL,
  `tspVersao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intCod`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=169 ;


-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_cidades`
--

CREATE TABLE IF NOT EXISTS `tbl_cidades` (
  `intCod` int(11) NOT NULL,
  `vcrNome` varchar(100) NOT NULL,
  `intCodUF` int(11) NOT NULL,
  `tspVersao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intCod`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_clientes`
--

CREATE TABLE IF NOT EXISTS `tbl_clientes` (
  `intClienteId` int(11) NOT NULL,
  `vcrCnpj` varchar(20) DEFAULT NULL,
  `vcrRazao` varchar(60) NOT NULL,
  `vcrCpf` varchar(20) DEFAULT NULL,
  `vcrEnder` varchar(60) DEFAULT NULL,
  `vcrBairro` varchar(30) DEFAULT NULL,
  `vcrCidade` varchar(30) DEFAULT NULL,
  `vcrEstado` char(2) DEFAULT NULL,
  `vcrCep` varchar(10) DEFAULT NULL,
  `vcrEmail` varchar(80) DEFAULT NULL,
  `vcrInscricao` varchar(20) DEFAULT NULL,
  `intBalcao` int(1) DEFAULT NULL,
  `vcrFantasia` varchar(60) NOT NULL,
  `txtobs` text,
  `vcrMapa` varchar(100) DEFAULT NULL,
  `intEntregaPadrao` int(4) DEFAULT NULL,
  `intPapelPadrao` int(4) DEFAULT NULL,
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intClienteId`),
  KEY `fk_tbl_clientes_tbl_entrega1` (`intEntregaPadrao`),
  KEY `fk_tbl_clientes_tbl_papel1` (`intPapelPadrao`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_cobrador`
--

CREATE TABLE IF NOT EXISTS `tbl_cobrador` (
  `intCodCobr` int(2) NOT NULL DEFAULT '0',
  `vcrNome` varchar(60) NOT NULL DEFAULT '',
  `vcrFoneCon` varchar(20) DEFAULT NULL,
  `chrAtivo` char(1) NOT NULL DEFAULT 'S',
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intCodCobr`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_entrega`
--

CREATE TABLE IF NOT EXISTS `tbl_entrega` (
  `intCodEnt` int(4) NOT NULL DEFAULT '0',
  `vcrLocal` varchar(20) NOT NULL DEFAULT '',
  `dblPreco` double DEFAULT NULL,
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intCodEnt`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_estados`
--

CREATE TABLE IF NOT EXISTS `tbl_estados` (
  `intCod` int(11) NOT NULL AUTO_INCREMENT,
  `vcrNome` varchar(40) NOT NULL,
  `vcrSigla` varchar(2) NOT NULL,
  `tspVersao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intCod`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=28 ;


-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_fonecli`
--

CREATE TABLE IF NOT EXISTS `tbl_fonecli` (
  `intClienteId` int(11) NOT NULL,
  `vcrFoneRes` varchar(60) DEFAULT NULL,
  `vcrFoneCom` varchar(60) DEFAULT NULL,
  `vcrFax` varchar(60) DEFAULT NULL,
  `vcrCelular` varchar(30) DEFAULT NULL,
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intClienteId`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_grupo`
--

CREATE TABLE IF NOT EXISTS `tbl_grupo` (
  `intGrupo` int(2) NOT NULL DEFAULT '0',
  `vcrGrupo` varchar(100) NOT NULL DEFAULT '',
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intGrupo`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_grupomenu`
--

CREATE TABLE IF NOT EXISTS `tbl_grupomenu` (
  `intGrupo` int(2) NOT NULL DEFAULT '0',
  `intMenu` int(2) NOT NULL,
  `chrEscrita` char(1) NOT NULL DEFAULT 'S',
  `chrVisivel` char(1) NOT NULL DEFAULT 'N',
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `GRMN_MENU_FK` (`intMenu`),
  KEY `GRMN_GRUPO_FK` (`intGrupo`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_linhareq`
--

CREATE TABLE IF NOT EXISTS `tbl_linhareq` (
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
  KEY `LINREQ_PAP_FK` (`intCodPap`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_menu`
--

CREATE TABLE IF NOT EXISTS `tbl_menu` (
  `intMenu` int(2) NOT NULL,
  `vcrMenu` varchar(100) NOT NULL DEFAULT '',
  `vcrAcao` varchar(100) DEFAULT NULL,
  `intMenupai` int(2) DEFAULT NULL,
  `chrHabilitado` char(1) NOT NULL DEFAULT 'S',
  `intNivelX` int(2) NOT NULL,
  `intNivelY` int(2) NOT NULL,
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intMenu`),
  KEY `menu_pai_fk` (`intMenupai`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_opcionaisreqserv`
--

CREATE TABLE IF NOT EXISTS `tbl_opcionaisreqserv` (
  `intCod` int(8) NOT NULL DEFAULT '0',
  `vcrNomeItem` varchar(40) DEFAULT NULL,
  `dblValorItem` double NOT NULL DEFAULT '0',
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intCod`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tbl_opcionaisreqserv`
--


-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_papel`
--

CREATE TABLE IF NOT EXISTS `tbl_papel` (
  `intCodPap` int(4) NOT NULL DEFAULT '0',
  `vcrNome` varchar(20) NOT NULL DEFAULT '',
  `dblImpMono` double DEFAULT NULL,
  `dblImpColor` double DEFAULT NULL,
  `dblImpShade` double DEFAULT NULL,
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intCodPap`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tbl_papel`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_parametroprog`
--

CREATE TABLE IF NOT EXISTS `tbl_parametroprog` (
  `vcrCodParametro` varchar(100) NOT NULL DEFAULT '',
  `vcrDescricao` varchar(200) NOT NULL DEFAULT '',
  `vcrValor` varchar(200) NOT NULL DEFAULT '',
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`vcrCodParametro`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tbl_parametroprog`
--


-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_projetos`
--

CREATE TABLE IF NOT EXISTS `tbl_projetos` (
  `intCodProj` int(8) NOT NULL DEFAULT '0',
  `intClienteId` int(11) NOT NULL,
  `vcrProjeto` varchar(100) DEFAULT NULL,
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intCodProj`),
  KEY `PROJ_CLIE_FK` (`intClienteId`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_reqserv`
--

CREATE TABLE IF NOT EXISTS `tbl_reqserv` (
  `intNumreq` int(8) NOT NULL DEFAULT '0',
  `datData` date DEFAULT NULL,
  `intCodProj` int(8) NOT NULL DEFAULT '0',
  `intCodEnt` int(4) DEFAULT NULL,
  `dblValorTotal` double DEFAULT NULL,
  `dblValorDesc` double DEFAULT NULL,
  `intStatus` int(1) DEFAULT NULL,
  `intVisivelNf` int(1) DEFAULT NULL,
  `intVisivelReq` int(1) DEFAULT NULL,
  `dblValorEnt` double DEFAULT NULL,
  `chrOrcamento` char(1) NOT NULL DEFAULT 'N',
  `dblDesconto` double DEFAULT NULL,
  `chrPago` char(1) DEFAULT 'N',
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intNumreq`),
  KEY `REQSERV_PROJ_FK` (`intCodProj`),
  KEY `REQSERV_ENTREGA_FK` (`intCodEnt`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Estrutura da tabela `tbl_reqservopcionais`
--

CREATE TABLE IF NOT EXISTS `tbl_reqservopcionais` (
  `intNumReq` int(8) NOT NULL,
  `intCodOp` int(8) NOT NULL DEFAULT '0',
  `intQuant` int(4) NOT NULL DEFAULT '0',
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intNumReq`,`intCodOp`),
  KEY `tbl_reqservopcionais_ibfk_1` (`intNumReq`),
  KEY `tbl_reqservopcionais_ibfk_2` (`intCodOp`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_reqservusuario`
--

CREATE TABLE IF NOT EXISTS `tbl_reqservusuario` (
  `intCodUsr` int(8) NOT NULL,
  `intNumReq` int(8) NOT NULL,
  `datData` date DEFAULT NULL,
  `intCodUsrAlter` int(8) DEFAULT NULL,
  `datdataAlter` date DEFAULT NULL,
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intNumReq`),
  KEY `REQSERUS_REQSERV_FK` (`intNumReq`),
  KEY `REQSERUS_USUA_FK` (`intCodUsr`),
  KEY `REQSERUS_USUA2_FK` (`intCodUsrAlter`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_saidamotoqueiro`
--

CREATE TABLE IF NOT EXISTS `tbl_saidamotoqueiro` (
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
  KEY `MOTOQ_COBR_FK` (`intCodCobr`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_statuscobranca`
--

CREATE TABLE IF NOT EXISTS `tbl_statuscobranca` (
  `intNumreq` int(8) NOT NULL DEFAULT '0',
  `intCodCobr` int(2) NOT NULL DEFAULT '0',
  `datDataCobr` date DEFAULT NULL,
  `datDataPag` date DEFAULT NULL,
  `horCobranca` time DEFAULT NULL,
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intCodCobr`,`intNumreq`),
  KEY `STSCOBR_REQSERV_FK` (`intNumreq`),
  KEY `STSCOBR_COBR_FK` (`intCodCobr`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tbl_statuscobranca`
--


-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_usuario`
--

CREATE TABLE IF NOT EXISTS `tbl_usuario` (
  `intCodUsr` int(8) NOT NULL,
  `vcrLogin` varchar(30) NOT NULL DEFAULT '',
  `vcrPassword` varchar(30) DEFAULT NULL,
  `vcrCpf` varchar(20) NOT NULL DEFAULT '',
  `intPermissao` int(1) NOT NULL DEFAULT '0',
  `vcrIpMaquina` varchar(20) DEFAULT NULL,
  `intGrupo` int(1) DEFAULT NULL,
  `vcrNome` varchar(100) DEFAULT NULL,
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intCodUsr`),
  UNIQUE KEY `vcrCpf_UNIQUE` (`vcrCpf`),
  KEY `USUA_GRP_FK` (`intGrupo`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

