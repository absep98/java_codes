
import java.io.*;
import java.util.*;

public class CollectingNumbers {
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
        int pos[] = new int[n+1];
        int count = 1;
        for(int i = 0 ; i < n ; i++){
        	pos[list.get(i)] = i;
        }

        for(int i = 1 ; i < n ; i++){
        	if(pos[i] > pos[i+1]){
        		count++;
        	}
        }
        out.write(count + "");
        out.flush();
        return;
    }
}

/*

You are given an array that contains each number between 1 \dots n exactly once. Your task is to collect the numbers from 1 to n in increasing order.
On each round, you go through the array from left to right and collect as many numbers as possible. What will be the total number of rounds?
Input
The first line has an integer n: the array size.
The next line has n integers x_1,x_2,\dots,x_n: the numbers in the array.
Output
Print one integer: the number of rounds.
Constraints

1 \le n \le 2 \cdot 10^5

Example
Input:
5
4 2 1 5 3

Output:
3
    

*/

