package org.vrprep.model.util;

import java.io.InputStream;
import java.nio.file.Path;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.helpers.DefaultValidationEventHandler;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;
import org.vrprep.model.instance.Instance;

public class InstanceHelper {
	
	/**
	 * Example method for writing XML from an Instance object.
	 * This method also process a validation when marshalling.
	 * 
	 * @param instance
	 * @param outputPath
	 * @throws SAXException
	 * @throws JAXBException
	 */
	public static Instance read(Path inputPath) throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(Instance.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		return (Instance) unmarshaller.unmarshal(inputPath.toFile());
	}

	/**
	 * Example method for writing XML from an Instance object.
	 * This method also process a validation when marshalling.
	 * 
	 * @param instance
	 * @param outputPath
	 * @throws SAXException
	 * @throws JAXBException
	 */
	public static void write(Instance instance, Path outputPath) throws SAXException, JAXBException {
		outputPath.getParent().toFile().mkdirs();

		InputStream stream = Instance.class.getResourceAsStream("/xsd/instance.xsd");
		Source schemaSource = new StreamSource(stream);

		SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = sf.newSchema(schemaSource);

		JAXBContext jc = JAXBContext.newInstance(Instance.class);
		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.setSchema(schema);
		marshaller.setEventHandler(new DefaultValidationEventHandler());
		marshaller.marshal(instance, outputPath.toFile());
	}

}
