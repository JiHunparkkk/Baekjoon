import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    private static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            String[] arr = new String[n];

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[j] = st.nextToken();
            }

            answer = Integer.MAX_VALUE;
            if (n >= 33) {
                answer = 0;
            } else {
                solution(arr, 0, 0, new String[3]);

            }
            System.out.println(answer);
        }
    }

    private static void solution(String[] arr, int depth, int start, String[] box) {
        if (depth >= 3) {
            answer = Math.min(answer, calculate(box));
            return;
        }

        for (int i = start; i < arr.length; i++) {
            box[depth] = arr[i];
            solution(arr, depth + 1, i + 1, box);
        }
    }

    private static int calculate(String[] box) {
        int cnt = 0;
        for (int i = 0; i < 3; i++) {
            String str1 = box[i];
            String str2;

            if (i == 2) {
                str2 = box[0];
            } else {
                str2 = box[i + 1];
            }

            for (int j = 0; j < 4; j++) {
                if (str1.charAt(j) != str2.charAt(j)) {
                    cnt++;
                }
            }
        }

        return cnt;
    }
}