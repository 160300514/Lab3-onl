package vertex;

abstract public class Vertex
{
    String label;

    //public Vertex(){}

    public Vertex(String label)
    {
        this.label = label;
    }

    abstract void fillVertexInfo(String[] args) throws NoSuchMethodException;

    public String getLabel()
    {
        return this.label;
    }

    @Override
    abstract public String toString();

    @Override
    abstract public boolean equals(Object obj);

    @Override
    abstract public int hashCode();
}
