
package mcquizer.model.interfaces;

import mcquizer.controllers.IProblemSelector;

/**
 * Interface for everything that can be selected by a {@link IProblemSelector}
 *
 * @author Konstantin Naryshkin
 */
public interface ISelectable
{
	
	/**
	 * Change the score of this question by a value. If the delta will lower the
	 * score of this question to 0 or less, the score will be lowered to 1
	 * instead.
	 * 
	 * @param delta The increment to change the score by.
	 */
	void changeWeight(double delta);
	
	/**
	 * Get the problem weight
	 * 
	 * @return The problem weight, with a higher value signifying that the problem
	 *         needs more practice
	 */
	double getWeight();
	
}
