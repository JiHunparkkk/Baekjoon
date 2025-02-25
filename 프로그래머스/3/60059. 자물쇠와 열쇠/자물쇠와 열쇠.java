import java.util.*;

class Solution {
    
    private static int[][] board;
    private static int keyLen, lockLen;
    private static int lockX, lockY;
    private static List<int[][]> rotatedKey = new ArrayList<>();
    
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = true;
        
        keyLen = key.length;
        lockLen = lock.length;
        board = new int[keyLen * 2 + lockLen - 2][keyLen * 2 + lockLen - 2];
        lockX = lockY = keyLen - 1;
        
        for(int k = lockX; k < lockX + lockLen; k++) {
            for(int z = lockY; z < lockY + lockLen; z++) {
                board[k][z] = lock[k - lockX][z - lockY];
            }
        }
       
        rotate(key);
        
        for(int i = 0; i < lockX + lockLen; i++) {
            for(int j = 0; j < lockY + lockLen; j++) {
                
                for(int[][] nowKey : rotatedKey) {
                    //칠하기
                    for(int k = i; k < i + keyLen; k++) {
                        for(int z = j; z < j + keyLen; z++) {
                            board[k][z] += nowKey[k - i][z - j];
                        }
                    }
                    
                    //확인하기
                    boolean isPossible = true;
                    for(int k = lockX; k < lockX + lockLen; k++) {
                        for(int z = lockY; z < lockY + lockLen; z++) {
                            if(board[k][z] != 1) {
                                isPossible = false;
                            }
                        }
                        if(!isPossible) {
                            break;
                        }
                    }
                    
                    if(isPossible) {
                        return true;
                    }
                    
                    //복구하기
                    for(int k = i; k < i + keyLen; k++) {
                        for(int z = j; z < j + keyLen; z++) {
                            board[k][z] -= nowKey[k - i][z - j];            
                        }
                    }
                }
                
            }
        }
        
        return false;
    }
    
    private static void rotate(int[][] key) {
        for(int i = 0; i < 4; i++) {
            int[][] newKey = new int[keyLen][keyLen];
            
            for(int j = 0; j < keyLen; j++) {
                for(int k = 0; k < keyLen; k++) {
                    newKey[j][k] = key[keyLen - 1 - k][j]; 
                }
            }
            rotatedKey.add(newKey);
            key = newKey;
        }
    }
}