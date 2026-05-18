import java.io.*;
import java.util.*;

public class KnightMovesGrid {
    public static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    static class FastScanner {
        private final InputStream is = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, buflen = 0;

        private boolean hasNextByte() {
            if (ptr < buflen) return true;
            ptr = 0;
            try { buflen = is.read(buffer); } catch (IOException e) { e.printStackTrace(); }
            return buflen > 0;
        }

        private byte readByte() { return hasNextByte() ? buffer[ptr++] : -1; }

        private static boolean isPrintableChar(byte c) { return 33 <= c && c <= 126; }

        public boolean hasNext() {
            while (hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++;
            return hasNextByte();
        }

        public String next() {
            if (!hasNext()) throw new NoSuchElementException();
            StringBuilder sb = new StringBuilder();
            byte b = readByte();
            while (isPrintableChar(b)) {
                sb.append((char) b);
                b = readByte();
            }
            return sb.toString();
        }

        public int nextInt() { return (int) nextLong(); }

        public long nextLong() {
            if (!hasNext()) throw new NoSuchElementException();
            long n = 0;
            boolean minus = false;
            byte b = readByte();
            if (b == '-') { minus = true; b = readByte(); }
            while ('0' <= b && b <= '9') {
                n = n * 10 + (b - '0');
                b = readByte();
            }
            return minus ? -n : n;
        }
    }

    public static void bfs(int n, int dist[][]){
    	int dirs[][] = {{-2,-1}, {-2, 1}, {2, -1}, {2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}};
    	dist[0][0] = 0;

    	Queue<int[]> q = new LinkedList<>();
    	q.add(new int[]{0, 0});
    	while(!q.isEmpty()){
    		int cur[] = q.poll();
    		int r = cur[0];
    		int c = cur[1];
    		for(int i = 0 ; i < 8 ; i++){
    			int nr = r + dirs[i][0];
    			int nc = c + dirs[i][1];
    			if(nr >= 0 && nr < n && nc >= 0 && nc < n && dist[nr][nc] == -1){
    				dist[nr][nc] = dist[r][c] + 1;
    				q.add(new int[]{nr, nc});
    			}
    		}
    	}
    }

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
      	
      	int board[][] = new int[n][n];
      	for(int arr[] : board){
      		Arrays.fill(arr, -1);
      	}
      	bfs(n, board);
      	for(int arr[] : board){
      		for(int x : arr){
      			out.write(x + " ");
      		}
      		out.newLine();
      	}
        out.flush();
    }
}
/*

There is a knight on an n \times n chessboard. For each square, print the minimum number of moves the knight needs to do to reach the top-left corner.
Input
The only line has an integer n.
Output
Print the number of moves for each square.
Constraints

4 \le n \le 1000

Example
Input:
8

Output:
0 3 2 3 2 3 4 5 
3 4 1 2 3 4 3 4 
2 1 4 3 2 3 4 5 
3 2 3 2 3 4 3 4 
2 3 2 3 4 3 4 5 
3 4 3 4 3 4 5 4 
4 3 4 3 4 5 4 5 
5 4 5 4 5 4 5 6 

*/