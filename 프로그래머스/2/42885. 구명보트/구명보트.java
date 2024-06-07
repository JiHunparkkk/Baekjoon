import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        int len = people.length;
        
        Arrays.sort(people);
        
        int left = 0;
        int right = len - 1;
        while(left <= right) {
            if(people[right] + people[left] <= limit) {
                left++;
            }
            answer++;
            right--;
        }
        
        return answer;
    }
}