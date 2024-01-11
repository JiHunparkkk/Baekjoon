import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int[][] rupy;
	static int[][] weight;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());
		
		int test_case=1;
		while(n!=0) {
			rupy = new int[n][n];
			weight = new int[n][n];
			visited = new boolean[n][n];
			
			for(int i=0;i<n;i++) {
				st = new StringTokenizer(br.readLine()," ");
				for(int j=0;st.hasMoreTokens();j++) {
					rupy[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					weight[i][j] = Integer.MAX_VALUE;
				}
			}
			
			moveLink();
			
			sb.append("Problem "+test_case+": ").append(weight[n-1][n-1]).append("\n");
			
			test_case++;
			n = Integer.parseInt(br.readLine());
		}
		
		bw.write(sb.toString());
		
		br.close();
		bw.close();
	}
	
	private static void moveLink() {
		PriorityQueue<Point> pq = new PriorityQueue<>(new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				return weight[o1.x][o1.y] - weight[o2.x][o2.y];
			}
		});
		pq.offer(new Point(0,0));
		weight[0][0] = rupy[0][0];
		
		while(!pq.isEmpty()) {
			Point poll = pq.poll();
			int now_x = poll.x;
			int now_y = poll.y;
			
			visited[now_x][now_y] = true;
			
			for(int i=0;i<4;i++) {
				int nx = now_x + dx[i];
				int ny = now_y + dy[i];
				
				if(nx>=0 && ny>=0 && nx<n && ny<n && visited[nx][ny] == false) {
					weight[nx][ny] = Math.min(weight[nx][ny], weight[now_x][now_y] + rupy[nx][ny]);
					pq.offer(new Point(nx,ny));
				}
			}
		}
	}
}

class Point{
	int x;
	int y;
	
	public Point(int x,int y) {
		this.x = x;
		this.y = y;
	}
}
