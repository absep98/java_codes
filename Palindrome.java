import java.io.*;
import java.util.*;

public class Palindrome {
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

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringBuilder sb = new StringBuilder();
        String s = sc.next();
        Map<Character, Integer> mp = new HashMap<>();
        for(char ch : s.toCharArray()){
        	mp.put(ch, mp.getOrDefault(ch, 0) + 1);
        }
        int count = 0;
        for (Map.Entry<Character, Integer> entry : mp.entrySet()) {
		    if(entry.getValue()%2 == 1){
		    	count++;
		    }
		}
		if(count > 1){
			out.write("NO SOLUTION");
			out.flush();
			return;
		}

		Character odd = null;

		for(Map.Entry<Character, Integer> entry : mp.entrySet()){
			char ch = entry.getKey();
			int fq = entry.getValue();
			if(fq == 1 || fq%2 == 1) {
				odd = ch;
			}
			int half = fq/2;
			for(int i = 0 ; i < half ; i++){
				sb.append(ch);
			}
			
		}
		if(odd != null){
			sb.append(odd);
			for(int i = sb.length()-2 ; i >= 0 ; i--){
				char curr = sb.charAt(i);
				sb.append(curr);
			}
			out.write(sb.toString());
			out.flush();
			return;
		}

		for(int i = sb.length()-1; i >= 0 ; i--){
			char curr = sb.charAt(i);
			sb.append(curr);
		}


        out.write(sb.toString());
        // out.newLine();
        out.flush();
    }
}
