import java.util.*;

public class Main {

    static boolean visit[];
    static int area[][];
    static int result[];
    static int n;
    static int min = 1000000;

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        area  = new int [n][n];
        visit = new boolean[n];
        result = new int [4];

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                area[i][j] = sc.nextInt();
            }
        }
        comb(0);

        System.out.println(min);

    }

    public static void comb(int idx){
        if(idx == n){
            int cal1 = 0;
            int cal2 = 0;
            for(int i=0;i<n;i++){
                // System.out.print(result[i] + " ");
                for(int j=0;j<n/2;j++){
                    for(int h=0;h<n/2;h++){
                        if(j == h)continue;
                         cal1 += area[result[j]][result[h]];

                    }
                    
                }
                for(int j=n/2;j<n;j++){
                    for(int h=n/2;h<n;h++){
                        if(j == h)continue;
                         cal2 += area[result[j]][result[h]];

                    }
                }
                
                min = Math.min(min, Math.abs(cal1 - cal2));
            }
            // System.out.println();
            return;
        }
        for(int i=0;i<n;i++){
            if(visit[i])continue;
            visit[i] = true;
            result[idx] = i;
            comb(idx+1);
            visit[i] = false;
        }

    }
}