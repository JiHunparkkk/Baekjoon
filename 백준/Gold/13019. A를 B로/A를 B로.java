import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static String target, origin;

    public static void main(String[] args) throws IOException {
        init();
        solution();
    }

    private static void init() throws IOException {
        target = br.readLine();
        origin = br.readLine();
    }

    private static void solution() {
        if (isException()) {
            System.out.println(-1);
            return;
        }

        int answer = 0;

        int len = target.length() - 1;
        int originIdx = len;
        for (int targetIdx = len; targetIdx >= 0; targetIdx--) {
            if (origin.charAt(originIdx) == target.charAt(targetIdx)) {
                originIdx--;
                continue;
            }

            answer++;
        }

        System.out.println(answer);
    }

    private static boolean isException() {
        int[] alpha = new int[26];
        for (int i = 0; i < target.length(); i++) {
            alpha[target.charAt(i) - 'A']++;
            alpha[origin.charAt(i) - 'A']--;
        }

        for (int i = 0; i < 26; i++) {
            if (alpha[i] != 0) {
                return true;
            }
        }
        return false;
    }
}