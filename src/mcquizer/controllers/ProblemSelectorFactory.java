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
	 * @return	A selector that randomly picks amoung the problems in the list
	 */
	public static IProblemSelector<IMCProblem> getMcSelector(List<? extends IProblem> probs)
	{
		if (probs instanceof IMCProblem.MCProbemList)
		{
			return new LinearRandomSelector<>((MCProbemList) probs);
		}
		//TODO: Throw if it is not an expected problem type
		System.out.println(probs);
		return new LinearRandomSelector<>(null);
	}
}
