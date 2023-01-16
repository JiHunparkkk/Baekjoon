import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        while(t-- >0){
            Stack stack = new Stack();
            String str = br.readLine();
            boolean flag=false;

            for (char ch : str.toCharArray()) {
                if(ch==')'){
                    if(!stack.isEmpty()){
                        stack.pop();
                    }else{
                        sb.append("NO\n");
                        flag=true;
                        break;
                    }
                }else{
                    stack.push(ch);
                }
            }
            if(!flag) {
                if(stack.isEmpty())
                    sb.append("YES\n");
                else
                    sb.append("NO\n");
            }
        }// end of while
        System.out.println(sb);
    }// end of main
}
