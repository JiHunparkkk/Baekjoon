import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static Map<String, Integer> map;
    private static Map<Integer, Integer> cMap;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testCase = Integer.parseInt(br.readLine());
        for(int t = 0; t < testCase; t++) {
            int f = Integer.parseInt(br.readLine());

            int idx = 0;
            map = new HashMap<>();
            cMap = new HashMap<>();
            arr = new int[f * 2 + 1];

            for(int i = 0; i < arr.length; i++) {
                arr[i] = i;
            }

            for(int i = 0; i < f; i++) {
                String[] names = br.readLine().split(" ");
                String name1 = names[0];
                String name2 = names[1];

                if (map.get(name1) == null) {
                    map.put(name1, idx++);
                }
                if (map.get(name2) == null) {
                    map.put(name2, idx++);
                }

                union(name1, name2);

                int targetIdx = map.get(name1);
                sb.append(cMap.get(find(targetIdx))).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static void union(String name1, String name2) {
        int na = map.get(name1);
        int nb = map.get(name2);

        int pa = find(na);
        int pb = find(nb);

        if(pa != pb) {
            arr[pb] = pa;
            cMap.put(pa, cMap.getOrDefault(pa, 1) + cMap.getOrDefault(pb, 1));
        }
    }

    private static int find(int x) {
        if(x == arr[x]) return x;
        return arr[x] = find(arr[x]);
    }
}