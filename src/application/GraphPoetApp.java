package application;

import graph.GraphPoet;
import helper.GeneralInputHelper;
import helper.GraphMetrics;
import vertex.Word;

import java.io.File;
import java.io.StringWriter;
import java.util.Scanner;

public class GraphPoetApp
{
    private GraphPoet gp;
    private GeneralInputHelper generalInputHelper = new GeneralInputHelper();

    public void General() throws Exception {
        //Calendar cl = new GregorianCalendar();
        System.out.println("GraphPoet App\nVersion=1.0.\nAuthor=marisuki\nUse \"GraphPoet --help\" to get details.");
        Scanner sc = new Scanner(System.in);
        System.out.println("Input: FilePath(Absolute path recommended)");
        gp = (GraphPoet) generalInputHelper.fileReadConfig(sc.nextLine());
        System.out.println(gp.toString());
        while(sc.hasNext())
        {
            boolean show = false;
            String str = sc.nextLine();
            if(str.contains("GraphPoet --help"))
                GraphPoetHelper();
            if(str.contains("file --in"))
            {
                String filePath = str.split(" ")[2];
                File file = new File(filePath);
                if(file.exists())
                {
                    gp = (GraphPoet) generalInputHelper.fileReadConfig(filePath);
                }
                else
                {
                    System.out.println("[E] File Path not exists.");
                }
                System.out.println("Graph Establish complete.\nUse \"GraphPoet --help\" to see operates supported.");
            }
            else if(str.contains("degreeCentrality"))
            {
                String []a = str.split(" ");
                System.out.println("DegreeCentrality Equals to:");
                if(a.length==1)
                    System.out.println(GraphMetrics.degreeCentrality(gp));
                else
                {
                    Word tmp = (Word) generalInputHelper.stov(a[1]);
                    if(tmp!=null)
                        System.out.println(GraphMetrics.degreeCentrality(gp, tmp));
                }
            }
            else if(str.contains("closenessCentrality"))
            {
                String []a = str.split(" ");
                assert a.length==2;
                Word tmp = (Word) generalInputHelper.stov(a[1]);
                if(tmp!=null)
                {
                    System.out.println("clossness Centrality: "+GraphMetrics.closenessCentrality(gp, tmp));
                }
            }
            else if(str.contains("betweennessCentrality"))
            {
                String[] a = str.split(" ");
                assert a.length==2;
                Word tmp = (Word) generalInputHelper.stov(a[1]);
                if(tmp!=null)
                {
                    System.out.println("betweeness Centrality:"+GraphMetrics.betweennessCentrality(gp, tmp));
                }
            }
            else if(str.contains("inDegreeCentrality"))
            {
                String[] a = str.split(" ");
                assert a.length==2;
                Word tmp = (Word) generalInputHelper.stov(a[1]);
                if(tmp!=null)
                {
                    System.out.println("indegree Centrality:"+GraphMetrics.inDegreeCentrality(gp, tmp));
                }
            }
            else if(str.contains("outDegreeCentrality"))
            {
                String[] a = str.split(" ");
                assert a.length==2;
                Word tmp = (Word) generalInputHelper.stov(a[1]);
                if(tmp!=null)
                {
                    System.out.println("outdegree Centrality:"+GraphMetrics.outDegreeCentrality(gp, tmp));
                }
            }
            else if(str.contains("distance"))
            {
                String[] a = str.split(" ");
                assert a.length==3;
                Word tmp1 = (Word) generalInputHelper.stov(a[1]);
                Word tmp2 = (Word) generalInputHelper.stov(a[2]);
                if(tmp1!=null&&tmp2!=null)
                {
                    System.out.println("distance:"+GraphMetrics.distance(gp, tmp1, tmp2));
                }
            }
            else if(str.contains("eccentricity"))
            {
                String[] a = str.split(" ");
                assert a.length==2;
                Word tmp = (Word) generalInputHelper.stov(a[1]);
                if(tmp!=null)
                {
                    System.out.println("eccentricity:"+GraphMetrics.eccentricity(gp, tmp));
                }
            }
            else if(str.contains("radius"))
            {
                String[] a = str.split(" ");
                assert a.length==1;
                System.out.println("radius:"+GraphMetrics.radius(gp));
            }
            else if(str.contains("diameter"))
            {
                String[] a = str.split(" ");
                assert a.length==1;
                System.out.println("diameter:"+GraphMetrics.diameter(gp));
            }
            else if(str.contains("save"))
            {
                String []a = str.split(" ");
                if(a[1].equals("-s"))
                {
                    generalInputHelper.SaveChange(gp, a[2]);
                    System.out.println("Save finished.");
                }
                else if(a[1].equals("-recall"))
                {
                    gp = (GraphPoet) generalInputHelper.recallSpecGra(a[2]);
                }
            }
            else
            {
                generalInputHelper.listenCmdInput(str);
                System.out.println(gp.toString());
            }
        }
    }

    private void GraphPoetHelper()
    {
        StringWriter swt = new StringWriter();
        swt.write("GraphPoet App\nVersion=1.0.\nAuthor=marisuki\n");
        swt.write("\"fileinput\"\t: file --in FilePath[StringType: Absolute Path Recommended]\n");
        swt.write("\"Using CMD as Graph Input: must as an adaptive method of file input\"\n\t\t: choice1: vertex --add label type\n");
        swt.write("\t\tchoice2: vertex --delete [regex]\n\t\tchoice3: edge --add label type [weighted] weight [directed] labelV1, labelV2\n");
        swt.write("choice4: edge --delete regex\n");
        swt.write("Usage to calculate Graph:\n");
        swt.write("choice1: \"degreeCentrality\" Or \"degreeCentrality label[Pattern of an Vertex]\"\n");
        swt.write("choice2: \"closenessCentrality label[Pattern of an Vertex]\nchoice3: \"betweennessCentrality label[Pattern of an Vertex]\"\n");
        swt.write("choice3: \"inDegreeCentrality label\" Or \"outDegreeCentrality label\"\n");
        swt.write("choice4: \"distance label1 label2\"\nchoice5: \"eccentricity label\"\n");
        swt.write("choice6: \"radius\"\nchoice7: \"diameter\"\n");
        swt.write("Memory-hold on: \"save -s Save_label\" to save temporary graph. Or \"save -recall Save_label\" to call back a history savage.\n");
        swt.write("While you input commands, please DO NOT add ->\"<-.\n");
        swt.write("CopyRight. 2018-5\n");
        System.out.println(swt.toString());
    }
}
