import java.io.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Stack<Character> stack = new Stack<>();

        String str = br.readLine();

        int result=0;
        for (int i = 0; i < str.length(); i++) {

            if(str.charAt(i)=='('){
                stack.push('(');
                continue;
            }
            if (str.charAt(i) == ')') {
                stack.pop();

                if (str.charAt(i - 1) == '(') {
                    result += stack.size();
                } else {
                    result++;
                }
            }
        }//for

        bw.write(result + "\n");
        br.close();
        bw.flush();
        bw.close();
    }//main
}
