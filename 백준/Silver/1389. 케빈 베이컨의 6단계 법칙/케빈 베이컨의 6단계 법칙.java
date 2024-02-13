import java.io.*;
import java.util.*;

public class Main {
	
	private static final int INF = 1000000;
	private static int n,m;
	private static int[][] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n+1][n+1];
		
		for(int i=0;i<=n;i++) {
			for(int j=0;j<=n;j++) {
				if(i==j) arr[i][j]=0;
				else arr[i][j] = INF;
			}
		}
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[a][b] = 1;
			arr[b][a] = 1;
		}
		
		
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				for(int k=1;k<=n;k++) {
					if(arr[j][i]+arr[i][k] < arr[j][k]) {
						arr[j][k] = arr[j][i]+arr[i][k];
					}
				}
			}
		}
		
		int sum,min,answer=0;
		min = Integer.MAX_VALUE;
		for(int i=1;i<=n;i++) {
			sum =0;
			for(int j=1;j<=n;j++) {
				sum+= arr[i][j];
			}
			if(sum<min) {
				min=sum;
				answer = i;
			}
		}
		System.out.println(answer);
	}
}
