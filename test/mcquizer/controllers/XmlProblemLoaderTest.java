
package mcquizer.controllers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import org.junit.Assert;

import mcquizer.controllers.XmlProblemLoader;
import mcquizer.model.interfaces.IMCProblem;
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
	 * Test a single multiple choice problem set with no answers
	 */
	@SuppressWarnings("static-method")
	@Test
	public void testSingleMcNoAnswer()
	{
		StringBuilder testData = new StringBuilder();
		testData.append("<questions type='multipleChoice'>");
		testData.append("<problem question='foo' weight='1' correct='2'>");
		testData.append("</problem>");
		testData.append("</questions>");
		List<? extends IProblem> actual = getProblems(testData.toString());
		Assert.assertEquals(1, actual.size());
		checkMc("foo", Arrays.asList(), 1, 2, (IMCProblem) actual.get(0));
	}
	
	/**
	 * Test a single multiple choice problem set with one answer
	 */
	@SuppressWarnings("static-method")
	@Test
	public void testSingleMcOneAnswer()
	{
		StringBuilder testData = new StringBuilder();
		testData.append("<questions type='multipleChoice'>");
		testData.append("<problem question='foo' weight='1' correct='2'>");
		testData.append("<answer>q2a1</answer>");
		testData.append("</problem>");
		testData.append("</questions>");
		List<? extends IProblem> actual = getProblems(testData.toString());
		Assert.assertEquals(1, actual.size());
		checkMc("foo", Arrays.asList("q2a1"), 1, 2, (IMCProblem) actual.get(0));
	}
	
	/**
	 * Test a single multiple choice problem set
	 */
	@SuppressWarnings("static-method")
	@Test
	public void testSingleMc()
	{
		StringBuilder testData = new StringBuilder();
		testData.append("<questions type='multipleChoice'>");
		testData.append("<problem question='foo' weight='1' correct='2'>");
		testData.append("<answer>q2a1</answer>");
		testData.append("<answer>q2a2</answer>");
		testData.append("</problem>");
		testData.append("</questions>");
		List<? extends IProblem> actual = getProblems(testData.toString());
		Assert.assertEquals(1, actual.size());
		checkMc("foo", Arrays.asList("q2a1", "q2a2"), 1, 2, (IMCProblem) actual.get(0));
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
		checkProblem(question, score, actual);
		Assert.assertEquals(answer, actual.getAnswer());
	}

	/**
	 * Check the value of a given {@link IProblem}
	 * 
	 * @param question The expected question
	 * @param score The expected score
	 * @param actual The value to test
	 */
	private static void checkProblem(String question, double score, IProblem actual) {
		Assert.assertEquals(question, actual.getQuestion());
		Assert.assertEquals(score, actual.getWeight(), score / 100000);
	}
	
	/**
	 * Check the value of a given {@link IMCProblem}
	 * 
	 * @param question The expected question
	 * @param answers The expected answers
	 * @param correctAnswer The expected correct answer
	 * @param score The expected score
	 * @param actual The value to test
	 */
	private static void checkMc(String question, List<String> answers, double score, int correctAnswer,
			IMCProblem actual)
	{
		checkProblem(question, score, actual);
		Assert.assertEquals(answers, actual.getPossibleAnswers());
		Assert.assertEquals(correctAnswer, actual.getCorrectAnswer());
	}
}
