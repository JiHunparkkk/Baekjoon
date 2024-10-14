import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static class Product {
        int w, v;

        public Product(int w, int v) {
            this.w = w;
            this.v = v;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        Product[] product = new Product[n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            product[i] = new Product(w, v);
        }

        int[][] dp = new int[n + 1][k + 1]; //n번째 까지 검사했을 때, 최대 무게 k 에서의 최대 가치

        for (int i = 1; i <= n; i++) {  //i번째 물건
            for (int j = 1; j <= k; j++) {  //j무게
                if(j >= product[i].w) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - product[i].w] + product[i].v);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        System.out.println(dp[n][k]);
    }
}