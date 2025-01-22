import java.util.*;

class Solution {
    
    private static class Dice implements Comparable<Dice> {
        int[] dices;
        int winCount;
        
        public Dice(int[] dices, int winCount) {
            this.dices = dices;
            this.winCount = winCount;
        }
        
        @Override
        public int compareTo(Dice o) {
            return Integer.compare(o.winCount, winCount);
        }
    }
    
    private static class Index { 
        int[] index;
        
        public Index(int[] index) {
            this.index = index;
        }
    }
    
    private static int diceCount;
    private static Map<Integer, Integer> sumListA;
    private static Map<Integer, Integer> sumListB;
    private static PriorityQueue<Dice> pq;
    private static List<Index> possibleChoiceSum;

    public int[] solution(int[][] dice) {
        //N = 10 일 때
        //10개 중 5개를 고르고
        //각 5개에서 나올 수 있는 숫자들의 합들을 구하고
        
        //나머지 5개를 고르고
        //각 5개에서 나올 수 있는 숫자들의 합들을 구하고
        
        //나온 합 들을 O(n^2)으로 비교하여 승/무/패 결정 반복
        diceCount = dice.length;
        sumListA = new HashMap<>();
        sumListB = new HashMap<>();
        pq = new PriorityQueue<>();
        possibleChoiceSum = new ArrayList<>();
        
        choicePossibleSumIndex(0, diceCount / 2, new int[diceCount / 2]);
        choiceDice(dice, 0, new int[diceCount / 2], 0);
        
        return pq.poll().dices;
    }
    
    private static void choicePossibleSumIndex(int depth, int limit, int[] index) {
        if(depth == limit) {
            int[] newIdx = new int[limit];
            for(int i = 0; i < limit; i++) {
               newIdx[i] = index[i]; 
            }
            possibleChoiceSum.add(new Index(newIdx));
            return;
        }
        
        for(int i = 0; i < 6; i++) {
            index[depth] = i;
            choicePossibleSumIndex(depth + 1, limit, index);
        }
    }
    
    private static void choiceDice(int[][] dice, int nowCount, int[] nowDice, int start) {
        if(nowCount == diceCount / 2) {
            
            int[] nowDiceB = new int[diceCount / 2];
            for(int i = 0, k = 0; i < diceCount; i++) {
                boolean flag = true;
                
                for(int j = 0; j < nowDice.length; j++) {
                    if(nowDice[j] == i) {
                        flag = false;
                        break;
                    }
                }
                
                if(flag) {
                    nowDiceB[k++] = i;
                }
            }                

            findPossibleSum(dice, nowDice, sumListA);
            findPossibleSum(dice, nowDiceB, sumListB);
            calculateWin(nowDice);
            return;
        }
        
        for(int i = start; i < diceCount; i++) {
            nowDice[nowCount] = i;
            choiceDice(dice, nowCount + 1, nowDice, i + 1);
        }
    }
    
    private static void findPossibleSum(int[][] dice, int[] nowDice, Map<Integer, Integer> sumList) {
        sumList.clear();
        
        //첫 번째 주사위 반복, 두 번째 주사위 반복, ...
//         List<Integer> sum = new ArrayList<>();
//         List<Integer> tmp = new ArrayList<>();
//         for(int i = 0; i < 6; i++) {
//             tmp.add(dice[nowDice[0]][i]);
//         }
        
//         for(int i = 1; i < nowDice.length; i++) {
//             for(int k = 0; k < tmp.size(); k++) {
//                 for(int j = 0; j < 6; j++) {
//                     sum.add(dice[nowDice[i]][j] + tmp.get(k));
//                 }
//             }
            
//             tmp.clear();
//             tmp.addAll(sum);
//             sum.clear();
//         }
        
//         sumList.addAll(tmp);
        ///
        
        for(int i = 0; i < possibleChoiceSum.size(); i++) {
            int[] nowIdx = possibleChoiceSum.get(i).index;
             
            int sum = 0;
            for(int j = 0, k = 0; j < nowDice.length; j++, k++) {
                sum += dice[nowDice[j]][nowIdx[k]];
            }
            sumList.put(sum, sumList.getOrDefault(sum, 0) + 1);
            // sumList.add(sum);
        }
    }
    
    private static void calculateWin(int[] nowDice) {
        int winCount = 0;
        
        // for(int i = 0; i < sumListA.size(); i++) {
        //     for(int j = 0; j < sumListB.size(); j++) {
        //         if(sumListA.get(i) > sumListB.get(j)) {
        //             winCount++;
        //         }        
        //     }
        // }
        
        for(Integer keyA : sumListA.keySet()) {
            for(Integer keyB : sumListB.keySet()) {
                if(keyA > keyB) {
                    winCount += sumListA.get(keyA) * sumListB.get(keyB);
                }
            }
        }
        
        int[] result = new int[nowDice.length];
        for(int i = 0; i < nowDice.length; i++) {
            result[i] = nowDice[i] + 1;
        }
        pq.add(new Dice(result, winCount));
    }
    
    
}