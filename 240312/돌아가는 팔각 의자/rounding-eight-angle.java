import java.util.*;

public class Main {

    // static class point{
    //     int number;
    //     int dir;

    //     point(int number, int dir){
    //         this.number = number;
    //         this.dir = dir;
    //     }
    // }

    static int score[] = {1,2,4,8};
    public static void main(String[] args) {
        
        
        Scanner sc = new Scanner(System.in);

        LinkedList<Integer>[] chair = new LinkedList[4];

        for(int i=0;i<4;i++){
            String table = sc.next();

            chair[i] = new LinkedList<>();

            for(int j=0;j<8;j++){
                int ns = table.charAt(j) - '0';
                chair[i].add(ns);
            }
        }

        int round = sc.nextInt();

        for(int i=0;i<round;i++){

            HashMap<Integer, Integer> map = new HashMap<>();

            int number = sc.nextInt()-1;

            int dir = sc.nextInt();

            map.put(number, dir);

            int cnt = number;

            while(cnt < 3){
                cnt++;
                if(chair[cnt-1].get(2) == chair[cnt].get(6))break;
                if(map.get(cnt-1) == 1){
                    map.put(cnt, -1);
                }
                else{
                    map.put(cnt, 1);
                }  
            }

            cnt = number;

            while(cnt > 0){
                cnt--;
                if(chair[cnt+1].get(6) == chair[cnt].get(2))break;
                if(map.get(cnt+1) == 1){
                    map.put(cnt, -1);
                }
                else{
                    map.put(cnt, 1);
                }
            }

            // System.out.println(map.size());

            for(int key : map.keySet()){
                int value = map.get(key);

                if(value == 1){
                    // 맨 뒤에 꺼를 맨 앞에
                    chair[key].addFirst(chair[key].get(chair[key].size()-1));
                    chair[key].removeLast();
                }
                else{
                    // 맨 앞에 꺼를 맨 뒤에
                    chair[key].add(chair[key].get(0));
                    chair[key].removeFirst();
                }
                
            }


        }

        int result = 0;

        for(int i=0;i<4;i++){
            if(chair[i].get(0) == 1){
                result += score[i]*1;
            }
        }

        System.out.println(result);



    }
}