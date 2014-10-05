/**
 * 
 */

package mcquizer.model.interfaces;

import java.util.Set;

/**
 * A multiple choice problem to give to user to solve. The question is presented
 * to the user accompanied by a number of possible answers; only one answer is
 * correct.
 * 
 * @author Konstantin Naryshkin
 */
public interface IMultipleChoiceProblem
{
	/**
	 * Get the question that the user should be asked
	 * 
	 * @return the question
	 */
	public String getQuestion();
	
	/**
	 * Get the answer to the question
	 * 
	 * @return the answer
	 */
	public String getCorrectAnswer();
	
	/**
	 * Get other (incorrect) answers to present to the user.
	 * 
	 * @return other answers to give the user
	 */
	public Set<String> getOtherAnswers();
	
	/**
	 * Indicate that the user got the question correct. The question score will be
	 * adjusted appropriately. Only one of this method or
	 * {@link #setWrong(String)} should ever be called and only once.
	 * 
	 * @throws DoubleAnswerException if this method or {@link #setWrong(String)}
	 *           was previously called
	 */
	public void setRight();
	
	/**
	 * Indicate that the user got the question wrong. The question score will be
	 * adjusted appropriately. Only one of this method or {@link #setRight()}
	 * should ever be called and only once.
	 * 
	 * @param wrongAnswer The incorrect answer that the user gave
	 * @throws DoubleAnswerException if this method or {@link #setRight()} was
	 *           previously called
	 */
	public void setWrong(String wrongAnswer);
	
	/**
	 * An exception to indicate to the user that they already answered the
	 * question.
	 */
	public class DoubleAnswerException extends IllegalStateException
	{
		/**
		 * A generated serial version
		 */
		private static final long serialVersionUID = -3158876903777648474L;
		
		/**
		 * Minimal no argument constructor
		 */
		public DoubleAnswerException()
		{
			super();
		}
	}
}
