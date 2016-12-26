import java.io.*;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ClientConnection implements Runnable {

    private Socket socket;

    private String s;

    private String[] nums;

    private String stringInputMessage;

    private StringBuilder builder;

    private PrintWriter pw;

    private BufferedReader br;

    private Pattern regexp1;

    private Pattern regexp2;

    private Matcher m1;

    private Matcher m2;

    private String exp;

    private Pattern regexp3;

    public void run() {

        while (true) {

            String st = null;

            try {
                st = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (st == null || st.trim().length() == 0) {
                break;
            }

            builder.append(st);

        }

        stringInputMessage = builder.toString();

        System.out.println(stringInputMessage);

        m1 = regexp1.matcher(stringInputMessage);

        boolean b = m1.matches();

       // System.out.println(b);

        if (!b) {
            pw.println("Incorrect REQUEST!");
        } else {

            int pos = stringInputMessage.indexOf("&");

            s = stringInputMessage.substring(pos + 1);

            String op = null;

            int n1, n2;

            if (s.contains("sum")) {
                op = "sum";
                nums = s.split(op);
                n1 = Integer.parseInt(nums[0]);
                n2 = Integer.parseInt(nums[1]);
                int sum = n1 + n2;
                System.out.println(sum);
                pw.println(String.valueOf(sum));
            }
            if (s.contains("min")) {
                op = "min";
                nums = s.split(op);
                n1 = Integer.parseInt(nums[0]);
                n2 = Integer.parseInt(nums[1]);
                int min = n1 - n2;
                System.out.println(min);
                pw.println(String.valueOf(min));
            }
            if (s.contains("div")) {
                op = "div";
                nums = s.split(op);
                n1 = Integer.parseInt(nums[0]);
                n2 = Integer.parseInt(nums[1]);
                int div = n1 / n2;
                System.out.println(div);
                pw.println(String.valueOf(div));
            }
            if (s.contains("pro")) {
                op = "pro";
                nums = s.split(op);
                n1 = Integer.parseInt(nums[0]);
                n2 = Integer.parseInt(nums[1]);
                int pro = n1 * n2;
                System.out.println(pro);
                pw.println(String.valueOf(pro));
            }

        }

    }

    public ClientConnection(Socket s) throws IOException {

        this.socket = s;

        builder = new StringBuilder();

        pw = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()));

        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        regexp1 = Pattern.compile("^GET / Ivan/1.0 HOST: localhost &\\d+[a-z][a-z][a-z]\\d+$");

        regexp2 = Pattern.compile("\\d+[+-/*]\\d+");

        regexp3 = Pattern.compile("\\d+");

    }
}
