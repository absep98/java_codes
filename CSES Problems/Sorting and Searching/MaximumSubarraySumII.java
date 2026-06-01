import java.io.*;
import java.util.*;

public class MaximumSubarraySumII {
    public static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));


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


    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
       	int N = sc.nextInt();
       	int A = sc.nextInt();
       	int B = sc.nextInt();
        long arr[] = new long[N];
        for(int i = 0 ; i < N ; i++){
            arr[i] = sc.nextLong();
        }
        

        Deque<Integer> dq = new ArrayDeque<>();

        long[] prefixSum = new long[N + 1];

        long ans = Long.MIN_VALUE;

        for (int i = 1; i <= N; i++) {
            prefixSum[i] += prefixSum[i - 1] + arr[i - 1];
        }

        for (int i = 1; i < B; i++) {
            while (!dq.isEmpty() && prefixSum[dq.peekFirst()] <= prefixSum[i]) {
                dq.pollFirst();
            }
            dq.addFirst(i);
        }

        for (int i = 0; i <= (N - A); i++) {
            while (i + B <= N && !dq.isEmpty() && prefixSum[dq.peekFirst()] <= prefixSum[i + B]) {
                dq.pollFirst();
            }

            if (i + B <= N)
                dq.addFirst(i + B);

            while (!dq.isEmpty() && dq.peekLast() < (A + i)) {
                dq.pollLast();
            }

            ans = Math.max(ans, prefixSum[dq.peekLast()] - prefixSum[i]);
        }
        out.write(ans + "");
        out.flush();
        return;
    }
}

/*

Given an array of n integers, your task is to find the maximum sum of values in a contiguous subarray with length between a and b.
Input
The first input line has three integers n, a and b: the size of the array and the minimum and maximum subarray length.
The second line has n integers x_1,x_2,\dots,x_n: the array values.
Output
Print one integer: the maximum subarray sum.
Constraints

1 \le n \le 2 \cdot 10^5
1 \le a \le b \le n
-10^9 \le x_i \le 10^9

Example
Input:
8 1 2
-1 3 -2 5 3 -5 2 2

Output:
8

*/