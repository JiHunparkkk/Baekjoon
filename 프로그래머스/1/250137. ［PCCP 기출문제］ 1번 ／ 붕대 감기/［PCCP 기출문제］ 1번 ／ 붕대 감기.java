class Solution {
    
    private static int mediCnt, curHealth;
    
    public int solution(int[] bandage, int health, int[][] attacks) {        
        //공격 시간동안 계속해서 반복
        //공격시간 o -> 바로 공격, 연속시간 초기화, 체력 감소 -> 0이되면 사망
        //공격시간 x -> 초당회복, 연속 성공-> 추가 회복
        //최대 최력 -> 회복 x, 성공횟수는 +1
        
        int curTime = 1;
        curHealth = health;
        for(int i = 0; i < attacks.length; i++) {
            int attackTime = attacks[i][0];
            for(; curTime < attackTime; curTime++) {
                medic(bandage, health);
            }
            
            //반복문 종료되면 공격시간
            attack(attacks[i][1]);
            curTime++;
            if(curHealth <= 0) {
                return -1;
            }
        }
        
        return curHealth;
    }
    
    private static void medic(int[] bandage, int health) {
        if(health == curHealth) {
            mediCnt++;
            return;
        }
        
        mediCnt++;
        recovery(bandage[1], health);   
        if(mediCnt >= bandage[0]) {
            mediCnt = 0;
            recovery(bandage[2], health);
        }
        
    }
    
    private static void recovery(int plus,int max) {
        curHealth += plus;
        if(curHealth > max) {
            curHealth = max;
        }
    }
    
    private static void attack(int damage) {
        mediCnt = 0;
        curHealth -= damage;
    }
    
}