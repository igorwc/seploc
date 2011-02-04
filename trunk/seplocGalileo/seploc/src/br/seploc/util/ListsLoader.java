package br.seploc.util;

import br.seploc.mbeans.AppServiceBean;

public class ListsLoader extends Thread {

	@Override
	public void run() {
		AppServiceBean.carregaListas();
	}

}
