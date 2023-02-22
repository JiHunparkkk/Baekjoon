import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        Stack<Integer> stack = new Stack();
        StringBuffer sb = new StringBuffer();

        int n = Integer.parseInt(br.readLine());
        int[] num = new int[n];
        int[] cnt = new int[1000001];

        String str = br.readLine();
        st = new StringTokenizer(str, " ");

        for (int i = 0; st.hasMoreTokens(); i++) {
            num[i] = Integer.parseInt(st.nextToken());
            cnt[num[i]]++;
        }

        for (int i = 0; i < n; i++) {

            while(!stack.isEmpty() && cnt[num[stack.peek()]] < cnt[num[i]]){
                num[stack.pop()] = num[i];
            }

            stack.push(i);
        }

        while(!stack.isEmpty()){
            num[stack.pop()] = -1;
        }

        for (int i : num) {
            sb.append(i).append(" ");
        }

        System.out.println(sb);
        br.close();
    }
}
