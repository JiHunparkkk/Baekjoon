import java.util.Scanner;

class Solution {

    static int n;
    static long c;
    static long h[]; // honey

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        int T = scanner.nextInt();

        for (int tc = 1; tc <= T; tc++) {

            n = scanner.nextInt();
            c = scanner.nextInt();

            h = new long[n];

            for (int i = 0; i < n; i++) {
                h[i] = scanner.nextLong();
            }

            sorting();
            System.out.println("#" + tc + " " + solution());

        }

        scanner.close();
    }

    private static long solution() {
        //무조건 큰 수의 제곱이 제일 큼
        long sum = 0;
        int idx = 0;
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            if (sum + h[i] <= c) {
                sum += h[i];
                arr[idx++] = h[i];
            }
        }

        long result = 0;
        for (long i : arr) {
            result += i * i;
        }

        sum = 0;
        arr = new long[n];
        idx = 0;
        for (int i = 1; i < n; i++) {
            if (sum + h[i] <= c) {
                sum += h[i];
                arr[idx++] = h[i];
            }
        }

        long result2 = 0;
        for (long i : arr) {
            result2 += i * i;
        }

        return result > result2 ? result : result2;
    }

    private static void sorting() {
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (h[i] < h[j]) {
                    long tmp = h[i];
                    h[i] = h[j];
                    h[j] = tmp;
                }
            }
        }
    }
}