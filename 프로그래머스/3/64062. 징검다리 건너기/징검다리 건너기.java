class Solution {
    public int solution(int[] stones, int k) {
        int left = 0, right = 200000000;
        while(left <= right) {
            int mid = (left + right) / 2;
            
            if(isPossible(k, mid, stones)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    
        return left;
    }
    
    private static boolean isPossible(int k, int mid, int[] stones) {
        int cnt = 0;
        for(int i = 0; i < stones.length; i++) {
            if(stones[i] - mid <= 0) {
                cnt++;
            } else {
                cnt = 0;
            }
            
            if(cnt >= k) {
                return false;
            }
        }
        
        return true;
    }
}