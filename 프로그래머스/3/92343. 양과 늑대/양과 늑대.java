import java.util.*;

class Solution {
    
    //지나가지 못한 곳도 다시 지나가야함.
    //갈 수 있는 곳이 여러곳이라면 모두 탐색해봐야 함
    private static final int SHEEP = 0;
    private static final int WOLF = 1;
    
    private static List<List<Integer>> list = new ArrayList<>();
    private static int[] infos;
    private static int answer;
    
    public int solution(int[] info, int[][] edges) {
        infos = info;
        
        for(int i = 0; i <= info.length; i++) {
            list.add(new ArrayList<>());
        }
        
        for(int i = 0; i < edges.length; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            list.get(a).add(b);
            // list.get(b).add(a);
        }
        
        
        move(new ArrayList<>(List.of(0)), 0, 0, 0);
        
        return answer;
    }

    private static void move(List<Integer> nextNodes, int now, int sheepCnt, int wolfCnt) {
        if(infos[now] == SHEEP) {
            sheepCnt++;
        } else {
            wolfCnt++;
        }
        
        if(sheepCnt <= wolfCnt) return;
        
        nextNodes.remove((Integer)now);
        answer = Math.max(answer, sheepCnt);
        
        for(int i = 0; i < list.get(now).size(); i++) {
            nextNodes.add(list.get(now).get(i));
        }
        
        for(int n : nextNodes) {
            move(new ArrayList<>(nextNodes), n, sheepCnt, wolfCnt);
        }
    }
}