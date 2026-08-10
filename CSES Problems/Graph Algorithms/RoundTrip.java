import java.io.*;
import java.util.*;

public class RoundTrip {

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

    static int cycleStart = -1, cycleEnd = -1;

    public static boolean dfs(int node, ArrayList<ArrayList<Integer>> adj, boolean vis[], int parent[]) {
        vis[node] = true;
        for (int ngh : adj.get(node)) {
            if (ngh == parent[node]) continue;
            if (!vis[ngh]) {
                parent[ngh] = node;
                if (dfs(ngh, adj, vis, parent)) {
                    return true;
                }
            } else {
                cycleStart = ngh;
                cycleEnd = node;
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        FastScanner sc = new FastScanner();

        int n = sc.nextInt();
        int m = sc.nextInt();
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        boolean vis[] = new boolean[n + 1];
        int parent[] = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (!vis[i]) {
                if (dfs(i, adj, vis, parent)) {
                    break;
                }
            }
        }

        if (cycleStart == -1) {
            out.write("IMPOSSIBLE");
        } else {
            List<Integer> path = new ArrayList<>();
            path.add(cycleStart);
            for (int v = cycleEnd; v != cycleStart; v = parent[v]) {
                path.add(v);
            }
            path.add(cycleStart);
            Collections.reverse(path);
            if (path.size() >= 3) {
                out.write(path.size() + "\n");
                for (int x : path) {
                    out.write(x + " ");
                }
            } else {
                out.write("IMPOSSIBLE");
            }
        }
        out.flush();
    }
}
