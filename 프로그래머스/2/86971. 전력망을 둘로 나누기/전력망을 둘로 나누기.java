import java.util.*;

class Solution {
    
    private static List<List<Integer>> list;
    
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        
        for(int i = 0; i < n - 1; i++) {
            init(n, wires, i);
            int result = separate(n);
            answer = Math.min(answer, result);
        }
        
        return answer;
    }
    
    private static void init(int n, int[][] wires, int cut) {
        list = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        
        for(int i = 0; i < n - 1; i++) {
            if(i == cut) {
                continue;
            }
            int a = wires[i][0];
            int b = wires[i][1];
            
            list.get(a).add(b);
            list.get(b).add(a);
        }
    }
    
    private static int separate(int n) {
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        visited[1] = true;
        
        int cnt = 1;
        while(!queue.isEmpty()) {
            int poll = queue.poll();
            List<Integer> now = list.get(poll);
            
            for(int i = 0; i < now.size(); i++) {
                int next = now.get(i);
                if(!visited[next]) {
                    visited[next] = true;
                    cnt++;
                    queue.add(next);
                }
            }
        }
        return Math.abs(n - cnt - cnt);
    }
}