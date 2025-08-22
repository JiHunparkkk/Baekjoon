import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        //1번 노드에서 각 노드까지의 최단 거리 구하기
        List<List<Integer>> list = new ArrayList<>();
        int[] dist = new int[n + 1];
        
        Arrays.fill(dist, 50001);
        
        for(int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        
        for(int i = 0; i < edge.length; i++) {
            int a = edge[i][0];
            int b = edge[i][1];
            
            list.get(a).add(b);
            list.get(b).add(a);
        }
        
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        dist[1] = 0;
        
        while(!queue.isEmpty()) {
            int poll = queue.poll();
            
            for(int i = 0; i < list.get(poll).size(); i++) {
                int next = list.get(poll).get(i);
                
                if(dist[next] > dist[poll] + 1) {
                    dist[next] = dist[poll] + 1;
                    queue.add(next);
                }
            }
        }
        
        int max = 0;
        for(int i = 1; i <= n; i++) {
            max = Math.max(max, dist[i]);
        }
        
        for(int i = 1; i <= n; i++) {
            if(max == dist[i]) answer++;
        }
        
        return answer;
    }
}