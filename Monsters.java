import java.util.*;
import java.io.*;

public class Monsters{
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		char ch[][] = new char[n][m];
		for(int i = 0 ; i < n ; i++){
			st = new StringTokenizer(br.readLine());
			String line = st.nextToken();
			for(int j = 0 ; j < m ; j++){
				ch[i][j] = line.charAt(j);
			}
		}
		int startR = -1, startC = -1;
		Queue<int[]> qMonster = new LinkedList<>();
		Queue<int[]> qAMoves = new LinkedList<>();
		boolean visMonster[][] = new boolean[n][m];
		boolean visA[][] = new boolean[n][m];
		int monstertiming[][] = new int[n][m];
		for(int arr[] : monstertiming){
			Arrays.fill(arr, (int)1e9);
		}

		for(int i = 0 ; i < n ; i++){
			for(int j = 0 ; j < m ; j++){
				if(ch[i][j] == 'M'){
					qMonster.add(new int[]{i, j, 0});
					visMonster[i][j] = true;
					monstertiming[i][j] = 0;
				} else if(ch[i][j] == 'A'){
					qAMoves.add(new int[]{i, j, 0});
					visA[i][j] = true;
					startR = i;
					startC = j;
				}
			}
		}
		
		if(startR == 0 || startR == n-1 || startC == 0 || startC == m-1){
			System.out.println("YES");
			System.out.println(0);
			return;
		}

		char dirs[] = {'U', 'R', 'D', 'L'};
		int dr[] = {-1, 0, 1, 0};
		int dc[] = {0, 1, 0, -1};

		while(!qMonster.isEmpty()){
			int cur[] = qMonster.poll();
			int r = cur[0];
			int c = cur[1];
			int t = cur[2];

			for(int i = 0 ; i < 4 ; i++){
				int nr = r + dr[i], nc = c + dc[i];
				if(nr >= 0 && nr < n && nc >= 0 && nc < m && visMonster[nr][nc] == false && ch[nr][nc] != '#'){
					qMonster.add(new int[]{nr, nc, t+1});
					visMonster[nr][nc] = true;
					monstertiming[nr][nc] = t+1;
				}
			}
		}
		//  bfs for A 
		boolean fd = false;
		char moveFrom[][] = new char[n][m];
		int parentR[][] = new int[n][m];
		int parentC[][] = new int[n][m];
		int endR = -1, endC = -1;
		while(!qAMoves.isEmpty()){
			int cur[] = qAMoves.poll();
			int r = cur[0];
			int c = cur[1];
			int t = cur[2];
			if (r == 0 || r == n-1 || c == 0 || c == m-1) {
			    fd = true;
			    endR = r;
			    endC = c;
			    break;
			}

			for(int i = 0 ; i < 4 ; i++){
				int nr = r + dr[i], nc = c + dc[i];
				if(nr >= 0 && nr < n && nc >= 0 && nc < m && visA[nr][nc] == false && ch[nr][nc] == '.' && monstertiming[nr][nc] > t + 1){
					qAMoves.add(new int[]{nr, nc, t + 1});
					visA[nr][nc] = true;
					moveFrom[nr][nc] = dirs[i];
					parentR[nr][nc] = r;
					parentC[nr][nc] = c;
				}
			}
		}

		if(!fd){
			System.out.println("NO");
            return;
		}
		StringBuilder path = new StringBuilder();
		int r = endR, c = endC;
		while (!(r == startR && c == startC)) {
            path.append(moveFrom[r][c]);
            int pr = parentR[r][c];
            int pc = parentC[r][c];
            r = pr;
            c = pc;
        }
		System.out.println("YES");
		System.out.println(path.length());
		System.out.println(path.reverse().toString());
	}
}
// input
// 5 8
// ########
// #M..A..#
// #.#.M#.#
// #M#..#..
// #.######
// output
// YES
// 5
// RRDDR