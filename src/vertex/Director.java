package vertex;

import helper.HashEncoderHelper;

import java.io.StringWriter;

public class Director extends Vertex
{
    private int age;
    private String Gender;

    public Director(String Name, int age, String Gender)
    {
        super(Name);
        this.age = age;
        this.Gender = Gender;
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
    void fillVertexInfo(String[] args) {

    }

    @Override
    public String toString() {
        StringWriter swt = new StringWriter();
        swt.write("Director: Name:   "+super.label+", whose age is "+age+", and"+this.Gender);
        return swt.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Director)
        {
            Director tmp = (Director) obj;
            return this.getGender().equals(tmp.Gender) && this.age == tmp.getAge() && tmp.getLabel().equals(super.label);
        }
        else return false;
    }

    @Override
    public int hashCode() {
        return (new HashEncoderHelper()).hash("Director"+age+Gender);
    }
}
