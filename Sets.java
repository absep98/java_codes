import java.io.*;
import java.util.*;

public class Sets {

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
        int n = sc.nextInt();
        long sum = (long)n*(n+1)/2;

        if(sum%2 == 1){
            out.write("NO");
            out.flush();
            return;
        }
        long target = sum/2;
        List<Integer> first = new ArrayList<>(), second = new ArrayList<>();
        for(int i = n ; i >= 1 ; i--){
            if(target >= i){
                target -= i;
                first.add(i);
            } else {
                second.add(i);
            }
        }
        out.write("YES");
        out.newLine();
        out.write(first.size() + "");
        out.newLine();
        for(int x : first){
            out.write(x + " ");
        }
        out.newLine();

        out.write(second.size() + "");
        out.newLine();
        for(int x : second){
            out.write(x + " ");
        }
        // out.newLine(); // optional: separates even and odd output
        out.flush();
    }
}
