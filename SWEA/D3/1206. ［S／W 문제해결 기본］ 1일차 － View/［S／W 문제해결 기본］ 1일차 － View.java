import java.util.Scanner;

class Solution {

    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T = 10;

        for (int test_case = 1; test_case <= T; test_case++) {
            int N, result = 0;
            N = sc.nextInt();

            int[] building = new int[N];

            for (int i = 0; i < N; i++) {
                building[i] = sc.nextInt();
            }

            for (int i = 2; i < N - 2; i++) {
                int min1 = 0, min2 = 0;
                int front1 = building[i] - building[i - 2];
                int front2 = building[i] - building[i - 1];
                int back1 = building[i] - building[i + 1];
                int back2 = building[i] - building[i + 2];

                if (front1 > 0 && front2 > 0 && back1 > 0 && back2 > 0) {
                    min1 = Math.min(front1, front2);
                    min2 = Math.min(back1, back2);
                }

                result += Math.min(min1, min2);
            }
            System.out.println();
            System.out.print("#" + test_case + " ");
            System.out.println(result);
        }

    }
}
