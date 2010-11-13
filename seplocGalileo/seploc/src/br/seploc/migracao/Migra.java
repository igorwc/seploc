package br.seploc.migracao;

import java.sql.Connection;
import java.util.List;

public abstract class Migra<T> {

	protected List<T> lista;
	protected Connection seplocConnection;
	protected Connection copytecConnection;

	public abstract void setConexoes(Connection copytec, Connection seploc);

	public List<T> getLista() {
		return lista;
	}

	public void fechaConexoes() {
		try {
			seplocConnection.close();
			copytecConnection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void migraDados() throws Exception{
		seleciona();
		insereDados();
		fechaConexoes();
	}
	
	protected abstract void seleciona() throws Exception;

	protected abstract void insereDados()  throws Exception;

	public void setLista(List<T> lista) {
		this.lista = lista;
	}

	public Connection getSeplocConnection() {
		return seplocConnection;
	}

	public void setSeplocConnection(Connection seplocConnection) {
		this.seplocConnection = seplocConnection;
	}

	public Connection getCopytecConnection() {
		return copytecConnection;
	}

	public void setCopytecConnection(Connection copytecConnection) {
		this.copytecConnection = copytecConnection;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
