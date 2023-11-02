import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int t;

        t = scanner.nextInt();

        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            int result = 0;

            int y = n;
            for (int x = 0; x <= n; ) {
                if (x * x + y * y <= n * n) {
                    result += y;
                    x++;
                } else {
                    y--;
                }
            }
            result = result * 4 + 1;
            System.out.println("#" + (i + 1) + " " + result);
        }
    }
}
