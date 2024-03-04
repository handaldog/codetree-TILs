import java.util.*;

public class Main {

    static class edge implements Comparable<edge>{
        int x;
        int y;
        int friends;
        int vacant;

        edge(int x, int y, int friends, int vacant){
            this.x=x;
            this.y=y;
            this.friends=friends;
            this.vacant=vacant;
        }

        @Override
        public int compareTo(edge e){
            if(this.friends == e.friends){
                if(this.vacant == e.vacant){
                    if(this.x == e.x){
                        return this.y - e.y;
                    }
                    else{
                        return this.x - e.x;
                    }
                }   
                else{
                    return e.vacant - this.vacant;
                }
            }
            else{
                return e.friends - this.friends;
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int play[][] = new int[n][n];

        Queue<Integer> que = new LinkedList<>();

        ArrayList<Integer>[] list = new ArrayList[n*n+1];

        for(int i=0;i<n*n;i++){
            int num = sc.nextInt();
            list[num] = new ArrayList<>();
            que.offer(num);
            for(int j=0;j<4;j++){
                int like = sc.nextInt();
                list[num].add(like);
            }
        }

        int di[] = {0,0,1,-1};
        int dj[] = {1,-1,0,0};

        while(!que.isEmpty()){


            PriorityQueue<edge> pq = new PriorityQueue<>();

            int number = que.poll();

            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(play[i][j] > 0)continue;
                    int friends = 0;
                    int vacant = 0;
                    for(int k=0;k<4;k++){
                        
                        int nexti = i + di[k];
                        int nextj = j + dj[k];

                        if(nexti >= 0 && nextj >= 0 && nexti < n && nextj < n){
                            if(list[number].contains(play[nexti][nextj])){
                                friends++;
                            }
                            if(play[nexti][nextj] == 0){
                                vacant++;
                            }

                            pq.offer(new edge(i,j,friends,vacant));
                        }
                    }
                }
            }

            edge e = pq.poll();

            play[e.x][e.y] = number;

        }

        int calc[] = {0,1,10,100,1000};
        int answer = 0;

        for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){

                    // System.out.println(play[i][j] + " ");
                    int friends = 0;
                    for(int k=0;k<4;k++){
                        
                        int nexti = i + di[k];
                        int nextj = j + dj[k];

                        if(nexti >= 0 && nextj >= 0 && nexti < n && nextj < n){
                            if(list[play[i][j]].contains(play[nexti][nextj])){
                                friends++;
                            }

                            
                        }
                    }

                    answer += calc[friends];

                }
            }

        System.out.println(answer);
        
    }
}