import java.util.*;

class Solution {
    
    // 1. 1~n까지 5개를 골라 모든 숫자 만들기
    // 2. q배열을 돌면서 일치하는 갯수 판단하기
    
    private static List<int[]> numbers = new ArrayList<>();
    
    public int solution(int n, int[][] q, int[] ans) {
        int answer = 0;
        
        makeNumbers(n, 0, new int[5], new boolean[n + 1], 1);
        answer = findPossibleArray(q, ans);
        
        return answer;
    }
    
    private static void makeNumbers(int n, int depth, int[] arr, boolean[] visited, int start) {
        if(depth == 5) {
            int[] newArr = new int[5];
            for(int i = 0; i < 5; i++) {
                newArr[i] = arr[i];
            }
            numbers.add(newArr);
            return;
        }
        
        for(int i = start; i <= n; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            arr[depth] = i;
            makeNumbers(n, depth + 1, arr, visited, i + 1);
            visited[i] = false;
        }
    }
    
    private static int findPossibleArray(int[][] q, int[] ans) {
        int result = 0;
        for(int[] number : numbers) {
            boolean isPossible = true;
            for(int i = 0; i < q.length; i++) {
                int cnt = 0;
                for(int j = 0, k = 0; j < 5 && k < 5;) {
                    if(number[j] < q[i][k]) {
                        j++;
                    } else if(number[j] > q[i][k]) {
                        k++;
                    } else {
                        cnt++;
                        j++;
                        k++;
                    }
                }

                if(ans[i] != cnt) {
                    isPossible = false;
                    break;
                }
            }
            
            if(isPossible) {
                result++;
            }
        }
        
        return result;
    }
    
}