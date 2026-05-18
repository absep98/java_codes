import java.io.*;
import java.util.*;

public class StringReorder {
    public static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    static class FastScanner {
        private final InputStream is = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, buflen = 0;

        private boolean hasNextByte() {
            if (ptr < buflen) return true;
            ptr = 0;
            try { buflen = is.read(buffer); } catch (IOException e) { e.printStackTrace(); }
            return buflen > 0;
        }

        private byte readByte() { return hasNextByte() ? buffer[ptr++] : -1; }

        private static boolean isPrintableChar(byte c) { return 33 <= c && c <= 126; }

        public boolean hasNext() {
            while (hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++;
            return hasNextByte();
        }

        public String next() {
            if (!hasNext()) throw new NoSuchElementException();
            StringBuilder sb = new StringBuilder();
            byte b = readByte();
            while (isPrintableChar(b)) {
                sb.append((char) b);
                b = readByte();
            }
            return sb.toString();
        }

        public int nextInt() { return (int) nextLong(); }

        public long nextLong() {
            if (!hasNext()) throw new NoSuchElementException();
            long n = 0;
            boolean minus = false;
            byte b = readByte();
            if (b == '-') { minus = true; b = readByte(); }
            while ('0' <= b && b <= '9') {
                n = n * 10 + (b - '0');
                b = readByte();
            }
            return minus ? -n : n;
        }
    }


    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();

        String s = sc.next();
        int n = s.length();
        
        int fq[] = new int[26];

        int maxxFq = 0;
        for(char ch : s.toCharArray()){
        	fq[ch - 'A']++;
        	if(fq[ch-'A'] > (n+1)/2){
        		out.write("-1");
	        	out.flush();
	        	return;
        	}
        }

        int rem = n;
        char prev = '?';
        StringBuilder ans = new StringBuilder();

       	for(int i = 0 ; i < n ; i++){
       		for(char ch = 'A' ; ch <= 'Z' ; ch++){
       			int idx = ch - 'A';
       			if(fq[idx] == 0){
       				continue;
       			}
       			if(ch == prev){
       				continue;
       			}

       			fq[idx] -= 1;
       			int remafter = rem - 1;
       			int maxrem = 0;
       			for(int t = 0 ; t < 26 ; t++){
       				if(fq[t] > maxrem){
       					maxrem = fq[t];
       				}
       			}
       			if(maxrem <= (remafter + 1) / 2){
       				ans.append(ch);
		            prev = ch;
		            rem = remafter;
		            break;
       			} else{
		            fq[idx] += 1;
		        }
       		}
       	}
       	if(ans.length() < n) {
       		out.write("-1");
       	} else {
       		out.write(ans.toString());
       	}
        out.flush();
    }
}
/*

Your task is to reorder the characters of a string so that no two adjacent characters are the same. What is the lexicographically minimal such string?
Input
The only line has a string of length n consisting of characters A–Z.
Output
Print the lexicographically minimal reordered string where no two adjacent characters are the same. If it is not possible to create such a string, print -1.
Constraints

1 \le n \le 10^6

Example
Input:
HATTIVATTI

Output:
AHATITITVT
*/