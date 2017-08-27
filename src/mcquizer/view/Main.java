package mcquizer.view;

import java.awt.EventQueue;
import java.awt.Frame;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import mcquizer.controllers.IProblemSelector;
import mcquizer.controllers.ProblemSelectorFactory;
import mcquizer.controllers.XmlProblemLoader;
import mcquizer.model.IProblemLoader;
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
			if (this.problem.getCorrectAnswer() == selectedAnswer)
			{
				message = "right";
				this.problem.changeWeight(1);
			}
			else
			{
				message = "wrong, should be " + this.problem.getPossibleAnswers()
						.get(this.problem.getCorrectAnswer());
				this.problem.changeWeight(-1);
			}
			JOptionPane.showMessageDialog(null, message);

			this.parent.setQuestion();
		}
	}

	private JFrame frame;
	private JSplitPane splitPane;
	private IProblemSelector<IMCProblem> selector;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			@Override
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
		IProblemLoader loader;
		try {
			loader = new XmlProblemLoader(new FileInputStream("test/testData.xml"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		//IProblemLoader loader = new HardCodedProblemLoader();
		this.selector =
				ProblemSelectorFactory.getSelector(loader.getProblems());
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
