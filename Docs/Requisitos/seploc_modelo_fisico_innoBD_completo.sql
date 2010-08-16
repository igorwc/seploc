-- phpMyAdmin SQL Dump
-- version 3.2.4
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tempo de Geração: Ago 15, 2010 as 09:19 
-- Versão do Servidor: 5.1.41
-- Versão do PHP: 5.3.1

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Banco de Dados: `seploc2`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `id_gen`
--

CREATE TABLE IF NOT EXISTS `id_gen` (
  `NOME_ID` varchar(100) NOT NULL,
  `VAL_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `id_gen`
--

INSERT INTO `id_gen` (`NOME_ID`, `VAL_ID`) VALUES
('USR_GEN', 9);

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_clientes`
--

CREATE TABLE IF NOT EXISTS `tbl_clientes` (
  `intClienteId` int(11) NOT NULL,
  `vcrCnpj` varchar(20) NOT NULL,
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tbl_clientes`
--


-- --------------------------------------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tbl_cobrador`
--


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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tbl_entrega`
--

INSERT INTO `tbl_entrega` (`intCodEnt`, `vcrLocal`, `dblPreco`, `tspVersao`) VALUES
(5, 'Alto José Bonifácio', NULL, '2010-08-08 20:56:49'),
(6, 'Alto José do PinhoAl', NULL, '2010-08-08 20:56:49'),
(7, 'Apipucos', NULL, '2010-08-08 20:56:49'),
(8, 'Areias', NULL, '2010-08-08 20:56:49'),
(9, 'Arruda', NULL, '2010-08-08 20:56:49'),
(10, 'Alto do Céu', NULL, '2010-08-08 20:56:49'),
(11, 'Alto do Deodato', NULL, '2010-08-08 20:56:49'),
(12, 'Alto do Pascoal', NULL, '2010-08-08 20:56:49'),
(13, 'Alto Santa Isabel', NULL, '2010-08-08 20:56:49'),
(14, 'Barro', NULL, '2010-08-08 20:56:49'),
(15, 'Beberibe', NULL, '2010-08-08 20:56:49'),
(16, 'Boa Viagem', NULL, '2010-08-08 20:56:49'),
(17, 'Boa Vista', NULL, '2010-08-08 20:56:49'),
(18, 'Bomba do Hemetério', NULL, '2010-08-08 20:56:49'),
(19, 'Bongi', NULL, '2010-08-08 20:56:49'),
(20, 'Brasília Teimosa', NULL, '2010-08-08 20:56:49'),
(21, 'Brejo da Guabiraba', NULL, '2010-08-08 20:56:49'),
(22, 'Brejo de Beberibe', NULL, '2010-08-08 20:56:49'),
(23, 'Barreira do Rosarinh', NULL, '2010-08-08 20:56:49'),
(24, 'Beira Rio', NULL, '2010-08-08 20:56:49'),
(25, 'Benfica', NULL, '2010-08-08 20:56:49'),
(26, 'Bomba Grande', NULL, '2010-08-08 20:56:49'),
(27, 'Cabanga', NULL, '2010-08-08 20:56:49'),
(28, 'Caçote', NULL, '2010-08-08 20:56:49'),
(29, 'Cajueiro', NULL, '2010-08-08 20:56:49'),
(30, 'Campina do Barreto', NULL, '2010-08-08 20:56:49'),
(31, 'Campo Grande', NULL, '2010-08-08 20:56:49'),
(32, 'Casa Amarela', NULL, '2010-08-08 20:56:49'),
(33, 'Casa Forte', NULL, '2010-08-08 20:56:49'),
(34, 'Caxangá', NULL, '2010-08-08 20:56:49'),
(35, 'Cidade Universitária', NULL, '2010-08-08 20:56:49'),
(36, 'Cohab', NULL, '2010-08-08 20:56:49'),
(37, 'Coelhos', NULL, '2010-08-08 20:56:49'),
(38, 'Coqueiral', NULL, '2010-08-08 20:56:49'),
(39, 'Cordeiro', NULL, '2010-08-08 20:56:49'),
(40, 'Córrego do Jenipapo', NULL, '2010-08-08 20:56:49'),
(41, 'Curado', NULL, '2010-08-08 20:56:49'),
(42, 'Capunga', NULL, '2010-08-08 20:56:49'),
(43, 'Coque', NULL, '2010-08-08 20:56:49'),
(44, 'Derby', NULL, '2010-08-08 20:56:49'),
(45, 'Dois Irmãos', NULL, '2010-08-08 20:56:49'),
(46, 'Dois Unidos', NULL, '2010-08-08 20:56:49'),
(47, 'Encruzilhada', NULL, '2010-08-08 20:56:49'),
(48, 'Engenho do Meio', NULL, '2010-08-08 20:56:49'),
(49, 'Espinheiro', NULL, '2010-08-08 20:56:49'),
(50, 'Estância', NULL, '2010-08-08 20:56:49'),
(51, 'Fundão', NULL, '2010-08-08 20:56:49');

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tbl_fonecli`
--


-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_grupo`
--

CREATE TABLE IF NOT EXISTS `tbl_grupo` (
  `intGrupo` int(2) NOT NULL DEFAULT '0',
  `vcrGrupo` varchar(100) NOT NULL DEFAULT '',
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intGrupo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tbl_grupo`
--


-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_grupomenu`
--

CREATE TABLE IF NOT EXISTS `tbl_grupomenu` (
  `intGrupo` int(2) NOT NULL DEFAULT '0',
  `intMenu` int(2) NOT NULL,
  `chrEscrita` char(1) NOT NULL DEFAULT 'S',
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intGrupo`),
  KEY `GRMN_MENU_FK` (`intMenu`),
  KEY `GRMN_GRUPO_FK` (`intGrupo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tbl_grupomenu`
--


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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tbl_linhareq`
--


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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tbl_menu`
--


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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tbl_projetos`
--


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
  `intStatus` int(1) DEFAULT NULL,
  `intVisivelNf` int(1) DEFAULT NULL,
  `intVisivelReq` int(1) DEFAULT NULL,
  `dblValorEnt` double DEFAULT NULL,
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intNumreq`),
  KEY `REQSERV_PROJ_FK` (`intCodProj`),
  KEY `REQSERV_ENTREGA_FK` (`intCodEnt`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tbl_reqserv`
--


-- --------------------------------------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tbl_reqservopcionais`
--


-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_reqservusuario`
--

CREATE TABLE IF NOT EXISTS `tbl_reqservusuario` (
  `intCodUsr` int(8) NOT NULL,
  `intNumReq` int(8) NOT NULL,
  `datData` date DEFAULT NULL,
  `vcrLoginAlter` varchar(30) DEFAULT NULL,
  `datdataAlter` date DEFAULT NULL,
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intCodUsr`),
  KEY `REQSERUS_REQSERV_FK` (`intNumReq`),
  KEY `REQSERUS_USUA_FK` (`intCodUsr`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tbl_reqservusuario`
--


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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tbl_saidamotoqueiro`
--


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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  KEY `USUA_GRP_FK` (`intGrupo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tbl_usuario`
--

INSERT INTO `tbl_usuario` (`intCodUsr`, `vcrLogin`, `vcrPassword`, `vcrCpf`, `intPermissao`, `vcrIpMaquina`, `intGrupo`, `vcrNome`, `tspVersao`) VALUES
(6, 'igorwc@gmail.com', NULL, '02278522493', 0, NULL, NULL, 'asasasas', '2010-08-08 20:56:49'),
(7, 'testeand', NULL, '02278522493', 0, NULL, NULL, 'igorr', '2010-08-08 21:14:37'),
(8, 'testedenovo', NULL, '03487103486', -1, NULL, NULL, 'vamosver22', '2010-08-08 21:16:26');

--
-- Restrições para as tabelas dumpadas
--

--
-- Restrições para a tabela `tbl_clientes`
--
ALTER TABLE `tbl_clientes`
  ADD CONSTRAINT `fk_tbl_clientes_tbl_entrega1` FOREIGN KEY (`intEntregaPadrao`) REFERENCES `tbl_entrega` (`intCodEnt`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_tbl_clientes_tbl_papel1` FOREIGN KEY (`intPapelPadrao`) REFERENCES `tbl_papel` (`intCodPap`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para a tabela `tbl_fonecli`
--
ALTER TABLE `tbl_fonecli`
  ADD CONSTRAINT `FONECLI_CLIE_FK` FOREIGN KEY (`intClienteId`) REFERENCES `tbl_clientes` (`intClienteId`);

--
-- Restrições para a tabela `tbl_grupomenu`
--
ALTER TABLE `tbl_grupomenu`
  ADD CONSTRAINT `GRMN_GRUPO_FK00` FOREIGN KEY (`intGrupo`) REFERENCES `tbl_grupo` (`intGrupo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `GRMN_MENU_FK00` FOREIGN KEY (`intMenu`) REFERENCES `tbl_menu` (`intMenu`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para a tabela `tbl_linhareq`
--
ALTER TABLE `tbl_linhareq`
  ADD CONSTRAINT `LINREQ_PAP_FK` FOREIGN KEY (`intCodPap`) REFERENCES `tbl_papel` (`intCodPap`),
  ADD CONSTRAINT `LINREQ_REQSRV_FK` FOREIGN KEY (`intNumreq`) REFERENCES `tbl_reqserv` (`intNumreq`);

--
-- Restrições para a tabela `tbl_menu`
--
ALTER TABLE `tbl_menu`
  ADD CONSTRAINT `menu_pai_fk` FOREIGN KEY (`intMenupai`) REFERENCES `tbl_menu` (`intMenu`);

--
-- Restrições para a tabela `tbl_projetos`
--
ALTER TABLE `tbl_projetos`
  ADD CONSTRAINT `PROJ_CLIE_FK` FOREIGN KEY (`intClienteId`) REFERENCES `tbl_clientes` (`intClienteId`);

--
-- Restrições para a tabela `tbl_reqserv`
--
ALTER TABLE `tbl_reqserv`
  ADD CONSTRAINT `REQSERV_ENTREGA_FK` FOREIGN KEY (`intCodEnt`) REFERENCES `tbl_entrega` (`intCodEnt`),
  ADD CONSTRAINT `REQSERV_PROJ_FK` FOREIGN KEY (`intCodProj`) REFERENCES `tbl_projetos` (`intCodProj`);

--
-- Restrições para a tabela `tbl_reqservopcionais`
--
ALTER TABLE `tbl_reqservopcionais`
  ADD CONSTRAINT `tbl_reqservopcionais_ibfk_1` FOREIGN KEY (`intNumReq`) REFERENCES `tbl_reqserv` (`intNumreq`),
  ADD CONSTRAINT `tbl_reqservopcionais_ibfk_2` FOREIGN KEY (`intCodOp`) REFERENCES `tbl_opcionaisreqserv` (`intCod`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para a tabela `tbl_reqservusuario`
--
ALTER TABLE `tbl_reqservusuario`
  ADD CONSTRAINT `REQSERUS_REQSERV_FK` FOREIGN KEY (`intNumReq`) REFERENCES `tbl_reqserv` (`intNumreq`),
  ADD CONSTRAINT `REQSERUS_USUA_FK` FOREIGN KEY (`intCodUsr`) REFERENCES `tbl_usuario` (`intCodUsr`);

--
-- Restrições para a tabela `tbl_saidamotoqueiro`
--
ALTER TABLE `tbl_saidamotoqueiro`
  ADD CONSTRAINT `MOTOQ_COBR_FK` FOREIGN KEY (`intCodCobr`) REFERENCES `tbl_cobrador` (`intCodCobr`),
  ADD CONSTRAINT `MOTOQ_REQSERV_FK` FOREIGN KEY (`intNumReq`) REFERENCES `tbl_reqserv` (`intNumreq`);

--
-- Restrições para a tabela `tbl_statuscobranca`
--
ALTER TABLE `tbl_statuscobranca`
  ADD CONSTRAINT `STSCOBR_COBR_FK` FOREIGN KEY (`intCodCobr`) REFERENCES `tbl_cobrador` (`intCodCobr`),
  ADD CONSTRAINT `STSCOBR_REQSERV_FK` FOREIGN KEY (`intNumreq`) REFERENCES `tbl_reqserv` (`intNumreq`);

--
-- Restrições para a tabela `tbl_usuario`
--
ALTER TABLE `tbl_usuario`
  ADD CONSTRAINT `USUA_GRP_FK` FOREIGN KEY (`intGrupo`) REFERENCES `tbl_grupo` (`intGrupo`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
