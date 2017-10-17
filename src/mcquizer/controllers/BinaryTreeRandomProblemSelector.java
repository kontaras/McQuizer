
package mcquizer.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import mcquizer.model.interfaces.ISelectable;

/**
 * Select a random problem weighted by score. Internally uses a binary tree to
 * get log(n) selection.
 * 
 * @author Konstantin Naryshkin
 * @param <T> The type of problem that the the selector is selecting
 */
public class BinaryTreeRandomProblemSelector<T extends ISelectable>
		implements IProblemSelector<T>
{
	/**
	 * A node making up the selection tree
	 *
	 * @author Konstantin Naryshkin
	 */
	private static class BinaryNode implements ISelectable
	{
		/**
		 * Left half of the subtree
		 */
		ISelectable left;
		
		/**
		 * Right half of the subtree
		 */
		ISelectable right;
		
		/**
		 * The total weight of all the problems under this node
		 */
		double weight;
		
		/**
		 * @param l Left subtree
		 * @param r Right subtree
		 * @param w Total weight of all problems in this tree
		 */
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
		
		/**
		 * @return The left subtree
		 */
		public ISelectable getLeft()
		{
			return this.left;
		}
		
		/**
		 * @return The right subtree
		 */
		public ISelectable getRight()
		{
			return this.right;
		}
	}
	
	/** The problems that we can be picked from */
	private final List<T> problems;
	
	/** The random number generator to use */
	private final Random rng;
	
	/**
	 * The root of the selection tree
	 */
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
	
	/**
	 * Build a balanced selection tree
	 * 
	 * @param leaves The problems to put as leaves of the tree
	 * @return A new tree
	 */
	private ISelectable buildSelectionTree(List<T> leaves)
	{
		List<ISelectable> currentLayer = new ArrayList<>(leaves);
		List<ISelectable> nextLayer;
		while (currentLayer.size() > 1)
		{
			nextLayer = new ArrayList<>(leaves.size() / 2 + 1);
			for (int i = 0; i <= leaves.size() - 1; i += 2)
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
