-- phpMyAdmin SQL Dump
-- version 3.2.4
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tempo de Geração: Jun 27, 2010 as 04:13 
-- Versão do Servidor: 5.1.41
-- Versão do PHP: 5.3.1

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`vcrCnpj`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_cobrador`
--

CREATE TABLE IF NOT EXISTS `tbl_cobrador` (
  `intCodCobr` int(2) NOT NULL DEFAULT '0',
  `vcrNome` varchar(60) NOT NULL DEFAULT '',
  `vcrFoneCon` varchar(20) DEFAULT NULL,
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intCodCobr`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_entrega`
--

CREATE TABLE IF NOT EXISTS `tbl_entrega` (
  `intCodEnt` int(4) NOT NULL DEFAULT '0',
  `vcrLocal` varchar(20) NOT NULL DEFAULT '',
  `dblPreco` double(10,2) DEFAULT NULL,
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intCodEnt`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`vcrCnpj`),
  CONSTRAINT FONECLI_CLIE_FK FOREIGN KEY (vcrCnpj)  
  REFERENCES TBL_CLIENTES(vcrCnpj)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_grupoacesso`
--

CREATE TABLE IF NOT EXISTS `tbl_grupoacesso` (
  `intGrupoAcesso` int(1) NOT NULL DEFAULT '0',
  `vcrGrupoMenu` varchar(100) NOT NULL DEFAULT '',
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intGrupoAcesso`,`vcrGrupoMenu`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Estrutura da tabela `tbl_GRUPO_menu`
--
create table tbl_grupomenu(
 `intGrupoAcesso` int(1) NOT NULL,
  `intMenu`       int(2) not null,
 `chrEscrita` char(1) default 'S' not null,
primary key (intMenu),
  CONSTRAINT GRP_MENU_FK FOREIGN KEY (intMenu)  
  REFERENCES TBL_MENU(intMenu),
  CONSTRAINT GRP_ACESS_FK FOREIGN KEY (intGrupoAcesso)  
  REFERENCES TBL_grupoacesso(intGrupoAcesso)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Estrutura da tabela `tbl_menu`
--
create table tbl_menu(
intMenu       int(2) not null,
vcrMenu       varchar(60) not null,
vcrAcao       varchar(100),
intMenuPai    int(2),
chrHabilitado char(1) default 'S' not null,
intNivelX     int(2) not null,
intNivelY     int(2) not null,
primary key (intMenu)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*
CREATE TABLE IF NOT EXISTS `tbl_menu` (
  `chrCodMenu` char(1) NOT NULL DEFAULT '',
  `vcrImagem` varchar(100) NOT NULL DEFAULT '',
  `vcrArquivo` varchar(100) NOT NULL DEFAULT '',
  `vcrRotulo` varchar(100) NOT NULL DEFAULT '',
  `vcrComentario` varchar(100) DEFAULT NULL,
  `vcrTextoAlt` varchar(100) DEFAULT NULL,
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`chrCodMenu`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;*/

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
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intNumNota`),
  CONSTRAINT NOTAFISCAL_CLIE_FK FOREIGN KEY (vcrCnpj)
  REFERENCES TBL_CLIENTES(vcrCnpj),
  CONSTRAINT NOTAFISCAL_ENT_FK FOREIGN KEY (intCodEnt)
  REFERENCES TBL_ENTREGA(intCodEnt)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_notaiss`
--

CREATE TABLE IF NOT EXISTS `tbl_notaiss` (
  `intNumNota` int(8) NOT NULL DEFAULT '0',
  `intValorIss` int(2) NOT NULL DEFAULT '0',
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intNumNota`) ,
  CONSTRAINT NOTAISS_NOTAF_FK FOREIGN KEY (intNumNota)
  REFERENCES TBL_NOTAFISCAL(intNumNota)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_opcionaisreqserv`
--

CREATE TABLE IF NOT EXISTS `tbl_opcionaisreqserv` (
  `vcrCod` varchar(4) NOT NULL DEFAULT '',
  `vcrNomeItem` varchar(40) DEFAULT NULL,
  `dblValorItem` double(10,2) NOT NULL DEFAULT '0.00',
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`vcrCod`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intCodPap`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_projetos`
--

CREATE TABLE IF NOT EXISTS `tbl_projetos` (
  `vcrCnpj` varchar(20) NOT NULL DEFAULT '',
  `intCodProj` int(8) NOT NULL DEFAULT '0',
  `vcrProjeto` varchar(100) DEFAULT NULL,
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intCodProj`),
  CONSTRAINT PROJ_CLIE_FK FOREIGN KEY (vcrCnpj)
  REFERENCES TBL_CLIENTES(vcrCnpj)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intNumreq`),
  CONSTRAINT REQSERV_CLIE_FK FOREIGN KEY (vcrCnpj)
  REFERENCES TBL_CLIENTES(vcrCnpj),
  CONSTRAINT REQSERV_PROJ_FK FOREIGN KEY (intCodProj)
  REFERENCES TBL_PROJETOS(intCodProj),
  CONSTRAINT REQSERV_ENTREGA_FK FOREIGN KEY (intCodEnt)
  REFERENCES TBL_ENTREGA(intCodEnt),
  CONSTRAINT REQSERV_COBR_FK FOREIGN KEY (intCodCobr)
  REFERENCES TBL_COBRADOR(intCodCobr)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------
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
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intNumLin`,`intNumreq`) ,
  CONSTRAINT LINREQ_REQSRV_FK FOREIGN KEY (intNumreq)
  REFERENCES TBL_REQSERV(intNumreq),
  CONSTRAINT LINREQ_PAP_FK FOREIGN KEY (intCodPap)
  REFERENCES TBL_PAPEL(intCodPap)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_reqservopcionais`
--

CREATE TABLE IF NOT EXISTS `tbl_reqservopcionais` (
  `intNumReq` int(8) NOT NULL ,
  `intCodOp` int(8) NOT NULL DEFAULT '0',
  `vcrCod` varchar(4) NOT NULL DEFAULT ' ',
  `intQuant` int(4) NOT NULL ,
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intNumReq`,`intCodOp`),
  CONSTRAINT REQSEROP_REQSERV_FK FOREIGN KEY (intNumReq)
  REFERENCES TBL_REQSERV(intNumReq),
  CONSTRAINT REQSEROP_OPRQSV_FK FOREIGN KEY (vcrCod)
  REFERENCES TBL_OPCIONAISREQSERV(vcrCod)
) ENGINE=InnoDB DEFAULT CHARSET=latin1

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_reqservusuario`
--



-- --------------------------------------------------------
--
-- Estrutura da tabela `tbl_reqnotafiscal`
--

CREATE TABLE IF NOT EXISTS `tbl_reqnotafiscal` (
  `intNumNota` int(8) NOT NULL DEFAULT '0',
  `intNumReq` int(8) NOT NULL DEFAULT '0',
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intNumNota`,`intNumReq`),
  CONSTRAINT REQNOTFISCAL_REQSERV_FK FOREIGN KEY (intNumReq)
  REFERENCES TBL_REQSERV(intNumReq) ,
  CONSTRAINT REQNOTFISCAL_NOTF_FK FOREIGN KEY (intNumNota)
  REFERENCES TBL_NOTAFISCAL(intNumNota)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  CONSTRAINT MOTOQ_REQSERV_FK FOREIGN KEY (intNumReq)
  REFERENCES TBL_REQSERV(intNumReq),
  CONSTRAINT MOTOQ_COBR_FK FOREIGN KEY (intCodCobr)
  REFERENCES TBL_COBRADOR(intCodCobr)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  CONSTRAINT STSCOBR_REQSERV_FK FOREIGN KEY (intNumReq)
  REFERENCES TBL_REQSERV(intNumReq),
  CONSTRAINT STSCOBR_COBR_FK FOREIGN KEY (intCodCobr)
  REFERENCES TBL_COBRADOR(intCodCobr)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`vcrLogin`),
  CONSTRAINT USUA_GRAC_FK FOREIGN KEY (intGrupoAcesso)
  REFERENCES TBL_GRUPOACESSO(intGrupoAcesso)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `tbl_reqservusuario` (
  `vcrLogin` varchar(30) NOT NULL DEFAULT '',
  `intNumReq` int(8) NOT NULL DEFAULT '0',
  `datData` date DEFAULT NULL,
  `vcrLoginAlter` varchar(30) DEFAULT NULL,
  `datdataAlter` date DEFAULT NULL,
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`vcrLogin`,`intNumReq`),
  CONSTRAINT REQSERUS_REQSERV_FK FOREIGN KEY (intNumReq)
  REFERENCES TBL_REQSERV(intNumReq),
  CONSTRAINT REQSERUS_USUA_FK FOREIGN KEY (vcrLogin)
  REFERENCES TBL_USUARIO(vcrLogin)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
