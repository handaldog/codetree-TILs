import java.util.*;

public class Main {

    static class point implements Comparable<point>{
        int x;
        int y;
        int trees;

        point(int x, int y, int trees){
            this.x = x;
            this.y = y;
            this.trees = trees;
        }

        @Override
        public int compareTo(point p){
            if(this.trees < p.trees){
                return 1;
            }
            else if(this.trees == p.trees){
                if(this.x > p.x){
                    return 1;
                }
                else if(this.x == p.x){
                    if(this.y > p.y){
                        return 1;
                    }
                }
            }
            return -1;
        }
    }

    static int area[][];
    static int n,k,m,c, time, answer;
    
    static int growi[] = {0,0,1,-1};
    static int growj[] = {1,-1,0,0};

    static int spreadi[] = {-1,-1,1,1};
    static int spreadj[] = {-1,1,-1,1};

    static int drug[][];

    static PriorityQueue<point> pq;
    

    public static void main(String[] args) {
       
       Scanner sc = new Scanner(System.in);

       n = sc.nextInt();
       m = sc.nextInt();
       k = sc.nextInt();
       c = sc.nextInt();

       area = new int [n][n];
       drug = new int [n][n];

       for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            area[i][j] = sc.nextInt();
        }
       }

       time = 0;

        while(true){

            if(time == m)break;

            // System.out.println("한번");

            treegrow();

            treebreed();
            

            // for(int i=0;i<n;i++){
            //     for(int j=0;j<n;j++){
            //         System.out.print(area[i][j] + " ");
            //     }
            //     System.out.println();
            // }
            //     System.out.println();
            pq = new PriorityQueue<>();

            selectdrug();

            // System.out.println("pqsize : " + pq.size());

            if(!pq.isEmpty()){

                point po = pq.poll();
                spreaddrug(po.x, po.y);

            }       


            // System.out.println("pox : " + po.x + "poy : " + po.y + "trees : " + po.trees);

            time++;

            drugcheck();
            

        }

        System.out.println(answer);
    }

    // 나무의 성장

    public static void treegrow(){
        for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){

            int cnt = 0;
            
            if(area[i][j] <= 0)continue;

            for(int k=0;k<4;k++){
                int nexti = i + growi[k];
                int nextj = j + growj[k];

                if(nexti >=0 && nextj >= 0 && nexti < n && nextj < n 
                && area[nexti][nextj] > 0){
                    cnt++;
                }
            }
            area[i][j] += cnt;
        }
       }

    }

    // 나무의 번식
    public static void treebreed(){

        ArrayList<point> list = new ArrayList<>();

        for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){

            if(area[i][j] <= 0)continue;
            
            
                int cnt = 0;
                for(int k=0;k<4;k++){
                    int nexti = i + growi[k];
                    int nextj = j + growj[k];

                if(nexti >=0 && nextj >= 0 && nexti < n && nextj < n 
                    && area[nexti][nextj] == 0){
                    cnt++;
                    }
                }

                
                if(cnt == 0)continue;
                
                int treecnt = area[i][j]/cnt;

                for(int k=0;k<4;k++){
                    int nexti = i + growi[k];
                    int nextj = j + growj[k];

                if(nexti >=0 && nextj >= 0 && nexti < n && nextj < n 
                    && area[nexti][nextj] == 0){
                    list.add(new point(nexti, nextj, treecnt));
                    }
                }

            
        }
       }
        for(int l=0;l<list.size();l++){
            area[list.get(l).x][list.get(l).y] += list.get(l).trees;
            
            }

    }

    // 살충제 장소 정하기
    public static void selectdrug(){
        for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            int sum = area[i][j];
            if(area[i][j] > 0){
                // System.out.println(area[i][j]);
                // System.out.println(i + " " + j);
                    for(int kk=0;kk<4;kk++){
                for(int h=1;h<=k;h++){
                        int nexti = i + spreadi[kk]*h;
                        int nextj = j + spreadj[kk]*h;

                        if(nexti >= 0 && nextj >= 0 && nexti < n && nextj < n 
                        && area[nexti][nextj] > 0){
                            sum += area[nexti][nextj];
                        }
                        else{
                            
                            break;
                        }
                        

                }
                    }
            pq.offer(new point(i,j,sum));
            }
        }
       }
    }

    // 정해진 살충제 뿌리기
    public static void spreaddrug(int i, int j){


                answer += area[i][j];

                area[i][j] = -2;
                drug[i][j] = c+1;

                    for(int kk=0;kk<4;kk++){
                        for(int h=1;h<=k;h++){
                        int nexti = i + spreadi[kk]*h;
                        int nextj = j + spreadj[kk]*h;

                        if(nexti >= 0 && nextj >= 0 && nexti < n && nextj < n){

                            if(area[nexti][nextj] > 0){
                            answer += area[nexti][nextj]; 
                            area[nexti][nextj] = -2;
                            drug[nexti][nextj] = c+1;

                            }
                            else {
                            area[nexti][nextj] = -2;
                            drug[nexti][nextj] = c+1;
                            break;
                        }
                            }
                        

                    }
                }

    }



    // 년수 체크
    public static void drugcheck(){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(drug[i][j] > 0 && area[i][j] == -2){
                    drug[i][j]--;
                }
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(drug[i][j] == 0 && area[i][j] == -2){
                    area[i][j] = 0;
                }
            }
        }
    }




}