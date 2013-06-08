import java.util.ArrayList;

public class ConceptFrame 
{
	String descriptor;
	String type;
	ArrayList<Slot> slots;
	ArrayList<ConceptEdge> edges;
	
	public ConceptFrame(String descriptor, String type)
	{
		slots = new ArrayList<Slot>();
		edges = new ArrayList<ConceptEdge>();
		this.descriptor = descriptor;
		this.type = type;
	}
	
	public void addSlot(Slot slot)
	{
		slots.add(slot);
	}
	
	public void addEdge(ConceptEdge edge)
	{
		edges.add(edge);
	}
	
	public String getValue(String slot)
	{
		String value = null;
		for (Slot s : slots)
		{
			if (s.slot.equals(slot))
				value = s.value;
		}
		
		return value;
	}
	
	public ConceptEdge getEdge(String descriptor)
	{
		for (ConceptEdge e : edges)
		{
			if (e.getDescriptor().equals(descriptor))
				return e;
		}
		
		return null;
	}
	
	public ArrayList<ConceptEdge> getEdges()
	{
		return edges;
	}
	
	public String toString()
	{
		return descriptor;
	}
}
