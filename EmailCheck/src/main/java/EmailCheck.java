import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EmailCheck {

    private Scanner sc;

    private static final Pattern p = Pattern.compile("^([a-zA-Z0-9\\.\\-]+)@(?:[a-zA-Z]+\\.)+[a-z]+$");

    private static final Pattern firstPart = Pattern.compile("([a-zA-Z0-9\\.\\-])+@");

    private static final Pattern secondPart = Pattern.compile("@(([a-zA-Z]+\\.)+[a-z]+)");


    private Matcher m;

    private String exp;

    private Matcher m1;

    private Matcher m2;


    public EmailCheck() {

        sc = new Scanner(System.in);
        check();

    }

    public void check() {

        exp = sc.nextLine();

        m = EmailCheck.p.matcher(exp);

        m1 = EmailCheck.firstPart.matcher(exp);

        m2 = EmailCheck.secondPart.matcher(exp);

        if (m.matches()) {
            while (m1.find())
                System.out.println(m1.group().replace("@", ""));
            while (m2.find())
                System.out.println(m2.group().replace("@", ""));
        } else {
            System.out.println("Your data doesn't match email pattern!");
        }
    }

    public static void main(String[] args) {

        EmailCheck e = new EmailCheck();

    }

}
