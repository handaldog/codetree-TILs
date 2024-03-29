import java.util.*;

public class Main {


    static int area[][];
    static int areacopy[][];
    static int n,m,t;
    static int cleantool[] = new int [2];

    static int di[] = {0,0,1,-1};
    static int dj[] = {1,-1,0,0};

    static int notclockx[] = {-1,0,1,0};
    static int notclocky[] = {0,1,0,-1};

    static int clockx[] = {1,0,-1,0};
    static int clocky[] = {0,1,0,-1};


    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        t = sc.nextInt();

        // System.out.println(t);

        area = new int [n][m];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                area[i][j] = sc.nextInt();

                if(cleantool[1] == 0 && area[i][j] == -1){
                    cleantool[0] = i;
                    cleantool[1] = i+1;
                }
            }
        }
        for(int tc=0;tc<t;tc++){

            // System.out.println("야");
            
            areacopy = new int [n][m];

            spread();

        //     for(int i=0;i<n;i++){
        //     for(int j=0;j<m;j++){
                
        //         System.out.print(area[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        // System.out.println(" ----------------------");

            spreadadd();

            upclean(cleantool[0]-1, 0);

            downclean(cleantool[1] +1, 0);

        }

        long answer = 0L;

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                answer += area[i][j];
            }
        }

        System.out.println(answer +2);



    }

    public static void spread(){
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(area[i][j] > 0){
                    int num = area[i][j]/5;

                    int cnt = 0;

                    for(int k=0;k<4;k++){
                        int nexti = i + di[k];
                        int nextj = j + dj[k];

                        if(nexti >=0 && nextj >=0 && nexti < n && nextj < m && area[nexti][nextj] != -1){
                            cnt++;
                            areacopy[nexti][nextj] += num;
                        }

                    }
                    area[i][j] -= num*cnt;
                }
            }
        }
    }

    public static void spreadadd(){
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                area[i][j] += areacopy[i][j];
            }
        }
    }

    public static void upclean(int x, int y){
        for(int k=0;k<4;k++){
            while(true){
                int nexti = x + notclockx[k];
                int nextj = y + notclocky[k];

                if(nexti >=0 && nextj >=0 && nexti <= cleantool[0] 
                && nextj < m && area[nexti][nextj] != -1){
                    area[x][y] = area[nexti][nextj];
                    x = nexti;
                    y = nextj;
                }
                else{
                    break;
                }
            }
        }
        area[cleantool[0]][1] = 0;

    }

    public static void downclean(int x, int y){
        for(int k=0;k<4;k++){
            while(true){
                int nexti = x + clockx[k];
                int nextj = y + clocky[k];

                if(nexti >= cleantool[1] && nextj >=0 && nexti < n
                && nextj < m && area[nexti][nextj] != -1){
                    area[x][y] = area[nexti][nextj];
                    x = nexti;
                    y = nextj;
                }
                else{
                    break;
                }
            }
        }
        area[cleantool[1]][1] = 0;

    }
}