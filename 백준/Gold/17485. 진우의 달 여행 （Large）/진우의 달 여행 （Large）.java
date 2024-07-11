import java.io.*;
import java.util.*;

//BOJ17484 진우의 달 여행
public class Main {

    static int n,m;
    static int[][] matrix;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        matrix = new int[n][m];
        dp = new int[n][m][3];

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
                if(i==0) Arrays.fill(dp[i][j],matrix[i][j]);
                else Arrays.fill(dp[i][j],Integer.MAX_VALUE);
            }
        }

        for(int i=1;i<n;i++){
            for(int j=0;j<m;j++){
                if(j!=0) dp[i][j][0] = Math.min(dp[i-1][j-1][1],dp[i-1][j-1][2]) + matrix[i][j];
                dp[i][j][1] = Math.min(dp[i-1][j][0],dp[i-1][j][2]) + matrix[i][j];
                if(j!=m-1) dp[i][j][2] = Math.min(dp[i-1][j+1][0],dp[i-1][j+1][1]) + matrix[i][j];
            }
        }

        int ans = Integer.MAX_VALUE;
        for(int j=0;j<m;j++){
            int minValue = dp[n-1][j][0];
            for(int k=1;k<3;k++){
                minValue = Math.min(minValue,dp[n-1][j][k]);
            }
            ans = Math.min(ans,minValue);
        }

        System.out.println(ans);


    }
}