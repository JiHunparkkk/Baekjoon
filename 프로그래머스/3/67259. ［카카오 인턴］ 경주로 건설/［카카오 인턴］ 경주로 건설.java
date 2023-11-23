import java.util.LinkedList;
import java.util.Queue;

class Solution {
    private static final int BLOCKED = 1;

    static int len;
    static int[][][] visited;
    static int[][] d = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int solution(int[][] board) {
        len = board.length;
        visited = new int[len][len][4];

        return bfs(board);
    }

    private static int bfs(int[][] board) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, -1, 0));

        int result = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            if (now.x == len - 1 && now.y == len - 1) {
                result = Math.min(result, now.cost);
            }

            for (int dir = 0; dir < 4; dir++) {
                int nx = now.x + d[dir][0];
                int ny = now.y + d[dir][1];

                if (0 > nx || nx >= len || 0 > ny || ny >= len || board[nx][ny] == BLOCKED) {
                    continue;
                }

                int nextCost = now.cost;
                if (now.dir == -1 || now.dir == dir) {
                    nextCost += 100;
                } else {
                    nextCost += 600;
                }

                if (visited[nx][ny][dir] == 0 || board[nx][ny] >= nextCost) {
                    visited[nx][ny][dir] = 1;
                    board[nx][ny] = nextCost;
                    queue.add(new Node(nx, ny, dir, nextCost));
                }
            }
        }
        return result;
    }


    static class Node {
        int x;
        int y;
        int dir;
        int cost;

        public Node(int x, int y, int dir, int cost) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cost = cost;
        }
    }
}