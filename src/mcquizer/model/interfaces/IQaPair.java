/**
 * 
 */

package mcquizer.model.interfaces;

import java.util.ArrayList;

/**
 * An interface that represents a question, answer combination.
 * 
 * @author Konstantin Naryshkin
 */
public interface IQaPair extends IProblem
{	
	/**
	 * Get an answer for this problem. If a problem has multiple answers, get one
	 * of them. Several calls to this method can return either different results
	 * or the same one repeatedly.
	 * 
	 * @return One of the answers that are correct for this problem.
	 */
	public String getAnswer();
	
	class PairProbemList extends ArrayList<IQaPair>{

		/**
		 * 
		 */
		private static final long serialVersionUID = 2620188701729057058L;
	}
}
