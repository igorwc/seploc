-- phpMyAdmin SQL Dump
-- version 3.2.4
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tempo de Geração: Ago 29, 2010 as 06:46 
-- Versão do Servidor: 5.1.41
-- Versão do PHP: 5.3.1

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES latin1 */;

--
-- Banco de Dados: `seploc2`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `id_gen`
--

DROP TABLE IF EXISTS `id_gen`;
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
-- Estrutura da tabela `tbl_bairros`
--

DROP TABLE IF EXISTS `tbl_bairros`;
CREATE TABLE IF NOT EXISTS `tbl_bairros` (
  `intCod` int(11) NOT NULL AUTO_INCREMENT,
  `vcrNome` varchar(150) NOT NULL,
  `intCodCidade` int(11) NOT NULL,
  `tspVersao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intCod`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=169 ;

--
-- Extraindo dados da tabela `tbl_bairros`
--

INSERT INTO `tbl_bairros` (`intCod`, `vcrNome`, `intCodCidade`, `tspVersao`) VALUES
(1, 'Aflitos', 133, '2010-08-28 00:00:00'),
(2, 'Afogados', 133, '2010-08-28 00:00:00'),
(3, 'Água Fria', 133, '2010-08-28 00:00:00'),
(4, 'Alto José Bonifácio', 133, '2010-08-28 00:00:00'),
(5, 'Alto José do Pinho', 133, '2010-08-28 00:00:00'),
(6, 'Alto do Mandu', 133, '2010-08-28 00:00:00'),
(7, 'Alto Santa Teresinha', 133, '2010-08-28 00:00:00'),
(8, 'Apipucos', 133, '2010-08-28 00:00:00'),
(9, 'Areias', 133, '2010-08-28 00:00:00'),
(10, 'Arruda', 133, '2010-08-28 00:00:00'),
(11, 'Barro', 133, '2010-08-28 00:00:00'),
(12, 'Beberibe', 133, '2010-08-28 00:00:00'),
(13, 'Benfica', 133, '2010-08-28 00:00:00'),
(14, 'Boa Viagem', 133, '2010-08-28 00:00:00'),
(15, 'Boa Vista', 133, '2010-08-28 00:00:00'),
(16, 'Bomba do Hemetério', 133, '2010-08-28 00:00:00'),
(17, 'Bongi', 133, '2010-08-28 00:00:00'),
(18, 'Brasília Teimosa', 133, '2010-08-28 00:00:00'),
(19, 'Brejo do Beberibe', 133, '2010-08-28 00:00:00'),
(20, 'Brejo da Guabiraba', 133, '2010-08-28 00:00:00'),
(21, 'Cabanga', 133, '2010-08-28 00:00:00'),
(22, 'Caçote', 133, '2010-08-28 00:00:00'),
(23, 'Cajueiro', 133, '2010-08-28 00:00:00'),
(24, 'Campina do Barreto', 133, '2010-08-28 00:00:00'),
(25, 'Campo Grande', 133, '2010-08-28 00:00:00'),
(26, 'Casa Amarela', 133, '2010-08-28 00:00:00'),
(27, 'Casa Forte', 133, '2010-08-28 00:00:00'),
(28, 'Caxangá', 133, '2010-08-28 00:00:00'),
(29, 'Cidade Universitária', 133, '2010-08-28 00:00:00'),
(30, 'Coelhos', 133, '2010-08-28 00:00:00'),
(31, 'Cohab', 133, '2010-08-28 00:00:00'),
(32, 'Coqueiral', 133, '2010-08-28 00:00:00'),
(33, 'Cordeiro', 133, '2010-08-28 00:00:00'),
(34, 'Córrego do Jenipapo', 133, '2010-08-28 00:00:00'),
(35, 'Curado', 133, '2010-08-28 00:00:00'),
(36, 'Derby', 133, '2010-08-28 00:00:00'),
(37, 'Dois Irmãos', 133, '2010-08-28 00:00:00'),
(38, 'Dois Unidos', 133, '2010-08-28 00:00:00'),
(39, 'Encruzilhada', 133, '2010-08-28 00:00:00'),
(40, 'Engenho do Meio', 133, '2010-08-28 00:00:00'),
(41, 'Espinheiro', 133, '2010-08-28 00:00:00'),
(42, 'Estância', 133, '2010-08-28 00:00:00'),
(43, 'Fundão', 133, '2010-08-28 00:00:00'),
(44, 'Graças', 133, '2010-08-28 00:00:00'),
(45, 'Guabiraba', 133, '2010-08-28 00:00:00'),
(46, 'Hipódromo', 133, '2010-08-28 00:00:00'),
(47, 'Ibura', 133, '2010-08-28 00:00:00'),
(48, 'Ilha Joana Bezerra', 133, '2010-08-28 00:00:00'),
(49, 'Ilha do Leite', 133, '2010-08-28 00:00:00'),
(50, 'Ilha do Retiro', 133, '2010-08-28 00:00:00'),
(51, 'Imbiribeira', 133, '2010-08-28 00:00:00'),
(52, 'Ipsep', 133, '2010-08-28 00:00:00'),
(53, 'Iputinga', 133, '2010-08-28 00:00:00'),
(54, 'Jaqueira', 133, '2010-08-28 00:00:00'),
(55, 'Jardim São Paulo', 133, '2010-08-28 00:00:00'),
(56, 'Jiquiá', 133, '2010-08-28 00:00:00'),
(57, 'Jordão', 133, '2010-08-28 00:00:00'),
(58, 'Linha do Tiro', 133, '2010-08-28 00:00:00'),
(59, 'Macaxeira', 133, '2010-08-28 00:00:00'),
(60, 'Madalena', 133, '2010-08-28 00:00:00'),
(61, 'Mangabeira', 133, '2010-08-28 00:00:00'),
(62, 'Mangueira', 133, '2010-08-28 00:00:00'),
(63, 'Monteiro', 133, '2010-08-28 00:00:00'),
(64, 'Morro da Conceição', 133, '2010-08-28 00:00:00'),
(65, 'Mustardinha', 133, '2010-08-28 00:00:00'),
(66, 'Nova Descoberta', 133, '2010-08-28 00:00:00'),
(67, 'Paissandu', 133, '2010-08-28 00:00:00'),
(68, 'Parnamirim', 133, '2010-08-28 00:00:00'),
(69, 'Passarinho', 133, '2010-08-28 00:00:00'),
(70, 'Pau Ferro', 133, '2010-08-28 00:00:00'),
(71, 'Peixinhos', 133, '2010-08-28 00:00:00'),
(72, 'Pina', 133, '2010-08-28 00:00:00'),
(73, 'Poço da Panela', 133, '2010-08-28 00:00:00'),
(74, 'Ponte d''Uchoa', 133, '2010-08-28 00:00:00'),
(75, 'Ponto de Parada', 133, '2010-08-28 00:00:00'),
(76, 'Porto da Madeira', 133, '2010-08-28 00:00:00'),
(77, 'Prado', 133, '2010-08-28 00:00:00'),
(78, 'Recife', 133, '2010-08-28 00:00:00'),
(79, 'Rosarinho', 133, '2010-08-28 00:00:00'),
(80, 'Sancho', 133, '2010-08-28 00:00:00'),
(81, 'Santana', 133, '2010-08-28 00:00:00'),
(82, 'Santo Amaro', 133, '2010-08-28 00:00:00'),
(83, 'Santo Antônio', 133, '2010-08-28 00:00:00'),
(84, 'São José', 133, '2010-08-28 00:00:00'),
(85, 'Setúbal', 133, '2010-08-28 00:00:00'),
(86, 'Sítio dos Pintos', 133, '2010-08-28 00:00:00'),
(87, 'Soledade', 133, '2010-08-28 00:00:00'),
(88, 'Tamarineira', 133, '2010-08-28 00:00:00'),
(89, 'Tejipió', 133, '2010-08-28 00:00:00'),
(90, 'Torre', 133, '2010-08-28 00:00:00'),
(91, 'Torreão', 133, '2010-08-28 00:00:00'),
(92, 'Torrões', 133, '2010-08-28 00:00:00'),
(93, 'Totó', 133, '2010-08-28 00:00:00'),
(94, 'Várzea', 133, '2010-08-28 00:00:00'),
(95, 'Vasco da Gama', 133, '2010-08-28 00:00:00'),
(96, 'Vila Tamandaré', 133, '2010-08-28 00:00:00'),
(97, 'Zumbi', 133, '2010-08-28 00:00:00'),
(98, 'Aeroclube', 279, '2010-08-28 00:00:00'),
(99, 'Água Fria', 279, '2010-08-28 00:00:00'),
(100, 'Altiplano', 279, '2010-08-28 00:00:00'),
(101, 'Alto do Céu', 279, '2010-08-28 00:00:00'),
(102, 'Alto do Mateus', 279, '2010-08-28 00:00:00'),
(103, 'Anatólia', 279, '2010-08-28 00:00:00'),
(104, 'Bancários', 279, '2010-08-28 00:00:00'),
(105, 'Bessa', 279, '2010-08-28 00:00:00'),
(106, 'Brisamar', 279, '2010-08-28 00:00:00'),
(107, 'Cabo Branco', 279, '2010-08-28 00:00:00'),
(108, 'Castelo Branco', 279, '2010-08-28 00:00:00'),
(109, 'Centro', 279, '2010-08-28 00:00:00'),
(110, 'Cidade dos Colibris', 279, '2010-08-28 00:00:00'),
(111, 'Cidade Verde', 279, '2010-08-28 00:00:00'),
(112, 'Cidade dos Colibris', 279, '2010-08-28 00:00:00'),
(113, 'Conjunto Boa Esperança', 279, '2010-08-28 00:00:00'),
(114, 'Conjunto Cehap I', 279, '2010-08-28 00:00:00'),
(115, 'Conjunto Pedro Gondim', 279, '2010-08-28 00:00:00'),
(116, 'Costa e Silva', 279, '2010-08-28 00:00:00'),
(117, 'Cristo Redentor', 279, '2010-08-28 00:00:00'),
(118, 'Cruz das Armas', 279, '2010-08-28 00:00:00'),
(119, 'Cuiá', 279, '2010-08-28 00:00:00'),
(120, 'Distrito Industrial', 279, '2010-08-28 00:00:00'),
(121, 'Ernesto Geisel', 279, '2010-08-28 00:00:00'),
(122, 'Ernâni Sátiro', 279, '2010-08-28 00:00:00'),
(123, 'Esplanada', 279, '2010-08-28 00:00:00'),
(124, 'Estados', 279, '2010-08-28 00:00:00'),
(125, 'Expedicionários', 279, '2010-08-28 00:00:00'),
(126, 'Funcionários I', 279, '2010-08-28 00:00:00'),
(127, 'Funcionários II', 279, '2010-08-28 00:00:00'),
(128, 'Funcionários III', 279, '2010-08-28 00:00:00'),
(129, 'Funcionários IV', 279, '2010-08-28 00:00:00'),
(130, 'Gramame', 279, '2010-08-28 00:00:00'),
(131, 'Grotão', 279, '2010-08-28 00:00:00'),
(132, 'Ilha do Bispo', 279, '2010-08-28 00:00:00'),
(133, 'Bairro das Indústrias', 279, '2010-08-28 00:00:00'),
(134, 'Intermares (bairro de Cabedelo)', 279, '2010-08-28 00:00:00'),
(135, 'Ipês', 279, '2010-08-28 00:00:00'),
(136, 'Jaguaribe', 279, '2010-08-28 00:00:00'),
(137, 'Jardim 13 de Maio', 279, '2010-08-28 00:00:00'),
(138, 'Jardim Cidade Universitária', 279, '2010-08-28 00:00:00'),
(139, 'Jardim Esther', 279, '2010-08-28 00:00:00'),
(140, 'Jardim Luna', 279, '2010-08-28 00:00:00'),
(141, 'Jardim Mangueira', 279, '2010-08-28 00:00:00'),
(142, 'Jardim Oceania', 279, '2010-08-28 00:00:00'),
(143, 'Jardim Planalto', 279, '2010-08-28 00:00:00'),
(144, 'Jardim São Paulo', 279, '2010-08-28 00:00:00'),
(145, 'Jardim Veneza', 279, '2010-08-28 00:00:00'),
(146, 'José Américo', 279, '2010-08-28 00:00:00'),
(147, 'João Agripino', 279, '2010-08-28 00:00:00'),
(148, 'João Paulo II', 279, '2010-08-28 00:00:00'),
(149, 'Manaíra', 279, '2010-08-28 00:00:00'),
(150, 'Mandacaru', 279, '2010-08-28 00:00:00'),
(151, 'Mangabeira', 279, '2010-08-28 00:00:00'),
(152, 'Miramar', 279, '2010-08-28 00:00:00'),
(153, 'Bairros dos Novais', 279, '2010-08-28 00:00:00'),
(154, 'Oitizeiro', 279, '2010-08-28 00:00:00'),
(155, 'Padre Zé', 279, '2010-08-28 00:00:00'),
(156, 'Paratibe', 279, '2010-08-28 00:00:00'),
(157, 'Penha', 279, '2010-08-28 00:00:00'),
(158, 'Planalto Boa Esperança', 279, '2010-08-28 00:00:00'),
(159, 'Praia do Sol', 279, '2010-08-28 00:00:00'),
(160, 'Rangel', 279, '2010-08-28 00:00:00'),
(161, 'Róger', 279, '2010-08-28 00:00:00'),
(162, 'São José', 279, '2010-08-28 00:00:00'),
(163, 'Tambauzinho', 279, '2010-08-28 00:00:00'),
(164, 'Tambaú', 279, '2010-08-28 00:00:00'),
(165, 'Tambiá', 279, '2010-08-28 00:00:00'),
(166, 'Torre', 279, '2010-08-28 00:00:00'),
(167, 'Valentina Figueiredo', 279, '2010-08-28 00:00:00'),
(168, 'Varadouro', 279, '2010-08-28 00:00:00');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_cidades`
--

DROP TABLE IF EXISTS `tbl_cidades`;
CREATE TABLE IF NOT EXISTS `tbl_cidades` (
  `intCod` int(11) NOT NULL,
  `vcrNome` varchar(100) NOT NULL,
  `intCodUF` int(11) NOT NULL,
  `tspVersao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intCod`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tbl_cidades`
--

INSERT INTO `tbl_cidades` (`intCod`, `vcrNome`, `intCodUF`, `tspVersao`) VALUES
(1, 'Abreu e Lima', 17, '2010-08-28 00:00:00'),
(2, 'Afogados da Ingazeira', 17, '2010-08-28 00:00:00'),
(3, 'Afrânio', 17, '2010-08-28 00:00:00'),
(4, 'Agrestina', 17, '2010-08-28 00:00:00'),
(5, 'Água Preta', 17, '2010-08-28 00:00:00'),
(6, 'Águas Belas', 17, '2010-08-28 00:00:00'),
(7, 'Alagoinha', 17, '2010-08-28 00:00:00'),
(8, 'Aliança', 17, '2010-08-28 00:00:00'),
(9, 'Altinho', 17, '2010-08-28 00:00:00'),
(10, 'Amaraji', 17, '2010-08-28 00:00:00'),
(11, 'Angelim', 17, '2010-08-28 00:00:00'),
(12, 'Araçoiaba', 17, '2010-08-28 00:00:00'),
(13, 'Araripina', 17, '2010-08-28 00:00:00'),
(14, 'Arcoverde', 17, '2010-08-28 00:00:00'),
(15, 'Barra de Guabiraba', 17, '2010-08-28 00:00:00'),
(16, 'Barreiros', 17, '2010-08-28 00:00:00'),
(17, 'Belém de Maria', 17, '2010-08-28 00:00:00'),
(18, 'Belém de São Francisco', 17, '2010-08-28 00:00:00'),
(19, 'Belo Jardim', 17, '2010-08-28 00:00:00'),
(20, 'Betânia', 17, '2010-08-28 00:00:00'),
(21, 'Bezerros', 17, '2010-08-28 00:00:00'),
(22, 'Bodocó', 17, '2010-08-28 00:00:00'),
(23, 'Bom Conselho', 17, '2010-08-28 00:00:00'),
(24, 'Bom Jardim', 17, '2010-08-28 00:00:00'),
(25, 'Bonito', 17, '2010-08-28 00:00:00'),
(26, 'Brejão', 17, '2010-08-28 00:00:00'),
(27, 'Brejinho', 17, '2010-08-28 00:00:00'),
(28, 'Brejo da Madre de Deus', 17, '2010-08-28 00:00:00'),
(29, 'Buenos Aires', 17, '2010-08-28 00:00:00'),
(30, 'Buíque', 17, '2010-08-28 00:00:00'),
(31, 'Cabo de Santo Agostinho', 17, '2010-08-28 00:00:00'),
(32, 'Cabrobó', 17, '2010-08-28 00:00:00'),
(33, 'Cachoeirinha', 17, '2010-08-28 00:00:00'),
(34, 'Caetés', 17, '2010-08-28 00:00:00'),
(35, 'Calçado', 17, '2010-08-28 00:00:00'),
(36, 'Calumbi', 17, '2010-08-28 00:00:00'),
(37, 'Camaragibe', 17, '2010-08-28 00:00:00'),
(38, 'Camocim de São Félix', 17, '2010-08-28 00:00:00'),
(39, 'Camutanga', 17, '2010-08-28 00:00:00'),
(40, 'Canhotinho', 17, '2010-08-28 00:00:00'),
(41, 'Capoeiras', 17, '2010-08-28 00:00:00'),
(42, 'Carnaíba', 17, '2010-08-28 00:00:00'),
(43, 'Carnaubeira da Penha', 17, '2010-08-28 00:00:00'),
(44, 'Carpina', 17, '2010-08-28 00:00:00'),
(45, 'Caruaru', 17, '2010-08-28 00:00:00'),
(46, 'Casinhas', 17, '2010-08-28 00:00:00'),
(47, 'Catende', 17, '2010-08-28 00:00:00'),
(48, 'Cedro', 17, '2010-08-28 00:00:00'),
(49, 'Chã de Alegria', 17, '2010-08-28 00:00:00'),
(50, 'Chã Grande', 17, '2010-08-28 00:00:00'),
(51, 'Condado', 17, '2010-08-28 00:00:00'),
(52, 'Correntes', 17, '2010-08-28 00:00:00'),
(53, 'Cortês', 17, '2010-08-28 00:00:00'),
(54, 'Cumaru', 17, '2010-08-28 00:00:00'),
(55, 'Cupira', 17, '2010-08-28 00:00:00'),
(56, 'Custódia', 17, '2010-08-28 00:00:00'),
(57, 'Dormentes', 17, '2010-08-28 00:00:00'),
(58, 'Escada', 17, '2010-08-28 00:00:00'),
(59, 'Exu', 17, '2010-08-28 00:00:00'),
(60, 'Feira Nova', 17, '2010-08-28 00:00:00'),
(61, 'Fernando de Noronha', 17, '2010-08-28 00:00:00'),
(62, 'Ferreiros', 17, '2010-08-28 00:00:00'),
(63, 'Flores', 17, '2010-08-28 00:00:00'),
(64, 'Floresta', 17, '2010-08-28 00:00:00'),
(65, 'Frei Miguelinho', 17, '2010-08-28 00:00:00'),
(66, 'Gameleira', 17, '2010-08-28 00:00:00'),
(67, 'Garanhuns', 17, '2010-08-28 00:00:00'),
(68, 'Glória do Goitá', 17, '2010-08-28 00:00:00'),
(69, 'Goiana', 17, '2010-08-28 00:00:00'),
(70, 'Granito', 17, '2010-08-28 00:00:00'),
(71, 'Gravatá', 17, '2010-08-28 00:00:00'),
(72, 'Iati', 17, '2010-08-28 00:00:00'),
(73, 'Ibimirim', 17, '2010-08-28 00:00:00'),
(74, 'Ibirajuba', 17, '2010-08-28 00:00:00'),
(75, 'Igarassu', 17, '2010-08-28 00:00:00'),
(76, 'Iguaraci', 17, '2010-08-28 00:00:00'),
(77, 'Inajá', 17, '2010-08-28 00:00:00'),
(78, 'Ingazeira', 17, '2010-08-28 00:00:00'),
(79, 'Ipojuca', 17, '2010-08-28 00:00:00'),
(80, 'Ipubi', 17, '2010-08-28 00:00:00'),
(81, 'Itacuruba', 17, '2010-08-28 00:00:00'),
(82, 'Itaíba', 17, '2010-08-28 00:00:00'),
(83, 'Itamaracá', 17, '2010-08-28 00:00:00'),
(84, 'Itambé', 17, '2010-08-28 00:00:00'),
(85, 'Itapetim', 17, '2010-08-28 00:00:00'),
(86, 'Itapissuma', 17, '2010-08-28 00:00:00'),
(87, 'Itaquitinga', 17, '2010-08-28 00:00:00'),
(88, 'Jaboatão dos Guararapes', 17, '2010-08-28 00:00:00'),
(89, 'Jaqueira', 17, '2010-08-28 00:00:00'),
(90, 'Jataúba', 17, '2010-08-28 00:00:00'),
(91, 'Jatobá', 17, '2010-08-28 00:00:00'),
(92, 'João Alfredo', 17, '2010-08-28 00:00:00'),
(93, 'Joaquim Nabuco', 17, '2010-08-28 00:00:00'),
(94, 'Jucati', 17, '2010-08-28 00:00:00'),
(95, 'Jupi', 17, '2010-08-28 00:00:00'),
(96, 'Jurema', 17, '2010-08-28 00:00:00'),
(97, 'Lagoa do Carro', 17, '2010-08-28 00:00:00'),
(98, 'Lagoa do Itaenga', 17, '2010-08-28 00:00:00'),
(99, 'Lagoa do Ouro', 17, '2010-08-28 00:00:00'),
(100, 'Lagoa dos Gatos', 17, '2010-08-28 00:00:00'),
(101, 'Lagoa Grande', 17, '2010-08-28 00:00:00'),
(102, 'Lajedo', 17, '2010-08-28 00:00:00'),
(103, 'Limoeiro', 17, '2010-08-28 00:00:00'),
(104, 'Macaparana', 17, '2010-08-28 00:00:00'),
(105, 'Machados', 17, '2010-08-28 00:00:00'),
(106, 'Manari', 17, '2010-08-28 00:00:00'),
(107, 'Maraial', 17, '2010-08-28 00:00:00'),
(108, 'Mirandiba', 17, '2010-08-28 00:00:00'),
(109, 'Moreilândia', 17, '2010-08-28 00:00:00'),
(110, 'Moreno', 17, '2010-08-28 00:00:00'),
(111, 'Nazaré da Mata', 17, '2010-08-28 00:00:00'),
(112, 'Olinda', 17, '2010-08-28 00:00:00'),
(113, 'Orobó', 17, '2010-08-28 00:00:00'),
(114, 'Orocó', 17, '2010-08-28 00:00:00'),
(115, 'Ouricuri', 17, '2010-08-28 00:00:00'),
(116, 'Palmares', 17, '2010-08-28 00:00:00'),
(117, 'Palmeirina', 17, '2010-08-28 00:00:00'),
(118, 'Panelas', 17, '2010-08-28 00:00:00'),
(119, 'Paranatama', 17, '2010-08-28 00:00:00'),
(120, 'Parnamirim', 17, '2010-08-28 00:00:00'),
(121, 'Passira', 17, '2010-08-28 00:00:00'),
(122, 'Paudalho', 17, '2010-08-28 00:00:00'),
(123, 'Paulista', 17, '2010-08-28 00:00:00'),
(124, 'Pedra', 17, '2010-08-28 00:00:00'),
(125, 'Pesqueira', 17, '2010-08-28 00:00:00'),
(126, 'Petrolândia', 17, '2010-08-28 00:00:00'),
(127, 'Petrolina', 17, '2010-08-28 00:00:00'),
(128, 'Poção', 17, '2010-08-28 00:00:00'),
(129, 'Pombos', 17, '2010-08-28 00:00:00'),
(130, 'Primavera', 17, '2010-08-28 00:00:00'),
(131, 'Quipapá', 17, '2010-08-28 00:00:00'),
(132, 'Quixaba', 17, '2010-08-28 00:00:00'),
(133, 'Recife', 17, '2010-08-28 00:00:00'),
(134, 'Riacho das Almas', 17, '2010-08-28 00:00:00'),
(135, 'Ribeirão', 17, '2010-08-28 00:00:00'),
(136, 'Rio Formoso', 17, '2010-08-28 00:00:00'),
(137, 'Sairé', 17, '2010-08-28 00:00:00'),
(138, 'Salgadinho', 17, '2010-08-28 00:00:00'),
(139, 'Salgueiro', 17, '2010-08-28 00:00:00'),
(140, 'Saloá', 17, '2010-08-28 00:00:00'),
(141, 'Sanharó', 17, '2010-08-28 00:00:00'),
(142, 'Santa Cruz', 17, '2010-08-28 00:00:00'),
(143, 'Santa Cruz da Baixa Verde', 17, '2010-08-28 00:00:00'),
(144, 'Santa Cruz do Capibaribe', 17, '2010-08-28 00:00:00'),
(145, 'Santa Filomena', 17, '2010-08-28 00:00:00'),
(146, 'Santa Maria da Boa Vista', 17, '2010-08-28 00:00:00'),
(147, 'Santa Maria do Cambucá', 17, '2010-08-28 00:00:00'),
(148, 'Santa Terezinha', 17, '2010-08-28 00:00:00'),
(149, 'São Benedito do Sul', 17, '2010-08-28 00:00:00'),
(150, 'São Bento do Una', 17, '2010-08-28 00:00:00'),
(151, 'São Caitano', 17, '2010-08-28 00:00:00'),
(152, 'São João', 17, '2010-08-28 00:00:00'),
(153, 'São Joaquim do Monte', 17, '2010-08-28 00:00:00'),
(154, 'São José da Coroa Grande', 17, '2010-08-28 00:00:00'),
(155, 'São José do Belmonte', 17, '2010-08-28 00:00:00'),
(156, 'São José do Egito', 17, '2010-08-28 00:00:00'),
(157, 'São Lourenço da Mata', 17, '2010-08-28 00:00:00'),
(158, 'São Vicente Ferrer', 17, '2010-08-28 00:00:00'),
(159, 'Serra Talhada', 17, '2010-08-28 00:00:00'),
(160, 'Serrita', 17, '2010-08-28 00:00:00'),
(161, 'Sertânia', 17, '2010-08-28 00:00:00'),
(162, 'Sirinhaém', 17, '2010-08-28 00:00:00'),
(163, 'Solidão', 17, '2010-08-28 00:00:00'),
(164, 'Surubim', 17, '2010-08-28 00:00:00'),
(165, 'Tabira', 17, '2010-08-28 00:00:00'),
(166, 'Tacaimbó', 17, '2010-08-28 00:00:00'),
(167, 'Tacaratu', 17, '2010-08-28 00:00:00'),
(168, 'Tamandaré', 17, '2010-08-28 00:00:00'),
(169, 'Taquaritinga do Norte', 17, '2010-08-28 00:00:00'),
(170, 'Terezinha', 17, '2010-08-28 00:00:00'),
(171, 'Terra Nova', 17, '2010-08-28 00:00:00'),
(172, 'Timbaúba', 17, '2010-08-28 00:00:00'),
(173, 'Toritama', 17, '2010-08-28 00:00:00'),
(174, 'Tracunhaém', 17, '2010-08-28 00:00:00'),
(175, 'Trindade', 17, '2010-08-28 00:00:00'),
(176, 'Triunfo', 17, '2010-08-28 00:00:00'),
(177, 'Tupanatinga', 17, '2010-08-28 00:00:00'),
(178, 'Tuparetama', 17, '2010-08-28 00:00:00'),
(179, 'Venturosa', 17, '2010-08-28 00:00:00'),
(180, 'Verdejante', 17, '2010-08-28 00:00:00'),
(181, 'Vertente do Lério', 17, '2010-08-28 00:00:00'),
(182, 'Vertentes', 17, '2010-08-28 00:00:00'),
(183, 'Vicência', 17, '2010-08-28 00:00:00'),
(184, 'Vitória de Santo Antão', 17, '2010-08-28 00:00:00'),
(185, 'Xexéu', 17, '2010-08-28 00:00:00'),
(186, 'Agua Branca', 17, '2010-08-28 00:00:00'),
(187, 'Aguiar', 15, '2010-08-28 00:00:00'),
(188, 'Alagoa Grande', 15, '2010-08-28 00:00:00'),
(189, 'Alagoa Nova', 15, '2010-08-28 00:00:00'),
(190, 'Alagoinha', 15, '2010-08-28 00:00:00'),
(191, 'Alcantil', 15, '2010-08-28 00:00:00'),
(192, 'Algodao de Jandaira', 15, '2010-08-28 00:00:00'),
(193, 'Alhandra', 15, '2010-08-28 00:00:00'),
(194, 'Amparo', 15, '2010-08-28 00:00:00'),
(195, 'Aparecida', 15, '2010-08-28 00:00:00'),
(196, 'Aracagi', 15, '2010-08-28 00:00:00'),
(197, 'Arara', 15, '2010-08-28 00:00:00'),
(198, 'Araruna', 15, '2010-08-28 00:00:00'),
(199, 'Areia de Baraunas', 15, '2010-08-28 00:00:00'),
(200, 'Areia', 15, '2010-08-28 00:00:00'),
(201, 'Areial', 15, '2010-08-28 00:00:00'),
(202, 'Aroeiras', 15, '2010-08-28 00:00:00'),
(203, 'Assuncao', 15, '2010-08-28 00:00:00'),
(204, 'Baia da Traicao', 15, '2010-08-28 00:00:00'),
(205, 'Bananeiras', 15, '2010-08-28 00:00:00'),
(206, 'Barauna', 15, '2010-08-28 00:00:00'),
(207, 'Barra de Santa Rosa', 15, '2010-08-28 00:00:00'),
(208, 'Barra de Santana', 15, '2010-08-28 00:00:00'),
(209, 'Barra de Sao Miguel', 15, '2010-08-28 00:00:00'),
(210, 'Bayeux', 15, '2010-08-28 00:00:00'),
(211, 'Belem do Brejo do Cruz', 15, '2010-08-28 00:00:00'),
(212, 'Belem', 15, '2010-08-28 00:00:00'),
(213, 'Bernardino Batista', 15, '2010-08-28 00:00:00'),
(214, 'Boa Ventura', 15, '2010-08-28 00:00:00'),
(215, 'Boa Vista', 15, '2010-08-28 00:00:00'),
(216, 'Bom Jesus', 15, '2010-08-28 00:00:00'),
(217, 'Bom Sucesso', 15, '2010-08-28 00:00:00'),
(218, 'Bonito de Santa Fe', 15, '2010-08-28 00:00:00'),
(219, 'Boqueirao', 15, '2010-08-28 00:00:00'),
(220, 'Borborema', 15, '2010-08-28 00:00:00'),
(221, 'Brejo do Cruz', 15, '2010-08-28 00:00:00'),
(222, 'Brejo dos Santos', 15, '2010-08-28 00:00:00'),
(223, 'Caapora', 15, '2010-08-28 00:00:00'),
(224, 'Cabaceiras', 15, '2010-08-28 00:00:00'),
(225, 'Cabedelo', 15, '2010-08-28 00:00:00'),
(226, 'Cachoeira dos ?ndios', 15, '2010-08-28 00:00:00'),
(227, 'Cacimba de Areia', 15, '2010-08-28 00:00:00'),
(228, 'Cacimba de Dentro', 15, '2010-08-28 00:00:00'),
(229, 'Cacimbas', 15, '2010-08-28 00:00:00'),
(230, 'Caicara', 15, '2010-08-28 00:00:00'),
(231, 'Cajazeiras', 15, '2010-08-28 00:00:00'),
(232, 'Cajazeirinhas', 15, '2010-08-28 00:00:00'),
(233, 'Caldas Brandao', 15, '2010-08-28 00:00:00'),
(234, 'Camalau', 15, '2010-08-28 00:00:00'),
(235, 'Campina Grande', 15, '2010-08-28 00:00:00'),
(236, 'Capim', 15, '2010-08-28 00:00:00'),
(237, 'Caraubas', 15, '2010-08-28 00:00:00'),
(238, 'Carrapateira', 15, '2010-08-28 00:00:00'),
(239, 'Casserengue', 15, '2010-08-28 00:00:00'),
(240, 'Catingueira', 15, '2010-08-28 00:00:00'),
(241, 'Catole do Rocha', 15, '2010-08-28 00:00:00'),
(242, 'Caturite', 15, '2010-08-28 00:00:00'),
(243, 'Conceicao', 15, '2010-08-28 00:00:00'),
(244, 'Condado', 15, '2010-08-28 00:00:00'),
(245, 'Conde', 15, '2010-08-28 00:00:00'),
(246, 'Congo', 15, '2010-08-28 00:00:00'),
(247, 'Coremas', 15, '2010-08-28 00:00:00'),
(248, 'Coxixola', 15, '2010-08-28 00:00:00'),
(249, 'Cruz do Espirito Santo', 15, '2010-08-28 00:00:00'),
(250, 'Cubati', 15, '2010-08-28 00:00:00'),
(251, 'Cuite de Mamanguape', 15, '2010-08-28 00:00:00'),
(252, 'Cuite', 15, '2010-08-28 00:00:00'),
(253, 'Cuitegi', 15, '2010-08-28 00:00:00'),
(254, 'Curral Velho', 15, '2010-08-28 00:00:00'),
(255, 'Curral de Cima', 15, '2010-08-28 00:00:00'),
(256, 'Damiao', 15, '2010-08-28 00:00:00'),
(257, 'Desterro', 15, '2010-08-28 00:00:00'),
(258, 'Diamante', 15, '2010-08-28 00:00:00'),
(259, 'Dona Ines', 15, '2010-08-28 00:00:00'),
(260, 'Duas Estradas', 15, '2010-08-28 00:00:00'),
(261, 'Emas', 15, '2010-08-28 00:00:00'),
(262, 'Esperanca', 15, '2010-08-28 00:00:00'),
(263, 'Fagundes', 15, '2010-08-28 00:00:00'),
(264, 'Frei Martinho', 15, '2010-08-28 00:00:00'),
(265, 'Gado Bravo', 15, '2010-08-28 00:00:00'),
(266, 'Guarabira', 15, '2010-08-28 00:00:00'),
(267, 'Gurinhem', 15, '2010-08-28 00:00:00'),
(268, 'Gurjao', 15, '2010-08-28 00:00:00'),
(269, 'Ibiara', 15, '2010-08-28 00:00:00'),
(270, 'Igaracy', 15, '2010-08-28 00:00:00'),
(271, 'Imaculada', 15, '2010-08-28 00:00:00'),
(272, 'Inga', 15, '2010-08-28 00:00:00'),
(273, 'Itabaiana', 15, '2010-08-28 00:00:00'),
(274, 'Itaporanga', 15, '2010-08-28 00:00:00'),
(275, 'Itapororoca', 15, '2010-08-28 00:00:00'),
(276, 'Itatuba', 15, '2010-08-28 00:00:00'),
(277, 'Jacarau', 15, '2010-08-28 00:00:00'),
(278, 'Jerico', 15, '2010-08-28 00:00:00'),
(279, 'Joao Pessoa', 15, '2010-08-28 00:00:00'),
(280, 'Juarez Tavora', 15, '2010-08-28 00:00:00'),
(281, 'Juazeirinho', 15, '2010-08-28 00:00:00'),
(282, 'Junco do Serido', 15, '2010-08-28 00:00:00'),
(283, 'Juripiranga', 15, '2010-08-28 00:00:00'),
(284, 'Juru', 15, '2010-08-28 00:00:00'),
(285, 'Lagoa Seca', 15, '2010-08-28 00:00:00'),
(286, 'Lagoa de Dentro', 15, '2010-08-28 00:00:00'),
(287, 'Lagoa', 15, '2010-08-28 00:00:00'),
(288, 'Lastro', 15, '2010-08-28 00:00:00'),
(289, 'Livramento', 15, '2010-08-28 00:00:00'),
(290, 'Logradouro', 15, '2010-08-28 00:00:00'),
(291, 'Lucena', 15, '2010-08-28 00:00:00'),
(292, 'Mae d''Agua', 15, '2010-08-28 00:00:00'),
(293, 'Malta', 15, '2010-08-28 00:00:00'),
(294, 'Mamanguape', 15, '2010-08-28 00:00:00'),
(295, 'Manaira', 15, '2010-08-28 00:00:00'),
(296, 'Marcacao', 15, '2010-08-28 00:00:00'),
(297, 'Mari', 15, '2010-08-28 00:00:00'),
(298, 'Marizopolis', 15, '2010-08-28 00:00:00'),
(299, 'Massaranduba', 15, '2010-08-28 00:00:00'),
(300, 'Mataraca', 15, '2010-08-28 00:00:00'),
(301, 'Matinhas', 15, '2010-08-28 00:00:00'),
(302, 'Mato Grosso', 15, '2010-08-28 00:00:00'),
(303, 'Matureia', 15, '2010-08-28 00:00:00'),
(304, 'Mogeiro', 15, '2010-08-28 00:00:00'),
(305, 'Montadas', 15, '2010-08-28 00:00:00'),
(306, 'Monte Horebe', 15, '2010-08-28 00:00:00'),
(307, 'Monteiro', 15, '2010-08-28 00:00:00'),
(308, 'Mulungu', 15, '2010-08-28 00:00:00'),
(309, 'Natuba', 15, '2010-08-28 00:00:00'),
(310, 'Nazarezinho', 15, '2010-08-28 00:00:00'),
(311, 'Nova Floresta', 15, '2010-08-28 00:00:00'),
(312, 'Nova Olinda', 15, '2010-08-28 00:00:00'),
(313, 'Nova Palmeira', 15, '2010-08-28 00:00:00'),
(314, 'Olho d''Agua', 15, '2010-08-28 00:00:00'),
(315, 'Olivedos', 15, '2010-08-28 00:00:00'),
(316, 'Ouro Velho', 15, '2010-08-28 00:00:00'),
(317, 'Parari', 15, '2010-08-28 00:00:00'),
(318, 'Passagem', 15, '2010-08-28 00:00:00'),
(319, 'Patos', 15, '2010-08-28 00:00:00'),
(320, 'Paulista', 15, '2010-08-28 00:00:00'),
(321, 'Pedra Branca', 15, '2010-08-28 00:00:00'),
(322, 'Pedra Lavrada', 15, '2010-08-28 00:00:00'),
(323, 'Pedras de Fogo', 15, '2010-08-28 00:00:00'),
(324, 'Pedro Regio', 15, '2010-08-28 00:00:00'),
(325, 'Pianco', 15, '2010-08-28 00:00:00'),
(326, 'Picui', 15, '2010-08-28 00:00:00'),
(327, 'Pilar', 15, '2010-08-28 00:00:00'),
(328, 'Piloes', 15, '2010-08-28 00:00:00'),
(329, 'Piloezinhos', 15, '2010-08-28 00:00:00'),
(330, 'Pirpirituba', 15, '2010-08-28 00:00:00'),
(331, 'Pitimbu', 15, '2010-08-28 00:00:00'),
(332, 'Pocinhos', 15, '2010-08-28 00:00:00'),
(333, 'Poco Dantas', 15, '2010-08-28 00:00:00'),
(334, 'Poco de Jose de Moura', 15, '2010-08-28 00:00:00'),
(335, 'Pombal', 15, '2010-08-28 00:00:00'),
(336, 'Prata', 15, '2010-08-28 00:00:00'),
(337, 'Princesa Isabel', 15, '2010-08-28 00:00:00'),
(338, 'Puxinana', 15, '2010-08-28 00:00:00'),
(339, 'Queimadas', 15, '2010-08-28 00:00:00'),
(340, 'Quixaba', 15, '2010-08-28 00:00:00'),
(341, 'Remigio', 15, '2010-08-28 00:00:00'),
(342, 'Riachao do Bacamarte', 15, '2010-08-28 00:00:00'),
(343, 'Riachao do Poco', 15, '2010-08-28 00:00:00'),
(344, 'Riachao', 15, '2010-08-28 00:00:00'),
(345, 'Riacho de Santo Antonio', 15, '2010-08-28 00:00:00'),
(346, 'Riacho dos Cavalos', 15, '2010-08-28 00:00:00'),
(347, 'Rio Tinto', 15, '2010-08-28 00:00:00'),
(348, 'Salgadinho', 15, '2010-08-28 00:00:00'),
(349, 'Salgado de Sao Felix', 15, '2010-08-28 00:00:00'),
(350, 'Santa Cecilia', 15, '2010-08-28 00:00:00'),
(351, 'Santa Cruz', 15, '2010-08-28 00:00:00'),
(352, 'Santa Helena', 15, '2010-08-28 00:00:00'),
(353, 'Santa Ines', 15, '2010-08-28 00:00:00'),
(354, 'Santa Luzia', 15, '2010-08-28 00:00:00'),
(355, 'Santa Rita', 15, '2010-08-28 00:00:00'),
(356, 'Santa Teresinha', 15, '2010-08-28 00:00:00'),
(357, 'Santana de Mangueira', 15, '2010-08-28 00:00:00'),
(358, 'Santana dos Garrotes', 15, '2010-08-28 00:00:00'),
(359, 'Santarem', 15, '2010-08-28 00:00:00'),
(360, 'Santo Andre', 15, '2010-08-28 00:00:00'),
(361, 'Sao Bento de Pombal', 15, '2010-08-28 00:00:00'),
(362, 'Sao Bento', 15, '2010-08-28 00:00:00'),
(363, 'Sao Domingos de Pombal', 15, '2010-08-28 00:00:00'),
(364, 'Sao Domingos do Cariri', 15, '2010-08-28 00:00:00'),
(365, 'Sao Francisco', 15, '2010-08-28 00:00:00'),
(366, 'Sao Joao do Cariri', 15, '2010-08-28 00:00:00'),
(367, 'Sao Joao do Rio do Peixe', 15, '2010-08-28 00:00:00'),
(368, 'Sao Joao do Tigre', 15, '2010-08-28 00:00:00'),
(369, 'Sao Jose da Lagoa Tapada', 15, '2010-08-28 00:00:00'),
(370, 'Sao Jose de Caiana', 15, '2010-08-28 00:00:00'),
(371, 'Sao Jose de Espinharas', 15, '2010-08-28 00:00:00'),
(372, 'Sao Jose de Piranhas', 15, '2010-08-28 00:00:00'),
(373, 'Sao Jose de Princesa', 15, '2010-08-28 00:00:00'),
(374, 'Sao Jose do Bonfim', 15, '2010-08-28 00:00:00'),
(375, 'Sao Jose do Brejo do Cruz', 15, '2010-08-28 00:00:00'),
(376, 'Sao Jose do Sabugi', 15, '2010-08-28 00:00:00'),
(377, 'Sao Jose dos Cordeiros', 15, '2010-08-28 00:00:00'),
(378, 'Sao Jose dos Ramos', 15, '2010-08-28 00:00:00'),
(379, 'Sao Mamede', 15, '2010-08-28 00:00:00'),
(380, 'Sao Miguel de Taipu', 15, '2010-08-28 00:00:00'),
(381, 'Sao Sebastiao de Lagoa de Roca', 15, '2010-08-28 00:00:00'),
(382, 'Sao Sebastiao do Umbuzeiro', 15, '2010-08-28 00:00:00'),
(383, 'Sape', 15, '2010-08-28 00:00:00'),
(384, 'Serido', 15, '2010-08-28 00:00:00'),
(385, 'Serra Branca', 15, '2010-08-28 00:00:00'),
(386, 'Serra Grande', 15, '2010-08-28 00:00:00'),
(387, 'Serra Redonda', 15, '2010-08-28 00:00:00'),
(388, 'Serra da Raiz', 15, '2010-08-28 00:00:00'),
(389, 'Serraria', 15, '2010-08-28 00:00:00'),
(390, 'Sertaozinho', 15, '2010-08-28 00:00:00'),
(391, 'Sobrado', 15, '2010-08-28 00:00:00'),
(392, 'Solanea', 15, '2010-08-28 00:00:00'),
(393, 'Soledade', 15, '2010-08-28 00:00:00'),
(394, 'Sossego', 15, '2010-08-28 00:00:00'),
(395, 'Sousa', 15, '2010-08-28 00:00:00'),
(396, 'Sume', 15, '2010-08-28 00:00:00'),
(397, 'Tacima', 15, '2010-08-28 00:00:00'),
(398, 'Taperoa', 15, '2010-08-28 00:00:00'),
(399, 'Tavares', 15, '2010-08-28 00:00:00'),
(400, 'Teixeira', 15, '2010-08-28 00:00:00'),
(401, 'Tenorio', 15, '2010-08-28 00:00:00'),
(402, 'Triunfo', 15, '2010-08-28 00:00:00'),
(403, 'Uirauna', 15, '2010-08-28 00:00:00'),
(404, 'Umbuzeiro', 15, '2010-08-28 00:00:00'),
(405, 'Varzea', 15, '2010-08-28 00:00:00'),
(406, 'Vieiropolis', 15, '2010-08-28 00:00:00'),
(407, 'Vista Serrana', 15, '2010-08-28 00:00:00'),
(408, 'Zabele', 15, '2010-08-28 00:00:00');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_clientes`
--

DROP TABLE IF EXISTS `tbl_clientes`;
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
-- RELAÇÕES PARA A TABELA `tbl_clientes`:
--   `intEntregaPadrao`
--       `tbl_entrega` -> `intCodEnt`
--   `intPapelPadrao`
--       `tbl_papel` -> `intCodPap`
--

--
-- Extraindo dados da tabela `tbl_clientes`
--


-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_cobrador`
--

DROP TABLE IF EXISTS `tbl_cobrador`;
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

DROP TABLE IF EXISTS `tbl_entrega`;
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
-- Estrutura da tabela `tbl_estados`
--

DROP TABLE IF EXISTS `tbl_estados`;
CREATE TABLE IF NOT EXISTS `tbl_estados` (
  `intCod` int(11) NOT NULL AUTO_INCREMENT,
  `vcrNome` varchar(40) NOT NULL,
  `vcrSigla` varchar(2) NOT NULL,
  `tspVersao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intCod`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=28 ;

--
-- Extraindo dados da tabela `tbl_estados`
--

INSERT INTO `tbl_estados` (`intCod`, `vcrNome`, `vcrSigla`, `tspVersao`) VALUES
(20, 'RioGrandedoNorte', 'RN', '2010-08-24 22:31:45'),
(19, 'RiodeJaneiro', 'RJ', '2010-08-24 22:31:45'),
(18, 'Piauí', 'PI', '2010-08-24 22:31:45'),
(17, 'Pernambuco', 'PE', '2010-08-24 22:31:45'),
(16, 'Paraná', 'PR', '2010-08-24 22:31:45'),
(15, 'Paraíba', 'PB', '2010-08-24 22:31:45'),
(14, 'Pará', 'PA', '2010-08-24 22:31:45'),
(13, 'MinasGerais', 'MG', '2010-08-24 22:31:45'),
(12, 'MatoGrossodoSul', 'MS', '2010-08-24 22:31:45'),
(11, 'MatoGrosso', 'MT', '2010-08-24 22:31:45'),
(10, 'Maranhão', 'MA', '2010-08-24 22:31:45'),
(9, 'Goiás', 'GO', '2010-08-24 22:31:45'),
(8, 'EspíritoSanto', 'ES', '2010-08-24 22:31:45'),
(7, 'DistritoFederal', 'DF', '2010-08-24 22:31:45'),
(6, 'Ceará', 'CE', '2010-08-24 22:31:45'),
(5, 'Bahia', 'BA', '2010-08-24 22:31:45'),
(4, 'Amazonas', 'AM', '2010-08-24 22:31:45'),
(3, 'Amapá', 'AP', '2010-08-24 22:31:45'),
(2, 'Alagoas', 'AL', '2010-08-24 22:31:45'),
(1, 'Acre', 'AC', '2010-08-24 22:31:45'),
(21, 'RioGrandedoSul', 'RS', '2010-08-24 22:31:45'),
(22, 'Rondônia', 'RO', '2010-08-24 22:31:45'),
(23, 'Roraima', 'RR', '2010-08-24 22:31:45'),
(24, 'SantaCatarina', 'SC', '2010-08-24 22:31:45'),
(25, 'SãoPaulo', 'SP', '2010-08-24 22:31:45'),
(26, 'Sergipe', 'SE', '2010-08-24 22:31:45'),
(27, 'Tocantins', 'TO', '2010-08-24 22:31:45');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_fonecli`
--

DROP TABLE IF EXISTS `tbl_fonecli`;
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
-- RELAÇÕES PARA A TABELA `tbl_fonecli`:
--   `intClienteId`
--       `tbl_clientes` -> `intClienteId`
--

--
-- Extraindo dados da tabela `tbl_fonecli`
--


-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_grupo`
--

DROP TABLE IF EXISTS `tbl_grupo`;
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

DROP TABLE IF EXISTS `tbl_grupomenu`;
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
-- RELAÇÕES PARA A TABELA `tbl_grupomenu`:
--   `intGrupo`
--       `tbl_grupo` -> `intGrupo`
--   `intMenu`
--       `tbl_menu` -> `intMenu`
--

--
-- Extraindo dados da tabela `tbl_grupomenu`
--


-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_linhareq`
--

DROP TABLE IF EXISTS `tbl_linhareq`;
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
-- RELAÇÕES PARA A TABELA `tbl_linhareq`:
--   `intCodPap`
--       `tbl_papel` -> `intCodPap`
--   `intNumreq`
--       `tbl_reqserv` -> `intNumreq`
--

--
-- Extraindo dados da tabela `tbl_linhareq`
--


-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_menu`
--

DROP TABLE IF EXISTS `tbl_menu`;
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
-- RELAÇÕES PARA A TABELA `tbl_menu`:
--   `intMenupai`
--       `tbl_menu` -> `intMenu`
--

--
-- Extraindo dados da tabela `tbl_menu`
--


-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_opcionaisreqserv`
--

DROP TABLE IF EXISTS `tbl_opcionaisreqserv`;
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

DROP TABLE IF EXISTS `tbl_papel`;
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

DROP TABLE IF EXISTS `tbl_parametroprog`;
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

DROP TABLE IF EXISTS `tbl_projetos`;
CREATE TABLE IF NOT EXISTS `tbl_projetos` (
  `intCodProj` int(8) NOT NULL DEFAULT '0',
  `intClienteId` int(11) NOT NULL,
  `vcrProjeto` varchar(100) DEFAULT NULL,
  `tspVersao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`intCodProj`),
  KEY `PROJ_CLIE_FK` (`intClienteId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELAÇÕES PARA A TABELA `tbl_projetos`:
--   `intClienteId`
--       `tbl_clientes` -> `intClienteId`
--

--
-- Extraindo dados da tabela `tbl_projetos`
--


-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_reqserv`
--

DROP TABLE IF EXISTS `tbl_reqserv`;
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
-- RELAÇÕES PARA A TABELA `tbl_reqserv`:
--   `intCodEnt`
--       `tbl_entrega` -> `intCodEnt`
--   `intCodProj`
--       `tbl_projetos` -> `intCodProj`
--

--
-- Extraindo dados da tabela `tbl_reqserv`
--


-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_reqservopcionais`
--

DROP TABLE IF EXISTS `tbl_reqservopcionais`;
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
-- RELAÇÕES PARA A TABELA `tbl_reqservopcionais`:
--   `intNumReq`
--       `tbl_reqserv` -> `intNumreq`
--   `intCodOp`
--       `tbl_opcionaisreqserv` -> `intCod`
--

--
-- Extraindo dados da tabela `tbl_reqservopcionais`
--


-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_reqservusuario`
--

DROP TABLE IF EXISTS `tbl_reqservusuario`;
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
-- RELAÇÕES PARA A TABELA `tbl_reqservusuario`:
--   `intNumReq`
--       `tbl_reqserv` -> `intNumreq`
--   `intCodUsr`
--       `tbl_usuario` -> `intCodUsr`
--

--
-- Extraindo dados da tabela `tbl_reqservusuario`
--


-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_saidamotoqueiro`
--

DROP TABLE IF EXISTS `tbl_saidamotoqueiro`;
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
-- RELAÇÕES PARA A TABELA `tbl_saidamotoqueiro`:
--   `intCodCobr`
--       `tbl_cobrador` -> `intCodCobr`
--   `intNumReq`
--       `tbl_reqserv` -> `intNumreq`
--

--
-- Extraindo dados da tabela `tbl_saidamotoqueiro`
--


-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_statuscobranca`
--

DROP TABLE IF EXISTS `tbl_statuscobranca`;
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
-- RELAÇÕES PARA A TABELA `tbl_statuscobranca`:
--   `intCodCobr`
--       `tbl_cobrador` -> `intCodCobr`
--   `intNumreq`
--       `tbl_reqserv` -> `intNumreq`
--

--
-- Extraindo dados da tabela `tbl_statuscobranca`
--


-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_usuario`
--

DROP TABLE IF EXISTS `tbl_usuario`;
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
-- RELAÇÕES PARA A TABELA `tbl_usuario`:
--   `intGrupo`
--       `tbl_grupo` -> `intGrupo`
--

--
-- Extraindo dados da tabela `tbl_usuario`
--

INSERT INTO `tbl_usuario` (`intCodUsr`, `vcrLogin`, `vcrPassword`, `vcrCpf`, `intPermissao`, `vcrIpMaquina`, `intGrupo`, `vcrNome`, `tspVersao`) VALUES
(6, 'igor@gmail.com', NULL, '02232522493', 0, NULL, NULL, 'asasasas', '2010-08-08 20:56:49'),
(7, 'testeand', NULL, '02278324493', 0, NULL, NULL, 'igorr', '2010-08-08 21:14:37'),
(8, 'testedenovo', NULL, '03432103486', -1, NULL, NULL, 'vamosver22', '2010-08-08 21:16:26');

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
