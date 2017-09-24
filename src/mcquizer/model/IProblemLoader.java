package mcquizer.model;

import java.util.List;

import mcquizer.model.interfaces.IProblem;

/**
 * The common interface for problem loaders. A problem loader takes in problems
 * from some source (usually an input stream of some type) and prepares them for
 * use in the application.
 *
 * @author Konstantin Naryshkin
 */
public interface IProblemLoader {

	/**
	 * Get all the problems
	 * 
	 * @return The hard coded list of problems
	 */
	public abstract List<? extends IProblem> getProblems();

}