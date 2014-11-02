package pl.radek.dvd.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sola on 2014-10-31.
 */
public class Foo {
    private String name;
    private String age;
    private String color;
    private Set<String> lista = new HashSet<String>(0);

    public Foo() {
    }

    public Foo(String name, String age, String color, Set<String> lista) {
        this.name = name;
        this.age = age;
        this.color = color;
        this.lista = lista;
    }

    public Set<String> getLista() {
        return lista;
    }

    public void setLista(Set<String> lista) {
        this.lista = lista;
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
