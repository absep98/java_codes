import java.io.*;
import java.util.*;

public class MessageRoute {

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
        int parentNode[] = new int[n + 1];
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        vis[1] = true;
        boolean reached = false;

        while (!q.isEmpty()) {
            int curNode = q.poll();
            if (curNode == n) {
                reached = true;
            }
            for (int ngh : adj.get(curNode)) {
                if (!vis[ngh]) {
                    q.add(ngh);
                    vis[ngh] = true;
                    parentNode[ngh] = curNode;
                }
            }
        }

        if (!reached) {
            out.write("IMPOSSIBLE");
            out.flush();
            return;
        }

        List<Integer> ans = new ArrayList<>();
        int curNode = n;
        while (curNode != 1) {
            ans.add(curNode);
            curNode = parentNode[curNode];
        }
        ans.add(1);
        Collections.reverse(ans);

        out.write(ans.size() + "\n");
        for (int x : ans) {
            out.write(x + " ");
        }
        out.flush();
    }
}
