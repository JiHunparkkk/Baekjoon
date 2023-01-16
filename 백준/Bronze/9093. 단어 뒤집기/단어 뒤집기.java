import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        int t; // 테스트 개수

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());

        while(t-->0){
            st = new StringTokenizer(br.readLine()," ");
            while(st.hasMoreTokens()){
                String str = new String(st.nextToken());

                for(int i=str.length()-1;i>=0;i--){
                    sb.append(str.charAt(i));
                }
                sb.append(" ");
            }
            sb.append("\n");
        }// while
        System.out.println(sb);
    }//main
}//end of 9093_class
