import java.io.*;
import java.util.*;

public class ConcertsTicket {
    public static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    static class FastScanner {
        private final InputStream is = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, buflen = 0;

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

        public boolean hasNext() {
            while (hasNextByte() && buffer[ptr] <= ' ') ptr++;
            return hasNextByte();
        }

        public int nextInt() {
            if (!hasNext()) throw new NoSuchElementException();
            int n = 0;
            boolean neg = false;
            byte b = readByte();
            if (b == '-') {
                neg = true;
                b = readByte();
            }
            while ('0' <= b && b <= '9') {
                n = n * 10 + (b - '0');
                b = readByte();
            }
            return neg ? -n : n;
        }
    }

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int m = sc.nextInt();

        TreeSet<Integer> ticketSet = new TreeSet<>();
        HashMap<Integer, Integer> freq = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int price = sc.nextInt();
            ticketSet.add(price);
            freq.put(price, freq.getOrDefault(price, 0) + 1);
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            int maxPrice = sc.nextInt();
            Integer key = ticketSet.floor(maxPrice); // largest ≤ maxPrice
            if (key == null) {
                sb.append("-1\n");
            } else {
                sb.append(key).append("\n");
                int f = freq.get(key) - 1;
                if (f == 0) {
                    freq.remove(key);
                    ticketSet.remove(key);
                } else {
                    freq.put(key, f);
                }
            }
        }

        out.write(sb.toString());
        out.flush();
    }
}
