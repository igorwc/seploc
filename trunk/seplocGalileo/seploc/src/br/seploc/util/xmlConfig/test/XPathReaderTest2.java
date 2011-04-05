package br.seploc.util.xmlConfig.test;
import javax.xml.xpath.XPathConstants;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import br.seploc.util.xmlConfig.XPathReader;
public class XPathReaderTest2 {
	 public XPathReaderTest2() {
	    }
	    
	    public static void main(String[] args){
		        
	        XPathReader reader = new XPathReader("src\\META-INF\\JDBC.xml");
		        
	        // To get a xml attribute.
	        String expression = "/conexoes/conexao[1]/@id";
	        System.out.println(reader.read(expression, 
				XPathConstants.STRING) + "\n");
	        expression = "/conexoes/conexao[1]/host";
	        System.out.println(reader.read(expression, 
				XPathConstants.STRING) + "\n");
	        expression = "/conexoes/conexao[1]/db";
	        System.out.println(reader.read(expression, 
				XPathConstants.STRING) + "\n");
		    // To get a child element's value.'
	         expression = "/conexoes/conexao[2]/@id";
	        System.out.println(reader.read(expression, 
				XPathConstants.STRING) + "\n");
	        expression = "/conexoes/conexao[2]/host";
	        System.out.println(reader.read(expression, 
				XPathConstants.STRING) + "\n");
     
	        
	    }
     
	    private static void traverse(NodeList rootNode){
	        for(int index = 0; index < rootNode.getLength();
			index ++){
	            Node aNode = rootNode.item(index);
	            if (aNode.getNodeType() == Node.ELEMENT_NODE){
	                NodeList childNodes = aNode.getChildNodes();            
	                if (childNodes.getLength() > 0){
						System.out.println("Node Name-->" + 
						aNode.getNodeName() +
							" , Node Value-->" + 
	                    aNode.getTextContent());
					}
	                traverse(aNode.getChildNodes());                
		        }
			}        
	    }
}
