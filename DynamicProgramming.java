import java.io.*;
import java.lang.reflect.Array;
import java.util.*;


class Project implements Comparable<Project> {
    int start;
    int end;
    int reward;
    public Project(int st, int ed, int rwd) {
        this.start = st;
        this.end = ed;
        this.reward = rwd;
    }

    @Override
    public int compareTo(Project other) {
        return Integer.compare(this.end, other.end);
    }
}
public class DynamicProgramming {

    public static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    public static FastScanner sc = new FastScanner();

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


    public static void DiceCombinations(int n) throws IOException {
        if(n <=  0){
            out.write(String.valueOf(n));
            return;
        }
        int dp[] = new int[n+1];
        int mod = 1000000007;
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2 ; i <= n ; i++){
            for(int j = 1 ; j <= 6 ; j++){
                if(i - j >= 0){
                    dp[i] = (dp[i-j] + dp[i])%mod;
                }
            }
        }
        out.write(String.valueOf(dp[n]));
        return;
    }

    public static void MinimizingCoins(int n, int amount, int coins[]) throws IOException {

        int dp[][] = new int[n+1][amount+1];

        for(int i = 0 ; i < amount+1 ; i++){
            dp[0][i] = Integer.MAX_VALUE;
        }

        for(int i = 0 ; i < n+1 ; i++){
            dp[i][0] = 0;
        }

        for(int i = 1 ; i < n+1 ; i++) {
            for(int j = 1 ; j < amount+1 ; j++) {
                if(coins[i-1] <= j){
                    int take = dp[i][j-coins[i-1]] == Integer.MAX_VALUE ? Integer.MAX_VALUE : 1 + dp[i][j-coins[i-1]];
                    dp[i][j] = Math.min(take, dp[i-1][j]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        out.write(String.valueOf(dp[n][amount] == Integer.MAX_VALUE ? -1 : dp[n][amount]));
    }

    public static void CoinCombinationsI(int n, int amount, int coins[]) throws IOException {
        int dp[] = new int[amount+1];
        int mod = 1000000007;
        // Base case: There is exactly 1 way to make a sum of 0 (using an empty set)

        // For performance
        Arrays.sort(coins);
        dp[0] = 1;
        for(int i = 1 ; i < amount+1 ; i++){
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

    public static void CoinCombinationsII(int n, int amount, int coins[]) throws IOException {
        int dp[] = new int[amount+1];
        int mod = 1000000007;
        dp[0] = 1;
        
        for(int coin : coins){
           for(int j = coin ; j <= amount ; j++){
                dp[j] = (dp[j] + dp[j - coin])%mod;
           }
        }

        out.write(String.valueOf(dp[amount]%mod));
        return;
    }
    
    public static void RemovingDigits(int n) throws IOException {
        int dp[] = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for(int i = 1 ; i <= n ; i++){
            int cur = i;
            while(cur != 0){
                int lastDg = cur%10;
                if(lastDg != 0){
                    dp[i] = Math.min(dp[i], 1 + dp[i-lastDg]);
                }
                cur = cur/10;
            }
        }
        
        out.write(String.valueOf(dp[n]));
        return;
    }
    
    public static void GridPathsI() throws IOException {
        int n = sc.nextInt();
        // read more input as needed
        char board[][] = new char[n][n];
        for(int i = 0 ; i < n ; i++){
            String s = sc.next();
            for(int j = 0 ; j < s.length() ; j++){
                board[i][j] = s.charAt(j);
            }
        }

        int dp[][] = new int[n][n];
        dp[0][0] = (board[0][0] == '*') ? 0 : 1;
        int mod = 1000000007;

        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                if(board[i][j] == '*'){
                    dp[i][j] = 0;
                } else if(i == 0 && j == 0){
                    dp[i][j] = 1;
                } else {
                    int up = (i > 0) ? dp[i-1][j] : 0;
                    int left = (j > 0) ? dp[i][j-1] : 0;
                    dp[i][j] = (up + left)%mod;
                }
            }
        }
        out.write(String.valueOf(dp[n-1][n-1]));
        return;
    }

    public static void BookShop() throws IOException {
        int n = sc.nextInt();
        int P = sc.nextInt();

        int price[] = new int[n];
        for(int i = 0 ; i < n ; i++){
            price[i] = sc.nextInt();
        }

        int pages[] = new int[n];
        for(int i = 0 ; i < n ; i++){
            pages[i] = sc.nextInt();
        }

        int dp[][] = new int[n+1][P+1];
        for(int i = 1 ; i < n+1 ; i++){
            for(int j = 1 ; j < P+1 ; j++){
                if(price[i-1] <= j){
                    dp[i][j] = Math.max(pages[i-1] + dp[i-1][j-price[i-1]], dp[i-1][j]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        
        out.write(String.valueOf(dp[n][P]));
    }

    public static void ArrayDescription() throws IOException {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int arr[] = new int[n];

        for(int i = 0 ; i < n ; i++){
            arr[i] = sc.nextInt();
        }

        long dp[][] = new long[n+1][m+1];
        if(arr[0] == 0) {
            for(int i = 1 ; i < m+1 ; i++) {
                dp[0][i] = 1;
            }
        } else {
            dp[0][arr[0]] = 1;
        }

        long mod = 1000000007;

        for(int i = 1 ; i < n ; i++){
            for(int v = 1 ; v <= m ; v++){
                if(arr[i] != 0 && arr[i] != v){
                    continue;
                }
                long lower = (v > 1) ? dp[i-1][v-1] : 0L;
                long upper = (v < m) ? dp[i-1][v+1] : 0L;
                dp[i][v] =  (dp[i-1][v] + lower + upper)%mod;
            }
        }
        long totalPaths = 0;
        for(int i = 0 ; i <= m ; i++){
            totalPaths = (totalPaths + dp[n-1][i])%mod;
        }
        out.write(String.valueOf(totalPaths));
        return;
    }

    public static void LongestCommonSubsequence() throws IOException {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int nums1[] = new int[n];
        int nums2[] = new int[m];

        for(int i = 0 ; i < n ; i++){
            nums1[i] = sc.nextInt();
        }

        for(int i = 0 ; i < m ; i++){
            nums2[i] = sc.nextInt();
        }
        
        int dp[][] = new int[n+1][m+1];
        for(int i = 1 ; i < n+1 ; i++){
            for(int j = 1 ; j < m+1 ; j++){
                if(nums1[i-1] == nums2[j-1]){
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        int i = n;
        int j = m;
        Stack<Integer> ans = new Stack<>();
        while(i > 0 && j > 0) {
            if(nums1[i-1] == nums2[j-1]) {
                ans.push(nums1[i-1]);
                i--;
                j--;
            } else {
                if(dp[i][j-1] > dp[i-1][j]){
                    j--;
                } else {
                    i--;
                }
            }
        }

        out.write(String.valueOf(dp[n][m]) + "\n");
        while (!ans.isEmpty()) {
            int val = ans.pop();
            out.write(String.valueOf(val) + " ");
        }
        return;
    }

    public static void EditDistance() throws IOException {
        String s1 = sc.next();
        String s2 = sc.next();
        
        int n = s1.length();
        int m = s2.length();

        int dp[][] = new int[n+1][m+1];

        for(int i = 0 ; i <= n ; i++){
            dp[i][0] = i;
        }

        for(int i = 0 ; i <= m ; i++){
            dp[0][i] = i;
        }

        for(int i = 1 ; i < n+1 ; i++){
            for(int j = 1 ; j < m+1 ; j++){
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(dp[i][j-1], Math.min(dp[i-1][j], dp[i-1][j-1])) + 1;
                }
            }
        }
        out.write(String.valueOf(dp[n][m]));
        return;
    }

    public static void MoneySums() throws IOException {
        int n = sc.nextInt();
        int arr[] = new int[n];
        for(int i = 0 ; i < n ; i++){
            arr[i] = sc.nextInt();
        }

        int sum = 0;
        for(int x : arr){
            sum += x;
        }
        boolean dp[] = new boolean[sum+1];
        dp[0] = true;
        for(int i = 0 ; i < n ; i++){
            int coin = arr[i];
            for(int j = sum ; j >= coin ; j--){
                if(dp[j - coin]) {
                    dp[j] = true;
                }
            }
        }
        int distinctSumsCount = 0;
        for (int i = 1; i <= sum; i++) {
            if (dp[i]) {
                distinctSumsCount++;
            }
        }
        out.write(String.valueOf(distinctSumsCount) + "\n");
        for(int i = 1 ; i < sum+1 ; i++){
            if(dp[i]){
                out.write(String.valueOf(i) + " ");
            }
        }
        return;
    }

    public static void TwoSetsII() throws IOException {
        int n = sc.nextInt();
        int arr[] = new int[n];
        for(int i = 0 ; i < n ; i++){
            arr[i] = i+1;
        }
        int sum = n * (n+1)/2;
        if(sum % 2 == 1){
            out.write(String.valueOf(0));
            return;
        }
        int target = sum/2;
        int dp[] = new int[target+1];
        dp[0] = 1;
        int mod = 1000000007;
        for(int i = 1 ; i < n ; i++){
            for(int j = target ; j >= i ; j--){
                dp[j] = (dp[j] + dp[j-i])%mod;
            }
        }
        out.write(String.valueOf(dp[target]));
    }

    public static void RectangleCutting() throws IOException {
        int n = sc.nextInt();
        int m = sc.nextInt();

        int dp[][] = new int[n+1][m+1];
        for(int arr[] : dp){
            Arrays.fill(arr, Integer.MAX_VALUE);
        }
        int tot = 0;
        for(int i = 1 ; i < n+1 ; i++){
            for(int j = 1 ; j < m+1 ; j++){
                if(i == j){
                    dp[i][j] = 0;
                }
                else {
                    for(int k = 1 ; k < i ; k++) {
                        int cost = dp[k][j] + dp[i-k][j] + 1;
                        dp[i][j] = Math.min(dp[i][j], cost);
                    }
                    for(int k = 1 ; k < j ; k++) {
                        int cost = dp[i][k] + dp[i][j-k] + 1;
                        dp[i][j] = Math.min(dp[i][j], cost);
                    }
                }
            }
        }
        out.write(String.valueOf(dp[n][m]));
        return;
    }

    public static int solveLIS(int cur, int prev, int arr[]) throws IOException {
        if(cur == arr.length) {
            return 0;
        }
        int take = 0;
        if(prev == -1 || arr[cur] > arr[prev]){
            take = 1 + solveLIS(cur+1, cur, arr);
        }
        int skip = solveLIS(cur+1, prev, arr);

        return Math.max(take, skip);
    }

    public static void IncreasingSubsequence() throws IOException {
        int n = sc.nextInt();
        int arr[] = new int[n];
        for(int i = 0 ; i < n ; i++){
            arr[i] = sc.nextInt();
        }
        // int dp[][] = new int[n+1][n+1];
        // for(int ind = n-1 ; ind >= 0 ; ind--){
        //     for(int prevInd = ind-1 ; prevInd >= -1 ; prevInd--){
        //         int len = 0 + dp[ind+1][prevInd+1];
        //         if(prevInd == -1 || arr[ind] > arr[prevInd]){
        //             len = Math.max(len, 1 + dp[ind+1][ind+1]);
        //         }
        //         dp[ind][prevInd+1] = len;
        //     }
        // }

        int tails[] = new int[n];
        int len = 0;
        for(int x  : arr) {
            int low = 0;
            int high = len-1;
            int index = len;
            while(low <= high) {
                int mid = low + (high-low)/2;
                if(tails[mid] >= x){
                    index = mid;
                    high = mid-1;
                } else {
                    low = mid+1;
                }
            }
            tails[index] = x;
            if(index ==  len){
                len++;
            }
        }
        
        // out.write(String.valueOf(dp[0][0]));
        out.write(String.valueOf(len));
        return;
    }

    public static void RemovalGame() throws IOException {
        int n = sc.nextInt();
        long arr[] = new long[n];
        for(int i = 0 ; i < n ; i++) {
            arr[i] = sc.nextLong();
        }
        
        long totalSum = 0;
        for(long x : arr) {
            totalSum += x;
        }
        long dp[][] = new long[n][n];
        for(int i = n-1 ; i >=0 ; i--){
            for(int j = i ; j < n ; j++){
                if(i == j) {
                    dp[i][j] = arr[i];
                } else {
                    dp[i][j] = Math.max(arr[i] - dp[i+1][j], arr[j] - dp[i][j-1]);
                }
            }
        }
        long player1Score = (totalSum + dp[0][n-1]) / 2;
        out.write(String.valueOf(player1Score));
        return;
    }

    public static void Projects() throws IOException {
        int n = sc.nextInt();
        int[][] projects = new int[n][3];

        for(int i = 0 ; i < n ; i++){
            projects[i][0] = sc.nextInt(); // start
            projects[i][1] = sc.nextInt(); // end
            projects[i][2] = sc.nextInt(); // reward
        }
        Arrays.sort(projects, (a, b) -> Integer.compare(a[1], b[1]));

        long dp[] = new long[n+1];
        dp[0] = 0;

        for(int i = 1 ; i <= n ; i++){
            int curSt = projects[i-1][0];
            int curReward = projects[i-1][2];
            
            long skip = dp[i-1];
            long take = curReward;
            
            // --- BINARY SEARCH ENGINE REPLACES OLD LOOP ---
            int validPastProject = 0;
            int low = 1, high = i - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (projects[mid - 1][1] < curSt) {
                    validPastProject = mid; 
                    low = mid + 1; 
                } else {
                    high = mid - 1;
                }
            }
            
            take += dp[validPastProject];
            dp[i] = Math.max(skip, take);
        }
        out.write(String.valueOf(dp[n]));
        return;
    }

    public static void IncreasingSubsequenceII() throws IOException {
        int n = sc.nextInt();
        int arr[] = new int[n];
        for(int i = 0 ; i < n ; i++){
            arr[i] = sc.nextInt();
        }

        int dp[][] = new int[n+1][n+1];
        for(int ind = ) {
            for(int )
        }
    }

    public static void main(String args[]) throws IOException{
        
        // int n = sc.nextInt();
        // int x = sc.nextInt();

        // DiceCombinations(n);

        // int arr[] = new int[n];
        // for(int i = 0 ; i < n ; i++){
        //     arr[i] = sc.nextInt();
        // }

        // MinimizingCoins(n, x, arr);
        // CoinCombinationsI(n, x, arr);
        // CoinCombinationsII(n, x, arr);
        // RemovingDigits(n);
        // GridPathsI();
        // BookShop();
        // ArrayDescription();

        // MoneySums();
        // RemovalGame();
        // Projects();
        // EditDistance();

        // TwoSetsII();
        // LongestCommonSubsequence();
        // IncreasingSubsequence();
        IncreasingSubsequenceII();

        // RectangleCutting();
        out.flush();
    }
}