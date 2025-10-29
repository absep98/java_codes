import java.io.*;
import java.util.*;

public class RaabGame {
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    static class FastScanner {
        private final InputStream is = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, buflen = 0;

        private boolean hasNextByte() {
            if (ptr < buflen) return true;
            ptr = 0;
            try {
                buflen = is.read(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return buflen > 0;
        }

        private byte readByte() {
            if (hasNextByte()) return buffer[ptr++];
            return -1;
        }

        public boolean hasNext() {
            while (hasNextByte() && buffer[ptr] <= ' ') ptr++;
            return hasNextByte();
        }

        public int nextInt() {
            if (!hasNext()) throw new NoSuchElementException();
            int n = 0;
            boolean neg = false;
            byte b = readByte();
            if (b == '-') {
                neg = true;
                b = readByte();
            }
            while ('0' <= b && b <= '9') {
                n = n * 10 + (b - '0');
                b = readByte();
            }
            return neg ? -n : n;
        }
    }

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();

        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();

            // Handle invalid cases
            if (a + b > n || (a == 0 && b != 0) || (b == 0 && a != 0)) {
                out.write("NO\n");
                continue;
            }

            // Degenerate case: all ties
            if (a == 0 && b == 0) {
                out.write("YES\n");
                for (int i = 1; i <= n; i++) out.write(i + " ");
                out.write("\n");
                for (int i = 1; i <= n; i++) out.write(i + " ");
                out.write("\n");
                continue;
            }

            // Constructive case
            out.write("YES\n");
            int[] xs = new int[n + 1];

            for (int i = 1; i <= a + b; i++) {
                int pos = (i + b - 1) % (a + b) + 1;
                xs[pos] = i;
            }

            for (int i = a + b + 1; i <= n; i++) xs[i] = i;

            // A plays 1..n
            for (int i = 1; i <= n; i++) out.write(i + " ");
            out.write("\n");

            // B plays rotated version
            for (int i = 1; i <= n; i++) out.write(xs[i] + " ");
            out.write("\n");
        }

        out.flush();
    }
}
