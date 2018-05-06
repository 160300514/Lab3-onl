package test;

import application.GraphPoetApp;
import application.MovieGraphApp;

public class Main
{
      public static void main(String[] argv) throws Exception {
        GraphPoetApp gpa = new GraphPoetApp();
        gpa.General();
        MovieGraphApp mga = new MovieGraphApp();
        mga.General();
    }
}
