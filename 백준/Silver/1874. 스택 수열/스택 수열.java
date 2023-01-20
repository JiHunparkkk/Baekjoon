import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {

        int n,start=1;

        Stack stack = new Stack();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());

        while(n-->0){
            int num = Integer.parseInt(br.readLine());
            boolean flag=false;

            for(int i=start;i<=num;i++){
                stack.push(i);
                sb.append("+").append("\n");
                flag=true;
            }
            if(flag)
                start = num+1;

            if((int)stack.peek() == num){
                stack.pop();
                sb.append("-").append("\n");
            }

        }// while
        if(stack.isEmpty())
            System.out.println(sb);
        else
            System.out.println("NO");
    }// main
}
