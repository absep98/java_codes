import java.io.*;
import java.util.*;

public class ConcertsTicket {

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
        int[] prices = new int[n];
        for (int i = 0; i < n; i++) prices[i] = sc.nextInt();
        int[] willingPay = new int[m];
        for (int i = 0; i < m; i++) willingPay[i] = sc.nextInt();

        TreeMap<Integer, Integer> fqSet = new TreeMap<>();
        for (int x : prices) {
            fqSet.put(x, fqSet.getOrDefault(x, 0) + 1);
        }

        for (int i = 0; i < m; i++) {
            Integer maxPrc = fqSet.floorKey(willingPay[i]);
            if (maxPrc == null) {
                out.write("-1\n");
            } else {
                out.write(String.valueOf(maxPrc));
                out.write('\n');
                int fq = fqSet.get(maxPrc);
                if (fq == 1) {
                    fqSet.remove(maxPrc);
                } else {
                    fqSet.put(maxPrc, fq - 1);
                }
            }
        }
        out.flush();
    }
}
