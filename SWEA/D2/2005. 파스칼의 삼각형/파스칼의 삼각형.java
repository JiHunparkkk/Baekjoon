import java.util.Scanner;

public class Solution {
    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T, N;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            N = sc.nextInt();
            System.out.println("#" + test_case);

            int[][] tree = new int[10][10];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j <= i; j++) {
                    if (j == 0 || j == i) {
                        tree[i][j] = 1;
                    } else {
                        int a = tree[i - 1][j - 1];
                        int b = tree[i - 1][j];
                        if (a != 0 && b != 0) {
                            tree[i][j] = a + b;
                        }
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j <= i; j++) {
                    System.out.print(tree[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}