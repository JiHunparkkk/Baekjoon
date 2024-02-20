import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	private static int n,m,answer;
	private static int[][] board;
	private static boolean[] visited;
	private static List<int[]> chicken;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		board = new int[n][n];
		chicken = new ArrayList<>();
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j]==2) {
					chicken.add(new int[] {i,j});
				}
			}
		}
		
		answer = Integer.MAX_VALUE;
		visited = new boolean[chicken.size()];
		
		//최대 m개 ~ 최소 1개 치킨집 선택
		while(m>0) {
			choiceChicken(0, 0, new int[m][2]);		
			m--;
		}
		
		System.out.println(answer);
	}
	
	//치킨집을 최대 m개 골라서 각 집과의 거리 계산
	private static void choiceChicken(int start, int depth,int[][] now) {//now는 치킨집의 좌표 저장
		if(depth==m) {
			int result = findDistance(now);
			answer = Math.min(answer, result);
			return;
		}
		
		for(int i=start;i<chicken.size();i++) {
			if(!visited[i]) {
				visited[i] = true;
				now[depth][0] = chicken.get(i)[0];	//선택된 치킨집의 x좌표
				now[depth][1] = chicken.get(i)[1];	//선택된 치킨집의 y좌표
				choiceChicken(i+1, depth+1, now);
				visited[i] = false;
			} 
		}
	}
	
	private static int findDistance(int[][] choicedChicken) {
		//각 집에서 선택된 치킨 집 까지의 거리를 측정 후 최소값 저장
		//각 집의 최소 거리를 모두 합하면 도시의 치킨 거리
		int result = 0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(board[i][j]==1) {
					result+=findMinDis(choicedChicken,new int[] {i,j});
				}
			}
		}
		return result;
	}
	
	private static int findMinDis(int[][] choicedChicken, int[] home) {
		int min = Integer.MAX_VALUE;
		for(int i=0;i<choicedChicken.length;i++) {
			int dis = Math.abs(choicedChicken[i][0] - home[0]) + Math.abs(choicedChicken[i][1] - home[1]);
			min = Math.min(min, dis);
		}
		return min;
	}
}
