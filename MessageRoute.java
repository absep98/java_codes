import java.util.*;
import java.io.*;

public class MessageRoute{
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
		Queue<Integer> q = new LinkedList<>();
		q.add(1);
		int path[] = new int[n+1];
		Arrays.fill(path, -1);
		path[1] = 0;
		while(!q.isEmpty()){
			int curNode = q.poll();
			if(curNode == n){
				break;
			}
			for(int adjNode : adj.get(curNode)){
				if(path[adjNode] == -1){
					path[adjNode] = curNode;
					q.add(adjNode);
				}
			}
		}
		List<Integer> ans = new ArrayList<>();
		if(path[n] == -1){
			System.out.println("IMPOSSIBLE");
			return;
		}
		int curr = n;
		while(curr != 0){
			ans.add(0, curr);
			curr = path[curr];
		}

		// Collections.reverse(ans);
		StringBuilder sb = new StringBuilder();
		for (int city : ans) {
		    sb.append(city).append(" ");
		}
		System.out.println(ans.size());

		System.out.println(sb.toString());
	}
}