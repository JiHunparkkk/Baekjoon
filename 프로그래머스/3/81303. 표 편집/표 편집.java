import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Solution {
     public String solution(int n, int k, String[] cmd) {
        String answer = "";

        Stack<Integer> stack = new Stack<>();
        int size = n;

        for (String command : cmd) {
            switch (command.charAt(0)) {
                case 'U':
                    k -= Integer.parseInt(command.substring(2));
                    break;
                case 'D':
                    k += Integer.parseInt(command.substring(2));
                    break;
                case 'C':
                    stack.push(k);
                    size -= 1;

                    if (k == size) {
                        k -= 1;
                    }
                    break;
                case 'Z':
                    Integer pop = stack.pop();
                    if (pop <= k) {
                        k += 1;
                    }
                    size += 1;
                    break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append("O");
        }

        while (!stack.isEmpty()) {
            sb.insert(stack.pop(), "X");
        }
        answer = sb.toString();

        return answer;
    }
}