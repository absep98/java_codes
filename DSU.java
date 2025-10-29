import java.util.*;

class DisjointSet{
    int parent[];
    int size[];
    DisjointSet(int n){
        parent = new int[n];
        size = new int[n];
        for(int i = 0 ; i < n ; i++){
            parent[i] = i;
            size[i] = 1;
        }
    }
    int find(int x){
        if(x != parent[x]){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    void union(int x, int y){
        int px = find(x);
        int py = find(y);
        if(px == py){ // already in same group
            return;
        } if(size[px] < size[py]){
            parent[px] = py;
            size[py] += size[px];
        } else {
            parent[py] = px;
            size[px] += size[py];
        }
    }
}
public class DSU {
    public static void main(String[] args) {
        DisjointSet ds = new DisjointSet(6);
        
        // Union operations
        ds.union(0, 1);
        ds.union(2, 3);
        ds.union(1, 2);
        
        // Check if elements are connected
        System.out.println("0 and 3 connected: " + (ds.find(0) == ds.find(3)));
        System.out.println("4 and 5 connected: " + (ds.find(4) == ds.find(5)));
        
        ds.union(4, 5);
        System.out.println("After union(4,5) - 4 and 5 connected: " + (ds.find(4) == ds.find(5)));
    }
}
