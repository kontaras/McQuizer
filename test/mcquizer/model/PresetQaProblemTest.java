package mcquizer.model;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Konstantin Naryshkin
 */
public class PresetQaProblemTest
{
	
	/**
	 * Test method for {@link mcquizer.model.PresetQaProblem#PresetQaProblem(java.lang.String, java.lang.String, double)}.
	 */
	@Test
	@SuppressWarnings("static-method")
	public final void testConstructor()
	{
		String question = "Test question";
		String answer = "The truth";
		double score = 34.0;
		Checker.checkPair(question, answer, score, new PresetQaProblem(question, answer, score));
	}
	
	/**
	 * Test method for {@link mcquizer.model.PresetQaProblem#changeWeight(double)}.
	 */
	@Test
	@SuppressWarnings("static-method")
	public final void testChangeWeight()
	{
		String question = "Test question";
		String answer = "The truth";
		double score = 34.0;
		PresetQaProblem problem = new PresetQaProblem(question, answer, score);
		Assert.assertEquals(score, problem.getWeight(), score / 10000);
		
		double delta = -1.2345465;
		problem.changeWeight(delta);
		Assert.assertEquals(score + delta, problem.getWeight(), score / 10000);
		
		problem.changeWeight(Double.NEGATIVE_INFINITY);
		Assert.assertEquals(1, problem.getWeight(), score / 10000);
	}
}
