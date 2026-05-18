import java.io.*;
import java.util.*;

public class SubarrayDivisibility {
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
       	int n = sc.nextInt();
        long arr[] = new long[n];
        for(int i = 0 ; i < n ; i++){
            arr[i] = sc.nextLong();
        }
        long sum = 0;
        Map<Long, Integer> mp = new HashMap<>();
        mp.put(0L, 1);
        long count = 0;
        for(int i = 0 ; i < n ; i++){
            sum += arr[i];
            long rem = (((sum%n) + n) % n);
            if(mp.containsKey(rem)){
            	count += mp.get(rem);
            }
            mp.put(rem, mp.getOrDefault(rem, 0) + 1);
            
           	
        }
        out.write(count + "");
        out.flush();
        return;
    }
}

/*
Given an array of n integers, your task is to count the number of subarrays where the sum of values is divisible by n.
Input
The first input line has an integer n: the size of the array.
The next line has n integers a_1,a_2,\dots,a_n: the contents of the array.
Output
Print one integer: the required number of subarrays.
Constraints

1 \le n \le 2 \cdot 10^5
-10^9 \le a_i \le 10^9

Example
Input:
5
3 1 2 7 4

Output:
1
*/