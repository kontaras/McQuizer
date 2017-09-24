package mcquizer.model.interfaces;

/**
 * The properties that are shared by all problems, regardless of type
 *
 * @author Konstantin Naryshkin
 */
public interface IProblem
{
	/**
	 * Get the question that the user should be asked
	 * 
	 * @return the question
	 */
	public String getQuestion();
	
	/**
	 * Change the score of this question by a value. If the delta will lower the
	 * score of this question to 0 or less, the score will be lowered to 1
	 * instead.
	 * 
	 * @param delta The increment to change the score by.
	 */
	abstract void changeWeight(double delta);
	
	/**
	 * Get the problem weight
	 * 
	 * @return The problem weight, with a higher value signifying that the problem
	 *         needs more practice
	 */
	public double getWeight();
}
