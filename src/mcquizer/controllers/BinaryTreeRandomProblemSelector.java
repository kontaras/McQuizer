package mcquizer.controllers;

import java.util.List;
import java.util.Random;

import mcquizer.model.interfaces.IMcProblem;

/**
 * Select a random problem weighted by score. Internally uses a binary tree to
 * 
 * @author Konstantin Naryshkin
 */
public class BinaryTreeRandomProblemSelector implements IProblemSelector
{
	/** The problems that we can be picked from */
	private final List<IMcProblem> problems;

	/** The random number generator to use */
	private final Random rng;

	/**
	 * @param problems
	 *            Problems to select from
	 */
	public BinaryTreeRandomProblemSelector(List<IMcProblem> problems)
	{
		this.problems = problems;
		this.rng = new Random();
	}

	@Override
	public IMcProblem getNextProblem()
	{
		// TODO: Implement properly

		return this.problems.get(this.rng.nextInt(this.problems.size()));
	}

}
