import java.io.*;
import java.util.*;

public class HighScore {

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

    static class Node {
        int a, b;
        long distance;
        Node(int a, int b, long d) { this.a = a; this.b = b; this.distance = d; }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        FastScanner sc = new FastScanner();

        int n = sc.nextInt();
        int m = sc.nextInt();
        List<Node> edges = new ArrayList<>();
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            long c = -sc.nextLong();
            edges.add(new Node(a, b, c));
            adj.get(b).add(a);
        }

        long dist[] = new long[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0;

        for (int i = 0; i < n - 1; i++) {
            for (Node curNode : edges) {
                int a = curNode.a;
                int b = curNode.b;
                long c = curNode.distance;
                if (dist[a] != Long.MAX_VALUE && dist[a] + c < dist[b]) {
                    dist[b] = dist[a] + c;
                }
            }
        }

        boolean bad[] = new boolean[n + 1];
        for (Node curNode : edges) {
            int a = curNode.a;
            int b = curNode.b;
            long c = curNode.distance;
            if (dist[a] != Long.MAX_VALUE && dist[a] + c < dist[b]) {
                bad[b] = true;
            }
        }

        boolean canReachN[] = new boolean[n + 1];
        canReachN[n] = true;
        Queue<Integer> q = new LinkedList<>();
        q.add(n);
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int ngh : adj.get(cur)) {
                if (!canReachN[ngh]) {
                    canReachN[ngh] = true;
                    q.add(ngh);
                }
            }
        }

        boolean hasInfiniteCycle = false;
        for (int i = 1; i <= n; i++) {
            if (bad[i] && canReachN[i]) {
                hasInfiniteCycle = true;
                break;
            }
        }

        if (hasInfiniteCycle) {
            out.write("-1");
        } else {
            out.write(String.valueOf(-dist[n]));
        }
        out.flush();
    }
}
