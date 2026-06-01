import java.io.*;
import java.util.*;

public class GridPath {

    static int count = 0;
    static final boolean[] diag1 = new boolean[15];
    static final boolean[] diag2 = new boolean[15];
    static final boolean[] cols = new boolean[8];

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

    public static boolean isBlocked(int r, int c, boolean[][] vis) {
        if (r < 0 || c < 0 || r >= 7 || c >= 7) return true;
        return vis[r][c];
    }

    public static void gridPath(int r, int c, int indx, String s, boolean[][] vis) {
        if (r < 0 || c < 0 || r >= 7 || c >= 7 || vis[r][c]) return;

        if (r == 6 && c == 0) {
            if (indx == s.length()) count++;
            return;
        }
        if (indx == s.length()) return;

        if ((isBlocked(r - 1, c, vis) && isBlocked(r + 1, c, vis)) && (!isBlocked(r, c - 1, vis) && !isBlocked(r, c + 1, vis))) return;
        if ((!isBlocked(r - 1, c, vis) && !isBlocked(r + 1, c, vis)) && (isBlocked(r, c - 1, vis) && isBlocked(r, c + 1, vis))) return;

        int minStepsRequired = Math.abs(6 - r) + Math.abs(0 - c);
        if (minStepsRequired > (s.length() - indx)) return;

        char ch = s.charAt(indx);
        vis[r][c] = true;
        if (ch == 'D') {
            gridPath(r + 1, c, indx + 1, s, vis);
        } else if (ch == 'U') {
            gridPath(r - 1, c, indx + 1, s, vis);
        } else if (ch == 'L') {
            gridPath(r, c - 1, indx + 1, s, vis);
        } else if (ch == 'R') {
            gridPath(r, c + 1, indx + 1, s, vis);
        } else {
            gridPath(r + 1, c, indx + 1, s, vis);
            gridPath(r - 1, c, indx + 1, s, vis);
            gridPath(r, c + 1, indx + 1, s, vis);
            gridPath(r, c - 1, indx + 1, s, vis);
        }
        vis[r][c] = false;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        FastScanner sc = new FastScanner();

        String s = sc.next();
        boolean[][] vis = new boolean[7][7];
        gridPath(0, 0, 0, s, vis);
        out.write(count + "");
        out.flush();
    }
}
