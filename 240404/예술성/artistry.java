import java.util.*;

public class Main {

    static class point{
        int x;
        int y;
        int number;
        int cnt;

        point(int x, int y){
            this.x = x;
            this.y = y;
        }

        point(int x, int y, int number, int cnt){
            this.x = x;
            this.y = y;
            this.number = number;
            this.cnt = cnt;
        }
    }

    static int di[] = {0,0,1,-1};
    static int dj[] = {1,-1,0,0};

    static int bingx[] = {1,0,-1,0};
    static int bingy[] = {0,1,0,-1};

    static int n,joha,sum;
    static int area[][];
    static int areacopy[][];
    static boolean visit[][];
    static int result[] = new int [2];
    static ArrayList<point> list = new ArrayList<>();

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        area = new int [n][n];
        areacopy = new int [n][n];
        visit = new boolean[n][n];

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                area[i][j] = sc.nextInt();
            }
        }


        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(visit[i][j])continue;
                visit[i][j] = true;
                point po = bfs(i,j);
                list.add(po);
            }
        }
        // System.out.println(list.size());
        sum = 0;
        comb(0,0);
        // System.out.println(sum);

        // bingbing();

        for(int tc=0;tc<3;tc++){
            
       
     tenbin();

            bingbing();

            // for(int i=0;i<n;i++){
            //     for(int j=0;j<n;j++){
            //         System.out.print(area[i][j]);
            //     }
            //     System.out.println();
            // }
            //     System.out.println("----------------");
            list = new ArrayList<>();
            visit = new boolean[n][n];
            for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(visit[i][j])continue;
                visit[i][j] = true;
                point po = bfs(i,j);
                list.add(po);
            }
        }
            sum = 0;
            comb(0,0);
            // System.out.println(sum);
            // System.out.println(sum);


        }
        System.out.println(joha);
        
    }

    public static void comb(int start, int idx){
        if(idx == 2){
            areacopy = new int [n][n];
            int dif = 0;
            for(int i=0;i<idx;i++){
                point po = list.get(result[i]);

                visit = new boolean[n][n];
                areacopy[po.x][po.y] = po.number;
                bfscopy(po.x, po.y, po.number);
            }

            // for(int i=0;i<n;i++){
            //     for(int j=0;j<n;j++){
            //         System.out.print(areacopy[i][j] + " ");
            //     }
            //     System.out.println();
            // }
            //     System.out.println("---------------------");

            int num = list.get(result[0]).number;

            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(areacopy[i][j] == num){
                        for(int k=0;k<4;k++){

                            int nexti = i + di[k];
                            int nextj = j + dj[k];

                            if(nexti >= 0 && nextj >= 0 && nexti < n && nextj < n 
                            && areacopy[nexti][nextj] != 0 && areacopy[nexti][nextj] != num){
                                dif++;
                            }
                        }
                    }
                }
            }

            if(dif > 0){

            // System.out.println(dif);

            point one = list.get(result[0]);
            point two = list.get(result[1]);

            // System.out.println("onecnt : " + one.cnt + "twocnt : " + two.cnt + "onenumber " + one.number + "twonumber" + two.number);
            sum += (one.cnt + two.cnt)*one.number*two.number*dif;
            joha += (one.cnt + two.cnt)*one.number*two.number*dif;

            // System.out.println(joha);
            }


            
            return;
        }
        for(int k=start;k<list.size();k++){
            result[idx] = k;
            comb(k+1,idx+1);
        }
    }

    public static point bfs(int x, int y){
        Queue<point> que = new LinkedList<>();

        que.add(new point(x,y));

        int allcnt = 0;

        while(!que.isEmpty()){

            point po = que.poll();
            allcnt++;

            for(int k=0;k<4;k++){
                int nexti = po.x + di[k];
                int nextj = po.y + dj[k];

                if(nexti >= 0 && nextj >= 0 && nexti < n && nextj < n 
                && !visit[nexti][nextj] && area[po.x][po.y] == area[nexti][nextj]){
                    visit[nexti][nextj] = true;
                    que.add(new point(nexti, nextj));
                }
        }

        }
        // System.out.println(allcnt);
        point point = new point (x,y,area[x][y],allcnt);
        return point;


    }
    public static void bfscopy(int x, int y, int number){
        Queue<point> que = new LinkedList<>();

        que.add(new point(x,y));

        while(!que.isEmpty()){

            point po = que.poll();

            for(int k=0;k<4;k++){
                int nexti = po.x + di[k];
                int nextj = po.y + dj[k];

                if(nexti >= 0 && nextj >= 0 && nexti < n && nextj < n 
                && !visit[nexti][nextj] && area[po.x][po.y] == area[nexti][nextj]){
                    visit[nexti][nextj] = true;
                    areacopy[nexti][nextj] = number;
                    que.add(new point(nexti, nextj));
                }
        }

        }


    }
    public static void tenbin(){
        int center = n/2;

        int cent[] = new int [center*2+1];

        for(int i=0;i<=center*2;i++){
            if(i == center)continue;
            cent[i] = area[i][center];
            area[i][center] = area[center][n-1-i];
        }

        for(int i=center*2;i>=0;i--){
            if(i == center)continue;
            area[center][i] = cent[i];
        }

    }

    public static void bingbing(){
        ArrayList<point> binglist = new ArrayList<>();

        binglist.add(new point(0,0));
        binglist.add(new point(0,n/2+1));
        binglist.add(new point(n/2+1, 0));
        binglist.add(new point(n/2+1, n/2+1));

        for(int i=0;i<4;i++){
            point po = binglist.get(i);
            
            for(int j=0;j<1;j++){

                int firstnum = area[po.x + j][po.y + j];
                int x = po.x + j;
                int y = po.y + j;

                int cnt = 0;
                while(true){

                    // System.out.println("cnt : " +cnt);

                    if(cnt == 4)break;
                    int nexti = x + bingx[cnt];
                    int nextj = y + bingy[cnt];

                    if(nexti>=po.x && nextj >= po.y && nexti < po.x+n/2 && nextj <po.y + n/2){
                        area[x][y] = area[nexti][nextj];
                        x=nexti;
                        y=nextj;

// System.out.println("nexti : " + nexti + " " + "nextj : " + nextj);
                    }
                    else{
                    
                        cnt++;
                    
                    }
            
                }
                // System.out.println("야");
                area[po.x][po.y+1]=firstnum;

            }
        }
    }

}