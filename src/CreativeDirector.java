import java.util.ArrayList;
import java.util.Scanner;


public class CreativeDirector 
{
	private ConceptMap map;
	/* User does A,B
	 * Determines that next the user should do A,B,C,D
	 * 
	 */
	
	public CreativeDirector(ConceptMap map)
	{
		this.map = map;
	}
	
	public ConceptFrame mergeExercise(ConceptFrame matchedExercise) throws Exception
	{
		ArrayList<Matrix> matrices = new ArrayList<Matrix>();
		matrices.add(new Matrix(1, 1));
		matrices.add(new Matrix(1, 1));
		
		ArrayList<ConceptFrame> nodes = map.nodes;
		
		for (Matrix m : matrices)
		{
			/* Good stuff happens here */
		}
				
		ConceptFrame suggested = null;
		if (matchedExercise != null)
		{			
			ConceptFrame post = null;
			for (ConceptEdge e : matchedExercise.edges)
			{
				if (e.getDescriptor().equals("endsWith"))
				{
					for (ConceptEdge eEnd : e.getDestinationFrame().edges)
					{
						if (eEnd.getDescriptor().equals("hasPostcondition"))
						{
							post = eEnd.getDestinationFrame();
						}
					}
				}
			}
			
			if (post != null)
			{
				String goalState = post.getValue("state");
				
				for (ConceptFrame n : nodes)
				{
					for (ConceptEdge e : n.edges)
					{
						if (e.getDescriptor().equals("startsWith"))
						{
							for (ConceptEdge eEnd : e.getDestinationFrame().edges)
							{
								if (eEnd.getDescriptor().equals("hasPrecondition"))
								{
									if (goalState.equals(eEnd.getDestinationFrame().getValue("state")))
										suggested = n;
								}
							}
						}
					}
				}
			}
		}
		
		FreeTTS speaker = new FreeTTS();
		speaker.speak("I want to join " + matchedExercise.getValue("name") + " with " + suggested.getValue("name"));
		speaker.speak("The new exercise has " + (Integer.parseInt(suggested.getValue("numberOfMovements")) + 
				Integer.parseInt(matchedExercise.getValue("numberOfMovements"))) +
				" movements.");
		
		ArrayList<ConceptFrame> allMov = getMovements(matchedExercise);
		allMov.addAll(getMovements(suggested));
		
		OutputLibrary ol = new OutputLibrary(map);
		
		speaker.speak("Would you like to skip the description?");
		Scanner scanner = new Scanner( System.in );
		String input = scanner.nextLine();
		
		if (input.equals("no"))
		{
			for (ConceptFrame m : allMov)
			{
				if (allMov.indexOf(m) == 0)
					speaker.speak("First. Start with");
				else
					speaker.speak("Then go to :");
				
				if (allMov.indexOf(m) == allMov.size()-1)
				{
					speaker.speak(ol.getState(m.getEdge("hasPreState").getDestinationFrame()));
					speaker.speak("Finally, do :" + ol.getState(m.getEdge("hasPostState").getDestinationFrame()));
				}
				else
					speaker.speak(ol.getState(m.getEdge("hasPreState").getDestinationFrame()));
			}
		}
		
		speaker.speak("Would you like to name this exercise?");
		Thread.sleep(500);
		
		input = scanner.nextLine();
		
		ConceptFrame newEx = new ConceptFrame(input, "exercise");
		Slot s1 = new Slot("name", input);
		Slot s2 = new Slot("numberOfMovements", String.valueOf(Integer.parseInt(suggested.getValue("numberOfMovements")) + 
				Integer.parseInt(matchedExercise.getValue("numberOfMovements"))));
		
		newEx.addSlot(s1);
		newEx.addSlot(s2);

		for (ConceptFrame mov : allMov)
		{
			ConceptEdge edg = new ConceptEdge("hasMovement" + (allMov.indexOf(mov)+1));
			edg.setSourceFrame(newEx);
			edg.setDestinationFrame(mov);
			newEx.addEdge(edg);
		}
		ConceptEdge edg1 = new ConceptEdge("startsWith");
		edg1.setSourceFrame(newEx);
		edg1.setDestinationFrame(allMov.get(0));
		ConceptEdge edg2 = new ConceptEdge("endsWith");
		edg2.setSourceFrame(newEx);
		edg2.setDestinationFrame(allMov.get(allMov.size()-1));
		newEx.addEdge(edg1);
		newEx.addEdge(edg2);
		
		return newEx;
	}
	
	public ConceptFrame createExercise(ConceptFrame matchedExercise)
	{
		FreeTTS speaker = new FreeTTS();
		OutputLibrary ol = new OutputLibrary(map);
		
		speaker.speak("This exercise: " + matchedExercise.getValue("name") + ". Has " + matchedExercise.getValue("numberOfMovements") + " movements.");

		ArrayList<ConceptFrame> movements = getMovements(matchedExercise);
		
		for (ConceptFrame m : movements)
		{
			System.out.println(m.descriptor);
			speaker.speak("Start with, " + ol.getState(m.getEdge("hasPreState").getDestinationFrame()));
			speaker.speak("End with, " + ol.getState(m.getEdge("hasPostState").getDestinationFrame()));
		}
		
		
		return null;
	}
	
	public ArrayList<ConceptFrame> getMovements(ConceptFrame exercise)
	{
		ArrayList<ConceptFrame> movements = new ArrayList<ConceptFrame>();
		
		for (ConceptEdge edg : exercise.getEdges())
		{
			int mov = Integer.parseInt(exercise.getValue("numberOfMovements"));
			
			for (int i = 1; i <= mov; i++)
			{
				if (edg.getDescriptor().equals("hasMovement" + i))
					movements.add(edg.getDestinationFrame());
			}
		}
		
		return movements;
	}
}
