import java.io.*;
import java.util.*;

public class StringReorder {

    static class FastScanner {
        private final InputStream is = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, buflen = 0;
        private boolean hasNextByte() { if (ptr < buflen) return true; ptr = 0; try { buflen = is.read(buffer); } catch (IOException e) { e.printStackTrace(); } return buflen > 0; }
        private byte readByte() { return hasNextByte() ? buffer[ptr++] : -1; }
        private static boolean isPrintableChar(byte c) { return 33 <= c && c <= 126; }
        public boolean hasNext() { while (hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++; return hasNextByte(); }
        public String next() { if (!hasNext()) throw new NoSuchElementException(); StringBuilder sb = new StringBuilder(); byte b = readByte(); while (isPrintableChar(b)) { sb.append((char) b); b = readByte(); } return sb.toString(); }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        FastScanner sc = new FastScanner();

        String s = sc.next();
        int[] fq = new int[26];
        for (char ch : s.toCharArray()) {
            fq[ch - 'A']++;
        }
        int n = s.length();
        boolean checkPossible = true;
        for (int i = 0; i < 26; i++) {
            if (fq[i] > (n + 1) / 2) {
                checkPossible = false;
                break;
            }
        }
        if (!checkPossible) {
            out.write("-1");
            out.flush();
            return;
        }
        int remain_len = n;
        int previndx = -1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int max_fq = -1;
            int maxidx = -1;
            int chosenIdx = -1;
            for (int j = 0; j < 26; j++) {
                if (max_fq < fq[j]) {
                    max_fq = fq[j];
                    maxidx = j;
                }
            }
            if (max_fq * 2 > remain_len) {
                if (maxidx == previndx) {
                    out.write("-1");
                    out.flush();
                    return;
                }
                chosenIdx = maxidx;
            } else {
                for (int j = 0; j < 26; j++) {
                    if (fq[j] > 0 && j != previndx) {
                        chosenIdx = j;
                        break;
                    }
                }
            }
            sb.append((char) (chosenIdx + 'A'));
            fq[chosenIdx]--;
            previndx = chosenIdx;
            remain_len--;
        }
        out.write(sb.toString());
        out.flush();
    }
}
