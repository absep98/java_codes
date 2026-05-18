import java.io.*;
import java.util.*;

public class MovieFest {
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
        List<long[]> list = new ArrayList<>();
        for(int i = 0 ; i < n ; i++){
            long a = sc.nextLong();
            long b = sc.nextLong();

            list.add(new long[]{a, b});
        }
        
        Collections.sort(list, (x, y) -> (x[1] == y[1]) ? Long.compare(x[0], y[0]) : Long.compare(x[1], y[1]));

        long first[] = list.get(0);
        long ft = first[0];
        long sec = first[1];
        int max_cust = 1;
        int curr = 1;
        for(int i = 1 ; i < list.size() ; i++){
            if(sec <= list.get(i)[0]){
                curr += 1;
                max_cust = Math.max(max_cust, curr);
                sec = list.get(i)[1];
            }
        }


        out.write(max_cust + "");
        out.flush();
    }
}

/*

In a movie festival n movies will be shown. You know the starting and ending time of each movie. What is the maximum number of movies you can watch entirely?
Input
The first input line has an integer n: the number of movies.
After this, there are n lines that describe the movies. Each line has two integers a and b: the starting and ending times of a movie.
Output
Print one integer: the maximum number of movies.
Constraints

1 \le n \le 2 \cdot 10^5
1 \le a < b \le 10^9

Example
Input:
3
3 5
4 9
5 8

Output:
2

*/

