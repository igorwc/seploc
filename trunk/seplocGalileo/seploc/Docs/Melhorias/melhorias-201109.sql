ALTER TABLE `tbl_usuario` 
  ADD `intGratificacao` 
  INT(2) NOT NULL
  DEFAULT '0'
  AFTER `vcrNome`;
ALTER TABLE `tbl_usuario` 
  ADD `chrAtivo` 
  CHAR(1) NOT NULL
  DEFAULT 'S'
  AFTER `intGratificacao`;   
  
ALTER TABLE  `tbl_papel` CHANGE  `vcrNome`  `vcrNome` VARCHAR( 30 )
CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL DEFAULT  '';
ALTER TABLE  `tbl_papel` ADD  `blEhPapel` TINYINT( 1 ) NULL DEFAULT  '1' AFTER  `dblImpShade`