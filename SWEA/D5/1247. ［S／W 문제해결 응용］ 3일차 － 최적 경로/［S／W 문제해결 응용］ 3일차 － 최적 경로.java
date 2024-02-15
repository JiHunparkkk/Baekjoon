import java.util.Scanner;

public class Solution {
	
	private static Point[] points;
	private static Point company,home;
	private static boolean[] visited;
	private static int n,answer;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++){
			n = sc.nextInt();
			points = new Point[n];
			company = new Point(sc.nextInt(), sc.nextInt());
			home = new Point(sc.nextInt(), sc.nextInt());
			
			for(int i=0;i<n;i++) {
				points[i] = new Point(sc.nextInt(), sc.nextInt());
			}
			
			answer = Integer.MAX_VALUE;
			visited = new boolean[n];
			for(int i=0;i<n;i++) {
				int w = Math.abs(company.x-points[i].x) + Math.abs(company.y-points[i].y);
				visited[i] = true;
				solution(points[i], w, 1);
				visited[i] = false;
			}
			
			System.out.println("#"+test_case+" "+answer);
		}
	}
	
	private static void solution(Point point,int weight,int depth) {
		if(depth==n) {
			int w = Math.abs(point.x-home.x) + Math.abs(point.y-home.y);
			answer = Math.min(answer, w+weight);
			return;
		}
		
		for(int i=0;i<n;i++) {
			if(!visited[i]) {
				int w = Math.abs(point.x-points[i].x) + Math.abs(point.y-points[i].y);
				visited[i] = true;
				solution(points[i], weight+w, depth+1);
				visited[i] = false;
			}
		}
	}
}

class Point{
	int x;
	int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}		
}