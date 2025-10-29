import java.io.*;
import java.util.*;
public class Permutations {

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
    public static void swap(int l, int r, int arr[]){
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }
    // public static void solve(int ind, int n, int arr[], List<List<Integer>> ans){
    //     if(ind == n){
    //         List<Integer> list = new ArrayList<>();
    //         for (int num : arr) {
    //             list.add(num);
    //         }
    //         ans.add(list);
    //         return;
    //     }
    //     for(int i = ind ; i < n ; i++){
    //         swap(i, ind, arr);
    //         solve(ind+1, n, arr, ans);
    //         swap(i, ind, arr);
    //     }
    // }
    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = sc.nextInt();
        if(n == 1){
            out.write("1");
            out.flush();
            return;
        }
        if(n == 2 || n == 3){
            out.write("NO SOLUTION");
            out.flush();
            return;
        }
    
        // List<List<Integer>> ans = new ArrayList<>();
        // solve(0, n, arr, ans);
        List<Integer> even  = new ArrayList<>(), odd = new ArrayList<>();
        for(int i = 1 ; i <= n ; i++){
           
            if(i%2 == 0){
                even.add(i);
            } else {
                odd.add(i);
            }
        }
        
        for (int num : even) {
            out.write(num + " ");
        }
        // out.newLine(); // optional: separates even and odd output

        for (int num : odd) {
            out.write(num + " ");
        }
        out.flush();
    }
}
