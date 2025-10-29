import java.io.*;
import java.util.*;

public class Pract {
	public static final long MOD = 1000000007;

    static class FastScanner {
        private final InputStream is = System.in;
        private final byte[] buffer = new byte[1 << 16]; // 64 KB buffer
        private int ptr = 0;
        private int buflen = 0;

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

        private static boolean isPrintableChar(byte c) {
            return 33 <= c && c <= 126;
        }

        public boolean hasNext() {
            while (hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++;
            return hasNextByte();
        }

        public String next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            StringBuilder sb = new StringBuilder();
            byte b = readByte();
            while (isPrintableChar(b)) {
                sb.append((char) b);
                b = readByte();
            }
            return sb.toString();
        }

        public char nextChar() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            return (char) readByte();
        }

        public long nextLong() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            long n = 0;
            boolean minus = false;
            byte b = readByte();
            if (b == '-') {
                minus = true;
                b = readByte();
            }
            while ('0' <= b && b <= '9') {
                n = n * 10 + (b - '0');
                b = readByte();
            }
            return minus ? -n : n;
        }

        public int nextInt() {
            return (int) nextLong();
        }
    }

    public static long digits(int n){
        int count = 0;
        while(n != 0){
            int cur = n%10;
            count += cur;
            n /= 10;
        }
        return count;
    }

    public static long solve(int n){
        long sum = 0;
        long p = 1;
        while(p <= n){
            long fullblock = n/(p*10);
            sum += fullblock*45*p;
            long remainder = n%(p*10);
            long current = remainder/p;
            long lower = remainder%p;

            sum += (current * (current-1)/2) * p;
            sum += current * (lower + 1);

            p *= 10;
        }
        return sum;
    }
    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int n = sc.nextInt();
        long ans = solve(n);
        out.write(ans + "");
        // out.newLine(); // optional: separates even and odd output
        out.flush();
    }
}

// Given an integer n, find the sum of digits of all numbers from 1 to n.

// Example:
// Input: n = 15
// Output: 66
// (1+2+3+…+9 + (1+0)+(1+1)+…+(1+5))

// Why this problem:
// It looks like an easy loop, but the optimal version uses pattern-based digit counting (recursion or place-value observation).
// It teaches you to see repetitive patterns instead of iterating.

// Skills built:

// Pattern decomposition

// Recursive thinking

// Recognizing O(log₁₀ n) optimizations

// Hint to think:
// How many times does each digit (0–9) appear in each position (ones, tens, hundreds…)?