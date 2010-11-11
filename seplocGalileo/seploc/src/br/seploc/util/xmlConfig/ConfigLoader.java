package br.seploc.util.xmlConfig;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import br.seploc.util.beans.EmpresaBean;

public class ConfigLoader {
	

	public static void main(String args[]) {
		System.out.println(System.getProperty("user.dir"));
		EmpresaBean bean =  ConfigLoader.loadEmpresaBean();

		System.out.println(bean);
	}

	public static EmpresaBean loadEmpresaBean() {
		EmpresaBean bean = new EmpresaBean();
		try {
			File file = new File("seploc/xmls/empresa.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
			NodeList busca = doc.getElementsByTagName("razao");
			Node noRazao = busca.item(0);
//			System.out.println("Razão: "
//					+ noRazao.getNodeName()
//					+ " "
//					+ ((Node) ((NodeList) (((Element) noRazao.getChildNodes())
//							.getChildNodes())).item(0)).getNodeValue());
			
			bean.setRazao(((Node) ((NodeList) (((Element) noRazao.getChildNodes())
							.getChildNodes())).item(0)).getNodeValue());
			busca = doc.getElementsByTagName("endereco");
			Node noEndereco = busca.item(0);
			bean.setEndereco(((Node) ((NodeList) (((Element) noEndereco.getChildNodes())
							.getChildNodes())).item(0)).getNodeValue());
			busca = doc.getElementsByTagName("cep");
			Node noCep = busca.item(0);
			bean.setCep(((Node) ((NodeList) (((Element) noCep.getChildNodes())
							.getChildNodes())).item(0)).getNodeValue());
			busca = doc.getElementsByTagName("fonefax");
			Node noFoneFax = busca.item(0);
			bean.setFoneFax(((Node) ((NodeList) (((Element) noFoneFax.getChildNodes())
							.getChildNodes())).item(0)).getNodeValue());
			busca = doc.getElementsByTagName("identificacao");
			Node noIdentificacao = busca.item(0);
			bean.setIdentificacao((((Node) ((NodeList) (((Element) noIdentificacao.getChildNodes())
							.getChildNodes())).item(0)).getNodeValue()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}

}
