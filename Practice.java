import java.io.*;
import java.lang.reflect.Array;
import java.util.*;


public class Practice {

    public static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    public static FastScanner sc = new FastScanner();
    public static int cycleStart = -1;
    public static int cycleEnd = -1;
    public static boolean possible = true;
    public static int mod = 1_000_000_007;
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

   

    public static void RoadConstruction() throws IOException {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int parent[] = new int[n+1];
        
    }

    public static void DiceCombinations() throws IOException {
        int n = sc.nextInt();
        int dp[] = new int[n+1];
        dp[0] = 1;
        for(int i = 1 ; i <= n ; i++) {
        
            for(int j = 1 ; j <= 6 ; j++){
                
                if(i-j >= 0) {
                    dp[i] = (dp[i] + dp[i-j])%1_000_000_007;
                }

            }
        }
        out.write(dp[n] + "");
        return;
    }

    public static void MinimizingCoins() throws IOException {
        int n = sc.nextInt();
        int amount = sc.nextInt();
        int coins[] = new int[n];
        for(int i = 0 ; i < n ; i++) {
            coins[i] = sc.nextInt();
        }
        int dp[][] = new int[n+1][amount+1];
        for(int i = 0 ; i < amount+1 ; i++){
            dp[0][i] = Integer.MAX_VALUE;
        }

        for(int i = 0 ; i < n+1 ; i++) {
            dp[i][0] = 0;
        }
        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= amount ; j++) {
                if(coins[i-1] <= j) {
                    int take = dp[i][j-coins[i-1]] == Integer.MAX_VALUE ? Integer.MAX_VALUE : 1 + dp[i][j-coins[i-1]];
                    dp[i][j] = Math.min(take , dp[i-1][j]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        out.write(String.valueOf(dp[n][amount] == Integer.MAX_VALUE ? -1 : dp[n][amount]));
    }

    public static void CoinCombinationsI() throws IOException {
        int n = sc.nextInt();
        int amount = sc.nextInt();
        int coins[] = new int[n];
        for(int i = 0 ; i < n ; i++) {
            coins[i] = sc.nextInt();
        }
        int dp[] = new int[amount+1];
        dp[0] = 1;
        for(int i = 1 ; i <= amount ; i++) {
            for(int coin : coins) {
                if(coin > i) {
                    break;
                }
                dp[i] = (dp[i] + dp[i-coin])%mod;
            }
        }
        out.write(String.valueOf(dp[amount]));
        return;
    }

    public static void main(String args[]) throws IOException {

        // DiceCombinations();
        // MinimizingCoins();
        CoinCombinationsI();


        out.flush();
    }

}