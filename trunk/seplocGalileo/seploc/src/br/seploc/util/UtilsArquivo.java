package br.seploc.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class UtilsArquivo {
	/**
	 * Salva o conteudo de uma variavel em um arquivo
	 * 
	 * @param arquivo
	 * @param conteudo
	 * @param adicionar
	 *            se true adicionar no final do arquivo
	 * @throws IOException
	 */
	public static void salvar(String arquivo, String conteudo, boolean adicionar)
			throws IOException {

		FileWriter fw = new FileWriter(arquivo, adicionar);

		fw.write(conteudo);
		fw.close();
	}

	/**
	 * Carrega o conteudo de um arquivo em uma String, se o aquivo nao existir,
	 * retornara null.
	 * 
	 * @param arquivo
	 * @return conteudo
	 * @throws Exception
	 */
	public static String carregar(String arquivo) throws FileNotFoundException,
			IOException {

		File file = new File(arquivo);

		if (!file.exists()) {
			return null;
		}

		BufferedReader br = new BufferedReader(new FileReader(arquivo));
		StringBuffer bufSaida = new StringBuffer();

		String linha;
		while ((linha = br.readLine()) != null) {
			bufSaida.append(linha + "\n");
		}
		br.close();
		return bufSaida.toString();
	}

	public static boolean CriarDiretorio(String arquivo) {
		// Cria um diretorio
		File dir = new File(arquivo);
		if (dir.exists()) {
			return true;
		}

		boolean success = (new File(arquivo)).mkdir();
		if (success) {
			System.out.println("Diretorio: " + arquivo + " criado");
		}
		return success;
	}

	public static void limpaDiretorio(String diretorio) {
		File dir = new File(diretorio);
		if (dir.exists()) {
			File[] lista = dir.listFiles();
			for (File arq : lista) {
				arq.delete();
			}
		}
	}

	public static void main(String args[]) {
		limpaDiretorio("c:\\temp");
	}
}
