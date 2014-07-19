package comum.classes;


public interface Join{
	
	public void open();
	public void close();
	public Tuple next();
	public boolean hasNext();
	
}
