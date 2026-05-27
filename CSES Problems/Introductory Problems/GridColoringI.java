import java.io.*;
import java.util.*;

public class GridColoringI {
    public static final BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

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
            long n = 0; boolean minus = false;
            byte b = readByte();
            if (b == '-') { minus = true; b = readByte(); }
            while ('0' <= b && b <= '9') { n = n * 10 + (b - '0'); b = readByte(); }
            return minus ? -n : n;
        }
    }

    // Process in topological order where each cell depends on (i-1,j) and (i,j-1).
    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int m = sc.nextInt();

        char[][] board = new char[n][m];
        for (int i = 0; i < n; i++) {
            String s = sc.next();
            for (int j = 0; j < m; j++) board[i][j] = s.charAt(j);
        }

        char[] letters = {'A','B','C','D'};
        char[][] ans = new char[n][m];
        for (char[] row : ans) Arrays.fill(row, '.');

        // indegree = number of dependencies (up and left)
        int[][] indeg = new int[n][m];
        ArrayDeque<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int d = 0;
                if (i > 0) d++;
                if (j > 0) d++;
                indeg[i][j] = d;
                if (d == 0) q.add(new int[]{i, j}); // ready to process
            }
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int i = cur[0], j = cur[1];

            // build forbidden: original, up-assigned (if any), left-assigned (if any)
            boolean[] forbidden = new boolean[4];
            forbidden[ board[i][j] - 'A' ] = true;
            if (i > 0) forbidden[ ans[i-1][j] - 'A' ] = true;
            if (j > 0) forbidden[ ans[i][j-1] - 'A' ] = true;

            // pick any allowed letter
            for (int k = 0; k < 4; k++) {
                if (!forbidden[k]) {
                    ans[i][j] = letters[k];
                    break;
                }
            }

            // decrease indegree of dependents: down (i+1,j) and right (i,j+1)
            if (i + 1 < n) {
                indeg[i+1][j]--;
                if (indeg[i+1][j] == 0) q.add(new int[]{i+1, j});
            }
            if (j + 1 < m) {
                indeg[i][j+1]--;
                if (indeg[i][j+1] == 0) q.add(new int[]{i, j+1});
            }
        }

        // Output without spaces
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.setLength(0);
            for (int j = 0; j < m; j++) sb.append(ans[i][j]);
            out.write(sb.toString());
            out.newLine();
        }
        out.flush();
    }
}
