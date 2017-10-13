package mcquizer.model.interfaces;

/**
 * The properties that are shared by all problems, regardless of type
 *
 * @author Konstantin Naryshkin
 */
public interface IProblem extends ISelectable
{
	/**
	 * Get the question that the user should be asked
	 * 
	 * @return the question
	 */
	public String getQuestion();
}
