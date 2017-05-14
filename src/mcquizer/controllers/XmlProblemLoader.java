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
			String type = d.getDocumentElement().getAttributes().getNamedItem("type").getTextContent();
			switch (type) {
				case "multipleChoice":
					NodeList problems = d.getDocumentElement().getElementsByTagName("problem");
					for (int i = 0; i < problems.getLength(); i++) {
						Node problem = problems.item(i);
						probs.add(parseMultipleChoiceProblem(problem));
					}
					break;
				case "pair":
					break;
				default:
					throw new RuntimeException("Unsupported questions type");
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
	 * @param problem
	 *            The XML node containing the problem
	 * @return The problem object that the XML represents
	 */
	private static IMCProblem parseMultipleChoiceProblem(Node problem) {
		String question = problem.getAttributes().getNamedItem("question").getTextContent();
		int weight = Integer.parseInt(problem.getAttributes().getNamedItem("weight").getTextContent());
		int correct = Integer.parseInt(problem.getAttributes().getNamedItem("correct").getTextContent());
		NodeList answerNodes = ((Element) problem).getElementsByTagName("answer");
		List<String> answers = parseMultipleChoiceAnswers(answerNodes);
		return new PresetMcProblem(question, answers, correct, weight);
	}

	/**
	 * Parse a list of XML nodes as problem answers
	 * 
	 * @param raw
	 *            The raw answer nodes
	 * @return A list of answers
	 */
	private static List<String> parseMultipleChoiceAnswers(NodeList raw) {
		List<String> answers = new ArrayList<>();
		for (int j = 0; j < raw.getLength(); j++) {
			Node part = raw.item(j);
			answers.add(part.getTextContent());
		}

		return answers;
	}

}
