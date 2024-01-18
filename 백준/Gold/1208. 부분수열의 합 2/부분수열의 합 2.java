import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static int n, s;
    static int[] arr;
    static ArrayList<Integer> leftList = new ArrayList<>();
    static ArrayList<Integer> rightList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        arr = new int[n];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        leftList = new ArrayList<>();
        rightList = new ArrayList<>();

        splitBinarySum(0, n / 2, 0, leftList);
        splitBinarySum(n / 2, n, 0, rightList);
        Collections.sort(leftList);
        Collections.sort(rightList);

        System.out.println(findSum());

        br.close();
    }

    private static void splitBinarySum(int start, int end, int sum, ArrayList<Integer> list) {
        if (start >= end) {
            list.add(sum);
            return;
        }

        splitBinarySum(start + 1, end, sum, list);
        splitBinarySum(start + 1, end, sum + arr[start], list);
    }

    private static long findSum() {
        int lp = 0;
        int rp = rightList.size() - 1;
        long result = 0L;

        while (lp < leftList.size() && rp >= 0) {

            int sum = leftList.get(lp) + rightList.get(rp);

            if (sum == s) {
                int now = leftList.get(lp);
                long cnt1 = 0L;
                while (lp < leftList.size() && now == leftList.get(lp)) {
                    lp++;
                    cnt1++;
                }

                now = rightList.get(rp);
                long cnt2 = 0L;
                while (rp >= 0 && now == rightList.get(rp)) {
                    rp--;
                    cnt2++;
                }

                result += cnt1 * cnt2;
            } else if (sum < s) {
                lp++;
            } else {
                rp--;
            }
        }
        if (s == 0) {
            result--;
        }

        return result;
    }
}