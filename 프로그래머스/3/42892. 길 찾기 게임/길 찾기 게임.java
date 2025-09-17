import java.util.*;

class Solution {
    
    private static class Node implements Comparable<Node> {
        int x, y, num;
        Node prev, next;
        
        public Node(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
        
        @Override
        public int compareTo(Node o) {
            if(o.y == this.y) {
                return this.x - o.x;
            }
            return o.y - this.y;
        }
    }
    
    private static List<Node> list = new ArrayList<>();
    private static boolean[] visited = new boolean[10001];
    private static List<Integer> prevResult = new ArrayList<>();
    private static List<Integer> nextResult = new ArrayList<>();
    
    public int[][] solution(int[][] nodeinfo) {
        int[][] answer = new int[2][nodeinfo.length];
        
        initNode(nodeinfo);
        for(int i = 1; i < list.size(); i++) {
            makeTree(list.get(0), list.get(i));
        }
        prevShow(list.get(0));
        afterShow(list.get(0));

        for(int i = 0; i < prevResult.size(); i++) {
            answer[0][i] = prevResult.get(i);
            answer[1][i] = nextResult.get(i);
        }
        
        return answer;
    }
    
    private static void initNode(int[][] nodeinfo) {        
        for(int i = 0; i < nodeinfo.length; i++) {
            int x = nodeinfo[i][0];
            int y = nodeinfo[i][1];
            
            list.add(new Node(x, y, i + 1));
        }
        Collections.sort(list);
    }
    
    private static void makeTree(Node parent, Node child) {
        if(parent.x > child.x) {
            if(parent.prev == null) parent.prev = child;
            else makeTree(parent.prev, child);
        } else {
            if(parent.next == null) parent.next = child;
            else makeTree(parent.next, child);
        }
    }
    
    private static void prevShow(Node now) {
        prevResult.add(now.num);
        if(now.prev != null) prevShow(now.prev);
        if(now.next != null) prevShow(now.next);
    }
    
    private static void afterShow(Node now) {
        if(now.prev != null) afterShow(now.prev);
        if(now.next != null) afterShow(now.next);
        nextResult.add(now.num);
    }
}