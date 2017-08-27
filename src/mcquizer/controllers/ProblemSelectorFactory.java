/**
 * 
 */

package mcquizer.controllers;

import java.util.List;

import mcquizer.model.interfaces.IMCProblem;
import mcquizer.model.interfaces.IMCProblem.MCProbemList;
import mcquizer.model.interfaces.IProblem;

public class ProblemSelectorFactory
{
	public static IProblemSelector<IMCProblem> getSelector(List<? extends IProblem> probs)
	{
		if (probs instanceof IMCProblem.MCProbemList)
		{
			return new LinearRandomSelector<IMCProblem>((MCProbemList) probs);
		}
		System.out.println(probs);
		return new LinearRandomSelector<IMCProblem>(null);
	}
}
