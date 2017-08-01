
package mcquizer.controllers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;

import org.junit.Assert;

import mcquizer.controllers.XmlProblemLoader;
import mcquizer.model.interfaces.IProblem;
import mcquizer.model.interfaces.IQaPair;

/**
 * Tests for {@link XmlProblemLoader}
 *
 * @author Konstantin Naryshkin
 */
public class XmlProblemLoaderTest
{
	
	/**
	 * Test an empty multiple choice problem set
	 */
	@SuppressWarnings("static-method")
	@Test
	public void testEmptyMc()
	{
		StringBuilder testData = new StringBuilder();
		testData.append("<questions type='multipleChoice'>");
		testData.append("</questions>");
		Assert.assertEquals(0, getProblems(testData.toString()).size());
	}
	
	/**
	 * Test an empty pair problem set
	 */
	@SuppressWarnings("static-method")
	@Test
	public void testEmptyQa()
	{
		StringBuilder testData = new StringBuilder();
		testData.append("<questions type='pair'>");
		testData.append("</questions>");
		Assert.assertEquals(0, getProblems(testData.toString()).size());
	}
	
	/**
	 * Test a basic one question pair problem set
	 */
	@SuppressWarnings("static-method")
	@Test
	public void testSingleQa()
	{
		StringBuilder testData = new StringBuilder();
		testData.append("<questions type='pair'>");
		testData.append("<pair question='foo' answer='bar' weight='1' />");
		testData.append("</questions>");
		List<? extends IProblem> problems = getProblems(testData.toString());
		Assert.assertEquals(1, problems.size());
		checkPair("foo", "bar", 1, (IQaPair) problems.get(0));
	}
	
	/**
	 * Create a new {@link XmlProblemLoader} using a given String as input and
	 * return the result of {@link XmlProblemLoader#getProblems()}
	 * 
	 * @param testData data to load problems from
	 * @return The problems loaded from the data
	 */
	private static List<? extends IProblem> getProblems(String testData)
	{
		InputStream in = new ByteArrayInputStream(testData.getBytes());
		XmlProblemLoader loader = new XmlProblemLoader(in);
		return loader.getProblems();
	}
	
	/**
	 * Check the value of a given {@link IQaPair}
	 * 
	 * @param question The expected question
	 * @param answer The expected answer
	 * @param score The expected score
	 * @param actual The value to test
	 */
	private static void checkPair(String question, String answer, double score,
			IQaPair actual)
	{
		Assert.assertEquals(question, actual.getQuestion());
		Assert.assertEquals(answer, actual.getAnswer());
		Assert.assertEquals(score, actual.getWeight(), score / 100000);
	}
}
