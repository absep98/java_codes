import java.io.*;
import java.util.*;

public class ChessBoardQueen {

	public static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    public static boolean diag1[] = new boolean[15];
    public static boolean col[] = new boolean[8];
    public static boolean diag2[] = new boolean[15];
    public static long count = 0;
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


    public static void solve(int r, char board[][]){
        if(r == 8){
            count++;
            return;
        }
        for(int c = 0 ; c < 8 ; c++){
            if (board[r][c] == '*') continue;  // skip blocked squares
            int d1 = r + c;
            int d2 = r - c + 7;
            if (col[c] || diag1[d1] || diag2[d2]) continue;  // skip attacked squares
            // place queen
            col[c] = diag1[d1] = diag2[d2] = true;
            solve(r + 1, board);  // move to next row
            // undo placement
            col[c] = diag1[d1] = diag2[d2] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        
       	char board[][] = new char[8][8];

        for(int i = 0 ; i < 8 ; i++){
            String cur = sc.next();
            for(int j = 0 ; j < 8 ; j++){
                board[i][j] = cur.charAt(j);
            }
        }
        solve(0, board);
        out.write(count + "");
        // out.newLine();
        out.flush();
    }
}


/*

Your task is to place eight queens on a chessboard so that no two queens are attacking each other. As an additional challenge, each square is either free or reserved, and you can only place queens on the free squares. However, the reserved squares do not prevent queens from attacking each other.

How many possible ways are there to place the queens?

Input
The input has eight lines, and each of them has eight characters. Each square is either free (.) or reserved (*).

Output
Print one integer: the number of ways you can place the queens.

Example
Input:

........
........
..*.....
........
........
.....**.
...*....
........
Output:

65
    
*/