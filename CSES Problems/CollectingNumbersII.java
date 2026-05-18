import java.io.*;
import java.util.*;

public class CollectingNumbersII {
    public static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    public static Map<Integer, Integer> mp = new TreeMap<>();

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

    public static int swap(int arr[], int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
        int count = 0;
        for(int i = 0 ; i < arr.length-1 ; i++){
            if(mp.get(arr[i]) > mp.get(arr[i+1])){
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int m = sc.nextInt();

        int arr[] = new int[n];
        for(int i = 0 ; i < n ; i++){
            arr[i] = sc.nextInt();
            mp.put(i+1, arr[i]);
        }

        int total = 0;
        for(int i = 0 ; i < n-1 ; i++){
            if(mp.get(arr[i]) > mp.get(arr[i+1])){
                total++;
            }
        }

        for(int i = 0 ; i < m ; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int cnt = swap(arr, a, b);
            out.write(cnt + "\n");
        }

        out.flush();
    }
}
