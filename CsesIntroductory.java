import java.io.*;
import java.util.*;

class Pair {
    int r;
    int c;
    public Pair(int _r, int _c){
        this.r = _r;
        this.c = _c;
    }
}
public class CsesIntroductory {

    public static final long MOD = 1000000007;
    private static int count = 0;
    private static final boolean diag1[] = new boolean[15];
    private static final boolean diag2[] = new boolean[15];
    private static final boolean cols[] = new boolean[8];
    public static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

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


    public static void towerofhanoi(int src, int to, int aux, int n) throws IOException{
        if(n == 0){
            return;
        }
        towerofhanoi(src, aux, to, n-1);
        out.write(src + " " + to + "\n");
        towerofhanoi(aux, to, src, n-1);
    }

    public static void coinpiles(int a, int b) throws IOException{
        if(Math.max(a, b) <= Math.min(a,b) *2 ){
            if((a + b)%3 == 0){
                out.write("YES\n");
            } else {
                out.write("NO\n");
            }
        } else {
            out.write("NO\n");
        }
    }

    public static List<String> graycode(int n)throws IOException {
        if(n == 1){
            List<String> cur = new ArrayList<>();
            cur.add("0");
            cur.add("1");
            return cur;
        }
        List<String> ans = new ArrayList<>();
        List<String> prev = graycode(n-1);
        for(int i = 0 ; i < prev.size() ; i++){
            ans.add("0" + prev.get(i));
        }
        for(int i = prev.size()-1 ; i >= 0 ; i--){
            ans.add("1" + prev.get(i));
        }
        return ans;
    }

    public static long bitStrings(long a, long n)throws IOException {
        if(n == 0){
            return 1;
        }
        long half = bitStrings(a, n/2);
        long res = (half*half)%MOD;
        if(n % 2 != 0){
            res = (res * a)%MOD;
        }
        return res;
    }

    public static String PalindromeReorder(String s) throws IOException{
        StringBuilder left = new StringBuilder();
        StringBuilder right = new StringBuilder();
        char mid = 0;
        int fq[] = new int[26];
        for(int i = 0 ; i < s.length() ; i++){
            fq[s.charAt(i) - 'A']++;
        }
        int count = 0;
        for(int i = 0 ; i < 26 ; i++){
            if(fq[i] % 2 != 0){
                count++;
            }
        }
        if(count > 1){
            return "NO SOLUTION";
        }
        for(int i = 0 ; i < 26 ; i++){
            char ch = (char)(i + 'A');
            for(int j = 0 ; j < fq[i]/2 ; j++){
                left.append(ch);
            }
            if(fq[i]%2 == 1){
                mid = ch;
            }
        }
        return left.toString() + (mid == 0 ? "" : mid) + left.reverse().toString();
    }

    public static void swap(int a, int b, char charArr[])throws IOException{
        char temp = charArr[a];
        charArr[a] = charArr[b];
        charArr[b] =  temp;
    }
    public static void creatingStrings(int indx, int n, char charArr[], TreeSet<String> ans)throws IOException{
        if(indx == n){
            String cur = new String(charArr);
            ans.add(cur);
            return;
        }
        // boolean 
        for(int i = indx ; i < n ; i++){
            swap(indx, i, charArr);
            creatingStrings(indx+1, n, charArr, ans);
            swap(i, indx, charArr);
        }
    }


    public static long AppleDivison(int indx, long asum, long bsum, int arr[]){
        if(indx == arr.length){
           return Math.abs(asum-bsum);
        }
        long ch1 = AppleDivison(indx+1, asum + arr[indx], bsum, arr);
        long ch2 = AppleDivison(indx+1, asum, bsum + arr[indx], arr);
        return Math.min(ch1, ch2);
    }


    public static boolean isSafe(int r, int c){
        if(diag1[r-c+7] == true || diag2[r+c] == true || cols[c] == true){
            return false;
        }
        return true;
    }

    public static void chessQueen(int r, char board[][]){
        if(r == 8){
            count++;
            return;
        }
        for(int col = 0 ; col < 8 ; col++){
            if(board[r][col] == '.'){
                if(isSafe(r, col)){
                    diag1[r - col + 7] = true;
                    diag2[r + col] = true;
                    cols[col] = true;
                    chessQueen(r+1, board);
                    diag1[r - col + 7] = false;
                    diag2[r + col] = false;
                    cols[col] = false;
                }
            }
        }
        return;
    }
    
