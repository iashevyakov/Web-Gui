package ru.itis.inform;

import java.io.*;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Scanner;


public class UsersDaoImpl implements UsersDao {
    public LinkedList<User> findAll() throws FileNotFoundException, SQLException {
        return null;
    }

    public void save(User user) throws FileNotFoundException {

    }

    public User find(int id) throws FileNotFoundException {
        return null;
    }



  /*  public LinkedList<User> findAll() throws FileNotFoundException {

        Scanner sc = new Scanner(new File("C:\\JavaProjects\\UserDao\\src\\main\\resources\\Users.txt"));

            LinkedList<User> userList = new LinkedList<User>();

            User newUser;

            String name;

            while(sc.hasNext()) {


                 name=sc.nextLine();

                 newUser = new User(name);

                 newUser.setId(newUser.hashCode());

                 userList.addLast(newUser);

            }

        sc.close();

            return userList;

        }



    public void save(User user) throws FileNotFoundException {


        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter("C:\\JavaProjects\\UserDao\\src\\main\\resources\\Users.txt", true));
            user.setId(user.hashCode());

            pw.println(user.toString());
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public User find(int id) throws FileNotFoundException {

        LinkedList<User> userList = findAll();

        User foundUser = null;

        while( userList.size()!=0 ) {


            if ( id==userList.getFirst().getId())  {

                foundUser=userList.getFirst();

                return foundUser;

            }
            else {

                userList.removeFirst();

            }



        }


        return foundUser;
    }
    */
}
