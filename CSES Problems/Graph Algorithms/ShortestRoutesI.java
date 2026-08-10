import java.io.*;
import java.util.*;

public class ShortestRoutesI {

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

    static class Edge {
        int to;
        long weight;
        Edge(int to, long weight) { this.to = to; this.weight = weight; }
    }

    static class State {
        int node;
        long distance;
        State(int node, long distance) { this.node = node; this.distance = distance; }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        FastScanner sc = new FastScanner();

        int n = sc.nextInt();
        int m = sc.nextInt();
        ArrayList<ArrayList<Edge>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            long c = sc.nextLong();
            adj.get(a).add(new Edge(b, c));
        }

        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingLong(s -> s.distance));
        long dist[] = new long[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0;
        pq.add(new State(1, 0L));

        while (!pq.isEmpty()) {
            State cur = pq.poll();
            if (cur.distance != dist[cur.node]) continue;
            for (Edge edge : adj.get(cur.node)) {
                long newDist = dist[cur.node] + edge.weight;
                if (newDist < dist[edge.to]) {
                    dist[edge.to] = newDist;
                    pq.add(new State(edge.to, newDist));
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            out.write(dist[i] + " ");
        }
        out.flush();
    }
}
