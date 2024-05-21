import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    private static int n, m;
    private static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        arr = Arrays.stream(arr).distinct().toArray();
        Arrays.sort(arr);
        solution(1, new int[m + 1]);
    }

    private static void solution(int depth, int[] nums) {
        if (depth == m + 1) {
            for (int i = 1; i <= m; i++) {
                System.out.print(nums[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if(nums[depth - 1] > arr[i]) continue;
            nums[depth] = arr[i];
            solution(depth + 1, nums);
        }
    }
}