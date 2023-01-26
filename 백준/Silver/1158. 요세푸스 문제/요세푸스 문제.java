import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// Queue를 돌면서 해당 위치의 숫자를 poll
public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        Queue<Integer> queue = new LinkedList();

        int n,k;

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        // n개 만큼 queue에 값 add
        for(int i=1;i<=n;i++){
            queue.add(i);
        }

        sb.append("<");
        // queue가 비어있을 때까지 poll
        while(n-->0){
            int m=k;
            while(--m>0){
                queue.add(queue.poll());
            }// while
            sb.append(queue.poll());
            if(n!=0)
                sb.append(", ");
        }// while
        sb.append(">");
        System.out.println(sb);
    }// main
}
