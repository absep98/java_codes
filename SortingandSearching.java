import java.io.*;
import java.lang.reflect.Array;
import java.util.*;


class Pair{
    int fs;
    int sc;
    Pair(int _f, int _s){
        this.fs = _f;
        this.sc = _s;
    }
}

class Customer {
    int id;    // The original 0-based input row index
    int start; // Arrival Day
    int end;   // Departure Day
    
    public Customer(int id, int start, int end) {
        this.id = id;
        this.start = start;
        this.end = end;
    }
}

class IndexPair {
    int idx1, idx2;
    IndexPair(int idx1, int idx2) {
        this.idx1 = idx1;
        this.idx2 = idx2;
    }
}

class Element {
    int value;
    int index;
    
    Element(int value, int index) {
        this.value = value;
        this.index = index;
    }
}

class Room {
    int vacantDay;
    int roomNumber;
    
    public Room(int vacantDay, int roomNumber) {
        this.vacantDay = vacantDay;
        this.roomNumber = roomNumber;
    }
}

public class SortingandSearching {

    public static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int totalRounds = 1;

    public static int checkInversion(int i, int n, int pos[]){
        if(i < 2 || i > n){
            return 0;
        }
        return (pos[i] < pos[i-1]) ? 1 : 0;
    }

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

    public static void StickLengths(int n, int arr[]) throws IOException{

        Arrays.sort(arr);
        long cost = 0;
        long costMed = arr[n/2];
        
        for(int i = 0 ; i < n ; i++){
            cost += (long)Math.abs(arr[i] - costMed);
        }
        out.write(String.valueOf(cost));
        return;
    }

    public static void MissingCoinSum(int n, int arr[]) throws IOException{
        long currMax = 0;
        Arrays.sort(arr);
        
        for(int i = 0 ; i < n ; i++){
            if(arr[i] <= currMax + 1){
                currMax += arr[i];
            } else {
                out.write(String.valueOf(currMax + 1));
                return;
            }
        }
        out.write(String.valueOf(currMax+1));
        return; 
    }

    public static void CollectingNumbers(int n, int arr[]) throws IOException{
        int pos[] = new int[n+1];
        
        for(int i = 0 ; i < n ; i++){
            pos[arr[i]] = i;
        }
        int rounds = 1;
        for(int i = 2 ; i <= n ; i++){
            if(pos[i] < pos[i-1]){
                rounds++;
            }
        }
        out.write(String.valueOf(rounds));
        return;
    }

    public static int CollectingNumbersII(int n, int a, int b, int pos[], int arr[]) throws IOException{
        // int n = sc.nextInt();
        // int m = sc.nextInt();
        // int arr[] = new int[n+1];
        // int pos[] = new int[n+1];
        // for(int i = 1 ; i <= n ; i++){
        //    arr[i] = sc.nextInt();
        //    pos[arr[i]] = i;
        // }
        
        // for (int i = 2; i <= n; i++) {
        //     totalRounds += checkInversion(i, n, pos);
        // }
        // for(int i = 0 ; i < m ; i++){
            // int a = sc.nextInt();
            // int b = sc.nextInt();
            // if(a != b){
            //     int x = arr[a];
            //     int y = arr[b];

            //     Set<Integer> affectedNums = new HashSet<>();
            //     affectedNums.add(x);
            //     affectedNums.add(x+1);
            //     affectedNums.add(y);
            //     affectedNums.add(y+1);

            //     for(int num : affectedNums){
            //         totalRounds -= checkInversion(num, n, pos);
            //     }

            //     arr[a] = y;
            //     arr[b] = x;
            //     pos[x] = b;
            //     pos[y] = a;

            //     // 3. Add back the new inversion states
            //     for (int num : affectedNums) {
            //         totalRounds += checkInversion(num, n, pos);
            //     }
            // }
            // out.write(String.valueOf(totalRounds));
            // return;
        // }
        return 0;
    }
    

    public static void Playlist(int n, int arr[]) throws IOException{
        Map<Integer, Integer> mp = new HashMap<>();
        
        int i = 0;
        int j = 0;
        int ans = 0;
        while(j < n){
            
            if (mp.containsKey(arr[j])) {
                // Jump the left pointer directly past the previous duplicate's index
                i = Math.max(i, mp.get(arr[j]) + 1);
            }

            mp.put(arr[j], j);
            ans = Math.max(ans, j-i+1);
            j++;
        }
        out.write(String.valueOf(ans));
        return;
    }

    public static void Towers(int n, int arr[]) throws IOException {
        TreeMap<Integer, Integer> mp = new TreeMap<>();
        int count = 0;
        
        for (int i = 0; i < n; i++) {
            Integer curr = mp.higherKey(arr[i]);
            
            if (curr != null) {
                // Place on an existing tower: reduce old top's frequency
                int fq = mp.get(curr);
                if (fq > 1) {
                    mp.put(curr, fq - 1);
                } else {
                    mp.remove(curr);
                }
            } else {
                // Start a brand-new tower
                count++;
            }
            
            // Unconditional: The new cube size ALWAYS becomes an active tower top!
            mp.put(arr[i], mp.getOrDefault(arr[i], 0) + 1);
        }
        
        out.write(String.valueOf(count));
        return;
    }

