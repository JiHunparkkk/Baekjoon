import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int count_answer;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] board;
    static int cnt,n,m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine(), " ");

        int k;
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        board = new int[m][n];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a,b,c,d;
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());

            for (int j = a; j < c; j++) {
                for (int z = b; z < d; z++) {
                    board[j][z] = 1;
                }
            }
        }

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                cnt=0;
                if(board[i][j]==0){
                    DFS(i, j);
                    count_answer++;
                    list.add(cnt+1);
                }
            }
        }

        Collections.sort(list);
        System.out.println(count_answer);
        for (Integer integer : list) {
            System.out.print(integer+" ");
        }
    }

    public static void DFS(int x,int y) {
        board[x][y] = 1;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx>=0 && ny>=0 && nx<m && ny<n && board[nx][ny]==0){
                cnt++;
                DFS(nx, ny);
            }
        }
    }
}
