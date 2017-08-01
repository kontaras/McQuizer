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
	public static IProblemSelector getSelector(List<? extends IProblem> probs)
	{
		if (probs instanceof IMCProblem.MCProbemList)
		{
			return new LinearRandomSelector((MCProbemList) probs);
		}
		System.out.println(probs);
		return new LinearRandomSelector(null);
	}
}
