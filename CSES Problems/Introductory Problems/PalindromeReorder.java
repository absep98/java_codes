import java.io.*;
import java.util.*;

public class PalindromeReorder {

    static class FastScanner {
        private final InputStream is = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, buflen = 0;
        private boolean hasNextByte() { if (ptr < buflen) return true; ptr = 0; try { buflen = is.read(buffer); } catch (IOException e) { e.printStackTrace(); } return buflen > 0; }
        private byte readByte() { return hasNextByte() ? buffer[ptr++] : -1; }
        private static boolean isPrintableChar(byte c) { return 33 <= c && c <= 126; }
        public boolean hasNext() { while (hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++; return hasNextByte(); }
        public String next() { if (!hasNext()) throw new NoSuchElementException(); StringBuilder sb = new StringBuilder(); byte b = readByte(); while (isPrintableChar(b)) { sb.append((char) b); b = readByte(); } return sb.toString(); }
        public long nextLong() { if (!hasNext()) throw new NoSuchElementException(); long n = 0; boolean minus = false; byte b = readByte(); if (b == '-') { minus = true; b = readByte(); } while ('0' <= b && b <= '9') { n = n * 10 + (b - '0'); b = readByte(); } return minus ? -n : n; }
        public int nextInt() { return (int) nextLong(); }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        FastScanner sc = new FastScanner();

        String s = sc.next();
        StringBuilder left = new StringBuilder();
        char mid = 0;
        int[] fq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            fq[s.charAt(i) - 'A']++;
        }
        int count = 0;
        for (int i = 0; i < 26; i++) {
            if (fq[i] % 2 != 0) count++;
        }
        if (count > 1) {
            out.write("NO SOLUTION");
            out.flush();
            return;
        }
        for (int i = 0; i < 26; i++) {
            char ch = (char) (i + 'A');
            for (int j = 0; j < fq[i] / 2; j++) {
                left.append(ch);
            }
            if (fq[i] % 2 == 1) {
                mid = ch;
            }
        }
        out.write(left.toString() + (mid == 0 ? "" : mid) + left.reverse().toString());
        out.flush();
    }
}
