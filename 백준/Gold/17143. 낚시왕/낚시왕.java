import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	private static class Shark {
		int x, y, s, d, z; // 속력, 방향, 크기

		public Shark(int x, int y, int s, int d, int z) {
			this.x = x;
			this.y = y;
			this.s = s;
			this.d = d;
			this.z = z;
		}

		@Override
		public String toString() {
			return "Shark [x=" + x + ", y=" + y + ", s=" + s + ", d=" + d + ", z=" + z + "]";
		}

	}

	private static int R, C;
	private static Shark[][] board;
	private static List<Shark> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int M;
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new Shark[R + 1][C + 1];
		list = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r, c, s, d, z;
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken()) - 1; // d는 0부터 시작
			z = Integer.parseInt(st.nextToken());

			Shark shark = new Shark(r, c, s, d, z);
			board[r][c] = shark;
			list.add(shark);
		}

		int answer = solution();
		System.out.println(answer);
	}

	private static int solution() {
		int answer = 0;

		// 초 단위로 이동
		for (int i = 1; i <= C; i++) {
			answer += catchShark(i);
			moveAllShark();
//			testPrint();
		}

		return answer;
	}

	private static int catchShark(int i) {
		int result = 0;

		for (int j = 1; j <= R; j++) {
			if (board[j][i] != null) {
				result += board[j][i].z;
				list.remove(board[j][i]);
				board[j][i] = null;
				break;
			}
		}
		return result;
	}

	private static void moveAllShark() {
		// 리스트별 상어 이동 후 새로운 board에 배치
		// 해당 위치에 상어가 있을 경우 크기 비교 후 작은거 리스트에서 삭제
		board = new Shark[R + 1][C + 1];

		for (int i = 0; i < list.size(); i++) {
			Shark shark = list.get(i);

			move(shark);

			int x = shark.x;
			int y = shark.y;
			if (board[x][y] == null) {
				board[x][y] = shark;
			} else {
				if (board[x][y].z > shark.z) {
					list.remove(shark);
					i--;
				} else {
					list.remove(board[x][y]);
					i--;
					board[x][y] = shark;
				}
			}
		}
	}

	private static void move(Shark shark) {
		int[][] dxy = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

		for (int i = 0; i < shark.s;) {
			int nx = shark.x + dxy[shark.d][0];
			int ny = shark.y + dxy[shark.d][1];

			if (isIn(nx, ny)) {
				shark.x = nx;
				shark.y = ny;
				i++;
			} else {
				if (shark.d == 0) {
					shark.d = 1;
				} else if (shark.d == 1) {
					shark.d = 0;
				}

				if (shark.d == 2) {
					shark.d = 3;
				} else if (shark.d == 3) {
					shark.d = 2;
				}
			}
		}
	}

	private static boolean isIn(int nx, int ny) {
		return nx >= 1 && ny >= 1 && nx <= R && ny <= C;
	}

	private static void testPrint() {
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				if (board[i][j] != null)
					System.out.print(board[i][j].z + " ");
				else {
					System.out.print(0 + " ");
				}
			}
			System.out.println();
		}

		System.out.println("==================================");
	}
}