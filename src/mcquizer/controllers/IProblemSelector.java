package mcquizer.controllers;

import mcquizer.model.interfaces.ISelectable;

/**
 * Controller to select the next question to ask of the user.
 * 
 * @author Konstantin Naryshkin
 * @param <T> The type of problem that the the selector is selecting
 */
public interface IProblemSelector <T extends ISelectable>
{
	/**
	 * Get the next problem.
	 * 
	 * @return The next problem that should be asked of the user
	 */
	public T getNextProblem();
}
