import java.io.*;
import java.util.*;

public class MissingCoinSum {
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
        List<Long> list = new ArrayList<>();
        long canFormUpto = 0;
        for(int i = 0 ; i < n ; i++){
            long a = sc.nextLong();
            list.add(a);
        }
        Collections.sort(list);

        for(int i = 0 ; i < n ; i++){
            
            if(list.get(i) > canFormUpto+1){
                break;
            } else {
                canFormUpto += list.get(i);
            }
        }
        out.write((canFormUpto + 1) + "");
        out.flush();
        return;
    }
}

/*

You have n coins with positive integer values. What is the smallest sum you cannot create using a subset of the coins?
Input
The first line has an integer n: the number of coins.
The second line has n integers x_1,x_2,\dots,x_n: the value of each coin.
Output
Print one integer: the smallest coin sum.
Constraints

1 \le n \le 2 \cdot 10^5
1 \le x_i \le 10^9

Example
Input:
5
2 9 1 2 7

Output:
6
*/

