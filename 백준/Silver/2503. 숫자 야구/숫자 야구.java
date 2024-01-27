import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (i == 0) {
                for (int j = 123; j <= 987; j++) {
                    if (findNum(String.valueOf(num), String.valueOf(j), s, b)) {
                        list.add(j);
                    }
                }
            } else {
                int size = list.size();
                for (int j = 0; j < size; j++) {
                    Integer remove = list.remove(0);
                    if (findNum(String.valueOf(num), String.valueOf(remove), s, b)) {
                        list.add(remove);
                    }
                }
            }
        }
        System.out.println(list.size());
    }

    private static boolean findNum(String num, String target, int s, int b) {
        int strike = 0;
        int ball = 0;

        if (!valNum(target)) {
            return false;
        }

        for (int i = 0; i < 3; i++) {
            if (num.charAt(i) == target.charAt(i)) {
                strike++;
            } else {
                for (int j = 0; j < 3; j++) {
                    if (num.charAt(i) == target.charAt(j)) {
                        ball++;
                    }
                }
            }
        }

        return strike == s && ball == b;
    }

    private static boolean valNum(String target) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            set.add(target.charAt(i));
            if (target.charAt(i) == '0') {
                return false;
            }
        }
        return set.size() == 3;
    }
}