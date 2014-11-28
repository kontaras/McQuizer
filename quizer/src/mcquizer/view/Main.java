package mcquizer.view;

import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import mcquizer.controllers.BinaryTreeRandomProblemSelector;
import mcquizer.controllers.IProblemSelector;
import mcquizer.model.HardCodedProblemLoader;
import mcquizer.model.interfaces.IMultipleChoiceProblem;

public class Main
{

	private final static class AnswerListener implements ListSelectionListener
	{
		IMultipleChoiceProblem problem;
		Main parent;

		public AnswerListener(IMultipleChoiceProblem quest, Main parent)
		{
			this.problem = quest;
			this.parent = parent;
		}

		@Override
		public void valueChanged(ListSelectionEvent e)
		{
			JList jList = (JList) e.getSource();
			int selectedAnswer = jList.getSelectedIndex();

			final String message;
			if (this.problem.setAnswered(selectedAnswer))
			{
				message = "right";
			}
			else
			{
				message = "wrong, should be " + this.problem.getCorrectAnswer();
			}
			JOptionPane.showMessageDialog(null, message);

			this.parent.setQuestion();
		}
	}

	private JFrame frame;
	private JSplitPane splitPane;
	private IProblemSelector selector;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Main window = new Main();
					window.frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main()
	{
		this.selector =
				new BinaryTreeRandomProblemSelector(
						HardCodedProblemLoader.getProblems());
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		this.frame = new JFrame();
		this.frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.splitPane = new JSplitPane();
		this.frame.setContentPane(this.splitPane);

		setQuestion();
	}

	void setQuestion()
	{
		IMultipleChoiceProblem quest = this.selector.getNextProblem();

		JTextPane questionPane = new JTextPane();
		questionPane.setText(quest.getQuestion());
		this.splitPane.setLeftComponent(questionPane);

		JList list = new JList(quest.getPossibleAnswers().toArray());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(new AnswerListener(quest, this));
		this.splitPane.setRightComponent(list);
		this.splitPane.setDividerLocation(.5);
	}

}
