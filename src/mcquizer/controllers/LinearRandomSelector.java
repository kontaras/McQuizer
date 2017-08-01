package mcquizer.controllers;

import java.util.List;
import java.util.Random;

import mcquizer.model.interfaces.IMCProblem;

public class LinearRandomSelector implements IProblemSelector {

	/** The problems that we can be picked from */
	private final List<IMCProblem> problems;

	/** The random number generator to use */
	private final Random rng;

	/**
	 * @param probs
	 *            Problems to select from
	 */
	public LinearRandomSelector(IMCProblem.MCProbemList probs)
	{
		this.problems = probs;
		this.rng = new Random();
	}
	
	@Override
	public IMCProblem getNextProblem() {
		int sum = 0;
		for (IMCProblem prob : this.problems)
		{
			sum += prob.getWeight();
		}
		
		int num = this.rng.nextInt(sum);
		for (IMCProblem prob : this.problems)
		{
			num -= prob.getWeight();
			if (num < 0)
			{
				return prob;
			}
		}
		
		assert false : "LinearRandomSelector is out of bounds. Overflow " + num;
		return this.problems.get(this.problems.size() - 1);
	}

}
