package vertex;

import helper.HashEncoderHelper;

import java.io.StringWriter;

public class Actor extends Vertex implements Cloneable
{
    private int age;
    private String Gender;

    public Actor(String Name, int age, String Gender)
    {
        super(Name);
        this.age = age;
        this.Gender = Gender;
    }


    @Override
    public String getLabel() {
        return super.getLabel();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Actor act = (Actor) super.clone();
        return act;
    }

    public String getGender()
    {
        return this.Gender;
    }

    public int getAge()
    {
        return this.age;
    }

    @Override
    void fillVertexInfo(String[] args)
    {

    }

    @Override
    public String toString() {
        StringWriter swt = new StringWriter();
        swt.write("Actor: Name:   "+super.label+", whose age is "+age+", and"+this.Gender);
        return swt.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Actor)
        {
            Actor tmp = (Actor) obj;
            return this.getGender().equals(tmp.Gender) && this.age == tmp.getAge() && tmp.getLabel().equals(super.label);
        }
        else return false;
    }

    @Override
    public int hashCode() {
        return (new HashEncoderHelper()).hash("Actor"+age+Gender);
    }
}
