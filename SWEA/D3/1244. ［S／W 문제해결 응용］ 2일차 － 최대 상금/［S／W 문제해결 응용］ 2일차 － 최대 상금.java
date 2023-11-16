import java.util.Scanner;

public class Solution {

    private static int[] number;
    private static int count;
    private static int answer;

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            String arr = sc.next();
            count = sc.nextInt();
            number = new int[arr.length()];

            for (int i = 0; i < arr.length(); i++) {
                number[i] = arr.charAt(i) - '0';
            }

            if (count > number.length) {
                count = number.length;
            }
            answer = 0;
            dfs(0, 0);

            System.out.println("#" + test_case + " " + answer);

        }
    }

    public static void dfs(int start, int cnt) {
        if (cnt == count) {
            int result = intArrToString();
            answer = Math.max(answer, result);
        } else {
            for (int i = start; i < number.length - 1; i++) {
                for (int j = i + 1; j < number.length; j++) {
                    swap(i, j);
                    dfs(i, cnt + 1);
                    swap(i, j);
                }
            }
        }
    }

    public static void swap(int i, int j) {
        int tmp = number[i];
        number[i] = number[j];
        number[j] = tmp;
    }

    public static int intArrToString() {
        int result = 0;
        for (int i = 0; i < number.length; i++) {
            result += (Math.pow(10, i) * number[number.length - 1 - i]);
        }
        return result;
    }
}
