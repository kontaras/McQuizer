package mcquizer.model;

import java.util.Arrays;
import java.util.List;

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
	
}
