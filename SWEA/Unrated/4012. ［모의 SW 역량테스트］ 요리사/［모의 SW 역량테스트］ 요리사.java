import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

	private static int N, answer;
	private static int[][] board;
	private static boolean[] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt();
			board = new int[N][N];
			visited = new boolean[N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					board[i][j] = sc.nextInt();
				}
			}

			answer = Integer.MAX_VALUE;

			solution(0, 0);

			System.out.println("#" + test_case + " " + answer);
			//System.out.println();
		}
	}

	private static void solution(int cnt, int start) {
		if (cnt == N / 2) {
			sum();
			return;
		}

		for (int i = start; i < N; i++) {
			visited[i] = true;
			solution(cnt+1, i+1);
			visited[i] = false;
		}
	}
	
	private static void sum() {
		int A=0, B=0;
		int sum=0;
		
		for(int i=0;i<N-1;i++) {
			for(int j=i+1;j<N;j++) {
				if(visited[i] && visited[j]) {
					A+=board[i][j]+board[j][i];
				}
				if(!visited[i] && !visited[j]){
					B+=board[i][j] + board[j][i];
				}
			}
		}
		
		sum = Math.abs(A-B);
		answer = Math.min(answer,sum);
	}
}