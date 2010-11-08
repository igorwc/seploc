package br.seploc.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.io.xml.DomDriver;

@XStreamAlias("bairros")
public class BairrosRecife {

	@XStreamImplicit(itemFieldName="bairro")
	private List<String> bairro;
	
	
	
	/**
	 * @return the bairro
	 */
	public List<String> getBairro() {
		return bairro;
	}



	public BairrosRecife() {
		XStream xs = new XStream(new DomDriver());
		BairrosRecife e = new BairrosRecife();

        try {
//        	URL myurl = e.getClass().getResource("C:\\Desenvolvimento\\workspace\\helios2\\seploc\\src\\bairros.xml");
           FileInputStream fis = new FileInputStream("C:\\Desenvolvimento\\workspace\\helios2\\seploc\\src\\bairros.xml");
          xs.fromXML(fis, this);

           //print the data from the object that has been read
            System.out.println(e.toString());

       } catch (FileNotFoundException ex) {
            ex.printStackTrace();
       }
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BairrosRecife [bairro=" + bairro + "]";
	}



	/**
	 * @param bairro the bairro to set
	 */
	public void setBairro(List<String> bairro) {
		this.bairro = bairro;
	}
	
	/**
	 * @param bairro
	 */
	public void setBairro( String... bairro) {
		
		this.bairro = Arrays.asList(bairro);
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		XStream xs = new XStream(new DomDriver());
		BairrosRecife e = new BairrosRecife();

        try {
//        	URL myurl = e.getClass().getResource("C:\\Desenvolvimento\\workspace\\helios2\\seploc\\src\\bairros.xml");
           FileInputStream fis = new FileInputStream("C:\\Desenvolvimento\\workspace\\helios2\\seploc\\src\\bairros.xml");
          xs.fromXML(fis, e);

           //print the data from the object that has been read
            System.out.println(e.toString());

       } catch (FileNotFoundException ex) {
            ex.printStackTrace();
       }
        
      //Write to a file in the file system
        try {
        	e.setBairro("teste","xa amarela","vamos ver");
            FileOutputStream fs = new FileOutputStream("C:\\Desenvolvimento\\workspace\\helios2\\seploc\\src\\bairros2.xml");
            xs.toXML(e, fs);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }



	}

}

class RendezvousMessage {

	@XStreamAlias("type")
	private int messageType;

	@XStreamImplicit(itemFieldName="part")
	private List<String> content;

	public RendezvousMessage(int messageType, String... content) {
		this.messageType = messageType;
		this.content = Arrays.asList(content);
	}

}
