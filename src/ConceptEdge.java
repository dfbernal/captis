
public class ConceptEdge 
{
	private String descriptor;
	private ConceptFrame source;
	private ConceptFrame destination;
	
	public ConceptEdge()
	{
	}
	
	public ConceptEdge(String descriptor)
	{
		setDescriptor(descriptor);
	}
	
	public String getDescriptor()
	{
		return this.descriptor;
	}
	
	public ConceptFrame getSourceFrame()
	{
		return this.source;
	}
	
	public ConceptFrame getDestinationFrame()
	{
		return this.destination;
	}
	
	public void setDescriptor(String descriptor)
	{
		this.descriptor = descriptor;
	}
	
	public void setSourceFrame(ConceptFrame source)
	{
		this.source = source;
	}
	
	public void setDestinationFrame(ConceptFrame destination)
	{
		this.destination = destination;
	}
	
	public String toString()
	{
		return descriptor;
	}
}
