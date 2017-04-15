package mcquizer.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import mcquizer.model.IProblemLoader;
import mcquizer.model.PresetMcProblem;
import mcquizer.model.interfaces.IMCProblem;

/**
 * A problem loader that takes problems from an XML file
 */
public class XmlProblemLoader implements IProblemLoader {

	private InputStream file;

	public XmlProblemLoader(InputStream file) {
		this.file = file;
	}

	@Override
	public List<IMCProblem> getProblems() {
		List<IMCProblem> probs = new ArrayList<>();
		
		try {
			Document d = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
			NodeList problems = d.getDocumentElement().getElementsByTagName("problem");
			for (int i = 0; i < problems.getLength(); i++)
			{ 
				Node problem = problems.item(i);
				probs.add(parseProblem(problem));
			}
		} catch (SAXException | IOException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		return probs;
	}

	/**
	 * Parse a single problem out of the XML file
	 * 
	 * @param problem	The XML node containing the problem
	 * @return The problem object that the XML represents
	 */
	private static IMCProblem parseProblem(Node problem) {
		String type = problem.getAttributes().getNamedItem("type").getTextContent();
		switch (type) {
			case "multipleChoice":
				String question = problem.getAttributes().getNamedItem("question").getTextContent();
				int weight = Integer.parseInt(problem.getAttributes().getNamedItem("weight").getTextContent());
				int correct = Integer.parseInt(problem.getAttributes().getNamedItem("correct").getTextContent());
				NodeList answerNodes = ((Element) problem).getElementsByTagName("answer") ;
				List<String> answers = parseAnswers(answerNodes);
				return new PresetMcProblem(question, answers, correct, weight);
			default:
				throw new RuntimeException("Unsupported problem type");
		}
	}

	/**
	 * Parse a list of XML nodes as problem answers
	 * 
	 * @param raw The raw answer nodes
	 * @return A list of answers
	 */
	private static List<String> parseAnswers(NodeList raw) {
		List<String> answers = new ArrayList<>();
		for (int j = 0; j < raw.getLength(); j++)
		{
			Node part = raw.item(j);
			answers.add(part.getTextContent());
		}

		return answers;
	}

}
