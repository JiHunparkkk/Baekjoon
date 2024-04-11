import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	private static class PairPoint {
		int redX, redY, blueX, blueY, cnt;

		public PairPoint(int redX, int redY, int blueX, int blueY, int cnt) {
			this.redX = redX;
			this.redY = redY;
			this.blueX = blueX;
			this.blueY = blueY;
			this.cnt = cnt;
		}
	}

	private static int[] dx = { 1, -1, 0, 0 };
	private static int[] dy = { 0, 0, 1, -1 };
	private static int n, m;
	private static char[][] board;
	private static boolean redHoleResult, blueHoleResult;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		board = new char[n][m];
		PairPoint pair = new PairPoint(0, 0, 0, 0, 0);
		for (int i = 0; i < n; i++) {
			String input = br.readLine();
			for (int j = 0; j < m; j++) {
				board[i][j] = input.charAt(j);

				if (board[i][j] == 'R') {
					pair.redX = i;
					pair.redY = j;
					board[i][j] = '.';
				}
				if (board[i][j] == 'B') {
					pair.blueX = i;
					pair.blueY = j;
					board[i][j] = '.';
				}
			}
		}

		System.out.println(bfs(pair));
	}

	private static int bfs(PairPoint pair) {
		Queue<PairPoint> queue = new ArrayDeque<>();

		queue.add(pair);

		while (!queue.isEmpty()) {

			PairPoint poll = queue.poll();

			if (poll.cnt >= 10) {
				return -1;
			}

			for (int d = 0; d < 4; d++) {
				int redX = poll.redX;
				int redY = poll.redY;
				int blueX = poll.blueX;
				int blueY = poll.blueY;
				redHoleResult = false;
				blueHoleResult = false;

				int[] result = move(redX, redY, dx[d], dy[d], 'R');
				redX = result[0];
				redY = result[1];

				result = move(blueX, blueY, dx[d], dy[d], 'B');
				blueX = result[0];
				blueY = result[1];

				if (blueHoleResult) {
					continue;
				}
				if (redHoleResult) {
					return poll.cnt + 1;
				}

				if (redX == poll.redX && redY == poll.redY && blueX == poll.blueX && blueY == poll.blueY) {
					continue;
				}

				// 구슬이 겹친 경우
				if (redX == blueX && redY == blueY) {
					// 아래
					if (d == 0) {
						if (poll.redX > poll.blueX) {
							blueX--;
						} else {
							redX--;
						}
					} else if (d == 1) { // 위
						if (poll.redX > poll.blueX) {
							redX++;
						} else {
							blueX++;
						}
					} else if (d == 2) { // 오른쪽
						if (poll.redY > poll.blueY) {
							blueY--;
						} else {
							redY--;
						}
					} else if (d == 3) { // 왼쪽
						if (poll.redY > poll.blueY) {
							redY++;
						} else {
							blueY++;
						}
					}
				}

				queue.add(new PairPoint(redX, redY, blueX, blueY, poll.cnt + 1));
			}
		}

		return -1;
	}

	private static int[] move(int x, int y, int d_x, int d_y, char color) {

		while (true) {
			int newX = x + d_x;
			int newY = y + d_y;

			if (board[newX][newY] == '#') {
				break;
			}

			if (board[newX][newY] == 'O') {
				if (color == 'R') {
					redHoleResult = true;
				} else {
					blueHoleResult = true;
				}
				return new int[] { newX, newY };
			}

			x = newX;
			y = newY;
		}

		return new int[] { x, y };
	}
}