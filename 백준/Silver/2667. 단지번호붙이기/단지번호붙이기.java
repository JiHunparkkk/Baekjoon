import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int[][] board;
    static int cnt,n;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                board[i][j] = input[j]-'0';
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        int answer_count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cnt=0;
                if (board[i][j] == 1) {
                    DFS(i, j);
                    answer_count++;
                    list.add(cnt+1);
                }
            }
        }

        Collections.sort(list);
        System.out.println(answer_count);
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }

    public static void DFS(int x,int y){
        board[x][y] = 0;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx>=0 && ny>=0 && nx<n && ny<n && board[nx][ny]==1){
                cnt++;
                DFS(nx, ny);
            }
        }
    }
}
