import java.util.*;
import java.io.*;

public class BuildingTeams{
	public static boolean possible = true;
	public static void bfs(int node, int teams[], List<List<Integer>> adj){
		Queue<Integer> q = new LinkedList<>();
		teams[node] = 1;
		q.add(node);
		while(!q.isEmpty()){
			int curNode = q.poll();
			for(int adjNode : adj.get(curNode)){
				if(teams[adjNode] == 0){
					teams[adjNode] = 3 - teams[curNode];
					q.add(adjNode);
				} else if(teams[adjNode] == teams[curNode]){
					possible = false;
					return;
				} 
			}
		}
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
		int teams[] = new int[n+1];
		Arrays.fill(teams, 0);
		Queue<Integer> q = new LinkedList<>();
		q.add(1);

		for(int i = 1 ; i < n+1 ; i++){
			if(teams[i] == 0){
				bfs(i, teams, adj);
			}
		}
		if(!possible){
			System.out.println("IMPOSSIBLE");
			return;
		}
		for(int i = 1 ; i < teams.length ; i++){
			System.out.print(teams[i] + " ");
		}
	}
}