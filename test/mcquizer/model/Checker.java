package mcquizer.model;

import java.util.List;

import org.junit.Assert;

import mcquizer.model.interfaces.IMCProblem;
import mcquizer.model.interfaces.IProblem;
import mcquizer.model.interfaces.IQaPair;

/**
 *
 * @author Konstantin Naryshkin
 */
public class Checker
{

	/**
	 * Check the value of a given {@link IQaPair}
	 * 
	 * @param question The expected question
	 * @param answer The expected answer
	 * @param score The expected score
	 * @param actual The value to test
	 */
	public static void checkPair(String question, String answer, double score,
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
	public static void checkProblem(String question, double score, IProblem actual) {
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
	public static void checkMc(String question, List<String> answers, double score, int correctAnswer,
			IMCProblem actual)
	{
		checkProblem(question, score, actual);
		Assert.assertEquals(answers, actual.getPossibleAnswers());
		Assert.assertEquals(correctAnswer, actual.getCorrectAnswer());
	}
	
}
