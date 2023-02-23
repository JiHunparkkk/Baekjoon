import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Double> stack = new Stack();

        int n = Integer.parseInt(br.readLine());
        String str = br.readLine();
        double[] num = new double[n];


        for (int i = 0; i < n; i++) {
            num[i] = Double.parseDouble(br.readLine());
        }

        for (int i = 0; i < str.length(); i++) {
            if ('A' <= str.charAt(i) && str.charAt(i) <= 'Z') {
                stack.push(num[str.charAt(i) - 'A']);
            }else {
                double a = stack.pop();
                double b = stack.pop();
                double c = 0;
                switch (str.charAt(i)) {
                    case '+':
                        c = b + a;
                        break;
                    case '-':
                        c = b - a;
                        break;
                    case '*':
                        c = b * a;
                        break;
                    case '/':
                        c = b / a;
                        break;
                }// switch
                stack.push(c);
            }//if
        }// for

        System.out.printf("%.2f\n", stack.pop());
    }
}
