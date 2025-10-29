import java.io.*;
import java.util.*;

public class ShortestPathI{
    
    // --- Optimized FastScanner Class for TLE Fix ---
    static class FastScanner {
        private final InputStream is = System.in;
        private final byte[] buffer = new byte[1024];
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

        public long nextLong() {
            if (!hasNext()) throw new NoSuchElementException();
            long n = 0;
            boolean minus = false;
            byte b = readByte();
            if (b == '-') {
                minus = true;
                b = readByte();
            }
            if (b < '0' || '9' < b) {
                throw new NumberFormatException();
            }
            while (true) {
                if ('0' <= b && b <= '9') {
                    n *= 10;
                    n += b - '0';
                } else if (b == -1 || !isPrintableChar(b)) {
                    return minus ? -n : n;
                } else {
                    throw new NumberFormatException();
                }
                b = readByte();
            }
        }
        
        public int nextInt() {
            return (int)nextLong();
        }
    }
    // --- END OF FastScanner Class ---


	// NodeEntry class for efficient PriorityQueue storage
	static class NodeEntry implements Comparable<NodeEntry> {
	    long distance;
	    int node;

	    public NodeEntry(long distance, int node) {
	        this.distance = distance;
	        this.node = node;
	    }

	    @Override
	    public int compareTo(NodeEntry other) {
	        return Long.compare(this.distance, other.distance);
	    }
	}

	public static void main(String args[]) throws IOException{
		// Use the optimized FastScanner
		FastScanner sc = new FastScanner(); 

		int n = sc.nextInt();
		int m = sc.nextInt();

		// Adjacency list: [weight(long), destination(int)]
		List<List<long[]>> adj = new ArrayList<>();
		PriorityQueue<NodeEntry> q = new PriorityQueue<>();
		
		for(int i = 0 ; i < n+1 ; i++){
			adj.add(new ArrayList<>());
		}
		
		for(int i = 0 ; i < m ; i++){
			int a = sc.nextInt();
			int b = sc.nextInt();
			long c = sc.nextLong(); // Correctly reads weight as long
			
			// Directed graph
			adj.get(a).add(new long[]{c, b});
		}

		long dist[] = new long[n+1];
		Arrays.fill(dist, Long.MAX_VALUE);
		dist[1] = 0;
		
		q.add(new NodeEntry(0L, 1));
        
		// --- Dijkstra's Algorithm ---
		while(!q.isEmpty()){
			// Correctly poll the NodeEntry object
			NodeEntry entry = q.poll(); 
			long curdist = entry.distance;
			int curNode = entry.node;
            
			// Optimization Check: Skip stale entries
			if(curdist > dist[curNode]){
				continue;
			}
            
			for(long adjNode[] : adj.get(curNode)){
				long weight = adjNode[0];
				int nextNode = (int)adjNode[1];
                
				// Relaxation
				if(dist[nextNode] > curdist + weight){
					dist[nextNode] = curdist + weight;
					q.add(new NodeEntry(dist[nextNode], nextNode));
				}
			}
		}
        
        // Use StringBuilder for fast output
        StringBuilder sb = new StringBuilder();
		for(int i = 1 ; i < dist.length ; i++){
            // The problem guarantees reachability, so we just print the distance.
            sb.append(dist[i]).append(" ");
		}
        // Print the entire output string at once
        System.out.println(sb.toString().trim());
	}
}