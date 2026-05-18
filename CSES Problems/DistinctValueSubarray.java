import java.io.*;
import java.util.*;

public class DistinctValueSubarray {
    public static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    public static final List<List<Long>> ans = new ArrayList<>();

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
       	
        long arr[] = new long[N];
        for(int i = 0 ; i < N ; i++){
            arr[i] = sc.nextLong();
        }
        
        long count = 0;
        Map<Long, Integer> mp = new HashMap<>();
        int i = 0, j = 0;
        while(j < N){
            while(mp.getOrDefault(arr[j], 0) > 0){
                int fq = mp.get(arr[i]);
                if(fq > 0){
                    mp.put(arr[i], fq-1);
                } else {
                    mp.remove(arr[i]);
                }
                i++;
            }
            mp.put(arr[j], mp.getOrDefault(arr[j], 0) + 1);
            count += (j-i+1);
            j++;
        }

        out.write(count + "");
        out.flush();
        return;
    }
}

/*

Given an array of n integers, count the number of subarrays where each element is dictinct.
Input
The first line has an integer n: the array size.
The second line has n integers x_1,x_2,\dots,x_n: the array contents.
Output
Print the number of subarrays with distinct elements.
Constraints

1 \le n \le 2 \cdot 10^5
1 \le x_i \le 10^9

Example
Input:
4
1 2 1 3

Output:
8

Explanation: The subarrays are [1] (two times), [2], [3], [1,2], [1,3], [2,1] and [2,1,3].

*/