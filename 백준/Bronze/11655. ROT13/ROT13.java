import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        String str = br.readLine();

        for (int i = 0; i < str.length(); i++) {
            char now = str.charAt(i);

            if ('a' <= now && now <= 'z') {
                now = (char) (now + 13);
                if(now>'z') now = (char)(now % 'z' + 'a'-1);
            } else if ('A' <= now && now <= 'Z') {
                now = (char) (now + 13);
                if(now>'Z') now = (char)(now % 'Z' + 'A'-1);

            }
            sb.append(now);
        }//for
        System.out.println(sb);

    }
}
