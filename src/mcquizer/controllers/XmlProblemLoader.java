
package mcquizer.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import mcquizer.model.IProblemLoader;
import mcquizer.model.PresetMcProblem;
import mcquizer.model.PresetQaProblem;
import mcquizer.model.interfaces.IMCProblem;
import mcquizer.model.interfaces.IMCProblem.MCProbemList;
import mcquizer.model.interfaces.IProblem;
import mcquizer.model.interfaces.IQaPair;
import mcquizer.model.interfaces.IQaPair.PairProbemList;

/**
 * A problem loader that takes problems from an XML file
 */
public class XmlProblemLoader implements IProblemLoader
{
	/**
	 * Source that we are parsing.
	 */
	private InputStream file;
	
	/**
	 * @param file The file to load questions from
	 */
	public XmlProblemLoader(InputStream file)
	{
		this.file = file;
	}
	
	@Override
	public List<? extends IProblem> getProblems()
	{
		List<? extends IProblem> probs = null;
		
		try
		{
			Document d = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(this.file);
			String type = d.getDocumentElement().getAttributes().getNamedItem("type")
					.getTextContent();
			switch (type)
			{
				case "multipleChoice":
					MCProbemList mprobs = new MCProbemList();
					NodeList problems =
							d.getDocumentElement().getElementsByTagName("problem");
					for (int i = 0; i < problems.getLength(); i++ )
					{
						Node problem = problems.item(i);
						mprobs.add(parseMultipleChoiceProblem(problem));
					}
					probs = mprobs;
					break;
				case "pair":
					PairProbemList pprobs = new PairProbemList();
					NodeList pairs = d.getDocumentElement().getElementsByTagName("pair");
					for (int i = 0; i < pairs.getLength(); i++ )
					{
						Node problem = pairs.item(i);
						pprobs.add(parseQaPair(problem));
					}
					probs = pprobs;
					break;
				default:
					throw new RuntimeException("Unsupported questions type");
			}
		}
		catch (SAXException | IOException | ParserConfigurationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		return probs;
	}
	
	/**
	 * Parse a single problem out of the XML file
	 * 
	 * @param problem The XML node containing the problem
	 * @return The problem object that the XML represents
	 */
	private static IMCProblem parseMultipleChoiceProblem(Node problem)
	{
		/*
		 * <problem question="q2b" weight="1" correct="2">
		 * 		<answer>q2a1</answer>
		 * 		<answer>q2a2</answer>
		 * 		<answer>*q2a3</answer>
		 * 		<answer>q2a4</answer>
		 * 	</problem>
		 */
		final NamedNodeMap attribs = problem.getAttributes();
		String question = attribs.getNamedItem("question").getTextContent();
		int weight =
				Integer.parseInt(attribs.getNamedItem("weight").getTextContent());
		int correct =
				Integer.parseInt(attribs.getNamedItem("correct").getTextContent());
		NodeList answerNodes = ((Element) problem).getElementsByTagName("answer");
		List<String> answers = parseMultipleChoiceAnswers(answerNodes);
		return new PresetMcProblem(question, answers, correct, weight);
	}
	
	/**
	 * Parse a list of XML nodes as problem answers
	 * 
	 * @param raw The raw answer nodes
	 * @return A list of answers
	 */
	private static List<String> parseMultipleChoiceAnswers(NodeList raw)
	{
		List<String> answers = new ArrayList<>();
		for (int j = 0; j < raw.getLength(); j++ )
		{
			Node part = raw.item(j);
			answers.add(part.getTextContent());
		}
		
		return answers;
	}
	
	/**
	 * Parse a single pair out of an XML file
	 * 
	 * @param pair The XML to parse
	 * @return The pair represented by the XML
	 */
	private static IQaPair parseQaPair(Node pair)
	{
		/*
		 * <pair question='foo' answer='bar' weight='1' />
		 */
		final NamedNodeMap attribs = pair.getAttributes();
		String question = attribs.getNamedItem("question").getTextContent();
		String answer = attribs.getNamedItem("answer").getTextContent();
		double weight =
				Double.parseDouble(attribs.getNamedItem("weight").getTextContent());
		return new PresetQaProblem(question, answer, weight);
	}
}
