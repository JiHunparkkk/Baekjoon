import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static class SegmentTree {

        private long[] tree;
        private int size;

        public SegmentTree(int arrSize) {
            int h = (int) Math.ceil(Math.log(arrSize) / Math.log(2));
            this.size = (int) Math.pow(2, h + 1);
            tree = new long[size];
        }

        public long init(long[] arr, int node, int start, int end) {
            if (start == end) {
                return tree[node] = arr[start];
            }

            return tree[node] =
                    init(arr, node * 2, start, (start + end) / 2)
                            + init(arr, node * 2 + 1, (start + end) / 2 + 1, end);
        }

        public void update(int node, int start, int end, int index, long diff) {
            if (index < start || end < index)
                return;

            tree[node] += diff;

            if (start != end) {
                update(node * 2, start, (start + end) / 2, index, diff);
                update(node * 2 + 1, (start + end) / 2 + 1, end, index, diff);
            }
        }

        public long sum(int node, int start, int end, int left, int right) {
            if (start > right || end < left) {
                return 0;
            }

            if (left <= start && end <= right) {
                return tree[node];
            }

            return sum(node * 2, start, (start + end) / 2, left, right)
                    + sum(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
        }
    }

    private static int n, m, k;
    private static long[] arr;
    private static SegmentTree tree;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        makeTree(arr);

        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            solution(a, b, c);
        }

        System.out.println(sb.toString());
    }

    private static void makeTree(long[] arr) {
        tree = new SegmentTree(n);
        tree.init(arr, 1, 1, n);
    }

    private static void solution(int a, int b, long c) {
        if(a == 1) changeNum(b, c);
        else calculateSum(b, (int)c);
    }

    private static void changeNum(int targetIdx, long num) {
        tree.update(1, 1, n, targetIdx, num - arr[targetIdx]);
        arr[targetIdx] = num;
    }

    private static void calculateSum(int startIdx, int endIdx) {
        long result = tree.sum(1, 1, n, startIdx, endIdx);
        sb.append(result).append("\n");
    }
}