import java.io.*;
import java.lang.reflect.Array;
import java.util.*;


public class JosephusProblemII {

    public static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int totalRounds = 1;


    static class FastScanner {
        private final InputStream is = System.in;
        private final byte[] buffer = new byte[1 << 16]; // 64 KB buffer
        private int ptr = 0;
        private int buflen = 0;

        private boolean hasNextByte() {
            if (ptr < buflen)
                return true;
            ptr = 0;
            try {
                buflen = is.read(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return buflen > 0;
        }

        private byte readByte() {
            if (hasNextByte())
                return buffer[ptr++];
            return -1;
        }

        private static boolean isPrintableChar(byte c) {
            return 33 <= c && c <= 126;
        }

        public boolean hasNext() {
            while (hasNextByte() && !isPrintableChar(buffer[ptr]))
                ptr++;
            return hasNextByte();
        }

        public String next() {
            if (!hasNext())
                throw new java.util.NoSuchElementException();
            StringBuilder sb = new StringBuilder();
            byte b = readByte();
            while (isPrintableChar(b)) {
                sb.append((char) b);
                b = readByte();
            }
            return sb.toString();
        }

        public char nextChar() {
            if (!hasNext())
                throw new java.util.NoSuchElementException();
            return (char) readByte();
        }

        public long nextLong() {
            if (!hasNext())
                throw new java.util.NoSuchElementException();
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


    public static void update(int idx, int delta, int n, int[] tree) {
        for (; idx <= n; idx += idx & -idx) tree[idx] += delta;
    }

    public static int query(int idx, int[] tree) {
        int sum = 0;
        for (; idx > 0; idx -= idx & -idx) sum += tree[idx];
        return sum;
    }

    public static void solve(int n, int k) throws IOException{
        int tree[] = new int[n+1];

        // Mark every chair from 1 to n as occupied (value 1)
        for (int i = 1; i <= n; i++) {
            update(i, 1, n, tree);
        }

        int currentPos = 0;
        StringBuilder sb = new StringBuilder();

        for (int remaining = n; remaining > 0; remaining--) {
            // Find the absolute rank of the next child to eliminate
            currentPos = (currentPos + k) % remaining;
            
            // Convert to 1-based rank for our tree logic
            int targetRank = currentPos + 1;

            // Use binary search to find the physical chair index matching that rank
            int victimChair = findKth(targetRank, n, tree);

            // Save this victim's chair to our output string
            sb.append(victimChair).append(" ");

            // Eliminate the child from the chair! (Add -1 to set it to 0)
            update(victimChair, -1, n, tree);
        }

        out.write(sb.toString().trim());

    }

    // This should return the 1-based chair index of the child with the given rank
    static int findKth(int target_rank, int n, int[] tree) {
        int low = 1, high = n, ans = n;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (query(mid, tree) >= target_rank) {
                ans = mid;
                high = mid - 1; // Try to find an even smaller index
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String args[]) throws IOException{
        
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int k = sc.nextInt();
        solve(n, k);
        out.flush();
    }
}