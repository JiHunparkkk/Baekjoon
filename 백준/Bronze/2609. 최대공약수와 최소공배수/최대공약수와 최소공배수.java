import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int max = a > b ? a : b;
        int min = a < b ? a : b;

        for (int i = max; i >= 1; i--) {
            if (max % i == 0 && min % i == 0) {
                sb.append(i).append("\n");
                break;
            }
        }

        for (int i = min; i <= min * max; i++) {
            if(i%min==0 && i%max==0){
                sb.append(i);
                break;
            }
        }

        System.out.println(sb);

        br.close();
    }
}
