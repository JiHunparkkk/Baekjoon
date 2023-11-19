import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T = 10;

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();
            int[] arr = new int[100];

            for (int i = 0; i < 100; i++) {
                arr[i] = sc.nextInt();
            }

            Arrays.sort(arr);

            for (int i = 0; i < n; i++) {
                flat(arr);
            }

            System.out.println("#" + test_case + " " + (arr[99] - arr[0]));
        }
    }

    private static void flat(int[] arr) {
        arr[0] += 1;
        arr[99] -= 1;

        for (int i = 0; i < 99; i++) {
            if (arr[i] > arr[i + 1]) {
                change(arr, i, i + 1);
            } else {
                break;
            }
        }

        for (int i = 99; i >= 0; i--) {
            if (arr[i] < arr[i - 1]) {
                change(arr, i, i - 1);
            } else {
                break;
            }
        }
    }

    private static void change(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}
