import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int answer = 0;
        int[][] total = new int[102][102];
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();

            for (int j = x+1; j <= x + 10; j++) {
                for (int k = y+1; k <= y + 10; k++) {
                    total[j][k] = 1;
                }
            }
        }

        for (int i = 1; i < 101; i++) {
            for (int j = 1; j < 101; j++) {
                if (total[i][j] == 1) {
                    for (int k = 0; k < 4; k++) {
                        int movei = i + dx[k];
                        int movej = j + dy[k];
//                        if(movej < 0 || movei < 0 || movej >= 100 || movei >= 100) continue;
                        if (total[movei][movej] == 0) {
                            answer++;
//                            System.out.println("movei = " + movei + " movej = " + movej);
                        }
                    }
                }
            }
        }

        System.out.println(answer);
    }
}
