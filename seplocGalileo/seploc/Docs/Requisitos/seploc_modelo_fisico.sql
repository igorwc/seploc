-- phpMyAdmin SQL Dump
-- version 3.2.4
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tempo de Gera��o: Jun 27, 2010 as 04:13 
-- Vers�o do Servidor: 5.1.41
-- Vers�o do PHP: 5.3.1

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES latin1 */;

--
-- Banco de Dados: `seploc`
--

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
-- Estrutura da tabela `tbl_clientes`
--

CREATE TABLE IF NOT EXISTS `tbl_clientes` (
  `vcrCnpj` varchar(20) NOT NULL DEFAULT '',
  `vcrRazao` varchar(60) NOT NULL DEFAULT '',
  `vcrCpf` varchar(20) DEFAULT NULL,
  `vcrEnder` varchar(60) DEFAULT NULL,
  `vcrBairro` varchar(30) DEFAULT NULL,
  `vcrCidade` varchar(30) DEFAULT NULL,
  `vcrEstado` char(2) DEFAULT NULL,
  `vcrCep` varchar(10) DEFAULT NULL,
  `vcrEmail` varchar(80) DEFAULT NULL,
  `vcrInscricao` varchar(20) DEFAULT NULL,
  `intBalcao` int(1) DEFAULT NULL,
  `vcrFantasia` varchar(60) NOT NULL DEFAULT '',
  `txtobs` text,
  `vcrMapa` varchar(100) DEFAULT NULL,
  `intEntregaPadrao` int(4) DEFAULT NULL,
  `intPapelPadrao` int(4) DEFAULT NULL,
  PRIMARY KEY (`vcrCnpj`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_cobrador`
--

CREATE TABLE IF NOT EXISTS `tbl_cobrador` (
  `intCodCobr` int(2) NOT NULL DEFAULT '0',
  `vcrNome` varchar(60) NOT NULL DEFAULT '',
  `vcrFoneCon` varchar(20) DEFAULT NULL,
  `tspVersao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intCodCobr`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_entrega`
--

CREATE TABLE IF NOT EXISTS `tbl_entrega` (
  `intCodEnt` int(4) NOT NULL DEFAULT '0',
  `vcrLocal` varchar(20) NOT NULL DEFAULT '',
  `dblPreco` double(10,2) DEFAULT NULL,
  `tspVersao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intCodEnt`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_fonecli`
--

CREATE TABLE IF NOT EXISTS `tbl_fonecli` (
  `vcrCnpj` varchar(20) NOT NULL DEFAULT '',
  `vcrFoneRes` varchar(60) DEFAULT NULL,
  `vcrFoneCom` varchar(60) DEFAULT NULL,
  `vcrFax` varchar(60) DEFAULT NULL,
  `vcrCelular` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`vcrCnpj`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_grupoacesso`
--

CREATE TABLE IF NOT EXISTS `tbl_grupoacesso` (
  `intGrupoAcesso` int(1) NOT NULL DEFAULT '0',
  `vcrGrupoMenu` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`intGrupoAcesso`,`vcrGrupoMenu`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_linhareq`
--

CREATE TABLE IF NOT EXISTS `tbl_linhareq` (
  `intNumreq` int(8) NOT NULL DEFAULT '0',
  `intNumLin` int(2) NOT NULL DEFAULT '0',
  `vcrNomeArq` varchar(60) NOT NULL DEFAULT '',
  `dblFormato` double(10,2) DEFAULT NULL,
  `dblDimensao` double(10,2) DEFAULT NULL,
  `intCodPap` int(4) NOT NULL DEFAULT '0',
  `vcrImpressao` varchar(30) DEFAULT NULL,
  `intQuant` int(2) DEFAULT NULL,
  `dblValorUnit` double(10,2) DEFAULT NULL,
  `dblValorSubUnit` double(10,2) DEFAULT NULL,
  PRIMARY KEY (`intNumLin`,`intNumreq`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_menu`
--

CREATE TABLE IF NOT EXISTS `tbl_menu` (
  `chrCodMenu` char(1) NOT NULL DEFAULT '',
  `vcrImagem` varchar(100) NOT NULL DEFAULT '',
  `vcrArquivo` varchar(100) NOT NULL DEFAULT '',
  `vcrRotulo` varchar(100) NOT NULL DEFAULT '',
  `vcrComentario` varchar(100) DEFAULT NULL,
  `vcrTextoAlt` varchar(100) DEFAULT NULL,
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`chrCodMenu`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_notafiscal`
--

CREATE TABLE IF NOT EXISTS `tbl_notafiscal` (
  `intNumNota` int(8) NOT NULL DEFAULT '0',
  `vcrCnpj` varchar(20) NOT NULL DEFAULT '',
  `datData` date DEFAULT NULL,
  `dblValor` double(10,2) NOT NULL DEFAULT '0.00',
  `dblSubValor` double(10,2) NOT NULL DEFAULT '0.00',
  `intCodEnt` int(4) DEFAULT NULL,
  `vcrDescricao` varchar(200) DEFAULT NULL,
  `vcrObservacao` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`intNumNota`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_notaiss`
--

CREATE TABLE IF NOT EXISTS `tbl_notaiss` (
  `intNumNota` int(8) NOT NULL DEFAULT '0',
  `intValorIss` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`intNumNota`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_opcionaisreqserv`
--

CREATE TABLE IF NOT EXISTS `tbl_opcionaisreqserv` (
  `vcrCod` varchar(4) NOT NULL DEFAULT '',
  `vcrNomeItem` varchar(40) DEFAULT NULL,
  `dblValorItem` double(10,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`vcrCod`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_papel`
--

CREATE TABLE IF NOT EXISTS `tbl_papel` (
  `intCodPap` int(4) NOT NULL DEFAULT '0',
  `vcrNome` varchar(20) NOT NULL DEFAULT '',
  `dblImpMono` double(10,2) DEFAULT NULL,
  `dblImpColor` double(10,2) DEFAULT NULL,
  `dblImpShade` double(10,2) DEFAULT NULL,
  `tspVersao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intCodPap`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_parametroprog`
--

CREATE TABLE IF NOT EXISTS `tbl_parametroprog` (
  `vcrCodParametro` varchar(100) NOT NULL DEFAULT '',
  `vcrDescricao` varchar(200) NOT NULL DEFAULT '',
  `vcrValor` varchar(200) NOT NULL DEFAULT '',
  PRIMARY KEY (`vcrCodParametro`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_projetos`
--

CREATE TABLE IF NOT EXISTS `tbl_projetos` (
  `vcrCnpj` varchar(20) NOT NULL DEFAULT '',
  `intCodProj` int(8) NOT NULL DEFAULT '0',
  `vcrProjeto` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`intCodProj`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_reqnotafiscal`
--

CREATE TABLE IF NOT EXISTS `tbl_reqnotafiscal` (
  `intNumNota` int(8) NOT NULL DEFAULT '0',
  `intNumReq` int(8) NOT NULL DEFAULT '0',
  PRIMARY KEY (`intNumNota`,`intNumReq`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_reqserv`
--

CREATE TABLE IF NOT EXISTS `tbl_reqserv` (
  `intNumreq` int(8) NOT NULL DEFAULT '0',
  `datData` date DEFAULT NULL,
  `vcrCnpj` varchar(20) NOT NULL DEFAULT '',
  `intCodProj` int(8) NOT NULL DEFAULT '0',
  `intCodEnt` int(4) DEFAULT NULL,
  `dblValorTotal` double(10,2) DEFAULT NULL,
  `intStatus` int(1) DEFAULT NULL,
  `intCodCobr` int(2) DEFAULT NULL,
  `intVisivelNf` int(1) DEFAULT NULL,
  `intVisivelReq` int(1) DEFAULT NULL,
  `dblValorEnt` double(10,2) DEFAULT NULL,
  PRIMARY KEY (`intNumreq`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_reqservopcionais`
--

CREATE TABLE IF NOT EXISTS `tbl_reqservopcionais` (
  `intNumReq` varchar(8) NOT NULL DEFAULT '',
  `intCodOp` int(8) NOT NULL DEFAULT '0',
  `vcrCod` varchar(4) NOT NULL DEFAULT '',
  `intQuant` int(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`intNumReq`,`intCodOp`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_reqservusuario`
--

CREATE TABLE IF NOT EXISTS `tbl_reqservusuario` (
  `vcrLogin` varchar(30) NOT NULL DEFAULT '',
  `intNumReq` int(8) NOT NULL DEFAULT '0',
  `datData` date DEFAULT NULL,
  `vcrLoginAlter` varchar(30) DEFAULT NULL,
  `datdataAlter` date DEFAULT NULL,
  PRIMARY KEY (`vcrLogin`,`intNumReq`)
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
  PRIMARY KEY (`intNumSaida`)
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
  PRIMARY KEY (`intCodCobr`,`intNumreq`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_usuario`
--

CREATE TABLE IF NOT EXISTS `tbl_usuario` (
  `vcrLogin` varchar(30) NOT NULL DEFAULT '',
  `vcrPassword` varchar(30) DEFAULT NULL,
  `vcrCpf` varchar(20) NOT NULL DEFAULT '',
  `intPermissao` int(1) NOT NULL DEFAULT '0',
  `vcrIpMaquina` varchar(20) DEFAULT NULL,
  `intGrupoAcesso` int(1) DEFAULT NULL,
  `vcrNome` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`vcrLogin`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;


