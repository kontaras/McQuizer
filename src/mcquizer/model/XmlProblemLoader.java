package mcquizer.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import mcquizer.model.interfaces.IMCProblem;

public class XmlProblemLoader implements IProblemLoader {

	@Override
	public List<IMCProblem> getProblems() {
		List<IMCProblem> probs = new ArrayList<IMCProblem>();
		
		File f = new File("test/testData.xml");
		try {
			Document d = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(f);
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

	private IMCProblem parseProblem(Node problem) {
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
				return null;
		}
	}

	private List<String> parseAnswers(NodeList raw) {
		List<String> answers = new ArrayList<String>();
		for (int j = 0; j < raw.getLength(); j++)
		{
			Node part = raw.item(j);
			answers.add(part.getTextContent());
		}

		return answers;
	}

}
