import java.io.*;
import java.util.*;

public class ResturantCustomers {
    public static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));


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
       	int n = sc.nextInt();
        List<long[]> list = new ArrayList<>();
        Set<Integer> leaving = new HashSet<>();
        for(int i = 0 ; i < n ; i++){
            long a = sc.nextLong();
            long b = sc.nextLong();

            list.add(new long[]{a, +1});
            list.add(new long[]{b, -1});
        }
        
        Collections.sort(list, (x, y) -> (x[0] == y[0]) ? Long.compare(x[1], y[1]) : Long.compare(x[0], y[0]));

        int current_customers = 0;
        int max_cust = 0;

        long current = 0, max = 0;
        for (long[] e : list) {
            current_customers += e[1];
            max_cust = Math.max(max_cust, current_customers);
        }


        out.write(max_cust + "");
        out.flush();
    }
}

/*

You are given the arrival and leaving times of n customers in a restaurant.
What was the maximum number of customers in the restaurant at any time?
Input
The first input line has an integer n: the number of customers.
After this, there are n lines that describe the customers. Each line has two integers a and b: the arrival and leaving times of a customer.
You may assume that all arrival and leaving times are distinct.
Output
Print one integer: the maximum number of customers.
Constraints

1 \le n \le 2 \cdot 10^5
1 \le a < b \le 10^9

Example
Input:
3
5 8
2 4
3 9

Output:
2



int i = 0, j = 0;
long current = 0, max = 0;

while (i < n && j < n) {
    if (arrivals.get(i) < departures.get(j)) {
        current++;
        max = Math.max(max, current);
        i++;
    } else {
        current--;
        j++;
    }
}


*/

