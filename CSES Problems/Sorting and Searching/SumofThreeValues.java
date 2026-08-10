import java.io.*;
import java.util.*;

public class SumofThreeValues {

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

    static class Element {
        int value, index;
        Element(int value, int index) { this.value = value; this.index = index; }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        FastScanner sc = new FastScanner();

        int n = sc.nextInt();
        int target = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        Element[] elements = new Element[n];
        for (int i = 0; i < n; i++) {
            elements[i] = new Element(arr[i], i + 1);
        }
        Arrays.sort(elements, (a, b) -> Integer.compare(a.value, b.value));

        for (int i = 0; i < n; i++) {
            if (i > 0 && elements[i].value == elements[i - 1].value) continue;
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                long sum = (long) elements[i].value + elements[j].value + elements[k].value;
                if (sum == target) {
                    out.write(elements[i].index + " " + elements[j].index + " " + elements[k].index);
                    out.flush();
                    return;
                }
                if (sum < target) j++;
                else k--;
            }
        }
        out.write("IMPOSSIBLE");
        out.flush();
    }
}
