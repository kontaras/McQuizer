
package mcquizer.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import mcquizer.model.interfaces.ISelectable;

/**
 * Select a random problem weighted by score. Internally uses a binary tree to
 * 
 * @author Konstantin Naryshkin
 * @param <T> The type of problem that the the selector is selecting
 */
public class BinaryTreeRandomProblemSelector<T extends ISelectable>
		implements IProblemSelector<T>
{
	private static class BinaryNode implements ISelectable
	{
		ISelectable left;
		
		ISelectable right;
		
		double weight;
		
		public BinaryNode(ISelectable l, ISelectable r, double w)
		{
			this.left = l;
			this.right = r;
			this.weight = w;
		}
		
		@Override
		public void changeWeight(double delta)
		{
			this.weight += delta;
		}
		
		@Override
		public double getWeight()
		{
			return this.weight;
		}
		
		public ISelectable getLeft()
		{
			return left;
		}
		
		public ISelectable getRight()
		{
			return right;
		}
	}
	
	/** The problems that we can be picked from */
	private final List<T> problems;
	
	/** The random number generator to use */
	private final Random rng;
	
	private final ISelectable root;
	
	/**
	 * @param problems Problems to select from
	 */
	public BinaryTreeRandomProblemSelector(List<T> problems)
	{
		this.problems = problems;
		this.rng = new Random();
		
		this.root = buildSelectionTree(this.problems);
	}
	
	@SuppressWarnings("unchecked") // We know that current can either be a
																	// BinaryNode or T. If it is not a
																	// BinaryNode...
	@Override
	public T getNextProblem()
	{
		double rand = this.rng.nextDouble() * this.root.getWeight();
		
		ISelectable current = this.root;
		
		while (current instanceof BinaryNode)
		{
			BinaryNode node = (BinaryNode) current;
			if (node.getLeft().getWeight() >= rand)
			{
				current = node.getLeft();
			}
			else
			{
				rand -= node.getLeft().getWeight();
				current = node.getRight();
			}
		}
		
		return (T) current;
	}
	
	private static <T extends ISelectable> ISelectable
			buildSelectionTree(List<T> problems)
	{
		List<ISelectable> currentLayer = new ArrayList<>(problems);
		List<ISelectable> nextLayer;
		while (currentLayer.size() > 1)
		{
			nextLayer = new ArrayList<>(problems.size() / 2 + 1);
			for (int i = 0; i <= problems.size() - 1; i += 2)
			{
				nextLayer.add(new BinaryNode(currentLayer.get(i),
						currentLayer.get(i + 1), currentLayer.get(i).getWeight()
								+ currentLayer.get(i + 1).getWeight()));
			}
			
			currentLayer = nextLayer;
		}
		
		return currentLayer.get(0);
	}
}
