import java.io.*;
import java.util.*;

public class SumofFourValues {

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

    static class IndexPair {
        int idx1, idx2;
        IndexPair(int idx1, int idx2) { this.idx1 = idx1; this.idx2 = idx2; }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        FastScanner sc = new FastScanner();

        int n = sc.nextInt();
        int target = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        Map<Long, IndexPair> mp = new HashMap<>();

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                long currentsum = (long) arr[i] + arr[j];
                long complement = target - currentsum;
                if (mp.containsKey(complement)) {
                    IndexPair p = mp.get(complement);
                    out.write(p.idx1 + " " + p.idx2 + " " + (i + 1) + " " + (j + 1));
                    out.flush();
                    return;
                }
            }
            for (int k = 0; k < i; k++) {
                long sum = (long) arr[k] + arr[i];
                mp.put(sum, new IndexPair(k + 1, i + 1));
            }
        }
        out.write("IMPOSSIBLE");
        out.flush();
    }
}
