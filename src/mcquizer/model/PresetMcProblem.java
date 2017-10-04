package mcquizer.model;

import java.util.List;

import mcquizer.model.interfaces.IMCProblem;
import mcquizer.model.interfaces.IProblem;

/**
 * A multiple choice question with a supplied question and answers, not backed
 * by any data source.
 * 
 * @author Konstantin Naryshkin
 */
public class PresetMcProblem implements IMCProblem
{
	/** The question being asked */
	private final String question;

	/** The possible answers to the question */
	private final List<String> answers;

	/** The index in {@link #answers} of the correct answer */
	private final int correct;
	
	/** The weight of the problem */
	private int weight;

	/**
	 * @param question
	 *            The question being asked
	 * @param answers
	 *            The possible answers to the question
	 * @param correct
	 *            The index in answers of the correct answer
	 * @param weight
	 *            The weight of the problem (see {@link IProblem#getWeight()}
	 */
	public PresetMcProblem(String question, List<String> answers, int correct, int weight)
	{
		this.question = question;
		this.answers = answers;
		this.correct = correct;
		this.weight = weight;
	}

	@Override
	public String getQuestion()
	{
		return this.question;
	}

	@Override
	public int getCorrectAnswer()
	{
		return this.correct;
	}

	@Override
	public List<String> getPossibleAnswers()
	{
		return this.answers;
	}

	@Override
	public void changeWeight(double delta)
	{
		this.weight += delta;
		if (this.weight < 1) {
			this.weight = 1;
		}
	}

	@Override
	public double getWeight() {
		return this.weight;
	}
}
