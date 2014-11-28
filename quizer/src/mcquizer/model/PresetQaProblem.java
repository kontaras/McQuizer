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
	 * The score, representing the confidence of the software that the user know
	 * the answer. A lower score represents better knowledge.
	 * 
	 * @see #getScore()
	 */
	private double score;
	
	/**
	 * Create a new problem instance
	 * 
	 * @param myQuestion The question that the problem asks
	 * @param myAnswer The correct answer to the problem
	 * @param myScore The score that this
	 */
	PresetQaProblem(String myQuestion, String myAnswer, int myScore)
	{
		this.question = myQuestion;
		this.answer = myAnswer;
		this.score = myScore;
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
	public double getScore()
	{
		return this.score;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.magku.quizer.model.IQuestion#changeScore(int)
	 */
	@Override
	public void changeScore(double delta)
	{
		this.score += delta;
		if (this.score <= 0)
		{
			this.score = 1;
		}
	}
}