    public static boolean isBlocked(int r, int c, boolean vis[][]) {
        if (r < 0 || c < 0 || r >= 7 || c >= 7) {
            return true;
        }
        return vis[r][c];
    }
    public static void gridPath(int r, int c, int indx, String s, boolean vis[][], char board[][]){

        if(r < 0 || c < 0 || r >= 7 || c >= 7 || vis[r][c] == true){
            return;
        }

        if(r == 6 && c == 0){
            if(indx == s.length()){
                count++;
            }
            return;
        }
        if (indx == s.length()) {
            return; // Ran out of moves without hitting the target
        }
        

        if((isBlocked(r-1, c, vis) && isBlocked(r+1, c, vis)) && (!isBlocked(r, c-1, vis) && !isBlocked(r, c+1, vis))){
            return;
        }

        if((!isBlocked(r-1, c, vis) && !isBlocked(r+1, c, vis)) && (isBlocked(r, c-1, vis) && isBlocked(r, c+1, vis))){
            return;
        }

        // If we can't physically reach (6,0) in the remaining steps, abort immediately!
        int minStepsRequired = Math.abs(6 - r) + Math.abs(0 - c);
        if (minStepsRequired > (s.length() - indx)) {
            return;
        }
        char ch = s.charAt(indx);
        if(ch == 'D'){
            vis[r][c] = true;
            gridPath(r+1, c, indx+1, s, vis, board);
            vis[r][c] = false;
        } else if(ch == 'U'){
            vis[r][c] = true;
            gridPath(r-1, c, indx+1, s, vis, board);
            vis[r][c] = false;
        } else if(ch == 'L'){
            vis[r][c] = true;
            gridPath(r, c-1, indx+1, s, vis, board);
            vis[r][c] = false;
        } else if(ch == 'R'){
            vis[r][c] = true;
            gridPath(r, c+1, indx+1, s, vis,  board);
            vis[r][c] = false;
        } else {
            vis[r][c] = true;
            gridPath(r+1, c, indx+1, s, vis, board);
            gridPath(r-1, c, indx+1, s,vis,  board);
            gridPath(r, c+1, indx+1, s, vis, board);
            gridPath(r, c-1, indx+1, s,vis,  board);
            vis[r][c] = false;
        }
        return;
    }

