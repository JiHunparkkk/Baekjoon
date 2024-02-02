import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws IOException {
		  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	        int n = Integer.parseInt(br.readLine());

	        Queue<Integer> queue = new LinkedList<>();

	        for (int i = 1; i <= n; i++) {
	            queue.offer(i);	//1부터 N까지 번호 초기화
	        }
	       
	        while (queue.size() != 1) {	//카드가 하나 남을 때 까지 반복
	            queue.poll();	//최상단 카드 버림
	            Integer poll = queue.poll();	//그 다음 카드를 빼서
	            queue.offer(poll);				//제일 아래 카드 밑으로 옮김
	        }
	        
	        System.out.println(queue.poll());	//마지막 카드를 출력
	}
}
