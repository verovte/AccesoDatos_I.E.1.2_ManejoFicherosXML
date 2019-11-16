package ejercicio01;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Dom {

	public static void main(String[] args) {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		TransformerFactory tf = TransformerFactory.newInstance();

		// LEER
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();

			Document doc = (Document) db.parse("alumnos.xml");
			NodeList alumnos = ((org.w3c.dom.Document) doc).getElementsByTagName("alumno");
			for (int i = 0; i < alumnos.getLength(); i++) {
				Node p = alumnos.item(i);
				if (p.getNodeType() == Node.ELEMENT_NODE) {
					Element alumno = (Element) p;
					String nombre = alumno.getElementsByTagName("nombre").item(0).getTextContent();
					String edad = alumno.getElementsByTagName("edad").item(0).getTextContent();
					System.out.println("Alumno: " + nombre + "\nEdad: " + edad);
				}

			}

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// ESCRIBIR
		try {
			Transformer t = tf.newTransformer();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse("alumnos.xml");
			doc.getDocumentElement().normalize();

			Node root = doc.getDocumentElement();
			Element newAlumno = doc.createElement("alumno");

			Element nombre = doc.createElement("nombre");
			nombre.setTextContent("Fue un exito");

			Element edad = doc.createElement("edad");
			edad.setTextContent("32");

			newAlumno.appendChild(nombre);
			newAlumno.appendChild(edad);
			root.appendChild(newAlumno);

			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult("alumnos.xml");
			t.transform(source, result);

		} catch (TransformerConfigurationException | SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}

	}

}
