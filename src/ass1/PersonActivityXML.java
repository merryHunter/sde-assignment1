package ass1;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class PersonActivityXML {
	
	
	/**
	 * Local path of a file containing people information.
	 */
	public static String xmlPath = "persons.xml";
	
	public static DocumentBuilderFactory domFactory;
	
	public static DocumentBuilder builder;
	
	static {
		domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setNamespaceAware(true);
	}
	
	/**
	 * Return description of an activity preference in the XML by a person id.
	 * @param id person id
	 * @return String description
	 */
	public static String getActivityDescription(int id) {
		String resultDescr = "Not found";
		try {
			builder = domFactory.newDocumentBuilder();
			System.out.println("Loading " + xmlPath);
			Document doc = builder.parse(xmlPath);
			
			XPath xpath = XPathFactory.newInstance().newXPath();
			System.out.println("Reading person info...");
			System.out.println("(using xpath = /people/person/id/text()");
			XPathExpression expr = xpath.compile("/people/person[@id='" + 
								Integer.toString(id) + 
								"']/activitypreference/description/text()");
	
			Object result = expr.evaluate(doc, XPathConstants.NODESET);
			NodeList nodes = (NodeList) result;
			
			// we expect person to have only a single one preferred activity !
			resultDescr = (String) nodes.item(0).getNodeValue();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		return resultDescr;
	}
	
	/**
	 * Return a place of an activity preference in the XML by a person id.
	 * @param id person id
	 * @return String place
	 */
	public static String getActivityPlace(int id) {
		String resultPlace = "Not found";
		try {
			builder = domFactory.newDocumentBuilder();
			System.out.println("Loading " + xmlPath);
			Document doc = builder.parse(xmlPath);
			
			XPath xpath = XPathFactory.newInstance().newXPath();
			System.out.println("Reading person info...");
			System.out.println("(using xpath = /people/person/id/text()");
			XPathExpression expr = xpath.compile("/people/person[@id='" + 
								Integer.toString(id) + 
								"']/activitypreference/place/text()");
	
			Object result = expr.evaluate(doc, XPathConstants.NODESET);
			NodeList nodes = (NodeList) result;
			
			// we expect person to have only a single one preferred activity !
			resultPlace = (String) nodes.item(0).getNodeValue();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		return resultPlace;
	}
	
	public static void printAllPeople() {
		try {
			builder = domFactory.newDocumentBuilder();
			System.out.println("Loading " + xmlPath);
			Document doc = builder.parse(xmlPath);
			
			XPathExpression xp = XPathFactory.newInstance().newXPath().compile("//*");
			NodeList nodes = (NodeList) xp.evaluate(doc, XPathConstants.NODESET);
			for (int i = 0; i < nodes.getLength(); i++) {
				System.out.println(nodes.item(i).getNodeValue());
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args)
			throws ParserConfigurationException, SAXException, IOException, XPathExpressionException  {
		int personId = 5;
		System.out.println("Activity for user with id=" + Integer.toString(personId) + ":\n" +
						 getActivityDescription(personId));
		System.out.println("Activity place for user with id=" + Integer.toString(personId) + ":\n" +
				 getActivityPlace(personId));
		printAllPeople();
		
	}
	
}
