import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int t;

        t = scanner.nextInt();

        for (int i = 0; i < t; i++) {
            int sum = 0;
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            for (int j = 0; j < 10; j++) {
                int tmp = scanner.nextInt();
                if (min > tmp) {
                    min = tmp;
                }
                if (max < tmp) {
                    max = tmp;
                }
                sum += tmp;
            }

            sum = sum - (min + max);
            System.out.println("#" + (i + 1) + " " + (int) ((double) sum / 8 + 0.5));
        }

    }
}
