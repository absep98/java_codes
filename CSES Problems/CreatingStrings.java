import java.io.*;
import java.util.*;

public class CreatingStrings {

	public static final long MOD = 1000000007;
	public static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	public static Set<String> ans = new TreeSet<>();
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

    public static void swap(int l, int r, StringBuilder sb){
    	char tmp = sb.charAt(l);
    	sb.setCharAt(l, sb.charAt(r));
    	sb.setCharAt(r, tmp);
    }
    public static void solve(int ind, int n, StringBuilder sb) throws IOException{
    	if(ind == n){
    		ans.add(sb.toString());
    		return;
    	}
    	Set<Character> used = new HashSet<>();

    	for(int i = ind ; i < n ; i++){
    		if (used.contains(sb.charAt(i))) continue;
    		used.add(sb.charAt(i));
    		swap(ind, i, sb);
    		solve(ind+1, n, sb);
    		swap(ind, i, sb);
    	}	
    }

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        
       	
        String s = sc.next();
        char charArr[] = s.toCharArray();
        Arrays.sort(charArr);
        s = new String(charArr);
        StringBuilder sb = new StringBuilder(s);
        solve(0, s.length(), sb);
        out.write(ans.size() + "");
        out.newLine();
        for (String str : ans) {
		    out.write(str);
		    out.newLine();
		}
        	
        // out.newLine();
        out.flush();
    }
}




/*
Given a string, your task is to generate all different strings that can be created using its 
characters.
Input
The only input line has a string of length n. Each character is between a–z.
Output
First print an integer k: the number of strings. Then print k lines: the strings in alphabetical 
order.
Constraints

1 \le n \le 8

Example
Input:
aabac

Output:
20
aaabc
aaacb
aabac
aabca
aacab
aacba
abaac
abaca
abcaa
acaab
acaba
acbaa
baaac
baaca
bacaa
bcaaa
caaab
caaba
cabaa
cbaaa
*/