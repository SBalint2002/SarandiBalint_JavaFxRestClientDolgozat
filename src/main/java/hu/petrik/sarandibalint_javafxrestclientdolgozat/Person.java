package hu.petrik.sarandibalint_javafxrestclientdolgozat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Person {
    private int id;
    @Expose
    @SerializedName("Name")
    private String name;
    @Expose
    @SerializedName("Oltas")
    private int oltas;
    @Expose
    @SerializedName("Eletkor")
    private int age;
    @Expose
    @SerializedName("Elkapta")
    private boolean elkapta;

    public Person(int id, String name, int oltas, int age, boolean elkapta) {
        this.id = id;
        this.name = name;
        this.oltas = oltas;
        this.age = age;
        this.elkapta = elkapta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOltas() {
        return oltas;
    }

    public void setOltas(int oltas) {
        this.oltas = oltas;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isElkapta() {
        return elkapta;
    }

    public void setElkapta(boolean elkapta) {
        this.elkapta = elkapta;
    }
}