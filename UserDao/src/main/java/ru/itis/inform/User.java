package ru.itis.inform;

public class User {

    private int idU;

    private String name;

    private int id;

    private String password;

    private String city;

    private String[] names;

    /*public User(String name) {


        names=name.split(" ");

        this.name=names[0];

        this.city=names[1];


    }
    */
    public User(String name, String city, int age)
    {
        this.name=name;
        this.id = age;
        this.city=city;
        this.idU = name.hashCode();
    }


    public User() {

    }

    public String getCity(){return city;}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    public String toString() {

        return this.name +" "+this.city;

    }

    public int getIdU() {
        return idU;
    }
    public void setIdU(int h){
        this.idU=h;
    }
}
