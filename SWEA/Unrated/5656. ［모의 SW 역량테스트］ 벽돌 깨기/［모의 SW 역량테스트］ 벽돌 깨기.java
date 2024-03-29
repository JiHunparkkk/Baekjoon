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
			 
			 int[][] board = new int[h][w];
			 for(int i = 0; i < h; i++) {
				 st = new StringTokenizer(br.readLine());
				 for(int j = 0; j < w; j++) {
					 board[i][j] = Integer.parseInt(st.nextToken());
				 }
			 }
			 
			 answer = Integer.MAX_VALUE;
			 solution(0, new int[n], board);
						 
			 sb.append("#"+test_case+" " + answer).append("\n");
		 }
		 
		 System.out.println(sb.toString());
	}
	
	private static void solution(int depth, int[] arr, int[][] board) {
		if(depth == n) {
			breakWall(copyBoard(board), arr);
			return;
		}
		
		for(int i = 0; i < w; i++) {
			arr[depth] = i;
			solution(depth + 1, arr, board);
		}
	}
	
	private static void breakWall(int[][] copy, int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			Point now = findTop(copy, arr[i]);
			
			if(now == null) {
				continue;
			}
			
			bomb(copy, now);
			down(copy);
//			organizeArray(copy);
		}
		answer = Math.min(answer, calSum(copy));
	}
	
	private static void bomb(int[][] copy, Point now) {
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, 1, 0, -1};
		
		Queue<Point> queue = new ArrayDeque<>();
		queue.add(now);
		
		boolean[][] visited = new boolean[h][w];
		copy[now.x][now.y] = 0;
		visited[now.x][now.y] = true;
		
		while(!queue.isEmpty()) {
			Point poll = queue.poll();
			
			copy[poll.x][poll.y] = 0;
			
			for(int i = 0; i < 4;i ++) {
				int nx = poll.x + dx[i];
				int ny = poll.y + dy[i];
				for(int j = 1; j < poll.num; j++) {
					if(isIn(nx, ny) && !visited[nx][ny]) {
						if(copy[nx][ny] > 0) {
							queue.add(new Point(nx, ny, copy[nx][ny]));	
							copy[nx][ny] = 0;
						}
						visited[nx][ny] = true;
					}

					nx += dx[i];
					ny += dy[i];
				}
			}
		}
		
	}
	
	 public static void down(int[][] test) {
	        for(int i = h-2; i>=0 ; i--) {
	            for(int j = 0 ; j<w ; j++) {
	                if(test[i][j] !=0 && test[i+1][j]==0) {
	                    int temp = test[i][j];
	                    test[i][j] = 0;
	                    boolean flag = false;
	                    for(int find = i+1 ; find<h ; find++) {
	                        if(test[find][j]!=0) {
	                            test[find-1][j] = temp;
	                            flag = true;
	                            break;
	                        }
	                    }
	                    if(!flag) test[h-1][j] = temp;
	                }
	            }
	        }
	    }

	private static void organizeArray(int[][] copy) {
		for(int i = 0; i < w; i++) {
			for(int j = h - 2; j > 0 ; j--) {
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
	
	private static Point findTop(int[][] board, int idx) {		
		for(int j = 0; j < h; j++) {
			if(board[j][idx] > 0) {
				return new Point(j, idx, board[j][idx]);
			}
		}
		return null;
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