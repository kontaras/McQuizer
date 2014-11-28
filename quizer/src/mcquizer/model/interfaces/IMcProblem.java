/**
 * 
 */

package mcquizer.model.interfaces;

import java.util.List;

/**
 * A multiple choice problem to give to user to solve. The question is presented
 * to the user accompanied by a number of possible answers; only one answer is
 * correct.
 * 
 * @author Konstantin Naryshkin
 */
public interface IMcProblem
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
	 * Get the possible answers that the user can chose from.
	 * 
	 * @return answers to give the user
	 */
	public List<String> getPossibleAnswers();

	/**
	 * Set the answer that the user provided.
	 * 
	 * @param index
	 *            The index of the answers (as given by
	 *            {@link #getPossibleAnswers()}) that the user supplied.
	 * @return if this is the correct answer
	 */
	public boolean setAnswered(int index);
}
