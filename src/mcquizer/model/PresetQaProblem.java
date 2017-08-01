/**
 * 
 */

package mcquizer.model;

import mcquizer.model.interfaces.IQaPair;

/**
 * A problem with a single constant question and answer provided at
 * instantiation.
 * 
 * @author Konstantin Naryshkin
 */
public class PresetQaProblem implements IQaPair
{
	/**
	 * The answer for the problem
	 */
	private String answer;
	
	/**
	 * The question of this problem
	 */
	private String question;
	
	/**
	 * The weight, representing the confidence of the software that the user know
	 * the answer. A lower weight represents better knowledge.
	 * 
	 * @see #getWeight()
	 */
	private double weight;
	
	/**
	 * Create a new problem instance
	 * 
	 * @param myQuestion The question that the problem asks
	 * @param myAnswer The correct answer to the problem
	 * @param myWeight The confidence weight for the problem. The higher the
	 *          value, the more practice the user needs.
	 */
	public PresetQaProblem(String myQuestion, String myAnswer, double myWeight)
	{
		this.question = myQuestion;
		this.answer = myAnswer;
		this.weight = myWeight;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.magku.quizer.model.IQuestion#getQuestion()
	 */
	@Override
	public String getQuestion()
	{
		return this.question;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.magku.quizer.model.IQuestion#getAnswer()
	 */
	@Override
	public String getAnswer()
	{
		return this.answer;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.magku.quizer.model.IQuestion#changeScore(int)
	 */
	@Override
	public double getWeight()
	{
		return this.weight;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.magku.quizer.model.IQuestion#changeScore(int)
	 */
	@Override
	public void changeWeight(double delta)
	{
		this.weight += delta;
		if (this.weight <= 0)
		{
			this.weight = 1;
		}
	}
}
