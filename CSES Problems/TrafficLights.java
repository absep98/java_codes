import java.io.*;
import java.util.*;

public class TrafficLights {
    public static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    // Reusing the FastScanner class for efficient input reading
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
        // x: street length (max 10^9), n: number of traffic lights (max 2*10^5)
        long x = sc.nextLong();
        int n = sc.nextInt();

        // Array to hold the positions of the new traffic lights
        long[] p = new long[n];
        for (int i = 0; i < n; i++) {
            p[i] = sc.nextLong();
        }

        // 1. TreeSet: Stores all light positions (P) and the street boundaries (0 and X).
        // This allows O(log N) lookup for neighbors (floor/ceiling).
        TreeSet<Long> positions = new TreeSet<>();
        positions.add(0L);
        positions.add(x);

        // 2. TreeMap: Stores the frequency of each passage length.
        // Key: Passage Length, Value: Count of passages with that length.
        // Allows O(1) retrieval of the maximum length using lastKey().
        TreeMap<Long, Integer> lengths = new TreeMap<>();
        // Initially, there is one passage of length X.
        lengths.put(x, 1);

        StringBuilder result = new StringBuilder();

        // Process each new traffic light
        for (long newLight : p) {
            
            // --- Step 1: Find the original passage boundaries (L, R) ---
            
            // floor(newLight): Find the largest light position L <= newLight. This is the left boundary.
            Long left = positions.floor(newLight);
            
            // ceiling(newLight): Find the smallest light position R >= newLight. This is the right boundary.
            Long right = positions.ceiling(newLight);

            // The light position must be unique, so left < newLight < right.
            
            // --- Step 2: Update the Lengths Map ---
            
            // The old length being destroyed is R - L.
            long oldLength = right - left;
            
            // a) Remove the old length
            int count = lengths.get(oldLength);
            if (count == 1) {
                lengths.remove(oldLength);
            } else {
                lengths.put(oldLength, count - 1);
            }

            // b) Calculate and add the two new lengths
            long newLength1 = newLight - left;
            long newLength2 = right - newLight;

            // Add newLength1
            lengths.put(newLength1, lengths.getOrDefault(newLength1, 0) + 1);
            
            // Add newLength2
            lengths.put(newLength2, lengths.getOrDefault(newLength2, 0) + 1);

            // --- Step 3: Update the Positions Set ---
            
            positions.add(newLight);

            // --- Step 4: Query the Answer ---
            
            // The longest passage is the largest key in the lengths map.
            long longestPassage = lengths.lastKey();
            
            // Append the result to the output
            result.append(longestPassage).append(" ");
        }

        // Print the final output string, trimming the trailing space
        out.write(result.toString().trim());
        out.flush();
    }
}