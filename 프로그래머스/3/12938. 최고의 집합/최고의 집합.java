import java.util.*;

class Solution {
    public int[] solution(int n, int s) {
        
        //S는 최대 1억, n은 최소 1
        //최고가 되려면 중간 원소들로만 이루어져야함.
        //ex) S=9, n=3
        //{1,1,7}, {1, 2, 6}, ... {3,3,3}, {3,4,2}, {5,4,1}, {6,2,1}
        //가장 중간에 있는 집합이 최고의 집합이 됨.
        
        //중간은 S / n 값을 기준으로 찾으면 됨.
        //ex) S=100, n=3
        //{33, 33, 34} -> S/n만큼 채우고, 마지막은 남은 값 채우기
        //{34, 34, 32}
        
        //예외처리
        if(n > s) {
            return new int[] {-1};
        }
        
        int[] answer = new int[n];
        int target = s / n;
        int remain = s % n;
        
        for(int i = 0; i < n; i++) {
            if(remain > 0) {
                answer[i] = target + 1;
                remain--;
            } else {
                answer[i] = target;
            }
        }
        
        Arrays.sort(answer);
        
        return answer;
    }
}