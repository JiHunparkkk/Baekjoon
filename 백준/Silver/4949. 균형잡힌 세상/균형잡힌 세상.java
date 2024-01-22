import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input;
        while (!(input = br.readLine()).equals(".")) {
            String answer = "yes";
            Stack<Character> stack = new Stack<>();

            for (char ch : input.toCharArray()) {
                if (ch == '(' || ch == '[') {
                    stack.push(ch);
                }
                if (ch == ')' && (stack.isEmpty() || stack.pop() != '(')) {
                    answer = "no";
                    break;
                }
                if (ch == ']' && (stack.isEmpty() || stack.pop() != '[')) {
                    answer = "no";
                    break;
                }
            }
            if (!stack.isEmpty()) {
                answer = "no";
            }

            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }
}