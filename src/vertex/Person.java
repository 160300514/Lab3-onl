package vertex;

import helper.HashEncoderHelper;

import java.io.StringWriter;

public class Person extends Vertex
{
    String gender;
    int age;
    public Person(String name, String gender, int age)
    {
        super(name);
        this.gender = gender;
        this.age = age;
    }

    @Override
    void fillVertexInfo(String[] args) {

    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        StringWriter swt = new StringWriter();
        swt.write("Person: Name:   "+super.label+", whose age is "+age+", and"+this.gender);
        return swt.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Person)
        {
            Person tmp = (Person) obj;
            return this.getGender().equals(tmp.gender) && this.age == tmp.getAge() && tmp.getLabel().equals(super.label);
        }
        else return false;
    }

    @Override
    public int hashCode() {
        return (new HashEncoderHelper()).hash("Person"+age+gender);
    }
}
