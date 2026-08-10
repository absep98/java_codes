import java.io.*;
import java.util.*;

public class Monsters {

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

    static class Cell {
        int row, col;
        Cell(int r, int c) { this.row = r; this.col = c; }
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

        int monsterTime[][] = new int[n][m];
        int playerTime[][] = new int[n][m];
        for (int arr[] : monsterTime) Arrays.fill(arr, (int) 1e9);

        Queue<Cell> monstersQ = new LinkedList<>();
        Queue<Cell> playerQ = new LinkedList<>();
        int startR = -1, startC = -1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'M') {
                    monstersQ.add(new Cell(i, j));
                    monsterTime[i][j] = 0;
                }
                if (board[i][j] == 'A') {
                    playerQ.add(new Cell(i, j));
                    playerTime[i][j] = 0;
                    startR = i;
                    startC = j;
                }
            }
        }

        if (startR == 0 || startR == n - 1 || startC == 0 || startC == m - 1) {
            out.write("YES\n0");
            out.flush();
            return;
        }

        int dr[] = {-1, 0, 1, 0};
        int dc[] = {0, 1, 0, -1};
        while (!monstersQ.isEmpty()) {
            Cell t = monstersQ.poll();
            for (int i = 0; i < 4; i++) {
                int nr = t.row + dr[i];
                int nc = t.col + dc[i];
                if (nr >= 0 && nr < n && nc >= 0 && nc < m && board[nr][nc] != '#' && monsterTime[nr][nc] == (int) 1e9) {
                    monsterTime[nr][nc] = monsterTime[t.row][t.col] + 1;
                    monstersQ.add(new Cell(nr, nc));
                }
            }
        }

        boolean fd = false;
        char moveFrom[][] = new char[n][m];
        int endR = -1, endC = -1;
        char dirs[] = {'U', 'R', 'D', 'L'};

        while (!playerQ.isEmpty()) {
            Cell t = playerQ.poll();
            if ((t.row == 0 || t.row == n - 1) || (t.col == 0 || t.col == m - 1)) {
                fd = true;
                endR = t.row;
                endC = t.col;
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nr = t.row + dr[i];
                int nc = t.col + dc[i];
                if (nr >= 0 && nr < n && nc >= 0 && nc < m && board[nr][nc] != '#' && playerTime[nr][nc] == 0 && playerTime[t.row][t.col] + 1 < monsterTime[nr][nc]) {
                    playerTime[nr][nc] = playerTime[t.row][t.col] + 1;
                    moveFrom[nr][nc] = dirs[i];
                    playerQ.add(new Cell(nr, nc));
                }
            }
        }

        if (!fd) {
            out.write("NO");
        } else {
            out.write("YES\n");
            List<Character> path = new ArrayList<>();
            while (!(endR == startR && endC == startC)) {
                char move = moveFrom[endR][endC];
                path.add(move);
                if (move == 'U') endR++;
                else if (move == 'D') endR--;
                else if (move == 'R') endC--;
                else if (move == 'L') endC++;
            }
            Collections.reverse(path);
            StringBuilder sb = new StringBuilder();
            for (char c : path) sb.append(c);
            out.write(path.size() + "\n");
            out.write(sb.toString());
        }
        out.flush();
    }
}
