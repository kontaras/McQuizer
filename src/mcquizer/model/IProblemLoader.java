package mcquizer.model;

import java.util.List;

import mcquizer.model.interfaces.IProblem;

public interface IProblemLoader {

	/**
	 * Get all the problems
	 * 
	 * @return The hard coded list of problems
	 */
	public abstract List<? extends IProblem> getProblems();

}