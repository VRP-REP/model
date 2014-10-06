package org.vrprep.model.test.util;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;

import javax.xml.bind.JAXBException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.vrprep.model.instance.Instance;
import org.vrprep.model.util.Instances;
import org.xml.sax.SAXException;

@RunWith(Parameterized.class)
public class InstancesTest {

	private Path inputPath;
	private Path outputPath;

	public InstancesTest(Path inputPath, Path outputPath) {
		this.inputPath = inputPath;
		this.outputPath = outputPath;
	}

	@Parameterized.Parameters
	public static Collection<?> data() {
		URL resourceUrl = Instances.class.getResource("/instance-example.xml");
		Path resourcePath = null;
		try {
			resourcePath = Paths.get(resourceUrl.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		Path outputPath = Paths.get(resourcePath.getParent().toString(), "output.xml");

		return Arrays.asList(new Object[][] {
				{resourcePath, outputPath}
		});
	}

	@Test
	public void readAndWriteTest() {
		Instance instance = null;
		try {
			instance = Instances.read(inputPath);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		assertNotNull(instance);
		
		File file = null;
		try {
			file = Instances.write(instance, outputPath);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		assertTrue(file.exists());
		file.delete();
	}

}