    public static void NearestSmallerValues(int n, int arr[]) throws IOException{

        Stack<Integer> st = new Stack<>();
        List<Integer> ans = new ArrayList<>();

        for(int i = 0 ; i < n ; i++){
            if(st.isEmpty()){
                ans.add(0);
            } else if(!st.isEmpty() && arr[st.peek()] < arr[i]){
                ans.add(st.peek() + 1);
            } else if(!st.isEmpty() && arr[st.peek()] >= arr[i]){
                while(!st.isEmpty() && arr[st.peek()] >= arr[i]){
                    st.pop();
                }
                if(!st.isEmpty()){
                    ans.add(st.peek() + 1);
                } else {
                    ans.add(0);
                }
            }
            st.add(i);
        }
        for(int x : ans){
            out.write(String.valueOf(x));
            out.write(" ");;
        }
        return;


        /*
        for (int i = 0; i < n; i++) {
            // Phase 1: Clear out anyone who is larger or equal (they can never be a "nearest smaller" anymore)
            while (!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop();
            }
            
            // Phase 2: If the stack is empty now, no smaller element exists to the left. 
            // Otherwise, the current top of the stack is officially the nearest smaller index!
            if (st.isEmpty()) {
                ans.add(0);
            } else {
                ans.add(st.peek() + 1); // +1 because CSES uses 1-based output positions
            }
            
            // Phase 3: Push the current index onto the stack
            st.push(i);
        }
        */
    }


    public static void SubarraySumsI(int n, int x, int arr[]) throws IOException {
        Map<Long, Integer> mp = new HashMap<>();
        mp.put(0L, 1);
        long sum = 0;
        int count = 0;
        for(int i = 0 ; i < n ; i++){
            sum += arr[i];
            if(mp.containsKey(sum - x)){
                count += mp.get(sum - x);
            } 
            mp.put(sum, mp.getOrDefault(sum, 0 ) + 1);
        }
        out.write(String.valueOf(count));
        return;
    }

    public static void SubarraySumsII(int n, int x, int arr[]) throws IOException {
        Map<Long, Integer> mp = new HashMap<>();
        mp.put(0L, 1);
        long sum = 0;
        long count = 0;
        for(int i = 0 ; i < n ; i++){
            sum += arr[i];
            if(mp.containsKey(sum - x)){
                count += mp.get(sum - x);
            } 
            mp.put(sum, mp.getOrDefault(sum, 0 ) + 1);
        }
        out.write(String.valueOf(count));
        return;
    }

    public static void SubarrayDivisibility(int n, int arr[]) throws IOException{
        Map<Long, Integer> mp = new HashMap<>();
        long count = 0;
        long sum = 0;
        mp.put(0L, 1);
        for(int i = 0 ; i < n ; i++){
            sum += arr[i];
            long rem = sum%n;
            if(rem < 0){
                rem += n;
            }
            if(mp.containsKey(rem)){
                count += mp.get(rem);
            }
            mp.put(rem, mp.getOrDefault(rem, 0) + 1);
        }
        out.write(String.valueOf(count));
        return;
    }



    public static void TasksandDeadlines(int n, PriorityQueue<Pair> pq) throws IOException{
        // TODO: fix this method
        // PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {
        //     if(a.fs != b.fs){
        //         return a.fs - b.fs;
        //     }
        //     return a.sc - b.sc;
        // });
        // for(int i = 0 ; i < n ; i++){
        //     int a = sc.nextInt();
        //     int b = sc.nextInt();
        //     pq.add(new Pair(a, b));
        // }
        long count = 0;
        long time = 0;
        while(!pq.isEmpty()){
            Pair p = pq.poll();
            int duration = p.fs;
            int deadline = p.sc;
            time += duration;
            count += (deadline - time);
        }
        out.write(String.valueOf(count));
        return;
    }

    public static void RoomAllocation(int n, Customer[] customers) throws IOException{

        // Customer[] customers = new Customer[n];
        // for(int i = 0 ; i < n ; i++){
        //     int a = sc.nextInt();
        //     int b = sc.nextInt();
        //     customers[i] = new Customer(i, a, b);
        // }
        Customer[] sorted = customers.clone();
        Arrays.sort(sorted, (a, b) -> a.start != b.start ? a.start - b.start : a.end - b.end);

        // min-heap by vacantDay (departure day of current occupant)
        PriorityQueue<Room> pq = new PriorityQueue<>((a, b) -> a.vacantDay - b.vacantDay);
        int[] result = new int[n];
        int roomCount = 0;

        for(Customer c : sorted){
            if(!pq.isEmpty() && pq.peek().vacantDay < c.start){
                // reuse the room that frees up earliest
                Room r = pq.poll();
                r.vacantDay = c.end;
                result[c.id] = r.roomNumber;
                pq.add(r);
            } else {
                // need a new room
                roomCount++;
                Room r = new Room(c.end, roomCount);
                result[c.id] = roomCount;
                pq.add(r);
            }
        }

        out.write(roomCount + "\n");
        for(int i = 0 ; i < n ; i++){
            out.write(result[i] + (i < n-1 ? " " : ""));
        }
        out.write("\n");
        return;
    }

