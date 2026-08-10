import java.io.*;
import java.util.*;

public class ShortestRoutesII {

    static class FastScanner {
        private final InputStream is = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, buflen = 0;
        private boolean hasNextByte() { if (ptr < buflen) return true; ptr = 0; try { buflen = is.read(buffer); } catch (IOException e) { e.printStackTrace(); } return buflen > 0; }
        private byte readByte() { return hasNextByte() ? buffer[ptr++] : -1; }
        private static boolean isPrintableChar(byte c) { return 33 <= c && c <= 126; }
        public boolean hasNext() { while (hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++; return hasNextByte(); }
        public long nextLong() { if (!hasNext()) throw new NoSuchElementException(); long n = 0; boolean minus = false; byte b = readByte(); if (b == '-') { minus = true; b = readByte(); } while ('0' <= b && b <= '9') { n = n * 10 + (b - '0'); b = readByte(); } return minus ? -n : n; }
        public int nextInt() { return (int) nextLong(); }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        FastScanner sc = new FastScanner();

        int n = sc.nextInt();
        int m = sc.nextInt();
        int q = sc.nextInt();

        long dist[][] = new long[n + 1][n + 1];
        for (long[] cur : dist) {
            Arrays.fill(cur, Long.MAX_VALUE);
        }

        for (int i = 1; i <= n; i++) {
            dist[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            long c = sc.nextLong();
            dist[a][b] = Math.min(dist[a][b], c);
            dist[b][a] = Math.min(dist[b][a], c);
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dist[i][k] != Long.MAX_VALUE && dist[k][j] != Long.MAX_VALUE) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        while (q-- > 0) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            if (dist[a][b] != Long.MAX_VALUE) {
                out.write(dist[a][b] + "\n");
            } else {
                out.write("-1\n");
            }
        }
        out.flush();
    }
}
