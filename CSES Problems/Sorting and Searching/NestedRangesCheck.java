import java.io.*;
import java.util.*;

public class NestedRangesCheck {

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

    static class Range {
        int start, end, orgIndx;
        Range(int s, int e, int o) { this.start = s; this.end = e; this.orgIndx = o; }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        FastScanner sc = new FastScanner();

        int n = sc.nextInt();
        Range[] ranges = new Range[n];
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            ranges[i] = new Range(x, y, i);
        }

        Arrays.sort(ranges, (a, b) -> {
            if (a.start != b.start) return Integer.compare(a.start, b.start);
            return Integer.compare(b.end, a.end);
        });

        int[] contains = new int[n];
        int[] isContained = new int[n];

        int maxend = 0;
        for (int i = 0; i < n; i++) {
            int curend = ranges[i].end;
            if (curend <= maxend) {
                isContained[ranges[i].orgIndx] = 1;
            }
            maxend = Math.max(maxend, curend);
        }

        int minend = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            int curend = ranges[i].end;
            if (curend >= minend) {
                contains[ranges[i].orgIndx] = 1;
            }
            minend = Math.min(minend, curend);
        }

        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb1.append(contains[i]).append(" ");
            sb2.append(isContained[i]).append(" ");
        }
        out.write(sb1.toString().trim() + "\n");
        out.write(sb2.toString().trim());
        out.flush();
    }
}
