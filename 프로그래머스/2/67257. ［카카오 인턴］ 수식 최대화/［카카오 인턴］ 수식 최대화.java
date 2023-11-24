import java.util.*;

class Solution {
    public long solution(String expression) {
        long answer = Long.MIN_VALUE;

        String operate[][] = {
                {"+", "-", "*"}, {"+", "*", "-"}, {"-", "*", "+"},
                {"-", "+", "*"}, {"*", "-", "+"}, {"*", "+", "-"}
        };

        List<String> list = new ArrayList<>();
        int p1 = 0;
        for (int p2 = 0; p2 < expression.length(); p2++) {
            if (expression.charAt(p2) == '+' || expression.charAt(p2) == '-' || expression.charAt(p2) == '*') {
                list.add(expression.substring(p1, p2));
                list.add(expression.charAt(p2) + "");
                p1 = p2 + 1;
            }
        }
        list.add(expression.substring(p1));

        for (int i = 0; i < operate.length; i++) {
            List<String> subList = new ArrayList<>(list);

            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < subList.size(); k++) {
                    if (operate[i][j].equals(subList.get(k))) {
                        String calculate = calculate(subList.get(k - 1), subList.get(k + 1), subList.get(k));
                        subList.set(k - 1, calculate);

                        subList.remove(k);
                        subList.remove(k);
                        k -= 1;

                    }
                }
            }
            answer = Math.max(answer, Math.abs(Long.parseLong(subList.get(0))));
            
        }

        return answer;
    }


    private String calculate(String a, String b, String op) {
        long x = Long.parseLong(a);
        long y = Long.parseLong(b);
        switch (op) {
            case "+":
                return x + y + "";
            case "-":
                return x - y + "";
        }
        return x * y + "";
    }

}