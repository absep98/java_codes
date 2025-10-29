import java.io.*;
import java.util.*;

public class AppleDivison {

	public static final long MOD = 1000000007;
	public static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	public static long diff = Long.MAX_VALUE;
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

   
    public static void solve(int ind, long sumA, long sumB, int arr[]) throws IOException{
    	if(ind == arr.length){
            long currDiff = Math.abs(sumA - sumB);
            diff = Math.min(diff, currDiff);
    		return;
    	}

        solve(ind+1, sumA + arr[ind], sumB, arr);
        // backtrack for 
        solve(ind+1, sumA, arr[ind] + sumB, arr);
        // backtrack for 
    }

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        
       	
        int n = sc.nextInt();
        int arr[] = new int[n];
        for(int i = 0 ; i < n ; i++){
            arr[i] = sc.nextInt();
        }
        solve(0, 0, 0, arr);
        out.write(diff + "");
        // out.newLine();
        out.flush();
    }
}


/*

There are n apples with known weights. Your task is to divide the apples into two groups so that
 the difference between the weights of the groups is minimal.
Input
The first input line has an integer n: the number of apples.
The next line has n integers p_1,p_2,\dots,p_n: the weight of each apple.
Output
Print one integer: the minimum difference between the weights of the groups.
Constraints

1 \le n \le 20
1 \le p_i \le 10^9

Example
Input:
5
3 2 7 4 1

Output:
1

Explanation: Group 1 has weights 2, 3 and 4 (total weight 9), and group 2 has weights 1 and 7 (total weight 8).
    
    
*/