package mcquizer.model;

import java.util.List;

import mcquizer.model.interfaces.IMultipleChoiceProblem;

/**
 * A multiple choice question with a supplied question and answers, not backed
 * by any data source.
 * 
 * @author Konstantin Naryshkin
 */
public class PresetMcProblem implements IMultipleChoiceProblem
{
	/** The question being asked */
	private final String question;

	/** The possible answers to the question */
	private final List<String> answers;

	/** The index in {@link #answers} of the correct answer */
	private final int correct;

	/**
	 * @param question
	 *            The question being asked
	 * @param answers
	 *            The possible answers to the question
	 * @param correct
	 *            The index in answers of the correct answer
	 */
	public PresetMcProblem(String question, List<String> answers, int correct)
	{
		this.question = question;
		this.answers = answers;
		this.correct = correct;
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
		return index == this.correct;
	}
}
