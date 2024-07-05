import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int H, W;
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        st= new StringTokenizer(br.readLine());
        int[] arr = new int[W];
        for (int i = 0; i < W; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        for (int i = H; i > 0; i--) {
            boolean exist = false;
            int sum = 0;
            for (int j = 0; j < W; j++) {
                if(i <= arr[j]) {
                    if(!exist) {
                        sum = 0;
                        exist = true;
                    } else {
                        answer += sum;
                        sum = 0;
                    }
                } else {
                    sum++;
                }
            }
        }

        System.out.println(answer);
    }
}