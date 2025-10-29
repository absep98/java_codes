import java.util.*;
import java.io.*;

public class RoundTrip{
	static int start = -1, end = -1;
	public static boolean dfs(int node, int parent[], boolean vis[], List<List<Integer>> adj){
		vis[node] = true;
		for(int adjNode : adj.get(node)){
			if(adjNode == parent[node]){
				continue;
			}
			if(vis[adjNode] == false){
				parent[adjNode] = node;
				if(dfs(adjNode, parent, vis, adj)){
					return true;
				}
			} else {
				start = adjNode;
				end = node;
				return true;
			}
		}
		return false;
	}
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		List<List<Integer>> adj = new ArrayList<>();

		for(int i = 0 ; i < n+1 ; i++){
			adj.add(new ArrayList<>());
		}
		for(int i = 0 ; i < m ; i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			adj.get(a).add(b);
			adj.get(b).add(a);
		}
		boolean vis[] = new boolean[n+1];
		int parent[] = new int[n+1];
		List<Integer> ans = new ArrayList<>();
		boolean fd = false;
		for(int i = 1 ; i < n+1 ; i++){
			if(vis[i] == false){
				if(dfs(i, parent, vis, adj)){
					break;
				}
			}
		}
		if(start == -1){
			System.out.print("IMPOSSIBLE");
		} else {
			ans.add(start);
			for(int v = end ; v != start ; v = parent[v]){
				ans.add(v);
			}
			ans.add(start);
			Collections.reverse(ans);
			
			if(ans.size() >= 3){
				System.out.println(ans.size());
				for(int x : ans){
					System.out.print(x + " ");
				}
			} else {
				System.out.println("IMPOSSIBLE");
			}
		}
		
	}
}