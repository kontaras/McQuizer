package mcquizer.model;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Konstantin Naryshkin
 */
public class PresetMcProblemTest
{
	
	/**
	 * Test method for {@link mcquizer.model.PresetMcProblem#PresetMcProblem(java.lang.String, java.util.List, int, int)}.
	 */
	@Test
	@SuppressWarnings("static-method")
	public final void testConstructor()
	{
		String question = "Test question";
		List<String> answers =  Arrays.asList("answer");
		double score = 3.14;
		int correctChoice = 0;
		Checker.checkMc(question, answers, score, correctChoice,
				new PresetMcProblem(question, answers, correctChoice, score));
	}
	
	/**
	 * Test method for {@link mcquizer.model.PresetMcProblem#changeWeight(double)}.
	 */
	@Test
	@SuppressWarnings("static-method")
	public final void testChangeWeight()
	{
		String question = "Test question";
		List<String> answers =  Arrays.asList("answer");
		double score = 3.14;
		int correctChoice = 0;
		final PresetMcProblem problem = new PresetMcProblem(question, answers, correctChoice, score);
		Assert.assertEquals(score, problem.getWeight(), score / 10000);
		
		double delta = -1.2345465;
		problem.changeWeight(delta);
		Assert.assertEquals(score + delta, problem.getWeight(), score / 10000);
		
		problem.changeWeight(Double.NEGATIVE_INFINITY);
		Assert.assertEquals(1, problem.getWeight(), score / 10000);
	}
}
