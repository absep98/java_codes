import java.io.*;
import java.util.*;

public class CoutingRooms {

    static class FastScanner {
        private final InputStream is = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, buflen = 0;
        private boolean hasNextByte() { if (ptr < buflen) return true; ptr = 0; try { buflen = is.read(buffer); } catch (IOException e) { e.printStackTrace(); } return buflen > 0; }
        private byte readByte() { return hasNextByte() ? buffer[ptr++] : -1; }
        private static boolean isPrintableChar(byte c) { return 33 <= c && c <= 126; }
        public boolean hasNext() { while (hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++; return hasNextByte(); }
        public String next() { if (!hasNext()) throw new NoSuchElementException(); StringBuilder sb = new StringBuilder(); byte b = readByte(); while (isPrintableChar(b)) { sb.append((char) b); b = readByte(); } return sb.toString(); }
        public long nextLong() { if (!hasNext()) throw new NoSuchElementException(); long n = 0; boolean minus = false; byte b = readByte(); if (b == '-') { minus = true; b = readByte(); } while ('0' <= b && b <= '9') { n = n * 10 + (b - '0'); b = readByte(); } return minus ? -n : n; }
        public int nextInt() { return (int) nextLong(); }
    }

    public static void dfs(int x, int y, int n, int m, boolean vis[][], char board[][]) {
        Stack<int[]> st = new Stack<>();
        st.push(new int[]{x, y});
        vis[x][y] = true;
        int dr[] = {-1, 0, 1, 0};
        int dc[] = {0, 1, 0, -1};
        while (!st.isEmpty()) {
            int cell[] = st.pop();
            int row = cell[0];
            int col = cell[1];
            for (int i = 0; i < 4; i++) {
                int nr = dr[i] + row;
                int nc = dc[i] + col;
                if (nr >= 0 && nr < n && nc >= 0 && nc < m && !vis[nr][nc] && board[nr][nc] == '.') {
                    st.push(new int[]{nr, nc});
                    vis[nr][nc] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        FastScanner sc = new FastScanner();

        int n = sc.nextInt();
        int m = sc.nextInt();
        char board[][] = new char[n][m];
        for (int i = 0; i < n; i++) {
            String s = sc.next();
            for (int j = 0; j < m; j++) {
                board[i][j] = s.charAt(j);
            }
        }
        boolean vis[][] = new boolean[n][m];
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == '.' && !vis[i][j]) {
                    dfs(i, j, n, m, vis, board);
                    count++;
                }
            }
        }
        out.write(String.valueOf(count));
        out.flush();
    }
}
