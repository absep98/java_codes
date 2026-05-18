import java.io.*;
import java.util.*;

public class SticksLength {
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
        List<Integer> list = new ArrayList<>();
        for(int i = 0 ; i < n ; i++){
            int a = sc.nextInt();
            list.add(a);
        }
        Collections.sort(list);
        long median = 0;
        if(n%2 == 1){
        	median = list.get(n/2);
        } else {
        	median = list.get(n/2 - 1) + list.get(n/2);
        	median = median/2;
        }
        long cost = 0;
        for(int i = 0 ; i < n ; i++){
        	cost += Math.abs(list.get(i) - median);
        }
      	out.write(cost + "");
        out.flush();
        return;
    }
}

/*

There are n sticks with some lengths. Your task is to modify the sticks so that each stick has the same length.
You can either lengthen and shorten each stick. Both operations cost x where x is the difference between the new and original length.
What is the minimum total cost?
Input
The first input line contains an integer n: the number of sticks.
Then there are n integers: p_1,p_2,\ldots,p_n: the lengths of the sticks.
Output
Print one integer: the minimum total cost.
Constraints

1 \le n \le 2 \cdot 10^5
1 \le p_i \le 10^9

Example
Input:
5
2 3 1 5 2

Output:
5
*/

