package vertex;

/*
Using the Builder method to create vertices and return it;
the vertices are recorded in the parserInputHelper(class)
 */

import graph.NetworkTopology;

import java.util.regex.Pattern;


/*
Using the factory pattern,
separate the input (String[], typename, label),
String[] is the input (either cmd or file) separated by regex.
Label, typename is gotten by the regex and can also be found in String[].

createVertexOfCertainType: judge the input legal or not, and construct the vertex.

 */
public class VertexFactory
{
    private static final String []vertexTypeSet = {"Word", "Person", "Computer", "Server", "Router", "Movie", "Actor", "Director"};

    public Vertex createVertexOfCertainType(String TypeName, String label, String []res) throws Exception
    {
        //System.out.println("Vertex:    "+TypeName+"label:  "+label);
        if(TypeName.equals(vertexTypeSet[0]))//word
        {
            assert res.length == 2;
            Word ans = new Word(label);
            return ans;
        }
        else if(TypeName.equals(vertexTypeSet[1]))
        {
            assert res.length == 4;
            Person p = new Person(label, res[2], Integer.parseInt(res[3]));
            return p;
        }
        else if(TypeName.equals(vertexTypeSet[2]))
        {
            assert res.length == 3;
            int []ip = new int[4];
            String Pat = "[(.)]+";
            Pattern p = Pattern.compile(Pat);
            String[] ress = p.split(res[2]);
            //for(String s:ress) System.out.println(s);
            ip[0] = Integer.parseInt(ress[0]);
            ip[1] = Integer.parseInt(ress[1]);
            ip[2] = Integer.parseInt(ress[2]);
            ip[3] = Integer.parseInt(ress[3]);
            Computer c=  new Computer(label, ip);
            return c;
        }
        else if(TypeName.equals(vertexTypeSet[3]))
        {
            assert res.length == 3;
            int []ip = new int[4];
            String Pat = "[(.)]+";
            Pattern p = Pattern.compile(Pat);
            String[] ress = p.split(res[2]);
            ip[0] = Integer.parseInt(ress[0]);
            ip[1] = Integer.parseInt(ress[1]);
            ip[2] = Integer.parseInt(ress[2]);
            ip[3] = Integer.parseInt(ress[3]);
            Server s = new Server(label, ip);
            return s;
        }
        else if(TypeName.equals(vertexTypeSet[4]))
        {
            assert res.length == 3;
            int []ip = new int[4];
            String Pat = "[(.)]+";
            Pattern p = Pattern.compile(Pat);
            String[] ress = p.split(res[2]);
            ip[0] = Integer.parseInt(ress[0]);
            ip[1] = Integer.parseInt(ress[1]);
            ip[2] = Integer.parseInt(ress[2]);
            ip[3] = Integer.parseInt(ress[3]);
            Router r=  new Router(label, ip);
            return r;
        }
        else if(TypeName.equals(vertexTypeSet[5]))//movie
        {
            assert res.length == 5;
            Movie m = new Movie(label, Integer.parseInt(res[2]), res[3], Double.parseDouble(res[4]));
            return m;
        }
        else if(TypeName.equals(vertexTypeSet[6]))//actor
        {
            assert res.length == 4;
            Actor a =  new Actor(label, Integer.parseInt(res[2]), res[3]);
            return a;
        }
        else if(TypeName.equals(vertexTypeSet[7]))//director
        {
            assert res.length == 4;
            Director d = new Director(label, Integer.parseInt(res[2]), res[3]);
            return d;
        }
        throw new Exception("vertices type name not included.");
    }

    public Vertex createVertexFromPreventVertex(Vertex pre, String newLabel)
    {
        if(pre.getClass().equals(Actor.class))
        {
            Actor tmp = (Actor) pre;
            return new Actor(newLabel, tmp.getAge(), tmp.getGender());
        }
        else if(pre.getClass().equals(Computer.class))
        {
            Computer tmp = (Computer) pre;
            return new Computer(newLabel, tmp.getIPAddress());
        }
        else if(pre.getClass().equals(Director.class))
        {
            Director tmp = (Director) pre;
            return new Director(newLabel, tmp.getAge(), tmp.getGender());
        }
        else if(pre.getClass().equals(Movie.class))
        {
            Movie mov = (Movie) pre;
            return new Movie(newLabel, mov.getYrOnline(), mov.getCountry(),mov.getImdbScore());
        }
        else if(pre.getClass().equals(Person.class))
        {
            Person p = (Person) pre;
            return new Person(newLabel, p.getGender(), p.getAge());
        }
        else if(pre.getClass().equals(Router.class))
        {
            Router r = (Router) pre;
            return new Router(newLabel, r.getIPAddress());
        }
        else if(pre.getClass().equals(Server.class))
        {
            Server s = (Server) pre;
            return new Server(newLabel, s.getIPAddress());
        }
        else if(pre.getClass().equals(Word.class))
        {
            Word w = (Word) pre;
            return new Word(newLabel);
        }
        else
        {
            System.out.println("[E] Debug Error: Inner Class Config Error on VertexFactory-createFromPreVertex");
            return null;
        }
    }
}
