import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        Stack<Integer> stack = new Stack();
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        String str = br.readLine();
        int[] x = new int[n];

        int i=0;
        st = new StringTokenizer(str," ");
        while (st.hasMoreTokens()) {
            x[i++] = Integer.parseInt(st.nextToken());
        }

        for (i = 0; i <n; i++) {

            while(!stack.empty() && x[stack.peek()] < x[i]){
                x[stack.pop()] = x[i];
            }
            stack.push(i);
        }

        while(!stack.empty()){
            x[stack.pop()] = -1;
        }

        for (i = 0; i < n; i++) {
            sb.append(x[i]).append(" ");
        }
        System.out.println(sb);

        br.close();
    }//main
}
