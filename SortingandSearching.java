import java.io.*;
import java.util.*;

class Pair{
    int fs;
    int sc;
    Pair(int _f, int _s){
        this.fs = _f;
        this.sc = _s;
    }
}

public class SortingandSearching {

    public static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));


    static class FastScanner {
        private final InputStream is = System.in;
        private final byte[] buffer = new byte[1 << 16]; // 64 KB buffer
        private int ptr = 0;
        private int buflen = 0;

        private boolean hasNextByte() {
            if (ptr < buflen)
                return true;
            ptr = 0;
            try {
                buflen = is.read(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return buflen > 0;
        }

        private byte readByte() {
            if (hasNextByte())
                return buffer[ptr++];
            return -1;
        }

        private static boolean isPrintableChar(byte c) {
            return 33 <= c && c <= 126;
        }

        public boolean hasNext() {
            while (hasNextByte() && !isPrintableChar(buffer[ptr]))
                ptr++;
            return hasNextByte();
        }

        public String next() {
            if (!hasNext())
                throw new java.util.NoSuchElementException();
            StringBuilder sb = new StringBuilder();
            byte b = readByte();
            while (isPrintableChar(b)) {
                sb.append((char) b);
                b = readByte();
            }
            return sb.toString();
        }

        public char nextChar() {
            if (!hasNext())
                throw new java.util.NoSuchElementException();
            return (char) readByte();
        }

        public long nextLong() {
            if (!hasNext())
                throw new java.util.NoSuchElementException();
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


    public static void DistinctNumbers(int n, int arr[]) throws IOException{
        Arrays.sort(arr);
        int count = 1;
        for(int i = 1 ; i < n ; i++){
            if(arr[i-1] != arr[i]){
                count++;
            }
        }
        out.write(count + "");
        return;
    }

    public static void Apartments(int n, int m, int k, int desires[], int aptsz[]) throws IOException {
        Arrays.sort(desires);
        Arrays.sort(aptsz);

        int i = 0;
        int j = 0;
        int count = 0;
        while(i < n && j < m){
            long currentDesr = desires[i];
            long currentApt = aptsz[j];
            long tolerance = k;
            if( (currentApt >= currentDesr - tolerance) &&  (currentApt <= currentDesr + tolerance) ){
                i++;
                j++;
                count++;
            } else if(currentDesr - tolerance > currentApt){
                j++;
            } else {
                i++;
            }
        }
        out.write(count + "");
        return;
    }

    public static void FerrisWheel(int n, int x, int wts[]) throws IOException{
        int l = 0, r = n-1;
        int count = 0;
        Arrays.sort(wts);
        while(l <= r){
            long lf = wts[l];
            long rt = wts[r];
            long sum = lf + rt;
            if(sum <= x){
                l++;
                r--;
                count++;
            } else {
                r--;
                count++;
            }
        }
        out.write(count + "");
        return;
    }

    public static void ConcertsTicket(int n, int m, int prices[], int willingPay[]) throws IOException{
        TreeMap<Integer, Integer> fqSet = new TreeMap<>();
        for(int x : prices){
            fqSet.put(x, fqSet.getOrDefault(x, 0) + 1);
        }
        
        for(int i = 0 ; i < m ; i++){
            Integer maxPrc = fqSet.floorKey(willingPay[i]);
            if(maxPrc == null) {
                out.write("-1\n");
            } else {
                out.write(String.valueOf(maxPrc));
                out.write('\n');
                int fq = fqSet.get(maxPrc);
                if(fq == 1){
                    fqSet.remove(maxPrc);
                } else {
                    fqSet.put(maxPrc, fq-1);
                }
            }
            
        }
        return;
        /*TreeMap<Integer, Integer> fqSet = new TreeMap<>();
        for(int i = 0 ; i < n ; i++){
            int ticketPrc = sc.nextInt();
            fqSet.put(ticketPrc, fqSet.getOrDefault(ticketPrc, 0) + 1);
        }
        
        for(int i = 0 ; i < m ; i++){
            int custWilling = sc.nextInt();
            Integer maxPrc = fqSet.floorKey(custWilling);

            if(maxPrc == null){
                out.write("-1\n");
            } else {
                out.write(String.valueOf(maxPrc));
                out.write('\n');

                int fq = fqSet.get(maxPrc);
                if(fq == 1){
                    fqSet.remove(maxPrc);
                } else {
                    fqSet.put(maxPrc, fq-1);
                }
            }
        } */
    }

    public static void ResturantCustomers(int n, PriorityQueue<Pair> pq) throws IOException{
        int maxxOcc = 0;
        int currOcc = 0;
        while(!pq.isEmpty()){
            Pair curP = pq.poll();
            int currLv = curP.sc;
            currOcc += currLv;
            maxxOcc = Math.max(maxxOcc, currOcc);
        }
        out.write(maxxOcc + "");
        return;
    }

    public static void MovieFest(int n, PriorityQueue<Pair> pq) throws IOException {
        
        // int n = sc.nextInt();
        // PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {
        //     if(a.sc != b.sc){
        //         return a.sc - b.sc;  // sort by time primarily
        //     }
        //     return a.fs - b.fs;
        // });
        // for(int i = 0 ; i < n ; i++){
        //     int arr = sc.nextInt();
        //     int lv = sc.nextInt();
        //     pq.add(new Pair(arr, lv));
        // }
        if (pq.isEmpty()) {
            out.write("0");
            return;
        }
        int count = 1;
        Pair p = pq.poll();
        int curEd = p.sc;
        while(!pq.isEmpty()){
            p = pq.poll();
            int nextSt = p.fs;
            int nextEd = p.sc;
            if(curEd <= nextSt){
                curEd = nextEd;
                count++;
            }
        }
        out.write(count + "");
        return;
    }

    public static void SumofTwoValues(int n, int x, int arr[]) throws IOException{
        Map<Integer, Integer> mp = new HashMap<>();
        for(int i = 0 ; i < n ; i++){
            if(mp.containsKey(x-arr[i])){
                out.write(mp.get(x-arr[i]) + 1 + " ");
                out.write(i+1 + "");
                return;
            }
            mp.put(arr[i], i);
        }
        out.write("IMPOSSIBLE");
        return;
    }

    public static void MaximumSubarraySum(int n, int arr[]) throws IOException{
        long curMax = Integer.MIN_VALUE;
        long maxTillNow = Integer.MIN_VALUE;
        for(int i = 0 ; i < n ; i++){
            curMax = Math.max(arr[i], arr[i] + curMax);
            maxTillNow = Math.max(curMax, maxTillNow);
        }
        out.write(maxTillNow + "");
        return;
    }

    public static void main(String args[]) throws IOException{
        
        FastScanner sc = new FastScanner();

        int n = sc.nextInt();
        int arr[] = new int[n];
        for(int i = 0 ; i < n ; i++){
           arr[i] = sc.nextInt();
        }
        
        
        // DistinctNumbers(n, arr);
        // Apartments(n, m, k, desires, aptsz);
        // FerrisWheel(n, x, wts);
        // ConcertsTicket(n, m, prices, willingPay);
        // ResturantCustomers(n, pq);
        // MovieFest(n, pq);
        // SumofTwoValues(n, x, arr);
        MaximumSubarraySum(n, arr);
        out.flush();
    }
}


// In a movie festival n movies will be shown. You know the starting and ending time of each movie. What is the maximum number of movies you can watch entirely?
// Input
// The first input line has an integer n: the number of movies.
// After this, there are n lines that describe the movies. Each line has two integers a and b: the starting and ending times of a movie.
// Output
// Print one integer: the maximum number of movies.
// Constraints

// 1 \le n \le 2 \cdot 10^5
// 1 \le a < b \le 10^9

// Example
// Input:
// 3
// 3 5
// 4 9
// 5 8

// Output:
// 2