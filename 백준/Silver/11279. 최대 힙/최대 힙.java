import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int val = Integer.parseInt(br.readLine());

            if (val == 0) {
                int result = deque(arr);
                System.out.println(result);
            } else {
                enqueue(arr, val);
            }
        }
    }

    private static void enqueue(int[] arr, int val) {
        int i = 0;

        size++;
        i = size;
        arr[i] = val;

        while (i > 1 && arr[i / 2] < arr[i]) {
            swap(arr, i / 2, i);
            i /= 2;
        }
    }

    private static int deque(int[] arr) {
        if (size == 0) {
            return 0;
        }

        int poll = arr[1];
        arr[1] = arr[size];
        size--;

        heapify(arr, 1);

        return poll;
    }

    private static void heapify(int[] arr, int i) {
        int parent = i;
        int left = 2 * i;
        int right = 2 * i + 1;

        if (left <= size && arr[left] > arr[parent]) {
            parent = left;
        }
        if (right <= size && arr[right] > arr[parent]) {
            parent = right;
        }

        if (parent != i) {
            swap(arr, i, parent);
            heapify(arr, parent);
        }
    }

    private static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}