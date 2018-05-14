package graph;

import edge.*;
import vertex.*;

/*
Using the build pattern, judge graph input legal,
and the vertex and edge construct is also put in
this class, keep the addVertex and addEdge method
the input is Vertex and Edge constructed by Vertex
Factory and Edge Factory., and judge this parameter
legal or not.

 */
public class GraphFactory
{
    private final String []graphTypeSet = {"GraphPoet", "SocialNetwork", "NetworkTopology", "MovieGraph"};
    private Graph ans;
    private String typeName;

    // return empty graph
    public Graph createGraph(String label, String typeName) throws Exception {
        this.typeName = typeName;
        //System.out.println(this.typeName);
        if(typeName.equals(this.graphTypeSet[0]))
        {
            this.ans = new GraphPoet(label);
        }
        else if(typeName.equals(this.graphTypeSet[1]))
        {
            this.ans = new SocialNetwork(label);
        }
        else if(typeName.equals(this.graphTypeSet[2]))
        {
            this.ans = new NetworkTopology(label);
        }
        else if(typeName.equals(this.graphTypeSet[3]))
        {
            this.ans = new MovieGraph(label);
        }
        else
            throw new Exception("graph typename not included.");
        return this.ans;
    }

    public Graph exportGraph()
    {
        return this.ans;
    }

    public Graph addVertex(Vertex vadd)
    {
        if(this.typeName.equals(this.graphTypeSet[0]))
        {
            assert vadd.getClass() == Word.class;
            this.ans.addVertex(vadd);
        }
        else if(this.typeName.equals(this.graphTypeSet[1]))
        {
            assert vadd.getClass() == Person.class;
            this.ans.addVertex(vadd);
        }
        else if(this.typeName.equals(this.graphTypeSet[2]))
        {
            assert vadd.getClass() == Computer.class || vadd.getClass() == Router.class || vadd.getClass() == Server.class;
            this.ans.addVertex(vadd);
        }
        else if(this.typeName.equals(this.graphTypeSet[3]))
        {
            assert vadd.getClass() == Movie.class || vadd.getClass() == Actor.class || vadd.getClass() == Director.class;
            this.ans.addVertex(vadd);
        }
        else
            System.out.println("[E] Vertex cannot be added: different type.");
        return this.ans;
    }

    public Graph addEdge(Edge eadd, Boolean filein) throws CloneNotSupportedException {
        if(typeName.equals(this.graphTypeSet[0]))
        {
            assert eadd.getClass() == WordNeighborhood.class;
            this.ans.addEdge(eadd, filein);
        }
        else if(typeName.equals(this.graphTypeSet[1]))
        {
            assert eadd.getClass() == FriendTie.class || eadd.getClass() == CommentTie.class || eadd.getClass() == ForwardTie.class;
            this.ans.addEdge(eadd, filein);
        }
        else if(typeName.equals(this.graphTypeSet[2]))
        {
            assert eadd.getClass() == NetworkConnection.class;
            this.ans.addEdge(eadd, filein);
        }
        else if(typeName.equals(this.graphTypeSet[3]))
        {
            assert eadd.getClass() == MovieActorRelation.class || eadd.getClass() == MovieDirectorRelation.class || eadd.getClass() == SameMovieHyperEdge.class;
            this.ans.addEdge(eadd, filein);
        }
        else
            System.out.println("[E] Edge cannot be added: different type.");
        return this.ans;
    }
}
