import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        String target = br.readLine();

        solution(input, target);
    }

    private static int[] failFunction(String target) {
        int targetLen = target.length();
        int[] arr = new int[targetLen];

        int j = 0;
        for (int i = 1; i < targetLen; i++) {
            //다르면 j 변경
            while (j > 0 && target.charAt(i) != target.charAt(j)) {
                j = arr[j - 1];
            }

            if (target.charAt(i) == target.charAt(j)) {
                arr[i] = ++j;
            }
        }
        return arr;
    }

    private static void solution(String input, String target) {
        List<Integer> answer = new ArrayList<>();
        int[] arr = failFunction(target);
        int inputLen = input.length();
        int targetLen = target.length();

        int j = 0;
        for (int i = 0; i < inputLen; i++) {
            while (j > 0 && input.charAt(i) != target.charAt(j)) {
                j = arr[j - 1];
            }

            if (input.charAt(i) == target.charAt(j)) {
                if (j == targetLen - 1) {
                    answer.add(i - targetLen + 2);
                    j = arr[j];
                } else {
                    j++;
                }
            }
        }

        System.out.println(answer.size());
        for (Integer integer : answer) {
            System.out.print(integer + " ");
        }
    }
}