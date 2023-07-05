import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        String str = br.readLine();
        int v = Integer.parseInt(br.readLine());
        int cnt = 0;

        st = new StringTokenizer(str, " ");
        while(st.hasMoreTokens()){
            if(Integer.parseInt(st.nextToken()) == v)
                cnt++;
        }
        System.out.println(cnt);
    }
}
