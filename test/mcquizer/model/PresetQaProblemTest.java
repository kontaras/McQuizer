package mcquizer.model;

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
	
}
