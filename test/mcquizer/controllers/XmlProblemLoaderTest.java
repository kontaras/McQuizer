package mcquizer.controllers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;

import org.junit.Assert;

import mcquizer.controllers.XmlProblemLoader;
import mcquizer.model.interfaces.IMCProblem;

public class XmlProblemLoaderTest {

	@Test
	public void testEmpty()
	{
		String testData = "<questions></questions>";
		Assert.assertEquals(0, getProblems(testData).size());
		
	}
	
	private List<IMCProblem> getProblems(String testData) {
		InputStream in = new ByteArrayInputStream(testData.getBytes());
		XmlProblemLoader loader = new XmlProblemLoader(in);
		return loader.getProblems();
	}
}
