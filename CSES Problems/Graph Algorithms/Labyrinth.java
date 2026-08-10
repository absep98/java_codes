import java.io.*;
import java.util.*;

public class Labyrinth {

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
        int stx = -1, sty = -1;
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'A') {
                    stx = i;
                    sty = j;
                    q.add(new int[]{i, j});
                    break;
                }
            }
        }

        char parentMove[][] = new char[n][m];
        if (stx != -1) {
            vis[stx][sty] = true;
        } else {
            out.write("NO");
            out.flush();
            return;
        }

        int dr[] = {-1, 0, 1, 0};
        int dc[] = {0, 1, 0, -1};
        char dirs[] = {'U', 'R', 'D', 'L'};
        String ans = null;

        while (!q.isEmpty()) {
            int cell[] = q.poll();
            int row = cell[0];
            int col = cell[1];

            if (board[row][col] == 'B') {
                List<Character> path = new ArrayList<>();
                int curR = row, curC = col;
                while (board[curR][curC] != 'A') {
                    char move = parentMove[curR][curC];
                    path.add(move);
                    if (move == 'U') curR++;
                    else if (move == 'D') curR--;
                    else if (move == 'R') curC--;
                    else if (move == 'L') curC++;
                }
                Collections.reverse(path);
                StringBuilder sb = new StringBuilder();
                for (char c : path) sb.append(c);
                ans = sb.toString();
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nr = row + dr[i];
                int nc = col + dc[i];
                if (nr >= 0 && nr < n && nc >= 0 && nc < m && !vis[nr][nc] && board[nr][nc] != '#') {
                    vis[nr][nc] = true;
                    q.add(new int[]{nr, nc});
                    parentMove[nr][nc] = dirs[i];
                }
            }
        }

        if (ans != null) {
            out.write("YES\n");
            out.write(ans.length() + "\n");
            out.write(ans);
        } else {
            out.write("NO");
        }
        out.flush();
    }
}
