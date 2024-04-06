import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
 
public class Main {
 
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, ans = Integer.MIN_VALUE;
    private static String[] words;
    private static List<Character> alpabetList = new ArrayList<>();
    private static int[] val;
    private static boolean[] visited = new boolean[10];
 
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
 
        words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
            for (int j = 0; j < words[i].length(); j++) {
                if (!alpabetList.contains(words[i].charAt(j))) {
                    alpabetList.add(words[i].charAt(j));
                }
            }
        }
 
        val = new int[alpabetList.size()];
 
        dfs(0);
        System.out.println(ans);
        br.close();
    }
 
    private static void dfs(int depth) {
        if (depth == alpabetList.size()) {
            int sum = 0;
            for (int i = 0; i < N; i++) {
                int num = 0;
                for (int j = 0; j < words[i].length(); j++) {
                    num *= 10;
                    num += val[ alpabetList.indexOf(words[i].charAt(j)) ];
                }
                sum += num;
            }
            ans = Math.max(ans, sum);
            return;
        }
 
        for (int i = 0; i <= 9; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            val[depth] = i;
            dfs(depth + 1);
            val[depth] = 0;
            visited[i] = false;
        }
    }
}