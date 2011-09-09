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