package ru.itis.inform.models;


public class User {

    //таблица users

    private int id;
    private String name;
    private String login;
    private String password;
    private boolean is_admin;
    private boolean is_workman;

    public User(String id, String name, String login, String password, boolean is_admin,boolean is_workman) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.is_admin = is_admin;
        this.id = Integer.parseInt(id);
        this.is_workman=is_workman;
    }

    public User(String name, String login, String password, boolean is_admin,boolean is_workman) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.is_admin = is_admin;
        this.id = (name+login+password).hashCode();
        this.is_workman=is_workman;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean getIs_admin() {
        return is_admin;
    }
    public boolean getIs_workman(){return  is_workman;}

    public String getLogin() {
        return login;
    }


    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }
    @Override
    public String toString() {
        return getName();
    }
}