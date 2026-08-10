import java.io.*;
import java.util.*;

public class TrafficLights {
    public static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    // Keep your FastScanner class exactly as you wrote it here...
    static class FastScanner {
        private final InputStream is = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0;
        private int buflen = 0;
        private boolean hasNextByte() { if (ptr < buflen) return true; ptr = 0; try { buflen = is.read(buffer); } catch (IOException e) { e.printStackTrace(); } return buflen > 0; }
        private byte readByte() { if (hasNextByte()) return buffer[ptr++]; return -1; }
        private static boolean isPrintableChar(byte c) { return 33 <= c && c <= 126; }
        public boolean hasNext() { while (hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++; return hasNextByte(); }
        public long nextLong() { if (!hasNext()) throw new java.util.NoSuchElementException(); long n = 0; boolean minus = false; byte b = readByte(); if (b == '-') { minus = true; b = readByte(); } while ('0' <= b && b <= '9') { n = n * 10 + (b - '0'); b = readByte(); } return minus ? -n : n; }
        public int nextInt() { return (int) nextLong(); }
    }

    // Class to remember a light's original placement order after we sort them
    static class Light implements Comparable<Light> {
        long pos;
        int originalOrder;

        Light(long pos, int originalOrder) {
            this.pos = pos;
            this.originalOrder = originalOrder;
        }

        @Override
        public int compareTo(Light other) {
            return Long.compare(this.pos, other.pos);
        }
    }

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        long x = sc.nextLong();
        int n = sc.nextInt();

        Light[] lights = new Light[n + 2];
        // Add the left boundary (0)
        lights[0] = new Light(0, -1);
        // Add all user traffic lights
        for (int i = 1; i <= n; i++) {
            lights[i] = new Light(sc.nextLong(), i - 1);
        }
        // Add the right boundary (x)
        lights[n + 1] = new Light(x, -1);

        // Map to quickly find where a light ended up in the sorted array using its original time index
        int[] sortedIndexMap = new int[n];

        // Sort lights by position so we can build neighbor links easily
        Arrays.sort(lights, 1, n + 1);

        int[] leftNeighbor = new int[n + 2];
        int[] rightNeighbor = new int[n + 2];

        long maxLen = 0;
        
        // Build initial pointers and find the largest segment present at the very end
        for (int i = 0; i <= n + 1; i++) {
            if (i > 0) {
                leftNeighbor[i] = i - 1;
                maxLen = Math.max(maxLen, lights[i].pos - lights[i - 1].pos);
            }
            if (i < n + 1) {
                rightNeighbor[i] = i + 1;
            }
            // If it's a real traffic light, track its sorted position
            if (lights[i].originalOrder != -1) {
                sortedIndexMap[lights[i].originalOrder] = i;
            }
        }

        long[] answers = new long[n];

        // Process backward: Remove lights from the end of the input sequence to the front
        for (int i = n - 1; i >= 0; i--) {
            // Record current maximum segment length before removing this light
            answers[i] = maxLen;

            // Get the sorted array location of the light we want to remove
            int currentSortedIdx = sortedIndexMap[i];

            int leftIdx = leftNeighbor[currentSortedIdx];
            int rightIdx = rightNeighbor[currentSortedIdx];

            // When this light is removed, its left and right neighbors merge!
            long mergedLength = lights[rightIdx].pos - lights[leftIdx].pos;
            maxLen = Math.max(maxLen, mergedLength);

            // Update neighbor links to completely bypass/delete the current light
            rightNeighbor[leftIdx] = rightIdx;
            leftNeighbor[rightIdx] = leftIdx;
        }

        // Print the answers in correct forward order
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            result.append(answers[i]).append(" ");
        }

        out.write(result.toString().trim());
        out.flush();
    }
}