import java.util.*;

class Solution {
    
    private static int[][] consultants;
    
    public int solution(int k, int n, int[][] reqs) {
        int answer = 0;
        consultants = new int[k + 1][n - k + 2];
        
        for(int i = 1; i <= k; i++) {
            List<Integer> attends = new ArrayList<>();
            for(int j = 0; j < reqs.length; j++) {
                if(reqs[j][2] == i) {
                    attends.add(j);
                }
            }
            checkTypeCnt(n - k + 1, i, attends, reqs);
        }
    
        return calMin(k, n);
    }
    
    private static void checkTypeCnt(int maxCnt, int type, List<Integer> attends, int[][] reqs) {
        for(int i = 1; i <= maxCnt; i++) {  // 멘토 1명 ~ 최대
            int waitTime = 0;
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            
            for(Integer attend : attends) {
                if(pq.size() < i) {
                    pq.add(reqs[attend][0] + reqs[attend][1]);
                } else { 
                    int poll = pq.poll();
                    int time = poll - reqs[attend][0];
                    if(time >= 0) {
                        waitTime += time;
                        pq.add(poll + reqs[attend][1]);
                    } else {
                        pq.add(reqs[attend][0] + reqs[attend][1]);
                    }
                }
            }
            consultants[type][i] = waitTime;
        }
    }
    
    private static int calMin(int k, int n) {
        int remain = n - k;
        int[] mentorCnt = new int[k + 1];
        Arrays.fill(mentorCnt, 1);
        while(remain-- > 0) {
            int maxDiff = 0;
            int maxIndex = 0;
            for(int i = 1; i <= k; i++) {
                if(mentorCnt[i] == n - k + 1) continue;
                int diff = consultants[i][mentorCnt[i]] - consultants[i][mentorCnt[i] + 1];
                if(maxDiff < diff) {
                    maxDiff = diff;
                    maxIndex = i;
                }
            }
            mentorCnt[maxIndex]++;
        }
        
        int sum = 0;
        for(int i = 1; i <= k; i++) {
            sum += consultants[i][mentorCnt[i]];
        }
        return sum;
    }
   
}