import java.util.*;

public class Main {

    static class point{
        int x;
        int y;
        int dir;

        point(int x, int y, int dir){
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    static int boxi[] = {0,1,1};
    static int boxj[] = {1,0,1};

  
    static int origin[] = {1,2,3,0};

    static int di[] = {0,-1,0,1};
    static int dj[] = {1,0,-1,0};
    
    // 우상좌하
    static int chdi[] = {-1,0,1,0};
    static int chdj[] = {0,-1,0,1};

    static boolean visit[][] = new boolean [101][101];;


    public static void main(String[] args) {

        int answer = 0;
        
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        for(int t=0;t<n;t++){

            ArrayList<point> list = new ArrayList<>();

            int x = sc.nextInt();
            int y = sc.nextInt();
            int dir = sc.nextInt();

            list.add(new point(x,y,dir));

            visit[x][y] = true;

            int time = sc.nextInt();

            int curx = x + di[dir];
            int cury = y + dj[dir];

            visit[curx][cury] = true;

            for(int tc=0;tc<time;tc++){
                for(int l=list.size() - 1;l>=0;l--){
                    int d = list.get(l).dir;
                    int nextx = curx + chdi[d];
                    int nexty = cury + chdj[d];

                    visit[nextx][nexty] = true;

                    list.add(new point(nextx, nexty, origin[d]));

                    curx = nextx;
                    cury = nexty;
                }
            }


        }

        // 정사각형 확인
        for(int i=0;i<7;i++){
            for(int j=0;j<7;j++){
                if(visit[i][j]){
                    int cnt = 0;
                    for(int k=0;k<3;k++){
                        if(visit[i+boxi[k]][j+boxj[k]])cnt++;
                            }   
                            
                    if(cnt == 3){
                    // System.out.println("di");
                    answer++;
                }             
                }
                
                
            }
            // System.out.println();
        }

        System.out.println(answer);
        
    }
}