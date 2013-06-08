import java.util.ArrayList;


public class OutputLibrary 
{
	private ConceptMap map;
	
	public OutputLibrary(ConceptMap map)
	{
		this.map = map;
	}
	
	public String getState(ConceptFrame state)
	{
		if (state.type == "state")
		{
			ConceptFrame j1;
			ConceptFrame j2;
			ConceptFrame j3;
			ConceptFrame jr12 = null;
			ConceptFrame jr32 = null;
			
			j1 = map.getFrame("Right Hand");
			j2 = map.getFrame("Chest");
			j3 = map.getFrame("Left Hand");
	
			ArrayList<ConceptFrame> actorFrames = getSourceFrames("hasActor", j1);
			ArrayList<ConceptFrame> accepterFrames = getSourceFrames("hasAccepter", j2);
			ArrayList<ConceptFrame> stateFrames = getDestinationFrames("isJointRelation", state);
			
			actorFrames.retainAll(accepterFrames);
			actorFrames.retainAll(stateFrames);
			jr12 = actorFrames.get(0);
			
			actorFrames = getSourceFrames("hasActor", j3);
			accepterFrames = getSourceFrames("hasAccepter", j2);
			stateFrames = getDestinationFrames("isJointRelation", state);
			
			actorFrames.retainAll(accepterFrames);
			actorFrames.retainAll(stateFrames);
			jr32 = actorFrames.get(0);
			
			return j1.descriptor + ". " + jr12.getValue("direction") + " " + 
					j2.descriptor + ". and ." + j3.descriptor + " " + jr32.getValue("direction") + " ." + j2.descriptor;
		}
		return null;
	}
	
	public ArrayList<ConceptFrame> getSourceFrames(String descriptor, ConceptFrame dstFrame)
	{
		ArrayList<ConceptFrame> beliefFrames = new ArrayList<ConceptFrame>();
		
		for (ConceptFrame n : map.nodes)
		{
			ConceptEdge edg;
			if ((edg = n.getEdge(descriptor)) != null)
			{
				if (edg.getDestinationFrame().equals(dstFrame))
					beliefFrames.add(edg.getSourceFrame());
					
			}
		}
		
		return beliefFrames;
	}
	
	public ArrayList<ConceptFrame> getDestinationFrames(String descriptor, ConceptFrame srcFrame)
	{
		ArrayList<ConceptFrame> beliefFrames = new ArrayList<ConceptFrame>();
		
		for (ConceptFrame n : map.nodes)
		{
			ConceptEdge edg;
			if ((edg = n.getEdge(descriptor)) != null)
			{
				if (edg.getSourceFrame().equals(srcFrame))
					beliefFrames.add(edg.getDestinationFrame());
					
			}
		}
		
		return beliefFrames;
	}
}
