import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n,answer=0;

        n = sc.nextInt();
        int[][] x = new int[n][2];
        int[][] total = new int[100][100];

        for (int i = 0; i < n; i++) {
            x[i][0] = sc.nextInt();
            x[i][1] = sc.nextInt();
            for (int j = x[i][0]; j < x[i][0] + 10; j++) {
                for (int k = x[i][1]; k < x[i][1]+10; k++) {
                    total[j][k]=1;
                }
            }
        }

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if(total[i][j]==1)
                    answer++;
            }
        }

        System.out.println(answer);
    }
}