    public static void stringReorder(String s) throws IOException{
        int fq[] = new int[26];
        for(char ch : s.toCharArray()){
            fq[ch - 'A']++;
        }
        int n = s.length();
        boolean checkPossible = true;
        for(int i = 0 ; i < 26 ; i++){
            if(fq[i] > (n+1)/2){
                checkPossible = false;
                break;
            }
        }
        if(!checkPossible){
            out.write("-1");
            return;
        }
        int remain_len = n;
        int previndx = -1;
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < n ; i++){
            int max_fq = -1;
            int maxidx = -1;
            int chosenIdx = -1;
            for(int j = 0 ; j < 26 ; j++){
                if(max_fq < fq[j]){
                    max_fq = fq[j];
                    maxidx = j;
                }
            }
            if(max_fq * 2 > remain_len){
                if(maxidx == previndx){
                    out.write("-1");
                    return;
                }
                chosenIdx = maxidx;
            } else {
                for(int j = 0 ; j < 26 ; j++){
                    if(fq[j] > 0 && j != previndx){
                        chosenIdx = j;
                        break;
                    }
                }
            }
            sb.append((char)(chosenIdx + 'A'));
            fq[chosenIdx]--;
            previndx = chosenIdx;
            remain_len--;
        }
        out.write(sb.toString() + "");
        return;
    }


    public static void knightMinMoves(int r, int c, int n, int board[][]){
        int moves[][] = {{-2, -1}, {-2, 1}, {2, -1}, {2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}};
        Queue<Pair> q = new LinkedList<>();
        board[0][0] = 0;
        q.add(new Pair(0, 0));
        while(!q.isEmpty()){
            Pair p = q.poll();
            int cur_r = p.r;
            int cur_c = p.c;
            for(int i = 0 ; i < 8 ; i++){
                int nr = cur_r + moves[i][0];
                int nc = cur_c + moves[i][1];
                if(nr >= 0 && nr < n && nc >= 0 && nc < n && board[nr][nc] == -1){
                    board[nr][nc] = board[cur_r][cur_c] + 1;
                    q.add(new Pair(nr, nc));
                }
            }
        }
    }

    public static void GridColor(int r, int c, int n, int m, char board[][]){
        char even[] = {'A', 'B'};
        char odd[] = {'C', 'D'};

        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                if((i+j)%2 == 0){
                    if(board[i][j] == 'A'){
                        board[i][j] = 'B';
                    } else {
                        board[i][j] = 'A';
                    }
                } else {
                    if(board[i][j] == 'C'){
                        board[i][j] = 'D';
                    } else {
                        board[i][j] = 'C';
                    }
                }
            }
        }
    }

    public static void mexGridConst(int n) throws IOException{
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                out.write((i^j) + " ");
            }
            out.newLine();
        }
        return;
    }

    public static void raabGame(int n, int a, int b) throws IOException{
        if((a + b > n) || (a + b > 0 && (a == 0 || b == 0))){
            out.write("NO\n");
            return;
        }
        out.write("YES\n");
        int idx = 0;
        int tiedGames = n - a - b;
        int gamesPlayed = n - tiedGames;
        int gamesNumber[] = new int[gamesPlayed];
        for(int i = tiedGames+1 ; i <= n ; i++){
            gamesNumber[idx++] = i;
        }
        for(int i = 1 ; i <= tiedGames ; i++){
            out.write(i + " ");
        }
        for(int i = 0 ; i < gamesPlayed ; i++){
            out.write(gamesNumber[i] + " ");
        }
        out.write("\n");
        for (int i = 1; i <= tiedGames; i++) {
            out.write(i + " ");
        }
        for(int i = 0 ; i < gamesPlayed ; i++){
            out.write(gamesNumber[(i+a)%gamesPlayed] + " ");
        }
        out.write("\n");
        return;
    }

    public static void digitQuery(long k) throws IOException {
        long digitCountPerNum = 1;
        long totalNumInBuck = 9;
        long startNum = 1;
        while(k > totalNumInBuck * digitCountPerNum) {
            k -= totalNumInBuck * digitCountPerNum;
            digitCountPerNum += 1;
            totalNumInBuck *= 10;
            startNum *= 10;
        }

        long targetNum = startNum + (k - 1) / digitCountPerNum;
        long digitIdx = (k - 1) % digitCountPerNum;
        String str = String.valueOf(targetNum);
        out.write(str.charAt((int)digitIdx) + "\n");
        // OR
        // long shiftsNeeded = digitCountPerNum - 1 - digitIdx;
        // for (int i = 0; i < shiftsNeeded; i++) {
        //     targetNum /= 10;
        // }
        // long answerDigit = targetNum % 10;
        // out.write(answerDigit + "\n");
    }

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        // int n = sc.nextInt();

        // out.write(((1 << n) - 1) + "\n");
        // towerofhanoi(1, 3, 2, n);
        // int t = sc.nextInt();
        // while(t-- > 0){
        //     int a = sc.nextInt();
        //     int b = sc.nextInt();
        //     coinpiles(a, b);
        // }


        // List<String> ans = graycode(n);
        // bitStrings(2, n);

        // String s = sc.next();
        // PalindromeReorder(s);
        // out.write("\n");
        // char charArr[] = s.toCharArray();
        // Arrays.sort(charArr);
        // TreeSet<String> ans =  new TreeSet<String>();
        // creatingStrings(0, charArr.length, charArr, ans);
        // for(String cur : ans){
        //     out.write(cur + "\n");
        // }
        // int arr[] = new int[n];
        // for(int i = 0 ; i < n ; i++){
        //     arr[i] = sc.nextInt();
        // }
        // long ans = AppleDivison(0, 0L, 0L, arr);
        // char board[][] = new char[8][8];
        // for(int i = 0 ; i < 8 ; i++){
        //     String cur = sc.nextLine();
        //     for(int j = 0 ; j < cur.length() ; j++){
        //         board[i][j] = cur.charAt(j);
        //     }
        // }
        // chessQueen(0, board);
        // String s = sc.next();
        // char board[][] = new char[7][7];
        // boolean vis[][] = new boolean[7][7];
        // gridPath(0, 0, 0, s, vis, board);

        // String s = sc.next();
        // stringReorder(s);
        int t = sc.nextInt();
        // int m = sc.nextInt();

        // char board[][] = new char[n][m];
        // for(int i = 0 ; i < n ; i++){
        //     String s = sc.next();
        //     for(int j = 0 ; j < m ; j++){
        //         board[i][j] = s.charAt(j);
        //     }
        // }
        // knightMinMoves(0, 0, n, board);

        // GridColor(0, 0, n, m, board);

        // mexGridConst(n);

        for(int i = 0 ; i < t ; i++){
            long n = sc.nextLong();
            
            digitQuery(n);
        }

        // out.write(count + "");
        // for(char cur[] : board){
        //     for(char x : cur){
        //         out.write(x + "");
        //     }
        //     out.newLine();
        // }
        out.flush();
    }
}

/*
There is a knight on an n \times n chessboard. For each square, print the minimum number of moves the knight needs to do to reach the top-left corner.
Input
The only line has an integer n.
Output
Print the number of moves for each square.
Constraints

4 \le n \le 1000

Example
Input:
8

Output:
0 3 2 3 2 3 4 5 
3 4 1 2 3 4 3 4 
2 1 4 3 2 3 4 5 
3 2 3 2 3 4 3 4 
2 3 2 3 4 3 4 5 
3 4 3 4 3 4 5 4 
4 3 4 3 4 5 4 5 
5 4 5 4 5 4 5 6 
 */