    public static void FactoryMachines(int n, int t, int arr[]) throws IOException{
        
        Arrays.sort(arr);
        long low = 1;
        long high = 1000000000000000000L;
        long ans = high;
        while(low <= high){
            long prodMade = 0;
            long mid = low + (high-low)/2;
            for(int mach : arr){
                prodMade += (mid/mach);
                if (prodMade >= t) {
                    break;
                }
            }
            if(prodMade >= t){
                ans = mid;
                high = mid-1;
            } else {
                low = mid+1;
            }
        }
        out.write(String.valueOf(ans));
        return;
    }

    public static void ArrayDivision(int n, int k, int arr[]) throws IOException{
        long low = Integer.MIN_VALUE;
        long high = 0;
        for(int x : arr){
            low = Math.max((long)x, low);
            high += x;
        }
        long ans = high;
        while(low <= high){
            long mid = low + (high-low)/2;
            long windowsum = 0;
            long chunksused = 1;
            for(int x : arr){
                if(windowsum + x > mid){
                    chunksused++;
                    windowsum = x;
                } else {
                    windowsum += x;
                }
            }
            if(chunksused <= k){
                ans = mid;
                high = mid-1;
            } else {
                low = mid+1;
            }
        }
        out.write(String.valueOf(ans));
        return;
    }

    public static void SumofThreeValues(int n, int target, int arr[]) throws IOException{
        
        Element[] elements = new Element[n];
        for(int i = 0 ; i < n ; i++){
            elements[i] = new Element(arr[i], i+1);
        }
        Arrays.sort(elements, (a, b) -> Integer.compare(a.value, b.value));

        for(int i = 0 ; i < n ; i++){
            if(i > 0 && elements[i].value == elements[i-1].value){
                continue;
            }
            int j = i+1;
            int k = n-1;
            while(j < k){
                long sum = (long)elements[i].value + elements[j].value + elements[k].value;
                if(sum == target){
                    out.write(elements[i].index + " " + elements[j].index + " " + elements[k].index);
                    return;
                }
                if(sum < target){
                    j++;
                } else {
                    k--;
                }
            }
        }
        out.write("IMPOSSIBLE");
        return;
    }

    public static void SumofFourValues(int n, int target, int arr[]) throws IOException{

        Element[] elements = new Element[n];

        for(int i = 0 ; i < n ; i++){
            elements[i] = new Element(arr[i], i+1);
        }

        Arrays.sort(elements, (a, b) -> Integer.compare(a.value, b.value));

        Map<Long, IndexPair> mp = new HashMap<>();

        for(int i = 0 ; i < n-1 ; i++){

            for(int j = i+1 ; j < n ; j++){
                long currentsum = (long)arr[i] + arr[j];
                long complement = target - currentsum;

                if(mp.containsKey(complement)){
                    IndexPair p = mp.get(complement);
                    out.write(p.idx1 + " " + p.idx2 + " " + (i+1) + " " + (j+1));
                    return;
                }
            }

            for (int k = 0; k < i; k++) {
                long sum = (long) arr[k] + arr[i];
                mp.put(sum, new IndexPair(k + 1, i + 1)); // Storing 1-based indices
            }
        }
        out.write("IMPOSSIBLE");
        return;
    }

    public static void main(String args[]) throws IOException{
        
        FastScanner sc = new FastScanner();

        int n = sc.nextInt();
        int t = sc.nextInt();
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
        // MaximumSubarraySum(n, arr);
        // StickLengths(n, arr);
        // MissingCoinSum(n, arr);
        // CollectingNumbers(n, arr);
        // CollectingNumbersII(totalRounds, totalRounds, totalRounds, null, null)
        // NearestSmallerValues(n, arr);
        // SubarraySumsI(n, x, arr);
        // SubarraySumsII(n, x, arr);
        // SubarrayDivisibility(n, arr);
        // RoomAllocation(n, customers);

        // FactoryMachines(n, t, arr);
        // ArrayDivision(n, t,  arr);

        // SumofThreeValues(n, t, arr);
        SumofFourValues(n, t, arr);


        // TasksandDeadlines(n, pq);
        // Playlist(n, arr);
        // Towers(n, arr);
        out.flush();
    }
}