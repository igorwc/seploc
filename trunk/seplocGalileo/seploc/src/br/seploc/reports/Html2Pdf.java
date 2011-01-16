package br.seploc.reports;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.w3c.dom.Document;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.pdf.PDFEncryption;

import com.itextpdf.text.DocumentException;

public class Html2Pdf {
	public static void convert(String input, OutputStream out)
			throws com.lowagie.text.DocumentException, DocumentException {
		convert(new ByteArrayInputStream(input.getBytes()), out);
	}

	public static void convert(InputStream input, OutputStream out)
			throws DocumentException, com.lowagie.text.DocumentException {
		Tidy tidy = new Tidy(); 
		Document doc = tidy.parseDOM(input, null);
		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocument(doc, null);
		renderer.layout();
		renderer.createPDF(out);
	}
	
	/*
	 * 
	 * 
	 * 
	 * <style>
@page {
size: 17.18in 15.88in;
margin: 0.25in;
-fs-flow-top: "header";
-fs-flow-bottom: "footer";
-fs-flow-left: "left";
-fs-flow-right: "right";
border: thin solid black;
padding: 1em;
}
</style>


# String corpo = "";  
# corpo   = "<style>";  
# corpo += "@page { ";  
# corpo += "      size: 4.18in 6.88in;"; //Tamanho da folha  
# corpo += "      margin: 30px 20px 15px 35px;"; //Margem: Cima Direita Baixo Esquerda  
# corpo += "}";  
# corpo += "</style>";  
# corpo += "<h1 style='color: red'>";  
# corpo += "      Hello PDF";  
# corpo += "</h1>";  
	 */
}
