import java.util.ArrayList;


public class Evaluator 
{
	private ConceptMap map;
	
	public Evaluator(ConceptMap map)
	{
		this.map = map;
	}
	
	public ConceptFrame findExercise(ArrayList<String> states)
	{
		ArrayList<ConceptFrame> movements = new ArrayList<ConceptFrame>();
		
		ArrayList<ConceptFrame> nodes = map.nodes;
		
		for (int i = 0; i < states.size()-1; i++)
		{
			ArrayList<ConceptFrame> beliefNodes = new ArrayList<ConceptFrame>();
			String pre = states.get(i);
			String post = states.get(i+1);
			
			for (ConceptFrame c : nodes)
			{
				for (ConceptEdge edg : c.edges)
				{
					if (edg.getDescriptor().equals("hasPrecondition"))
					{
						ArrayList<Slot> slots = edg.getDestinationFrame().slots;
						for (Slot s : slots)
						{
							if (pre.equals(s.value))
							{
								beliefNodes.add(c);
							}
						}
					}
				}
			}
			
			for (ConceptFrame bn : beliefNodes)
			{
				for (ConceptEdge edg : bn.edges)
				{
					if (edg.getDescriptor().equals("hasPostcondition"))
					{
						ArrayList<Slot> slots = edg.getDestinationFrame().slots;
						for (Slot s : slots)
						{
							if (post.equals(s.value))
							{
								movements.add(bn);
							}
						}
					}
				}
			}
		}
		
		ConceptFrame matchedExercise = null;
		for (ConceptFrame c : nodes)
		{
			if (c.type.equals("exercise"))
			{
				if (c.getValue("numberOfMovements").equals(String.valueOf(movements.size())))
				{
					ArrayList<ConceptFrame> exMov = new ArrayList<ConceptFrame>();
					for (int i = 0; i < movements.size(); i++)
					{
						String edgeRelation = "hasMovement" + (i + 1);

						for (ConceptEdge e : c.edges)
						{
							if (c.edges.get(i).getDescriptor().equals(edgeRelation))
							{
								exMov.add(e.getDestinationFrame());
							}
						}
					}
					
					boolean matches = true;
					int i = 0;
					while (matches && i < movements.size())
					{
						if (!movements.get(i).equals(exMov.get(i)))
						{
							matches = false;
						}
						i++;
					}
					
					if (matches)
						matchedExercise = c;
				}
			}
		}

		return matchedExercise;
	}
}
