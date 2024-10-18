import java.util.*;

class Solution {
    
    private static Set<Integer> set;
    private static int length;
    private static int maxNum;
    
    public String[] solution(String[] expressions) {
        set = new HashSet<>();
        
        maxNum = 1;
        for(String expression : expressions) {
            String[] strs = expression.split(" ");
            int num1 = Integer.parseInt(strs[0]);
            int num2 = Integer.parseInt(strs[2]);
            String num3 = strs[4];
            
            maxNum = findMax(maxNum, num1);
            maxNum = findMax(maxNum, num2);
            if(!num3.equals("X")) {
                maxNum = findMax(maxNum, Integer.parseInt(num3));
            } else {
                length++;
            }
        }
        
        maxNum++;
        for(int i = maxNum; i <= 9; i++) {
            set.add(i);
        }
        
        for(String expression : expressions) {
            findFormation(expression);
        }

        String[] answer = new String[length];
        int index = 0;
        for(String expression : expressions) {
            if(!expression.split(" ")[4].equals("X")) {
                continue;
            }
            answer[index++] = findMystery(expression);
        }
        
        return answer;
    }
    
    private static void findFormation(String expression) {
        String[] expr = expression.split(" ");
        String num1 = expr[0];
        String ex = expr[1];
        String num2 = expr[2];
        String expect = expr[4];
        
        //n진수 -> 10진수 -> 계산 후 다시 n진수
        for(int i = maxNum; i <= 9; i++) {
            int a = Integer.parseInt(num1, i);
            int b = Integer.parseInt(num2, i);
            int result = calculateTen(a, b, ex);

            checkFormation(expect, result, i);
        }
    }
    
    private static int findMax(int maxNum, int num) {
         while(num > 0) {
                maxNum = Math.max(maxNum, num % 10);
                num /= 10;
            }
        return maxNum;
    }
    
    private static String findMystery(String expression) {
        String[] expr = expression.split(" ");
        String num1 = expr[0];
        String ex = expr[1];
        String num2 = expr[2];
        String expect = expr[4];

        Set<String> resultSet = new HashSet<>();
        for(Integer num : set) {
            int a = Integer.parseInt(num1, num);
            int b = Integer.parseInt(num2, num);
            int result = calculateTen(a, b, ex);

            expect = calculateFormation(result, num);
            resultSet.add(expect);
        }
        
        if(resultSet.size() > 1 || resultSet.size() == 0) {
            expect = "?";
        }
        
        return num1 + " " + ex + " " + num2 + " = " + expect;
    }
    
    private static int calculateTen(int num1, int num2, String ex) {
        switch(ex) {
            case "+" :
                return num1 + num2;
            default :
                return num1 - num2;
        }
    }
    
    private static void checkFormation(String expect, int result, int formation) {
        if(expect.equals("X")) {
            return;
        }
        
        if(!expect.equals(Integer.toString(result, formation)) && set.contains(formation)) {
            set.remove(formation);
        }
    }
    
    private static String calculateFormation(int result, int formation) {
        return Integer.toString(result, formation);
    }
}