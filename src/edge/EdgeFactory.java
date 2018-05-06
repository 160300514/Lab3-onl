package edge;

import vertex.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/*
Builder pattern, outside: factory, edge->vertices.

Using the factory pattern,
separate the input (String[], typename, label), String[] is the input
(either cmd or file) separated by regex. Label, typename is gotten by
 the regex and can also be found in String[].

createEdgeOfCertainType: judge the input legal or not, and construct the edge.

 */
public class EdgeFactory
{
    private static final String []edgeTypeSet = {"WordNeighborhood", "FriendTie", "CommentTie", "ForwardTie", "NetworkConnection", "MovieActorRelation", "MovieDirectorRelation", "SameMovieHyperEdge"};

    public Edge createEdgeOfCertainType(String EdgeTypeName, String label, String[] res, Map<String , Vertex> stov) throws Exception {

        //System.out.println("EdgeTypename:  "+EdgeTypeName+" "+res[1]);

        if(stov.get(res[3])==null || stov.get(res[4])==null)
        {
            System.out.println("[E] EdgeFactory: Vertex Point not added.\nHalted.");
            return null;
        }

        if(res[1].equals(edgeTypeSet[0]))
        {
            assert res[5].equals("Yes");
            return new WordNeighborhood(label, Double.parseDouble(res[2]), (Word)stov.get(res[3]), (Word)stov.get(res[4]));
        }
        else if(res[1].equals(edgeTypeSet[1]))
        {
            assert res[5].equals("Yes");
            if(res[3].equals(res[4]))
            {
                System.out.println("[E] FriendTie: Same label: self loops!\nHalted.");
                return null;
            }
            FriendTie ft = new FriendTie(label, Double.parseDouble(res[2]), (Person)stov.get(res[3]), (Person)stov.get(res[4]));
            return ft;
        }
        else if(res[1].equals(edgeTypeSet[2]))
        {
            if(res[3].equals(res[4]))
            {
                System.out.println("[E] CommentTie: Same label: self loops!\nHalted.");
                return null;
            }
            //int cnt = 0;
            //for(String s:res) System.out.println(s+String.valueOf(cnt++));
            assert res[5].equals("Yes");
            CommentTie ct = new CommentTie(label, Double.parseDouble(res[2]), (Person)stov.get(res[3]), (Person)stov.get(res[4]));
            return ct;
        }
        else if(res[1].equals(edgeTypeSet[3]))
        {
            if(res[3].equals(res[4]))
            {
                System.out.println("[E] ForwardTie: Same label: self loops!\nHalted.");
                return null;
            }
            assert res[5].equals("Yes");
            ForwardTie ft = new ForwardTie(label, Double.parseDouble(res[2]), (Person)stov.get(res[3]), (Person)stov.get(res[4]));
            return ft;
        }
        else if(res[1].equals(edgeTypeSet[4]))
        {
            if(res[3].equals(res[4]))
            {
                System.out.println("[E] NetworkConnection: Self loop is not allowed in network connection.\nHalted.");
                return null;
            }
            if(stov.get(res[3]).getClass()==stov.get(res[4]).getClass())
            {
                if(stov.get(res[3]).getClass().equals(Computer.class)||stov.get(res[3]).getClass().equals(Server.class))
                {
                    System.out.println("[E] NetWorkConnection: Error on Server-Server Or Computer-Computer connection: Reason: not allowed.\nHalted.");
                    return null;
                }
            }
            assert res[5].equals("No");
            NetworkConnection nc = new NetworkConnection(label, Double.parseDouble(res[2]), (NetworkPather) stov.get(res[3]), (NetworkPather) stov.get(res[4]));
            return nc;
        }
        else if(res[1].equals(edgeTypeSet[5]))
        {
            if(res[3].equals(res[4]))
            {
                System.out.println("[E] Movie Actor Connection cannot allow any self loops.\nHalted.");
                return null;
            }
            assert res[5].equals("No");
            MovieActorRelation ma = new MovieActorRelation(label, Double.parseDouble(res[2]), (Movie)stov.get(res[3]), (Actor)stov.get(res[4]));
            return ma;
        }
        else if(res[1].equals(edgeTypeSet[6]))
        {
            if(res[3].equals(res[4]))
            {
                System.out.println("[E] Movie Director Connection: cannot allow any self loops.\nHalted.");
                return null;
            }
            assert res[5].equals("No");
            return new MovieDirectorRelation(label, (Movie)stov.get(res[3]), (Director)stov.get(res[4]));
        }
        else if(res[1].equals(edgeTypeSet[7]))
        {
            Set<Movie> ver = new HashSet<>();
            for(int i=2;i<res.length;i++)
            {
                Movie m = (Movie)stov.get(res[i]);
                if(ver.contains(m))
                {
                    System.out.println("[E] HyperEdge: cannot allow a self loop.\nHalted.");
                    return null;
                }
                ver.add(m);
            }
            return new SameMovieHyperEdge(label, ver);
        }
        throw new Exception("the edge type name not included.");
    }
}
