import java.util.Scanner;
import java.util.Stack;

public class Solution{
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		for(int test_case = 1; test_case <= 10; test_case++)
		{
			int n = sc.nextInt();
			String input = sc.next();
			int answer = 1;
			Stack<Character> stack = new Stack<>();
			
			for(int i=0;i<input.length();i++) {
				char op = input.charAt(i);
				
				if(isOpen(op)) {
					stack.push(op);
				}else {
					if(!Matching(stack.pop(), op)) {
						answer = 0;
						break;
					}
				}
			}
			
			if(!stack.isEmpty()) {
				answer=0;
			}
			
			System.out.println("#"+test_case+" "+answer);
		}
	}
	
	private static boolean isOpen(char op) {
		return op == '(' || op == '['  || op == '{' || op == '<';
	}
	
	private static boolean Matching(char open, char close) {
		switch(open) {
		case '(':
			return close==')';
		case '[':
			return close==']';
		case '{':
			return close=='}';
		case '<':
			return close=='>';
		}
		return true;
	}
}
