package pl.radek.dvd.model;

/**
 * Created by Sola on 2014-10-31.
 */
public class Foo {
    private String name;
    private String age;
    private String color;

    public Foo() {
    }

    public Foo(String name, String age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
