import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        Integer[] arr = new Integer[8];
        int i = 0;
        while (st.hasMoreTokens()) {
            arr[i++] = Integer.parseInt(st.nextToken());
        }
        Integer[] copy = arr.clone();
        Arrays.sort(copy);

        if (Arrays.equals(arr, copy)) {
            bw.write("ascending");
        } else {
            Arrays.sort(copy, (i1, i2) -> i2 - i1);
            if (Arrays.equals(arr, copy)) {
                bw.write("descending");
            } else {
                bw.write("mixed");
            }
        }
        br.close();
        bw.close();
    }
}