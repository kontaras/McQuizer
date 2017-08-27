package mcquizer.controllers;

import java.util.List;
import java.util.Random;

import mcquizer.model.interfaces.IProblem;

/**
 * Select a random problem weighted by score. Internally uses a binary tree to
 * 
 * @author Konstantin Naryshkin
 */
public class BinaryTreeRandomProblemSelector<T extends IProblem> implements IProblemSelector<T>
{
	/** The problems that we can be picked from */
	private final List<T> problems;

	/** The random number generator to use */
	private final Random rng;

	/**
	 * @param problems
	 *            Problems to select from
	 */
	public BinaryTreeRandomProblemSelector(List<T> problems)
	{
		this.problems = problems;
		this.rng = new Random();
	}

	@Override
	public T getNextProblem()
	{
		// TODO: Implement properly

		return this.problems.get(this.rng.nextInt(this.problems.size()));
	}

}
