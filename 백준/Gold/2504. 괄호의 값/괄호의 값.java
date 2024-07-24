import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        System.out.println(solution(str));
    }

    private static int solution(String str) {
        Stack<Character> stack = new Stack<>();
        int sum = 1, result =0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '(') {
                stack.push(ch);
                sum *= 2;
            }
            if (ch == '[') {
                stack.push(ch);
                sum *= 3;
            }

            if (ch == ')') {
                if (stack.isEmpty() || stack.peek() != '(') {
                    return 0;
                } else if (str.charAt(i - 1) == '(') {
                    result += sum;
                }
                stack.pop();
                sum /= 2;
            }
            if (ch == ']') {
                if (stack.isEmpty() || stack.peek() != '[') {
                    return 0;
                } else if(str.charAt(i - 1) == '[') {
                    result += sum;
                }
                stack.pop();
                sum /= 3;
            }
        }
        
        if (!stack.isEmpty()) {
            return 0;
        }

        return result;
    }
}