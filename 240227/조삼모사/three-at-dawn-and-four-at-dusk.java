import java.util.*;

public class Main {

    // static boolean visit[];
    static int area[][];
    static boolean result[];
    static int n;
    static int min = 1000000;

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        area  = new int [n][n];
        // visit = new boolean[n];
        result = new boolean [n];

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                area[i][j] = sc.nextInt();
            }
        }
        comb(0,0);

        System.out.println(min);

    }

    public static void comb(int idx, int start){
        if(idx == n/2){
            int cal1 = 0;
            int cal2 = 0;
            for(int i=0;i<n;i++){
                // System.out.print(i + ":" + result[i] + " ");
                for(int j=0;j<n;j++){
                    if(i == j) continue;
                    if(!result[i] && !result[j]){
                        cal1 += area[i][j];
                    }
                    else if(result[i] && result[j]){
                        cal2 += area[i][j];
                    }
                }
                
            }
                min = Math.min(min, Math.abs(cal1 - cal2));
            // System.out.println();
            return;
        }
        for(int i=start;i<n;i++){

            result[i] = true;
            comb(idx+1, i+1);
            result[i] = false;
            
        }

    }
}