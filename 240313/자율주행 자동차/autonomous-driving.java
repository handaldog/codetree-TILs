import java.util.*;

public class Main {

    // 북 동 남 서
    static int di[] = {0,-1,1,0};
    static int dj[] = {-1,0,0,1};

    static int backx[] = {1,0,-1,0};
    static int backy[] = {0,-1,0,1};

    static int n,m, dir;
    static int street[][];
    static boolean visit[][];

    static int answer = 0;


    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

         n = sc.nextInt();
         m = sc.nextInt();

        int firstx = sc.nextInt();
        int firsty = sc.nextInt();

        int firstdir = sc.nextInt();

        street = new int [n][m];
        visit = new boolean[n][m];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                street[i][j] = sc.nextInt();
            }
        }

        int x = firstx;
        int y = firsty;
        dir = firstdir;

        visit[x][y] = true;

        while(true){

        //     for(int i=0;i<n;i++){
        //     for(int j=0;j<m;j++){
                
        //         System.out.print(visit[i][j] + " ");
        //     }
        //     System.out.println();
        // }

        // System.out.println("dir : " + dir);
        // System.out.println("-------------------");

        


            if(go(x, y, dir)){
                x = x + di[dir];
                y = y + dj[dir];

                dirchange();

                firstdir = dir;

                visit[x][y] = true;


            }
            else{

                dirchange();

                if(firstdir == dir){
                    if(back(x,y,dir)){
                        x = x + backx[dir];
                        y = y + backy[dir];

                        visit[x][y] = true;
                    }
                    else{
                        break;
                    }
                }

            }


        } // while

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(visit[i][j]){
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }

    public static boolean go(int x, int y, int dir){
        
        int nexti = x + di[dir];
        int nextj = y + dj[dir];

        if(nexti >= 0 && nextj >= 0 && nexti < n && nextj < m && street[nexti][nextj] == 0 && !visit[nexti][nextj]){
            return true;
        }
        return false;
    }

    public static void dirchange(){

                if(dir == 0)dir = 3;
                else if(dir == 1)dir = 0;
                else if(dir == 2)dir = 1;
                else dir = 2;
    }

    public static boolean back(int x, int y, int dir){

        int nexti = x + backx[dir];
        int nextj = y + backy[dir];

        if(nexti >= 0 && nextj >= 0 && nexti < n && nextj < m && street[nexti][nextj] == 0){
            return true;
        }
        return false;
    }
}