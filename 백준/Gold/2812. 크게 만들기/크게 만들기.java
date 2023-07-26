import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        Stack<Integer> stack = new Stack<>();

        int n, k,cnt=0;
        boolean flag = false;

        n = sc.nextInt();
        k = sc.nextInt();
        String str = sc.next();

        stack.push(str.charAt(0) - '0');
        int i=1;
        int j=0;
        for (i=1; i < n; i++) {
            int cur = str.charAt(i)-'0';

            while(!stack.isEmpty()&&stack.peek()<cur){
                stack.pop();
                cnt++;
                if(cnt==k) {
                    flag=true;
                    break;
                }
            }
            stack.push(cur);

            if(flag){
                j=i+1;
                while(stack.size()<n-k) {
                    stack.push(str.charAt(j++)-'0');
                }
                break;
            }
        }


        for (int z = 0; z < n - k; z++) {
            sb.append(stack.get(z));
        }
        System.out.println(sb);
    }
}