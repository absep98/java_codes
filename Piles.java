import java.io.*;
import java.util.*;

public class Piles {
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

    public static boolean solve(int a, int b, Boolean dp[][]){
    	if(a < 0 || b < 0){
    		return false;
    	}
    	if(dp[a][b] != null){
    		return dp[a][b];
    	}
    	if(a == 0 && b == 0){
    		return true;
    	}
    	if(a != 0 && b == 0 || a == 0 && b != 0){
    		return false;
    	}
    	return dp[a][b] = solve(a-1, b-2, dp) || solve(a-2, b-1, dp);
    }

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int n = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while(n-- > 0){
        	
        	long a = sc.nextLong();
        	long b = sc.nextLong();

        	boolean ans = false;
            if((a + b)%3 == 0 && Math.min(a, b)*2 >= Math.max(a, b)){
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }
        out.write(sb.toString());
        // out.newLine();
        out.flush();
    }
}

/*
You have two coin piles containing a and b coins. On each move, you can either remove one coin from the left pile and two coins from the right pile, or two coins from the left pile and one coin from the right pile.
Your task is to efficiently find out if you can empty both the piles.
Input
The first input line has an integer t: the number of tests.
After this, there are t lines, each of which has two integers a and b: the numbers of coins in the piles.
Output
For each test, print "YES" if you can empty the piles and "NO" otherwise.
Constraints

1 \le t \le 10^5
0 \le a, b \le 10^9

Example
Input:
3
2 1
2 2
3 3

Output:
YES
NO
YES
*/