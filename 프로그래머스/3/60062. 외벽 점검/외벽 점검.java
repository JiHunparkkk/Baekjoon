import java.util.*;

class Solution {
    
    private static int N, answer;
    private static int[] weak;
    private static int[] dist;
    private static Set<Integer> weakSet = new HashSet<>();

    public int solution(int n, int[] w, int[] d) {
        N = n;
        weak = w;
        dist = d;

        for(int x : weak) {
            weakSet.add(x);
        }
        
        answer = Integer.MAX_VALUE;
        for(int i = 1; i <= d.length; i++) {
            if(answer != Integer.MAX_VALUE) break;
            selectFriends(0, i, new int[i], new boolean[dist.length]);
        }
        
        if(answer == Integer.MAX_VALUE) {
            answer = -1;
        }
        
        return answer;
    }
    
    private static void selectFriends(int depth, int limit, int[] arr, boolean[] visited) {
        // if(answer != Integer.MAX_VALUE) return;
        
        if(depth == limit) {
            answer = Math.min(answer, validCheck(limit, arr));
            return;
        }

        for(int i = dist.length - 1; i >= 0; i--) {
            if(visited[i]) continue;
            arr[depth] = i;
            visited[i] = true;
            selectFriends(depth + 1, limit, arr, visited);
            visited[i] = false;
        }
    }

    private static int validCheck(int limit, int[] index) { 
        for(int i = 0; i < N; i++) {
            boolean[] visited = new boolean[N];
            int nowFriendIdx = 0;
            int cnt = 0;
            
            if(!weakSet.contains(i)) continue;

            for(int startIdx = i; startIdx < N; startIdx++) {
                if(weakSet.contains(startIdx)) {
                    if(nowFriendIdx >= limit) break;
                    
                    for(int j = 0; j <= dist[index[nowFriendIdx]]; j++) {
                        if(!visited[startIdx] && weakSet.contains(startIdx)) {
                            cnt++;
                        }
                        visited[startIdx] = true;
                        startIdx = (startIdx + 1) % N;
                    }
                    nowFriendIdx++;
                    startIdx--;
                    
                    if(startIdx + 1 == N) {
                        startIdx = -1;
                    }
                }
            }
            
            if(cnt == weakSet.size()) {
                return limit;
            }
        }

        return Integer.MAX_VALUE;
    }
}