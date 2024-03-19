import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int x = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String op = st.nextToken();

            switch (op) {
                case "add":
                    int num = Integer.parseInt(st.nextToken());
                    x |= (1 << num);
                    break;
                case "remove":
                    num = Integer.parseInt(st.nextToken());
                    x &= ~(1 << num);
                    break;
                case "check":
                    num = Integer.parseInt(st.nextToken());
                    if ((x & (1 << num)) == (1 << num)) {
                        sb.append(1).append("\n");
                    } else {
                        sb.append(0).append("\n");
                    }
                    break;
                case "toggle":
                    num = Integer.parseInt(st.nextToken());
                    x ^= (1 << num);
                    break;
                case "all":
                    x = (1 << 21) - 1;
                    break;
                case "empty":
                    x = 0;
                    break;
            }
        }
        System.out.println(sb);
    }
}