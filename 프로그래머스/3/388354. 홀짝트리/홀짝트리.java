import java.util.*;

class Solution {
    
    private static TreeSet<Integer> treeSet = new TreeSet<>();
    private static Map<Integer, Integer> map = new HashMap<>();
    private static Map<Integer, Integer> map2 = new HashMap<>();
    private static List<List<Integer>> list = new ArrayList<>();
    private static int originCnt, reverseCnt;
    private static int originFlagCount, reverseFlagCount;
    private static boolean[] visited;
    
    public int[] solution(int[] nodes, int[][] edges) {
        
        visited = new boolean[nodes.length + 1];
        
        init(nodes, edges);
        
        for(int i = 1; i <= nodes.length; i++) {
            if(visited[i]) continue;
            
            originFlagCount = reverseFlagCount = 0;
                
            check(i);
            
            if(originFlagCount == 1) {
                originCnt++;
            }
            
            if(reverseFlagCount == 1) {
                reverseCnt++;
            }
        }
        
        return new int[] {originCnt, reverseCnt};
    }
    
    private static void init(int[] nodes, int[][] edges) {
        for(int x : nodes) {
            treeSet.add(x);
        }
        
        int idx = 1;
        for(int x : treeSet) {
            map.put(x, idx);
            map2.put(idx, x);
            idx++;
        }
        
        for(int i = 0; i <= idx; i++) {
            list.add(new ArrayList<>());
        }
        
        for(int i = 0; i < edges.length; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            
            a = map.get(a);
            b = map.get(b);
            
            list.get(a).add(b);
            list.get(b).add(a);
        }
    }
    
    private static void check(int idx) {        
        
        visited[idx] = true;
        
        int root = map2.get(idx);
        int count = list.get(idx).size();
        
        if(count % 2 != root % 2) {
            reverseFlagCount++;
        } else {
            originFlagCount++;
        }
        
        for(int i = 0; i < list.get(idx).size(); i++) {
            int childIdx = list.get(idx).get(i);
            
            if(!visited[childIdx]) {
                check(childIdx);
            }
        }
    }
}