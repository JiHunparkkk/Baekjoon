class Solution {
    
    //각 칸의 현 상태를 유지하긴 해야 함
    //but, 매번 돌면서 유지하긴 시간이 오래걸림
    //각 범위의 +, - 를 한 번에 모아서 전체 계산
    //누적합
    
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int rowLength = board.length;
        int colLength = board[0].length;
        int[][] sumRecord = new int[rowLength + 1][colLength + 1];
        
        for(int i = 0; i < skill.length; i++) {
            int type = skill[i][0];
            int r1 = skill[i][1];
            int c1 = skill[i][2];
            int r2 = skill[i][3];
            int c2 = skill[i][4];
            int degree = skill[i][5];
            
            if(type == 1) {
                sumRecord[r1][c1] -= degree;
                sumRecord[r1][c2 + 1] += degree;
                sumRecord[r2 + 1][c1] += degree;
                sumRecord[r2 + 1][c2 + 1] -= degree;
            }
            
            if(type == 2) {
                sumRecord[r1][c1] += degree;
                sumRecord[r1][c2 + 1] -= degree;
                sumRecord[r2 + 1][c1] -= degree;
                sumRecord[r2 + 1][c2 + 1] += degree;
            }
        }
        
        for(int i = 0; i < rowLength + 1; i++) {
            for(int j = 0; j < colLength + 1; j++) {
                if(j == 0) continue;
                sumRecord[i][j] += sumRecord[i][j - 1];
            }
        }
        
        for(int i = 0; i < rowLength + 1; i++) {
            for(int j = 0; j < colLength + 1; j++) {
                if(i == 0) continue;
                sumRecord[i][j] += sumRecord[i - 1][j];
            }
        }
        
        for(int i = 0; i < rowLength; i++) {
            for(int j = 0; j < colLength; j++) {
                board[i][j] += sumRecord[i][j];
                if(board[i][j] >= 1) answer++;
            }
        }
        
        
        return answer;
    }
}