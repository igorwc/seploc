package br.seploc.migracao.factories;

import java.sql.Connection;


import br.seploc.migracao.Migra;

public interface MigraFactory {

	  Migra getInstance(Connection copytec, Connection seploc);
	  Migra getInstance(String copytec, String seploc);
}
