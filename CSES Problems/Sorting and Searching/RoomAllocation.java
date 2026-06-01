import java.io.*;
import java.util.*;

public class RoomAllocation {

    static class FastScanner {
        private final InputStream is = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, buflen = 0;
        private boolean hasNextByte() { if (ptr < buflen) return true; ptr = 0; try { buflen = is.read(buffer); } catch (IOException e) { e.printStackTrace(); } return buflen > 0; }
        private byte readByte() { return hasNextByte() ? buffer[ptr++] : -1; }
        private static boolean isPrintableChar(byte c) { return 33 <= c && c <= 126; }
        public boolean hasNext() { while (hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++; return hasNextByte(); }
        public long nextLong() { if (!hasNext()) throw new NoSuchElementException(); long n = 0; boolean minus = false; byte b = readByte(); if (b == '-') { minus = true; b = readByte(); } while ('0' <= b && b <= '9') { n = n * 10 + (b - '0'); b = readByte(); } return minus ? -n : n; }
        public int nextInt() { return (int) nextLong(); }
    }

    static int[] id, start, end;

    public static void main(String[] args) throws IOException {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        FastScanner sc = new FastScanner();

        int n = sc.nextInt();
        id = new int[n];
        start = new int[n];
        end = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            start[i] = sc.nextInt();
            end[i] = sc.nextInt();
        }

        // sort indices by arrival day
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) order[i] = i;
        Arrays.sort(order, (a, b) -> start[a] != start[b] ? start[a] - start[b] : end[a] - end[b]);

        // min-heap by vacantDay
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]); // [vacantDay, roomNumber]
        int[] result = new int[n];
        int roomCount = 0;

        for (int idx : order) {
            if (!pq.isEmpty() && pq.peek()[0] < start[idx]) {
                int[] room = pq.poll();
                room[0] = end[idx];
                result[idx] = room[1];
                pq.add(room);
            } else {
                roomCount++;
                result[idx] = roomCount;
                pq.add(new int[]{end[idx], roomCount});
            }
        }

        out.write(roomCount + "\n");
        for (int i = 0; i < n; i++) {
            out.write(result[i] + (i < n - 1 ? " " : ""));
        }
        out.write("\n");
        out.flush();
    }
}
