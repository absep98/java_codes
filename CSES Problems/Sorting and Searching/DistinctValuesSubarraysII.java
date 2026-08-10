import java.io.*;
import java.util.*;

public class DistinctValuesSubarraysII {

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
        int k = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        Map<Integer, Integer> mp = new HashMap<>();
        int i = 0, j = 0;
        long count = 0;
        while (j < n) {
            mp.put(arr[j], mp.getOrDefault(arr[j], 0) + 1);
            while (mp.size() > k) {
                mp.put(arr[i], mp.get(arr[i]) - 1);
                if (mp.get(arr[i]) == 0) mp.remove(arr[i]);
                i++;
            }
            count += (j - i + 1);
            j++;
        }
        out.write(String.valueOf(count));
        out.flush();
    }
}
