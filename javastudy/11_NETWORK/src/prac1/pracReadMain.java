package prac1;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class pracReadMain {

	public static void main(String[] args) {
		try {
			File file = new File("C:\\storage", "xmlprc.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(file);
			
//			Element root = document.getDocumentElement();
//			System.out.println("최상위 요소 : " + root.getNodeName());
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
