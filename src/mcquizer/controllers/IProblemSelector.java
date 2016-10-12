/**
 * 
 */

package mcquizer.controllers;

import mcquizer.model.interfaces.IMCProblem;

/**
 * Controller to select the next question to ask of the user.
 * 
 * @author Konstantin Naryshkin
 */
public interface IProblemSelector
{
	/**
	 * Get the next problem.
	 * 
	 * @return The next problem that should be asked of the user
	 */
	public IMCProblem getNextProblem();
}
