import java.util.*;

public class Main {
    static int n;
    static boolean visit[];
    static int result[];
    static ArrayList<Integer> calc;
    static long min = 1000000000000;
    static long max = -1254895;
    static long number[];
    

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        number = new long[n];
        calc = new ArrayList<>();

        for(int i=0;i<n;i++){
            number[i] = sc.nextInt();
        }

        for(int i=0;i<3;i++){
            int num = sc.nextInt();
            for(int j=0;j<num;j++){
                calc.add(i);
            }
        }
        visit = new boolean[n-1];
        result = new int [n-1];

        recur(0);

        System.out.print(min +" ");
        System.out.print(max);

    }
    public static void recur(int idx){
        if(idx == n-1){
            long sum = number[0];
            for(int i=0;i<n-1;i++){
                int res = calc.get(result[i]);
                
                if(res == 0){
                    sum += number[i+1];
                    
                }
                else if(res == 1){
                    sum -= number[i+1];
                }
                else if(res == 2){
                    sum *= number[i+1];
                }
                
            }
            min = Math.min(min, sum);
            max = Math.max(max, sum);
            return;
        }
        for(int i=0;i<n-1;i++){
            if(visit[i])continue;
            visit[i] = true;
            result[idx] = i;
            recur(idx+1);
            visit[i] = false;
        }
    }
}