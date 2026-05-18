import java.io.*;
import java.util.*;

public class NumberSpiral {

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
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        long n = sc.nextLong();
        
        while(n-- > 0){
            long x = sc.nextLong(), y = sc.nextLong();
            long ans = 0;
            if(x < y){
                if(y%2 != 0){
                    ans = y*y - x + 1;
                } else {
                    ans = (y-1)*(y-1) + x;
                }
            } else {
                if(x % 2 != 0){
                    ans = (x-1)*(x-1) + y;
                } else {
                    ans = x*x - y + 1;
                }
            }
            out.write(Long.toString(ans));
            out.newLine();
        }


        // out.newLine(); // optional: separates even and odd output
        out.flush();
    }
}
