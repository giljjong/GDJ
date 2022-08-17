package prac1;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class pracReadMain {

	public static void main(String[] args) {
		try {
		File file = new File("C:\\storage", "AnyConv.com__xmlprc.xml");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(file);
		
		Element root = doc.getDocumentElement();
		System.out.println("최상위 요소 : " + root.getNodeName());
		
		Node firstNode = root.getFirstChild();
		
		Node article = firstNode.getNextSibling();
		
		NodeList childList = article.getChildNodes();
		
		for(int i = 0; i < childList.getLength(); i++) {
			Node item = childList.item(i);
			if(item.getNodeType() == Node.ELEMENT_NODE) { // 노드의 타입이 Element일 경우(공백이 아닌 경우)
				System.out.println(item.getNodeName());
				System.out.println(item.getTextContent());
			} else {
				System.out.println("공백 입니다.");
			}
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
