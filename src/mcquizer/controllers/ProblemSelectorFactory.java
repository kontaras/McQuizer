package mcquizer.controllers;

import java.util.List;

import mcquizer.model.interfaces.IMCProblem;
import mcquizer.model.interfaces.IMCProblem.MCProbemList;
import mcquizer.model.interfaces.IProblem;

/**
 * A factory to return the appropriate {@link IMCProblem} for a given {@link IProblem}
 *
 * @author Konstantin Naryshkin
 */
public class ProblemSelectorFactory
{
	/**
	 * Get an {@link IProblemSelector} that returns {@link IMCProblem}.
	 * 
	 * @param probs	The problem list that the Selector will pick from
	 * @return	A selector that randomly picks among the problems in the list
	 * @throws IllegalArgumentException If the problem list is of an unexpected type.
	 */
	public static IProblemSelector<IMCProblem> getMcSelector(List<? extends IProblem> probs)
	{
		if (probs instanceof IMCProblem.MCProbemList)
		{
			return new LinearRandomSelector<>((MCProbemList) probs);
		}
		throw new IllegalArgumentException("Unexpeced problem list " + probs.getClass());
	}
}
