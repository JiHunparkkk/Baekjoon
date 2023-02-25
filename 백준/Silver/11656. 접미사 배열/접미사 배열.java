import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        String input = br.readLine();
        String[] str = new String[input.length()];
        int len = input.length();

        for (int i = 0; i < len; i++) {
            str[i] = input.substring(i, len);
        }

        Arrays.sort(str);

        for (String s : str) {
            sb.append(s).append("\n");
        }
        System.out.print(sb);

        br.close();
    }//main
}
