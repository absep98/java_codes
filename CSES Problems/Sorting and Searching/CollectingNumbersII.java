import java.io.*;
import java.util.*;

public class CollectingNumbersII {

    static int totalRounds = 1;

    static int checkInversion(int i, int n, int pos[]) {
        if (i < 2 || i > n) return 0;
        return (pos[i] < pos[i - 1]) ? 1 : 0;
    }

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
        int[] arr = new int[n + 1];
        int[] pos = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = sc.nextInt();
            pos[arr[i]] = i;
        }

        for (int i = 2; i <= n; i++) {
            totalRounds += checkInversion(i, n, pos);
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            if (a != b) {
                int x = arr[a];
                int y = arr[b];

                Set<Integer> affectedNums = new HashSet<>();
                affectedNums.add(x);
                affectedNums.add(x + 1);
                affectedNums.add(y);
                affectedNums.add(y + 1);

                for (int num : affectedNums) {
                    totalRounds -= checkInversion(num, n, pos);
                }

                arr[a] = y;
                arr[b] = x;
                pos[x] = b;
                pos[y] = a;

                for (int num : affectedNums) {
                    totalRounds += checkInversion(num, n, pos);
                }
            }
            out.write(String.valueOf(totalRounds));
            out.write('\n');
        }
        out.flush();
    }
}
