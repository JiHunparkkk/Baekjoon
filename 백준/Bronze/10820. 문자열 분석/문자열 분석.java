import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        String str = "";
        while((str=br.readLine())!=null){

            int[] cnt = new int[4];

            for (int i = 0; i < str.length(); i++) {
                char now = str.charAt(i);
                if ('a' <= now && now <= 'z') {
                    cnt[0]++;
                } else if ('A' <= now && now <= 'Z') {
                    cnt[1]++;
                } else if ('0' <= now && now <= '9') {
                    cnt[2]++;
                } else if (' ' == now) {
                    cnt[3]++;
                }
            }//for

            for (int i : cnt) {
                sb.append(i).append(" ");
            }
            sb.append("\n");

        }//while
        System.out.println(sb);
        br.close();

    }
}
