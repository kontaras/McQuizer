package mcquizer.controllers;

import java.util.List;
import java.util.Random;

import mcquizer.model.interfaces.IProblem;

public class LinearRandomSelector<T extends IProblem> implements IProblemSelector<T> {

	/** The problems that we can be picked from */
	private final List<T> problems;

	/** The random number generator to use */
	private final Random rng;

	/**
	 * @param probs
	 *            Problems to select from
	 */
	public LinearRandomSelector(List<T> probs)
	{
		this.problems = probs;
		this.rng = new Random();
	}
	
	@Override
	public T getNextProblem() {
		int sum = 0;
		for (T prob : this.problems)
		{
			sum += prob.getWeight();
		}
		
		int num = this.rng.nextInt(sum);
		for (T prob : this.problems)
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
