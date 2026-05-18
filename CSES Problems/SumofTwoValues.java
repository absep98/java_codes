

import java.io.*;
import java.util.*;

public class SumofTwoValues {
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
       	int target = sc.nextInt();
        List<Integer> list = new ArrayList<>();
        for(int i = 0 ; i < n ; i++){
            int a = sc.nextInt();
            list.add(a);
        }
        
        Map<Integer, Integer> mp = new HashMap<>();
        long sum = 0;
      	for(int i = 0 ; i < n ; i++){
      		if(mp.containsKey(target-list.get(i))){
      			out.write(mp.get(target-list.get(i)) + 1 + " " + (i+1);
      			out.flush();
      			return;
      		}
      		mp.put(list.get(i), i);
      	}
      	out.write("IMPOSSIBLE");
        out.flush();
        return;
    }
}

/*

You are given an array of n integers, and your task is to find two values (at distinct positions) whose sum is x.
Input
The first input line has two integers n and x: the array size and the target sum.
The second line has n integers a_1,a_2,\dots,a_n: the array values.
Output
Print two integers: the positions of the values. If there are several solutions, you may print any of them. If there are no solutions, print IMPOSSIBLE.
Constraints

1 \le n \le 2 \cdot 10^5
1 \le x,a_i \le 10^9

Example
Input:
4 8
2 7 5 1

Output:
2 4
*/

