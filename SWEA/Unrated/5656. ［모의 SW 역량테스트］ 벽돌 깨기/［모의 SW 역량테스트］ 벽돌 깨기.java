import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	
	private static class Point{
		int x, y, num;
		
		public Point(int x, int y, int num) {
			this.x = x;
			this.y = y;
			this.num = num;
		}
	}
	
	private static int n, w, h, answer;
		
	public static void main(String[] args) throws IOException{
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 StringTokenizer st;
		 StringBuilder sb = new StringBuilder();
		 
		 int T = Integer.parseInt(br.readLine());
		 for(int test_case = 1; test_case <= T; test_case++) {
	
			 st = new StringTokenizer(br.readLine());
			 n = Integer.parseInt(st.nextToken());
			 w = Integer.parseInt(st.nextToken());
			 h = Integer.parseInt(st.nextToken());
			 
			 int[][] board = new int[h + 1][w + 1];
			 for(int i = 0; i < h; i++) {
				 st = new StringTokenizer(br.readLine());
				 for(int j = 0; j < w; j++) {
					 board[i][j] = Integer.parseInt(st.nextToken());
					 if(board[i][j] > 0) {
					 }
				 }
			 }
			 
			 answer = Integer.MAX_VALUE;
			 solution(board, n);
						 
			 sb.append("#"+test_case+" " + answer).append("\n");
		 }
		 
		 System.out.println(sb.toString());
	}
	
	private static void solution(int[][] board, int n) {
		List<Point> firsts = findTop(board);
		
		if(firsts.isEmpty()) {
			answer = 0;
			return;
		}
		
		for(int i = 0; i < firsts.size(); i++) {
			Point first = firsts.get(i);
			
			int[][] copy = copyBoard(board);
			breakWall(copy, first, 0);
		}
	}
	
	private static void breakWall(int[][] copy, Point now, int depth) {
		if(depth == n) {
			return;
		}
		
		
		bomb(copy, now);	//now의 현재 벽돌 부수기 (부숴진 갯수 저장)
		organizeArray(copy);	//벽돌 정렬해주기
		answer = Math.min(answer, calSum(copy));
		
		if(answer == 0) return;

		//다음 벽돌 찾기
		List<Point> nexts = findTop(copy);
		for(int i = 0; i < nexts.size(); i++) {
			Point next = nexts.get(i);
			
			int[][] nextBoard = copyBoard(copy);
			breakWall(nextBoard, next, depth + 1);
		}
	}
	
	private static void bomb(int[][] copy, Point now) {
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, 1, 0, -1};
		
		Queue<Point> queue = new ArrayDeque<>();
		queue.add(now);
		
		copy[now.x][now.y] = 0;

		while(!queue.isEmpty()) {
			Point poll = queue.poll();
			
			for(int i = 0; i < 4;i ++) {
				int nx = poll.x + dx[i];
				int ny = poll.y + dy[i];
				for(int j = 1; j < poll.num; j++) {
					if(isIn(nx, ny)) {
						if(copy[nx][ny] > 0) {
							queue.add(new Point(nx, ny, copy[nx][ny]));
						}
						
						copy[nx][ny] = 0;
					}
					nx += dx[i];
					ny += dy[i];
				}
			}
		}
		
	}

	private static void organizeArray(int[][] copy) {
		for(int i = 0; i < w; i++) {
			for(int j = h - 2; j >= 0 ; j--) {
				if(copy[j][i] > 0) {
					int idx = j;
					while(idx + 1 < h && copy[idx][i] > 0 && copy[idx + 1][i] == 0) {
						copy[idx + 1][i] = copy[idx][i];
						copy[idx][i] = 0;
						idx++;
					}
				}
			}
		}
	}

	private static boolean isIn(int nx, int ny) {
		return nx >= 0 && ny >=0 && nx < h && ny < w;
	}

	private static int calSum(int[][] board) {
		int sum = 0;
		for(int i = 0; i < h; i++) {
			for(int j = 0; j < w; j++) {
				if(board[i][j] > 0) {
					sum++;
				}
			}
		}
		return sum;
	}
	
	private static List<Point> findTop(int[][] board) {
		List<Point> list = new ArrayList<>();
		
		for(int i = 0; i < w; i++) {
			for(int j = 0; j < h; j++) {
				if(board[j][i] > 0) {
					list.add(new Point(j, i, board[j][i]));
					break;
				}
			}
		}
		return list;
	}
	
	private static int[][] copyBoard(int[][] board){
		int[][] newBoard = new int[h][w];
		
		for(int i = 0; i < h; i++) {
			 for(int j = 0; j < w; j++) {
				 newBoard[i][j] = board[i][j];
			 }
		 }
		return newBoard;
	}

}