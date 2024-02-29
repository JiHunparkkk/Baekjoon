import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {	
	
	static class Point{
		int x,y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}	
	}
	
	private static int N,M,answer;
	private static int[][] board;
	private static int[][] copyBoard;
	private static List<Point> virus = new ArrayList<>();
	private static List<Point> safeArea = new ArrayList<>();
	private static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				
				if(board[i][j]==2) {
					virus.add(new Point(i,j));
				}
				if(board[i][j]==0) {
					safeArea.add(new Point(i,j));
				}
			}
		}
		
		visited = new boolean[safeArea.size()];
		findBlock(0,0,new Point[3]);
		
		System.out.println(answer);
		//벽을 빈칸중 임의의 위치에 3개를 놓는다.
		//바이러스를 퍼트려 안전구역의 개수를 확인한다.
	}
	
	private static void copyArray() {
		int[][] copy = new int[N][M];
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				copy[i][j] = board[i][j];
			}
		}
		copyBoard = copy;
	}
	
	private static void findBlock(int start, int depth, Point[] blocks) {
		 if(depth==3) {
			 copyArray();
			 setBlock(blocks);
			 int result = moveVirus();
//			 testPrint(result);
			 answer = Math.max(answer, result);
			 return;
		 }
		 
		 for(int i=start;i<safeArea.size();i++) {
			 if(!visited[i]) {
				 visited[i] = true;
				 blocks[depth] = safeArea.get(i);
				 findBlock(i+1,depth+1,blocks);
				 visited[i] = false;
			 }
		 }
	}
	
	private static void setBlock(Point[] blocks) {
		for(Point point : blocks) {
			copyBoard[point.x][point.y] = 1;
		}
	}

	
	private static int moveVirus() {
		int[] dx = {-1,0,1,0};
		int[] dy = {0,1,0,-1};

		Queue<Point> queue = new ArrayDeque<>();
		for(Point point : virus) {
			queue.add(point);
		}

		while(!queue.isEmpty()) {
			Point poll = queue.poll();
			
			for(int i=0;i<4;i++) {
				int nx = poll.x + dx[i];
				int ny = poll.y + dy[i];
				
				if(nx>=0 && ny>=0 && nx<N && ny<M 
						&& copyBoard[nx][ny]==0) {
					copyBoard[nx][ny] = 2;
					queue.add(new Point(nx,ny));
				}
			}
		}
		
		long result = Arrays.stream(copyBoard)
				.flatMapToInt(Arrays::stream)
				.filter(s->s==0).count();
		
		return (int)result;
	}
	
	private static void testPrint(int result) {
		for(int[] block : copyBoard) {
			for(int x : block) {
				System.out.print(x+" ");
			}
			System.out.println();
		}
		System.out.println(result);
		System.out.println("====================");
	}
}
