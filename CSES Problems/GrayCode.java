import java.io.*;
import java.util.*;

public class GrayCode {
	public static final long MOD = 1000000007;

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

    public static List<String> solve(int n){
    	if(n == 1){
            List<String> list = new ArrayList<>();
    		list.add("0");
            list.add("1");
    		return list;
    	}

        List<String> res = new ArrayList<>();
    	List<String> prev = solve(n-1);

        for(String s : prev){
            res.add("0" + s);
        }
        for(int i = prev.size()-1 ; i >= 0 ; i--){
            res.add("1" + prev.get(i));
        }
        return res;

    }

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        
       	
        int n = sc.nextInt();

        List<String> codes = solve(n);

        for(String s : codes){
            out.write(s + "\n");
        }
        // out.newLine();
        out.flush();
    }
}

// A Gray code is a list of all 2^n bit strings of length n, where any two successive strings differ in exactly one bit (i.e., their Hamming distance is one).
// Your task is to create a Gray code for a given length n.
// Input
// The only input line has an integer n.
// Output
// Print 2^n lines that describe the Gray code. You can print any valid solution.
// Constraints

// 1 \le n \le 16

// Example
// Input:
// 2

// Output:
// 00
// 01
// 11
// 10