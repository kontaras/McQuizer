package mcquizer.model;

import java.util.ArrayList;
import java.util.List;

import mcquizer.model.interfaces.IMCProblem;

/**
 * Temporary problem loader that gives a preset, hard coded list of problems for
 * prototyping purposes.
 * 
 * @author Konstantin Naryshkin
 */
public class HardCodedProblemLoader implements IProblemLoader
{
	
	@Override
	public List<IMCProblem> getProblems()
	{
		List<IMCProblem> probs = new ArrayList<>();

		List<String> ans = new ArrayList<>();
		
		int startWeight = 10;

		ans.clear();
		ans.add("Thermoluminescence");
		ans.add("Amino Acid Racemization");
		ans.add("Typology");
		ans.add("Oxygen Isotope Analysis");
		probs.add(new PresetMcProblem(
				"Which of the following techniques uses changes in style of artifacts over time to provide a relative date for an archaeological context:",
				new ArrayList<>(ans), 2, startWeight));

		ans.clear();
		ans.add("Predate those from the Medieval level as well as virgin soil");
		ans.add("Postdate those from the Medieval level as well as Virgin soil");
		ans.add("Predate those from the Medieval level as well as those from the Trash Pit");
		ans.add("Predate those from the Medieval level but Post Date those from the Trash Pit");
		probs.add(new PresetMcProblem(
				"Artifacts found in the iron age levels:",
				new ArrayList<>(ans), 0, startWeight));

		ans.clear();
		ans.add("Trash pit<Iron Age Levels<Medieval Levels<Topsoil");
		ans.add("Iron Age Levels<Trash pit<Medieval Levels<Topsoil");
		ans.add("Virgin Soil<Trash pit<Iron Age Levels<Medieval Levels");
		ans.add("Topsoil<Medieval Levels<Trash pit<Iron Age Levels");
		probs.add(new PresetMcProblem(
				"Which of the following squences in order of oldest to youngest, is correct:",
				new ArrayList<>(ans), 2, startWeight));

		ans.clear();
		ans.add("Dissemenation of data");
		ans.add("Archeological Excavation");
		ans.add("Selling of Artifacts at Auction");
		ans.add("Survey");
		probs.add(new PresetMcProblem(
				"Which of the following is not part of the archaeological process:",
				new ArrayList<>(ans), 2, startWeight));

		return probs;
	}
}
