import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        List<List<Integer>> list = new ArrayList<>();

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            list.add(new ArrayList<>());
            list.get(i).add(arr[i]);
        }

        int maxCnt = 1, maxIndex = 0;
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] > arr[j]) {
                    if (list.get(i).size() <= list.get(j).size()) {
                        list.get(i).clear();
                        list.get(i).addAll(list.get(j));
                        list.get(i).add(arr[i]);
                    }
                }
            }
            if (maxCnt < list.get(i).size()) {
                maxCnt = list.get(i).size();
                maxIndex = i;
            }
        }

        sb.append(maxCnt).append("\n");
        for (int i = 0; i < maxCnt; i++) {
            sb.append(list.get(maxIndex).get(i)).append(" ");
        }
        System.out.println(sb);

        br.close();
    }
}