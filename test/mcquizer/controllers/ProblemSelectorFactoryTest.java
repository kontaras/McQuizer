package mcquizer.controllers;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import mcquizer.model.Checker;
import mcquizer.model.PresetMcProblem;
import mcquizer.model.interfaces.IMCProblem;
import mcquizer.model.interfaces.IMCProblem.MCProbemList;
import mcquizer.model.interfaces.IProblem;

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
		Checker.smartCheckSelectable(prob, selector.getNextProblem());
	}
	
	/**
	 * Test that {@link ProblemSelectorFactory} can handle a list of unknown
	 * problems.
	 */
	@Test(expected = IllegalArgumentException.class)
	@SuppressWarnings({"static-method"})
	public void testInvalidType() {
		ProblemSelectorFactory.getMcSelector(new ArrayList<TestProblem>());
	}
	
	/**
	 * A problem type that nothing has implementation to handle.
	 */
	private class TestProblem implements IProblem {

		@Override
		public void changeWeight(double delta) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public double getWeight() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public String getQuestion() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
}
