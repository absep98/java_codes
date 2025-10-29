import java.util.*;
import java.io.*;
class DSU{
    int parent[];
    int size[];
    DSU(int n){
        parent = new int[n+1];
        size = new int[n+1];
        for(int i = 0 ; i < n+1 ; i++){
            parent[i] = i;
        }
        Arrays.fill(size, 1);
    }

    int find(int x){
        if(parent[x] == x){
            return x;
        }
        return parent[x] = find(parent[x]);
    }
    boolean union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);
        if(rootX != rootY){
            if(size[rootX] < size[rootY]){
                parent[rootX] = rootY;
                size[rootY] += size[rootX];
            } else {
                parent[rootY] = rootX;
                size[rootX] += size[rootY];
            }
            return true;
        }
        return false;
    }
}
public class Labyrinth {
    public static void main(String args[]) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        DSU dsu = new DSU(n);
        int edges = m;
            while(edges-- > 0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            dsu.union(a, b);
        }
        Set<Integer> uniqueRoots = new HashSet<>();

        for(int i = 1 ; i < n+1 ; i++){
            uniqueRoots.add(dsu.find(i));
        }
        List<Integer> leaders = new ArrayList<>(uniqueRoots);
        int k = leaders.size()-1;
        System.out.println(k);
        for (int i = 0; i < k; i++) {
            int ctyA = leaders.get(i);
            int ctyB = leaders.get(i + 1);
            System.out.println(ctyA + " " + ctyB);
        }
    }
}
