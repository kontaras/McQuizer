/**
 * 
 */

package mcquizer.model.interfaces;

/**
 * An interface that represents a question, answer combination.
 * 
 * @author Konstantin Naryshkin
 */
public interface IQaPair
{
	/**
	 * Get the question that this problem is testing.
	 * 
	 * @return a <code>String</code> representing the question
	 */
	public String getQuestion();
	
	/**
	 * Get an answer for this problem. If a problem has multiple answers, get one
	 * of them. Several calls to this method can return either different results
	 * or the same one repeatedly.
	 * 
	 * @return One of the answers that are correct for this problem.
	 */
	public String getAnswer();
	
	/**
	 * Get the score of this problem. The score represents our confidence that the
	 * user does not know the problem. A problem that the user has answered
	 * correctly will have a low score, though the score will always be at least
	 * 1. There is no upper limit on the score.
	 * 
	 * @return A positive number representing the score.
	 */
	public abstract double getScore();
	
	/**
	 * Change the score of this question by a value. If the delta will lower the
	 * score of this question to 0 or less, the score will be lowered to 1
	 * instead.
	 * 
	 * @param delta	The increment to change the score by.
	 */
	abstract void changeScore(double delta);
}
