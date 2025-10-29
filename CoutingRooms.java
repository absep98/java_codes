import java.util.*;
public class CoutingRooms {
    public static void dfs(int x, int y, int n, int m, char ch[][], boolean vis[][]){
        Stack<int[]> st = new Stack<>();
        st.push(new int[]{x, y});
        vis[x][y] = true;
        int dr[] = {-1, 0, +1, 0};
        int dc[] = {0, 1, 0, -1};

        while(!st.isEmpty()){
            int cell[] = st.pop();
            int row = cell[0], col = cell[1];
            for(int i = 0 ; i < 4 ; i++){
                int nr = dr[i] + row, nc = dc[i] + col;
                if(nr >= 0 && nr < n && nc >= 0 && nc < m && vis[nr][nc] == false && ch[nr][nc] == '.'){
                    vis[nr][nc] = true;
                    st.push(new int[]{nr, nc});
                }
            }
        }
        
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        char ch[][] = new char[n][m];
        for(int i = 0 ; i < n ; i++){
            String line = sc.next();
            for(int j = 0 ; j < m ; j++){
                ch[i][j] = line.charAt(j);
            }
        }
        boolean vis[][] = new boolean[n][m];
        int count = 0;
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                if(ch[i][j] == '.' && vis[i][j] == false){
                    dfs(i, j, n, m, ch, vis);
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
