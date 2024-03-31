import java.util.*;

public class Main {

    static class point{
        int m;
        int s;
        int d;
        int x;
        int y;

        point(int m, int s, int d){
            this.m = m;
            this.s = s;
            this.d = d;
        }
        point(int x, int y, int m, int s, int d){
            this.x = x;
            this.y = y;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }

    // ↑, ↗, →, ↘, ↓, ↙, ←, ↖ 
    static int di[] = {-1,-1,0,1,1,1,0,-1};
    static int dj[] = {0,1,1,1,0,-1,-1,-1};

    // 상하좌우
    static int onex[] = {0,2,4,6};
    static int twox[] = {1,3,5,7};

    static int n,m,k;

    static long answer;
   
    static ArrayList<point>[][] area;
    static ArrayList<point> list;

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();


        area = new ArrayList[n][n];

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                area[i][j] = new ArrayList<>();
            }
        }

        for(int i=0;i<m;i++){
            area[sc.nextInt()-1][sc.nextInt()-1].add(new point(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        
        }

        for(int tc=0;tc<k;tc++){

            list = new ArrayList<>();

            move();

            // System.out.println(list.size());

            for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                area[i][j] = new ArrayList<>();
            }
        }

            spread();

            changeone();


        }
            result();

        System.out.println(answer);

        
    }
    public static void move(){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(area[i][j].size() > 0){

                    for(int l=0;l<area[i][j].size();l++){

                    point po = area[i][j].get(l);

                    int nexti = (n + i +di[po.d] * (po.s%n))%n;
                    int nextj = (n + j +dj[po.d] * (po.s%n))%n;
                    

                    list.add(new point(nexti, nextj, po.m,po.s,po.d));
                   
                    }
                    

                }
            }
        }
    }
    public static void spread(){
        for(int i=0;i<list.size();i++){
            point po = list.get(i);
            area[po.x][po.y].add(new point(po.m, po.s, po.d));
        }
    }
    public static void changeone(){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(area[i][j].size() >= 2){

                    // System.out.println("x : "+ i + "y :" + j);
                    // System.out.println(area[i][j].size());
                    int sum = 0;
                    int sok = 0;
                    int one = 0;
                    int two = 0;
                    int size = area[i][j].size();
                    for(int l=0;l<size;l++){

                        point po = area[i][j].get(0);
                        area[i][j].remove(0);
                        
                        sum += po.m;
                        sok += po.s;

                        if(po.d%2 == 0){
                            one++;
                        }
                        else if(po.d%2 == 1){
                            two++;
                        }


                    }

                    // System.out.println("sum : " + sum);
                    // System.out.println("sum/5 : " + sum/5);
                        if(sum/5 > 0 && ((one == 0 && two > 0)  || (one > 0 && two == 0))){
                            for(int k=0;k<4;k++){
                                area[i][j].add(new point(sum/5,sok/size,onex[k]));
                            }
                        }
                        else if(sum/5 > 0 && one > 0 && two > 0){
                            for(int k=0;k<4;k++){
                                area[i][j].add(new point(sum/5,sok/size,twox[k]));
                            }

                        }
                }
            }
        }
    }
    public static void result(){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(area[i][j].size() > 0){

                    for(int l=0;l<area[i][j].size();l++){

                    point po = area[i][j].get(l);
                    
                    answer += po.m;
                   
                    }
                    

                }
            }
        }
    }
}