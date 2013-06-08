import java.util.ArrayList;


public class FrameFactory 
{
	private ConceptMap map;
	
	public FrameFactory(ConceptMap map)
	{
		this.map = map;
	}
	
	public ConceptFrame createJointRelationFrame(ConceptFrame actor, ConceptFrame accepter, String direction)
	{
		if (actor.type == "joint" && accepter.type == "joint")
		{
			ConceptFrame jr = new ConceptFrame("JointRelation" + actor.descriptor + accepter.descriptor, "jointrelation");
			Slot s1 = new Slot("direction", direction);
			jr.addSlot(s1);
			
			ConceptEdge e1 = new ConceptEdge("hasActor");
			e1.setSourceFrame(jr);
			e1.setDestinationFrame(actor);
			
			ConceptEdge e2 = new ConceptEdge("hasAccepter");
			e2.setSourceFrame(jr);
			e2.setDestinationFrame(accepter);
			
			ConceptEdge e3 = new ConceptEdge("isActor");
			e3.setSourceFrame(actor);
			e3.setDestinationFrame(jr);
			
			ConceptEdge e4 = new ConceptEdge("isAccepter");
			e4.setSourceFrame(accepter);
			e4.setDestinationFrame(jr);
			
			jr.addEdge(e1);
			jr.addEdge(e2);
			actor.addEdge(e3);
			accepter.addEdge(e4);
			
			int flag = jointRelationExists(jr);
			
			if (flag != 1 && flag != -1)
			{
				return jr;
			}
			
			System.out.println("Joint exists already");
			
			return null;
		}		
		
		return null;
	}
	
	public ConceptFrame createStateFrame(ArrayList<ConceptFrame> jointRelations, int num)
	{		
		ConceptFrame st = new ConceptFrame("State" + num, "state");
		
		for (ConceptFrame jr : jointRelations)
		{
			ConceptEdge e1 = new ConceptEdge();
			e1.setDescriptor("hasJointRelation");
			e1.setSourceFrame(jr);
			e1.setDestinationFrame(st);
			st.addEdge(e1);
	
			ConceptEdge e2 = new ConceptEdge();
			e2.setDescriptor("isJointRelation");
			e2.setSourceFrame(st);
			e2.setDestinationFrame(jr);
			jr.addEdge(e2);
		}
		
		return st;
	}
	
	public int jointRelationExists(ConceptFrame jointRelation)
	{
		if (jointRelation.type == "jointrelation")
		{
			for (ConceptFrame n : map.nodes)
			{
				if (n.type == "jointrelation")
				{
					if (n.descriptor.equals(jointRelation.descriptor))
					{
						if (n.getEdge("hasActor").getDestinationFrame().equals(jointRelation.getEdge("hasActor").getDestinationFrame())
								&& n.getEdge("hasAccepter").getDestinationFrame().equals(jointRelation.getEdge("hasAccepter").getDestinationFrame()))
						{
							return 1;
						}
					}
				}
			}
			
			return 0;
		}
		
		return -1;
	}
}
