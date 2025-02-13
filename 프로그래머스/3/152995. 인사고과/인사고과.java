import java.util.*;

class Solution {
    
    private static class Score {
        int first, second, sum;
        boolean wonHo;
        
        public Score(int first, int second) {
            this.first = first;
            this.second = second;
            wonHo = false;
            this.sum = first + second;
        }
        
        public void makeWonHo() {
            this.wonHo = true;
        }
    }
    
    private static int length;
    private static List<Score> list = new ArrayList<>();
    
    public int solution(int[][] scores) {
        int answer = 0;
        length = scores.length;
        for(int i = 0; i < length; i++) {
            Score score = new Score(scores[i][0], scores[i][1]);
            list.add(score);
            
            if(i == 0) {
                score.makeWonHo();    
            }
        }
        
        Collections.sort(list, (o1, o2) -> {
            if(o1.first == o2.first) return Integer.compare(o1.second, o2.second);
            return Integer.compare(o2.first, o1.first);
        });
        checkNoInsentive();
        Collections.sort(list, (o1, o2) -> o2.sum - o1.sum);
        answer = findWonHo();
        
        return answer;
    }
    
    private static void checkNoInsentive() {
        int maxSecond = -1;
        
        for(int i = 0; i < list.size(); i++) {
            Score now = list.get(i);
            
            if(maxSecond > now.second) {
                list.remove(i);
                i--;
            } else {
                maxSecond = now.second;
            }
        }
    }
    
    private static int findWonHo() {
        int rank = 0;
        int dupCnt = 1;
        int prevScore = -1;
        
        for(int i = 0; i < list.size(); i++) {
            if(prevScore == list.get(i).sum) {
                dupCnt++;
            } else {
                rank += dupCnt;
                dupCnt = 1;
                prevScore = list.get(i).sum;
            }
            
            if(list.get(i).wonHo) {
                return rank;
            }
        }
          
        return -1;
    }
}