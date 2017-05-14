package mcquizer.model;

import java.util.List;

import mcquizer.model.interfaces.IMCProblem;

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
	public String getCorrectAnswer()
	{
		return this.answers.get(this.correct);
	}

	@Override
	public List<String> getPossibleAnswers()
	{
		return this.answers;
	}

	@Override
	public boolean setAnswered(int index)
	{
		boolean right = index == this.correct;
		if (right)
		{
			if(weight > 1)
			{
				this.weight--;
			}
		} else {
			this.weight++;
		}
		return right;
	}

	@Override
	public double getWeight() {
		return weight;
	}
}
