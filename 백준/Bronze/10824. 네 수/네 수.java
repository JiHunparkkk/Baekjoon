import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String input = br.readLine();
        Long[] num = new Long[2];
        Long result=0L;
        String tmp = "";
        int i=1,j=0;

        st = new StringTokenizer(input, " ");
        while (st.hasMoreTokens()) {
            tmp += st.nextToken();
            if (i % 2 == 0) {
                num[j++] = Long.parseLong(tmp);
                tmp = "";
            }
            i++;
        }
        result = num[0] + num[1];
        System.out.println(result);
    }
}
