import java.io.*;
import java.util.*;

public class ShortestPathII {
    static final long INF = (long)1e18;

    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner();
        StringBuilder sb = new StringBuilder();

        int n = fs.nextInt();
        int m = fs.nextInt();
        int q = fs.nextInt();

        long[][] dist = new long[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            int a = fs.nextInt(), b = fs.nextInt();
            long c = fs.nextLong();
            if (c < dist[a][b]) {
                dist[a][b] = c;
                dist[b][a] = c;
            }
        }

        // Floyd–Warshall
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                if (dist[i][k] == INF) continue;
                for (int j = 1; j <= n; j++) {
                    long nd = dist[i][k] + dist[k][j];
                    if (nd < dist[i][j]) dist[i][j] = nd;
                }
            }
        }

        for (int i = 0; i < q; i++) {
            int a = fs.nextInt(), b = fs.nextInt();
            long ans = dist[a][b];
            sb.append(ans == INF ? -1 : ans).append('\n');
        }

        System.out.print(sb);
    }

    // fast input
    static class FastScanner {
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        private final InputStream in = System.in;

        private int readByte() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        long nextLong() throws IOException {
            int c;
            do { c = readByte(); } while (c <= ' ');
            boolean neg = false;
            if (c == '-') { neg = true; c = readByte(); }
            long val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return neg ? -val : val;
        }

        int nextInt() throws IOException { return (int) nextLong(); }
    }
}
