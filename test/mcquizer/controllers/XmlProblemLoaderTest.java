package mcquizer.controllers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;

import org.junit.Assert;

import mcquizer.controllers.XmlProblemLoader;
import mcquizer.model.interfaces.IMCProblem;
import mcquizer.model.interfaces.IQaPair;

public class XmlProblemLoaderTest {

	@Test
	public void testEmptyMc()
	{
		StringBuilder testData = new StringBuilder();
		testData.append("<questions type='multipleChoice'>");
		testData.append("</questions>");
		Assert.assertEquals(0, getProblems(testData.toString()).size());
	}
	
	@Test
	public void testEmptyQa()
	{
		StringBuilder testData = new StringBuilder();
		testData.append("<questions type='match'>");
		testData.append("</questions>");
		Assert.assertEquals(0, getProblems(testData.toString()).size());
	}
	
	@Test
	public void testQa()
	{
		StringBuilder testData = new StringBuilder();
		testData.append("<questions type='pair'>");
		testData.append("<pair question='foo' answer='bar'  />");
		testData.append("</questions>");
		List<IMCProblem> problems = getProblems(testData.toString());
		Assert.assertEquals(1, problems.size());
		
	}
	
	private List<IMCProblem> getProblems(String testData) {
		InputStream in = new ByteArrayInputStream(testData.getBytes());
		XmlProblemLoader loader = new XmlProblemLoader(in);
		return loader.getProblems();
	}
	
	private void checkPair(String question, String answer, double score, IQaPair actual) {
		Assert.assertEquals(question, actual.getQuestion());
		Assert.assertEquals(answer, actual.getAnswer());
		Assert.assertEquals(score, actual.getWeight(), score/100000);
	}
}
