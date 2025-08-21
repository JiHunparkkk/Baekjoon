class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int[] curServerCnt = new int[players.length];
        for(int i = 0; i < players.length; i++) {
            if(players[i] >= (curServerCnt[i] + 1) * m) {
                int need = players[i] / m - curServerCnt[i];
                answer += need;
                // System.out.println(i + ", " + need +","+curServerCnt[i]);
                for(int j = 0; j < k && i + j < players.length; j++) {
                    curServerCnt[i + j] += need;
                }
            }
        }
        
        return answer;
    }
}