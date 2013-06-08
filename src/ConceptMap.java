import java.util.ArrayList;

public class ConceptMap 
{
	ArrayList<ConceptFrame> nodes;
	ArrayList<ConceptEdge> edges;
	
	public ConceptMap()
	{
		nodes = new ArrayList<ConceptFrame>();
		edges = new ArrayList<ConceptEdge>();
	}
	
	public void addFrame(ConceptFrame frame)
	{
		nodes.add(frame);
		for (ConceptEdge e :frame.getEdges())
		{
			edges.add(e);
		}
	}
	
	public void addEdge(String desc, ConceptFrame src, ConceptFrame dst) throws Exception
	{
		if (nodes.contains(src) && nodes.contains(dst))
		{
			ConceptEdge e = new ConceptEdge(desc);
			e.setSourceFrame(src);
			e.setDestinationFrame(dst);
			
			src.edges.add(e);
			//dst.edges.add(e);
		} 
		else {
			throw new Exception("The concept frame cannot be found.");
		}
	}
	
	public void addEdge(ConceptEdge edge)
	{
		edges.add(edge);
	}
	
	public ConceptFrame getFrame(String descriptor)
	{
		ConceptFrame ret = null;
		for (ConceptFrame n : nodes)
		{
			if (n.descriptor.equals(descriptor))
				ret = n;
		}
		
		return ret;
	}
}
