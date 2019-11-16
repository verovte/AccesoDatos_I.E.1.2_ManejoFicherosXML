package ejercicio01;

import java.io.FileOutputStream;

import java.io.OutputStream;

import javax.xml.transform.Source;

import javax.xml.transform.Transformer;

import javax.xml.transform.TransformerFactory;

import javax.xml.transform.stream.StreamResult;

import javax.xml.transform.stream.StreamSource;

public class HTML {

	public static void main(String[] args) {

		try {

			TransformerFactory tf = TransformerFactory.newInstance();

			Source xslFile = new StreamSource("alumnosPlantilla.xsl");

			Source xmlFile = new StreamSource("alumnos.xml");

			String outputFileName = "pruebaAlumnos.html";

			OutputStream htmlFile = new FileOutputStream(outputFileName);

			Transformer trasformer = tf.newTransformer(xslFile);

			trasformer.transform(xmlFile, new StreamResult(htmlFile));

		} catch (Exception e)

		{

			e.printStackTrace();

		}

	}

}
