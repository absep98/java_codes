import java.io.*;
import java.util.*;

public class MexGridConstruction {
    public static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    static class FastScanner {
        private final InputStream is = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, buflen = 0;

        private boolean hasNextByte() {
            if (ptr < buflen) return true;
            ptr = 0;
            try { buflen = is.read(buffer); } catch (IOException e) { e.printStackTrace(); }
            return buflen > 0;
        }

        private byte readByte() { return hasNextByte() ? buffer[ptr++] : -1; }

        private static boolean isPrintableChar(byte c) { return 33 <= c && c <= 126; }

        public boolean hasNext() {
            while (hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++;
            return hasNextByte();
        }

        public String next() {
            if (!hasNext()) throw new NoSuchElementException();
            StringBuilder sb = new StringBuilder();
            byte b = readByte();
            while (isPrintableChar(b)) {
                sb.append((char) b);
                b = readByte();
            }
            return sb.toString();
        }

        public int nextInt() { return (int) nextLong(); }

        public long nextLong() {
            if (!hasNext()) throw new NoSuchElementException();
            long n = 0;
            boolean minus = false;
            byte b = readByte();
            if (b == '-') { minus = true; b = readByte(); }
            while ('0' <= b && b <= '9') {
                n = n * 10 + (b - '0');
                b = readByte();
            }
            return minus ? -n : n;
        }
    }

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        StringBuilder outSb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            // build row without trailing space
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (j > 0) row.append(' ');
                row.append(i ^ j);
            }
            outSb.append(row);
            if (i < n - 1) outSb.append('\n');
        }

        out.write(outSb.toString());
        out.flush();
    }
}


/*

Your task is to construct an n \times n grid where each square has the smallest nonnegative integer that does not appear to the left on the same row or above on the same column.
Input
The only line has an integer n.
Output
Print the grid according to the example.
Constraints

1 \le n \le 100

Example
Input:
5

Output:
0 1 2 3 4
1 0 3 2 5
2 3 0 1 6
3 2 1 0 7
4 5 6 7 0

*/