package mcquizer.controllers;

import java.util.Arrays;

import org.junit.Test;

import mcquizer.model.Checker;
import mcquizer.model.PresetMcProblem;
import mcquizer.model.interfaces.IMCProblem;
import mcquizer.model.interfaces.IMCProblem.MCProbemList;

/**
 * 
 * @author Konstantin Tarashchanskiy
 */
public class ProblemSelectorFactoryTest {

	/**
	 * Test for {@link MCProbemList} to {@link IProblemSelector}<{@link IMCProblem}>
	 */
	@Test
	@SuppressWarnings({"static-method"})
	public void testGetMcSelector() {
		IMCProblem prob = new PresetMcProblem("FOO", Arrays.asList("BAR"), 0, 1);
		MCProbemList list = new MCProbemList();
		list.add(prob);
		IProblemSelector<?> selector = ProblemSelectorFactory.getMcSelector(list);
		Checker.checkSelectable(prob, selector.getNextProblem());
	}

}
