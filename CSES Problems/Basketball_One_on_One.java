import java.io.*;
import java.util.*;

public class Basketball_One_on_One {
    public static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    public static final List<List<Long>> ans = new ArrayList<>();

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
       	
       	String s = sc.next();
       	char firstP = 'A';
       	char secondP = 'B';
       	int n = s.length();
       	int countA = 0, countB = 0;
       	int prevAScr = 0, prevBScr = 0;

       	for(int i = 0 ; i < n-1 ; i += 2){
       		if(s.charAt(i) == firstP){
       			prevAScr = s.charAt(i+1) - '0';
       			countA += prevAScr;
       			// out.write(prevAScr + "A");
       			// out.newLine();
       		} else {
				prevBScr = s.charAt(i+1) - '0';
				countB += prevBScr;
       			// out.write(prevBScr + "B");
       			// out.newLine();
       		} 
       		if(countA == countB && countA == 10){
       			prevAScr += 2;
       			prevBScr += 2;
       			countA += prevAScr;
       			countB += prevBScr;
       		}
       	}
       	if(countA > countB){
       		out.write(firstP + "");
       	} else {
       		out.write(secondP + "");
       	}

        // out.write(ans + "");
        out.flush();
        return;
    }
}
/*

Alice and Barbara played some friendly games of one-on-one basketball after work, and you agreed to help them keep score. The rules of the game were simple:

Each successful shot by a player earns them either one or two points;

The first player to eleven points wins, with one exception;

If the score is tied 
–
, the previous rule is replaced by a “win by 2” rule: the first player to lead the other by at least two points wins.

So for example, 
–
, 
–
, and 
–
 are possible final scores (but not 
–
).

Whenever Alice or Barbara scored points, you jotted down an A or B (indicating a score by Alice or by Barbara) followed by a 1 or 2 (the number of points scored). You have some records of the games Alice and Barbara played in this format, but do not remember who won each game. Can you reconstruct the winner from the game record?

Input
The input consists of a single line with no more than 
 characters: the record of one game. The record consists of single letters (either A or B) alternating with single numbers (either 1 or 2), and includes no spaces or other extraneous characters. Each record will be a correct scoring history of a single completed game, played under the rules described above.

Output
Print a single character, either A or B: the winner of the recorded game.

Sample Input 1	Sample Output 1
A2B1A2B2A1A2A2A2
A
Sample Input 2	Sample Output 2
A2B2A1B2A2B1A2B2A1B2A1A1B1A1A2
A

*/