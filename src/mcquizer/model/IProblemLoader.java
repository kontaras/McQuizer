package mcquizer.model;

import java.util.List;

import mcquizer.model.interfaces.IMCProblem;

public interface IProblemLoader {

	/**
	 * Get all the problems
	 * 
	 * @return The hard coded list of problems
	 */
	public abstract List<IMCProblem> getProblems();

}