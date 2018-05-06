package edge;

import helper.HashEncoderHelper;
import vertex.Movie;
import vertex.Vertex;

import java.io.StringWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SameMovieHyperEdge extends Edge implements Cloneable
{
    private Set<Movie> verticespassiing;

    public SameMovieHyperEdge(String label, Set<Movie> vertices)
    {
        super(label, -1);
        this.verticespassiing = new HashSet<>(vertices);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Set<Movie> getVerticespassiing() {
        return verticespassiing;
    }

    @Override
    public void checkRep()
    {
        assert this.verticespassiing!=null;
    }

    @Override
    public boolean addVertices(List<Vertex> vertices) {
        for(Vertex v: vertices)
        {
            this.verticespassiing.add((Movie)v);
        }
        checkRep();
        return true;
    }

    @Override
    public boolean containVertex(Vertex v) {
        if(v instanceof Movie)
        {
            Movie mt = (Movie)v;
            return this.verticespassiing.contains(mt);
        }
        return false;
    }

    @Override
    public Set<Vertex> vertices() {
        return new HashSet<>(this.verticespassiing);
    }

    @Override
    public Set<Vertex> sourceVertices() {
        return this.vertices();
    }

    @Override
    public Set<Vertex> targetVertices() {
        return this.vertices();
    }

    @Override
    public String toString() {
        StringWriter swt = new StringWriter();
        swt.write("Edge:   HyperEdge: with vertices: \n");
        for(Movie m:this.verticespassiing)
            swt.write(m.toString()+"\t");
        return swt.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof SameMovieHyperEdge)
        {
            SameMovieHyperEdge se = (SameMovieHyperEdge) obj;
            for(Movie m:se.getVerticespassiing())
            {
                if(!this.verticespassiing.contains(m))
                    return false;
            }
            if(se.getVerticespassiing().size()==this.verticespassiing.size())
                return true;
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (new HashEncoderHelper()).hash(this.toString());
    }
}
