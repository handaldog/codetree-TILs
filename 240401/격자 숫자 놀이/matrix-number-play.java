import java.util.*;

public class Main {
        
    static int r,c,k, hang, yul, answer, time;
    static int area[][] = new int[100][100];
        
    static HashMap<Integer, Integer> map;
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        r = sc.nextInt();
        c = sc.nextInt();
        k = sc.nextInt();

        for(int i=0;i<100;i++){
            Arrays.fill(area[i], 0);
        }
        
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                area[i][j] = sc.nextInt();
            }
        }

            if(area[r-1][c-1] == k){
                System.out.println(time);
                return;
            }
        
        while(true){


            // System.out.println("야");

            time++;
                        
            if(time > 100){
                System.out.println(-1);
                break;
            }
            // 열 <= 행
            if(yul <= hang){
                yul = 0;
                hangwin();

//                 for(int i=0;i<8;i++){
//                     for(int j=0;j<8;j++){
//                         System.out.print(area[i][j]);
//                     } 
//                     System.out.println();
//                 }
// System.out.println("-------------------"); 
           }

            // 열 > 행
            else if(yul > hang){
                hang = 0;
                yulwin();
            //     for(int i=0;i<8;i++){
            //         for(int j=0;j<8;j++){
            //             System.out.print(area[i][j]);
            //         }
            //         System.out.println(); 
            //     }

            //         System.out.println("-------------------"); 
            }

            if(answer > 0){
                System.out.println(answer);
                break;
            }

        }
        
    }
    public static void hangwin(){
        // System.out.println("행 들어옴");
        for(int i=0;i<100;i++){
            map = new HashMap<>();
            for(int j=0;j<100;j++){
               if(area[i][j] == 0)continue;
                map.put(area[i][j], map.getOrDefault(area[i][j],0)+1);
            }
            if(map.size() == 0) break;
            List<Integer> key = mapsort();

            // System.out.println("size : " + key.size());

            int cnt = 0;

            for(int num : key){
                area[i][cnt] = num;
                cnt++;
                area[i][cnt] = map.get(num);
                cnt++; 
                // System.out.print(num + " ");
                // System.out.print(map.get(num) + " ");
                // System.out.println("!!!" + area[r-1][c-1]);
                if(area[r-1][c-1] == k){
                    answer = time;
                    return;
                }
                if(cnt == 100){

                    // Arrays.fill(area[i], 0);
                    cnt = 0;
                }
            }

            // System.out.println();
                       

            yul = Math.max(cnt, yul);

            for(int h=cnt;h<100;h++){
                area[i][h] = 0;
            }

        }
    }

    public static void yulwin(){
        // System.out.println("열 들어옴");
        for(int i=0;i<100;i++){
            map = new HashMap<>();
            for(int j=0;j<100;j++){
                if(area[j][i] == 0)continue;
                map.put(area[j][i], map.getOrDefault(area[j][i],0)+1);
            }
            if(map.size() == 0)break;
            // System.out.println("??" + map.size());
           
            List<Integer> key = mapsort();

            int cnt = 0;
           
            for(int num : key){
                area[cnt][i] = num;
                cnt++;
                area[cnt][i] = map.get(num);
                cnt++; 
                // System.out.print(num + " ");
                // System.out.print(map.get(num) + " ");
                
                // System.out.println("!!!" + area[r-1][c-1]);
                if(area[r-1][c-1] == k){
                    answer = time;
                    return;
                }
                if(cnt == 100){
                    cnt = 0;
                }
            }

            // System.out.println();
            

            hang = Math.max(cnt, hang);

            for(int h=cnt;h<100;h++){
                area[h][i] = 0;
            }

        }
    }
    public static List<Integer> mapsort(){
        List<Integer> keySet = new ArrayList<>(map.keySet());

        Collections.sort(keySet);
        
        keySet.sort((o1,o2) -> map.get(o1)-map.get(o2));


        return keySet;
    }
   
}