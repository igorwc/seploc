package br.seploc.reports.utils;

import java.util.Comparator;

import br.seploc.reports.beans.CobradorBeanGrid;

public class CobradorBeanGridComparator implements Comparator<CobradorBeanGrid> {

	@Override
	public int compare(CobradorBeanGrid c1, CobradorBeanGrid c2) {
		 
		return c1.getQuantidade()-c2.getQuantidade();
	}

	public CobradorBeanGridComparator() {
		// TODO Auto-generated constructor stub
	}

}
