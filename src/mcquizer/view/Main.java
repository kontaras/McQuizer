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

import mcquizer.controllers.IProblemSelector;
import mcquizer.controllers.LinearRandomSelector;
import mcquizer.model.HardCodedProblemLoader;
import mcquizer.model.IProblemLoader;
import mcquizer.model.XmlProblemLoader;
import mcquizer.model.interfaces.IMCProblem;

public class Main
{

	private final static class AnswerListener implements ListSelectionListener
	{
		IMCProblem problem;
		Main parent;

		public AnswerListener(IMCProblem quest, Main parent)
		{
			this.problem = quest;
			this.parent = parent;
		}

		@Override
		public void valueChanged(ListSelectionEvent e)
		{
			JList<?> jList = (JList<?>) e.getSource();
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
		IProblemLoader loader = new XmlProblemLoader();
		//IProblemLoader loader = new HardCodedProblemLoader();
		this.selector =
				new LinearRandomSelector(
						loader.getProblems());
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
		IMCProblem quest = this.selector.getNextProblem();

		JTextPane questionPane = new JTextPane();
		questionPane.setText(quest.getQuestion());
		this.splitPane.setLeftComponent(questionPane);

		JList<Object> list = new JList<Object>(quest.getPossibleAnswers().toArray());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(new AnswerListener(quest, this));
		this.splitPane.setRightComponent(list);
		this.splitPane.setDividerLocation(.5);
	}

}
