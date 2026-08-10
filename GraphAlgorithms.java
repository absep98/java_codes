import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

class Pair {
    int first;
    int second;

    Pair(int _f, int _s) {
        this.first = _f;
        this.second = _s;
    }
}

class Edge {
    int to;
    long weight;

    Edge(int to, long weight) {
        this.to = to;
        this.weight = weight;
    }
}

class State {
    int node;
    long distance;

    State(int node, long distance) {
        this.node = node;
        this.distance = distance;
    }
}

class CoupState {
    int node;
    long distance;
    boolean coupon;

    CoupState(int node, long distance, boolean _coup) {
        this.node = node;
        this.distance = distance;
        this.coupon = _coup;
    }
}

class Cell {
    int row;
    int col;

    Cell(int _r, int _c) {
        this.row = _r;
        this.col = _c;
    }
}

class Node {
    int a;
    int b;
    long distance;

    Node(int _a, int _b, long _d) {
        this.a = _a;
        this.b = _b;
        this.distance = _d;
    }
}

public class GraphAlgorithms {

    public static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    public static FastScanner sc = new FastScanner();
    public static int cycleStart = -1;
    public static int cycleEnd = -1;
    public static boolean possible = true;

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

    public static void bfsCountingRooms(int x, int y, int n, int m, boolean vis[][], char board[][]) {
        Stack<int[]> st = new Stack<>();
        st.push(new int[] { x, y });
        vis[x][y] = true;
        int dr[] = { -1, 0, 1, 0 };
        int dc[] = { 0, 1, 0, -1 };
        while (!st.isEmpty()) {
            int cell[] = st.pop();
            int row = cell[0];
            int col = cell[1];
            for (int i = 0; i < 4; i++) {
                int nr = dr[i] + row;
                int nc = dc[i] + col;
                if (nr >= 0 && nr < n && nc >= 0 && nc < m && !vis[nr][nc] && board[nr][nc] == '.') {
                    st.push(new int[] { nr, nc });
                    vis[nr][nc] = true;
                }
            }
        }
    }

    public static void CountingRooms() throws IOException {
        int n = sc.nextInt();
        int m = sc.nextInt();

        char board[][] = new char[n][m];
        for (int i = 0; i < n; i++) {
            String s = sc.next();
            for (int j = 0; j < m; j++) {
                board[i][j] = s.charAt(j);
            }
        }
        boolean vis[][] = new boolean[n][m];
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == '.' && vis[i][j] == false) {
                    bfsCountingRooms(i, j, n, m, vis, board);
                    count++;
                }
            }
        }
        out.write(String.valueOf(count));
        return;
    }

    public static void Labyrinth() throws IOException {
        /*
         * Can you use DFS (stack)?
         * Technically yes — DFS can find a path if one exists. But DFS does not
         * guarantee the
         * shortest path. It might wander down a long corridor, find B, and return a
         * path much longer
         * than the minimum. The problem explicitly asks for the shortest path length
         * and its description.
         */
        int n = sc.nextInt();
        int m = sc.nextInt();

        char board[][] = new char[n][m];
        for (int i = 0; i < n; i++) {
            String s = sc.next();
            for (int j = 0; j < m; j++) {
                board[i][j] = s.charAt(j);
            }
        }
        boolean vis[][] = new boolean[n][m];
        int stx = -1;
        int sty = -1;
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'A') {
                    stx = i;
                    sty = j;
                    q.add(new int[] { i, j });
                    break;
                }
            }
        }
        char parentMove[][] = new char[n][m];
        if (stx != -1) {
            vis[stx][sty] = true;
        } else {
            out.write("NO");
            return;
        }
        int dr[] = { -1, 0, 1, 0 };
        int dc[] = { 0, 1, 0, -1 };
        char dirs[] = { 'U', 'R', 'D', 'L' };
        String ans = null;
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            int cell[] = q.poll();
            int row = cell[0];
            int col = cell[1];
            if (board[row][col] == 'B') {
                List<Character> path = new ArrayList<>();
                int curR = row;
                int curC = col;
                while (board[curR][curC] != 'A') {
                    char move = parentMove[curR][curC];
                    path.add(move);
                    if (move == 'U') {
                        curR++;
                    } else if (move == 'D') {
                        curR--;
                    } else if (move == 'R') {
                        curC--;
                    } else if (move == 'L') {
                        curC++;
                    }
                }
                Collections.reverse(path);
                for (char c : path) {
                    sb.append(c);
                }
                ans = sb.toString();
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nr = row + dr[i];
                int nc = col + dc[i];
                if (nr >= 0 && nr < n && nc >= 0 && nc < m && !vis[nr][nc] && board[nr][nc] != '#') {
                    vis[nr][nc] = true;
                    q.add(new int[] { nr, nc });
                    parentMove[nr][nc] = dirs[i];
                }
            }
        }
        if (ans != null) {
            out.write("YES\n");
            out.write(String.valueOf(ans.length()) + "\n");
            out.write(ans);
        } else {
            out.write("NO");
        }
        return;
    }

    public static void dfsBuildingRoads(int node, boolean vis[], ArrayList<ArrayList<Integer>> adj) {
        vis[node] = true;
        for (int ngh : adj.get(node)) {
            if (vis[ngh] == false) {
                dfsBuildingRoads(ngh, vis, adj);
            }
        }
    }

    public static void BuildingRoads() throws IOException {
        int n = sc.nextInt();
        int m = sc.nextInt();
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        boolean vis[] = new boolean[n + 1];
        int count = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < n + 1; i++) {
            if (vis[i] == false) {
                list.add(i);
                dfsBuildingRoads(i, vis, adj);
                count++;
            }
        }
        out.write(String.valueOf(count - 1) + "\n"); // for joining the components
        for (int i = 0; i < list.size() - 1; i++) {
            out.write(String.valueOf(list.get(i)) + " " + list.get(i + 1));
            out.newLine();
        }
        return;
    }

    public static void MessageRoute() throws IOException {
        int n = sc.nextInt();
        int m = sc.nextInt();
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        boolean vis[] = new boolean[n + 1];
        int parentNode[] = new int[n + 1];
        Queue<Integer> q = new LinkedList<>();

        q.add(1); // source
        vis[1] = true;
        boolean reached = false;
        while (!q.isEmpty()) {
            int curNode = q.poll();
            if (curNode == n) {
                reached = true;
            }

            for (int ngh : adj.get(curNode)) {
                if (vis[ngh] == false) {
                    q.add(ngh);
                    vis[ngh] = true;
                    parentNode[ngh] = curNode;
                }
            }
        }

        if (!reached) {
            out.write("IMPOSSIBLE");
            return;
        }
        List<Integer> ans = new ArrayList<>();
        int k = 0;
        int curNode = n;
        while (curNode != 1) {
            ans.add(curNode);
            curNode = parentNode[curNode];
        }
        ans.add(curNode);
        Collections.reverse(ans);
        out.write(String.valueOf(ans.size()) + "\n");
        for (int x : ans) {
            out.write(x + " ");
        }
    }

    public static boolean dfsBuildingTeams(int node, ArrayList<ArrayList<Integer>> adj, int color[], int curColor) {
        color[node] = curColor;
        for (int ngh : adj.get(node)) {
            if (color[ngh] == 0) {
                if (!dfsBuildingTeams(ngh, adj, color, 3 - curColor)) {
                    return false;
                }

            } else if (color[node] == color[ngh]) {
                return false;
            }
        }
        return true;
    }

    public static void BuildingTeams() throws IOException {
        int n = sc.nextInt();
        int m = sc.nextInt();
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        int color[] = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            if (color[i] == 0) {
                if (!dfsBuildingTeams(i, adj, color, 1)) {
                    out.write("IMPOSSIBLE");
                    return;
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            out.write(color[i] + " ");
        }
        return;
    }

    public static boolean checkForCycleDFS(int node, ArrayList<ArrayList<Integer>> adj, boolean vis[], int parent[]) {
        vis[node] = true;

        for (int ngh : adj.get(node)) {

            if (ngh == parent[node]) {
                continue;
            }
            if (vis[ngh] == false) {
                parent[ngh] = node;
                if (checkForCycleDFS(ngh, adj, vis, parent)) {
                    return true;
                }
            } else {
                cycleStart = ngh;
                cycleEnd = node;
                return true;
            }
        }
        return false;
    }

    public static void RoundTrip() throws IOException {
        int n = sc.nextInt();
        int m = sc.nextInt();
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        boolean vis[] = new boolean[n + 1];
        List<Integer> path = new ArrayList<>();
        int parent[] = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (vis[i] == false) {
                // bfs wont work here as we need path so dfs having stack maintainence which
                // helps to get the path
                if (checkForCycleDFS(i, adj, vis, parent)) {
                    break;
                }
            }
        }
        if (cycleStart == -1) {
            System.out.println("IMPOSSIBLE");
        } else {
            path.add(cycleStart);
            for (int v = cycleEnd; v != cycleStart; v = parent[v]) {
                path.add(v);
            }
            path.add(cycleStart);
            Collections.reverse(path);
            if (path.size() >= 3) {
                System.out.println(path.size());
                for (int x : path) {
                    System.out.print(x + " ");
                }
            } else {
                System.out.println("IMPOSSIBLE");
            }
        }

    }

    public static void Monsters() throws IOException {
        int n = sc.nextInt();
        int m = sc.nextInt();

        char board[][] = new char[n][m];
        for (int i = 0; i < n; i++) {
            String s = sc.next();
            for (int j = 0; j < m; j++) {
                board[i][j] = s.charAt(j);
            }
        }
        boolean visMons[][] = new boolean[n][m];
        boolean visPlay[][] = new boolean[n][m];
        int monsterTime[][] = new int[n][m];
        int playerTime[][] = new int[n][m];
        Queue<Cell> MonstersQ = new LinkedList<>();
        for (int arr[] : monsterTime) {
            Arrays.fill(arr, (int) 1e9);
        }
        Queue<Cell> playerQ = new LinkedList<>();
        int startR = -1;
        int startC = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'M') {
                    MonstersQ.add(new Cell(i, j));
                    monsterTime[i][j] = 0;
                    visMons[i][j] = true;
                }
                if (board[i][j] == 'A') {
                    playerQ.add(new Cell(i, j));
                    visPlay[i][j] = true;
                    playerTime[i][j] = 0;
                    startR = i;
                    startC = j;
                }
            }
        }

        if (startR == 0 || startR == n - 1 || startC == 0 || startC == m - 1) {
            System.out.println("YES");
            System.out.println(0);
            return;
        }
        int dr[] = { -1, 0, 1, 0 };
        int dc[] = { 0, 1, 0, -1 };
        char dirs[] = { 'U', 'R', 'D', 'L' };
        while (!MonstersQ.isEmpty()) {
            Cell t = MonstersQ.poll();
            int curR = t.row;
            int curC = t.col;

            for (int i = 0; i < 4; i++) {
                int nr = curR + dr[i];
                int nc = curC + dc[i];
                if (nr >= 0 && nr < n && nc >= 0 && nc < m && board[nr][nc] != '#' && visMons[nr][nc] == false) {
                    MonstersQ.add(new Cell(nr, nc));
                    visMons[nr][nc] = true;
                    monsterTime[nr][nc] = monsterTime[curR][curC] + 1;
                }
            }
        }

        boolean fd = false;
        char moveFrom[][] = new char[n][m];
        int endR = -1;
        int endC = -1;

        while (!playerQ.isEmpty()) {
            Cell t = playerQ.poll();
            int curR = t.row;
            int curC = t.col;

            if ((curR == 0 || curR == n - 1) || (curC == 0 || curC == m - 1)) {
                // found
                fd = true;
                endR = curR;
                endC = curC;
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nr = curR + dr[i];
                int nc = curC + dc[i];

                if (nr >= 0 && nr < n && nc >= 0 && nc < m && board[nr][nc] != '#' && visPlay[nr][nc] == false
                        && playerTime[curR][curC] + 1 < monsterTime[nr][nc]) {
                    playerQ.add(new Cell(nr, nc));
                    visPlay[nr][nc] = true;
                    playerTime[nr][nc] = playerTime[curR][curC] + 1;
                    moveFrom[nr][nc] = dirs[i];
                }
            }
        }

        if (!fd) {
            System.out.println("NO");
            return;
        }
        out.write("YES\n");

        List<Character> path = new ArrayList<>();

        while (!(endR == startR && endC == startC)) {
            char move = moveFrom[endR][endC];
            path.add(move);
            if (move == 'U') {
                endR++;
            } else if (move == 'D') {
                endR--;
            } else if (move == 'R') {
                endC--;
            } else if (move == 'L') {
                endC++;
            }
        }
        Collections.reverse(path);
        StringBuilder sb = new StringBuilder();
        for (char c : path) {
            sb.append(c);
        }
        String ans = sb.toString();
        out.write(String.valueOf(ans.length()) + "\n");
        out.write(ans);
    }

    public static void ShortestRoutesI() throws IOException {
        int n = sc.nextInt();
        int m = sc.nextInt();
        ArrayList<ArrayList<Edge>> adj = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            long c = sc.nextLong();
            adj.get(a).add(new Edge(b, c));
        }
        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingLong(s -> s.distance));
        long dist[] = new long[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0;
        pq.add(new State(1, 0L));
        while (!pq.isEmpty()) {
            State cur = pq.poll();
            // ignore stale entry
            if (cur.distance != dist[cur.node]) {
                continue;
            }

            for (Edge edge : adj.get(cur.node)) {
                long newDist = dist[cur.node] + edge.weight;
                if (newDist < dist[edge.to]) {
                    dist[edge.to] = newDist;
                    pq.add(new State(edge.to, newDist));
                }
            }
        }

        for (int i = 1; i < n + 1; i++) {
            out.write(dist[i] + " ");
        }
        return;
    }

    public static void ShortestRoutesII() throws IOException {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int q = sc.nextInt();

        long dist[][] = new long[n + 1][n + 1];
        for (long cur[] : dist) {
            Arrays.fill(cur, Long.MAX_VALUE);
        }

        for (int i = 1; i <= n; i++) {
            dist[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            long c = sc.nextLong();

            dist[a][b] = dist[a][b] > c ? c : dist[a][b];
            dist[b][a] = dist[b][a] > c ? c : dist[b][a];
        }

        for (int k = 0; k < n + 1; k++) {
            for (int i = 0; i < n + 1; i++) {
                for (int j = 0; j < n + 1; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        if (dist[i][k] == Long.MAX_VALUE || dist[k][j] == Long.MAX_VALUE) {
                            continue;
                        }
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        while (q-- > 0) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            if (dist[a][b] != Long.MAX_VALUE) {
                out.write(dist[a][b] + "\n");
            } else {
                out.write(-1 + "\n");
            }

        }
        return;
    }

    // public static void HighScore() throws IOException {
    // int n = sc.nextInt();
    // int m = sc.nextInt();
    // List<Node> adj = new ArrayList<>();
    // ArrayList<ArrayList<Integer>> rev = new ArrayList<>();

    // for(int i = 0 ; i <= n ; i++)
    // rev.add(new ArrayList<>());

    // for(int i = 0 ; i < m ; i++){
    // int a = sc.nextInt();
    // int b = sc.nextInt();
    // long c = sc.nextLong();
    // adj.add(new Node(a, b, c));
    // rev.get(b).add(a);
    // }
    // long dist[] = new long[n+1];
    // Arrays.fill(dist, Long.MAX_VALUE);
    // dist[1] = 0L;
    // for(int i = 0 ; i < n - 1; i++){
    // for(Node e : adj){
    // int u = e.a;
    // int v = e.b;
    // long curDist = -e.distance;
    // if(dist[u] != Long.MAX_VALUE && dist[u] + curDist < dist[v]){
    // dist[v] = dist[u] + curDist;
    // }
    // }
    // }
    // boolean bad[] = new boolean[n+1];
    // for(Node e : adj) {
    // int u = e.a;
    // int v = e.b;
    // long curDist = -e.distance;
    // if(dist[u] != Long.MAX_VALUE && dist[u] + curDist < dist[v]) {
    // bad[v] = true;
    // }
    // }
    // boolean vis[] = new boolean[n+1];
    // vis[n] = true;
    // Queue<Integer> q = new LinkedList<>();
    // q.add(n);
    // while(!q.isEmpty()) {
    // int curNode = q.poll();
    // for(int ngh : rev.get(curNode)){
    // if(vis[ngh] == false) {
    // q.add(ngh);
    // vis[ngh] = true;
    // }
    // }
    // }
    // boolean hasInfiniteCycle = false;
    // for(int i = 1 ; i <= n ; i++){
    // if(bad[i] && vis[i]) {
    // hasInfiniteCycle = true;
    // break;
    // }
    // }
    // if(hasInfiniteCycle) {
    // out.write("-1");
    // } else {
    // out.write(String.valueOf(-dist[n]));
    // }
    // return;
    // }

    public static void HighScore() throws IOException {
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<Node> edges = new ArrayList<>();
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            long c = -sc.nextLong();
            edges.add(new Node(a, b, c));
            adj.get(b).add(a);
        }
        long dist[] = new long[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0;
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n - 1; i++) {
            for (Node curNode : edges) {
                int a = curNode.a;
                int b = curNode.b;
                long c = curNode.distance;
                if (dist[a] != Long.MAX_VALUE && dist[a] + c < dist[b]) {
                    dist[b] = dist[a] + c;
                }
            }
        }
        boolean cycleFound = false;
        boolean bad[] = new boolean[n + 1];
        for (Node curNode : edges) {
            int a = curNode.a;
            int b = curNode.b;
            long c = curNode.distance;
            if (dist[a] != Long.MAX_VALUE && dist[a] + c < dist[b]) {
                cycleFound = true;
                bad[b] = true;
            }
        }
        boolean canReachN[] = new boolean[n + 1];
        canReachN[n] = true;
        q.add(n);
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int ngh : adj.get(cur)) {
                if (!canReachN[ngh]) {
                    canReachN[ngh] = true;
                    q.add(ngh);
                }
            }
        }
        boolean hasInfiniteCycle = false;
        for (int i = 1; i <= n; i++) {
            if (bad[i] && canReachN[i]) {
                hasInfiniteCycle = true;
                break;
            }
        }
        if (hasInfiniteCycle) {
            out.write("-1");
        } else {
            out.write(String.valueOf(-dist[n]));
        }

    }

    public static void FlightDiscount() throws IOException {
        int n = sc.nextInt();
        int m = sc.nextInt();
        ArrayList<ArrayList<Edge>> adj = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            adj.add(new ArrayList<>());
        }
        PriorityQueue<CoupState> q = new PriorityQueue<>(Comparator.comparingLong(s -> s.distance));
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            long c = sc.nextLong();
            adj.get(a).add(new Edge(b, c));
        }
        long dist[][] = new long[n + 1][2];
        for (long cur[] : dist) {
            Arrays.fill(cur, Long.MAX_VALUE);
        }
        dist[1][0] = 0L;

        q.add(new CoupState(1, 0L, false));
        while (!q.isEmpty()) {
            CoupState curState = q.poll();
            if (!curState.coupon) {
                if (curState.distance != dist[curState.node][0]) {
                    continue;
                }
            } else {
                if (curState.distance != dist[curState.node][1]) {
                    continue;
                }
            }
            for (Edge edge : adj.get(curState.node)) {
                if (!curState.coupon) {
                    long nd = curState.distance + edge.weight;
                    if (nd < dist[edge.to][0]) {
                        dist[edge.to][0] = nd;
                        q.add(new CoupState(edge.to, nd, false));
                    }
                    long ndWithCoupon = curState.distance + edge.weight / 2;
                    if (ndWithCoupon < dist[edge.to][1]) {
                        dist[edge.to][1] = ndWithCoupon;
                        q.add(new CoupState(edge.to, ndWithCoupon, true));
                    }
                } else {
                    long nd = curState.distance + edge.weight;
                    if (nd < dist[edge.to][1]) {
                        dist[edge.to][1] = nd;
                        q.add(new CoupState(edge.to, nd, true));
                    }
                }
            }
        }

        // for(long x : dist) {
        out.write(Math.min(dist[n][0], dist[n][1]) + " ");
        // }
    }

    public static void CycleFinding() throws IOException {
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<Node> edges = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            long c = sc.nextLong();
            edges.add(new Node(a, b, c));
        }
        long dist[] = new long[n + 1];
        Arrays.fill(dist, 0L);
        int parent[] = new int[n + 1];
        int x = -1;
        for (int i = 0; i < n - 1; i++) {
            for (Node curNode : edges) {
                int u = curNode.a;
                int v = curNode.b;
                long curDist = curNode.distance;
                if (dist[u] + curDist < dist[v]) {
                    parent[v] = u;
                    dist[v] = dist[u] + curDist;
                }
            }
        }
        x = -1;
        for (Node curNode : edges) {
            int u = curNode.a;
            int v = curNode.b;
            long curDist = curNode.distance;
            if (dist[u] + curDist < dist[v]) {
                x = v;
                parent[v] = u;
                dist[v] = dist[u] + curDist;
            }
        }
        if (x == -1) {
            out.write("NO");
            return;
        }
        for (int i = 0; i < n; i++) {
            x = parent[x];
        }
        List<Integer> cycle = new ArrayList<>();
        int cur = x;
        do {
            cycle.add(cur);
            cur = parent[cur];
        } while (cur != x);
        cycle.add(x);
        Collections.reverse(cycle);
        out.write("YES\n");
        for (int node : cycle) {
            out.write(node + " ");
        }
        return;
    }

    public static void CourseSchedule() throws IOException {
        int n = sc.nextInt();
        int m = sc.nextInt();
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            adj.add(new ArrayList<>());
        }
        int indg[] = new int[n + 1];
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj.get(u).add(v);
        }
        int processed = 0;
        for (int i = 1; i < n + 1; i++) {
            for (int ngh : adj.get(i)) {
                indg[ngh]++;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i < n + 1; i++) {
            if (indg[i] == 0) {
                q.add(i);
            }
        }
        int topo[] = new int[n + 1];
        int i = 1;
        while (!q.isEmpty()) {
            int curNode = q.poll();
            processed++;
            topo[i++] = curNode;
            for (int ngh : adj.get(curNode)) {
                indg[ngh]--;
                if (indg[ngh] == 0) {
                    q.add(ngh);
                }
            }
        }
        if (processed != n) {
            out.write("IMPOSSIBLE");
            return;
        }
        for (int ii = 1; ii < n + 1; ii++) {
            out.write(topo[ii] + " ");
        }
        return;
    }

    public static void FlightRoutes() throws IOException {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        ArrayList<ArrayList<Edge>> adj = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            long dist = sc.nextLong();
            adj.get(u).add(new Edge(v, dist));
        }

        List<PriorityQueue<Long>> best = new ArrayList<>(n + 1);
        for (int i = 0; i < n + 1; i++) {
            best.add(new PriorityQueue<>(Collections.reverseOrder()));
        }

        best.get(1).add(0L);

        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingLong(s -> s.distance));
        pq.add(new State(1, 0L));

        while (!pq.isEmpty()) {
            State curState = pq.poll();
            int curNode = curState.node;
            long curDist = curState.distance;

            if (best.get(curNode).size() == k && curDist > best.get(curNode).peek()) {
                continue;
            }

            for (Edge adjEdge : adj.get(curNode)) {
                long nd = curDist + adjEdge.weight;
                if (best.get(adjEdge.to).size() < k) {
                    best.get(adjEdge.to).add(nd);
                    pq.add(new State(adjEdge.to, nd));
                } else if (nd < best.get(adjEdge.to).peek()) {
                    best.get(adjEdge.to).poll();
                    best.get(adjEdge.to).add(nd);
                    pq.add(new State(adjEdge.to, nd));
                }
            }
        }
        List<Long> ans = new ArrayList<>(best.get(n));

        Collections.sort(ans);

        for (long x : ans) {
            out.write(x + " ");
        }
        return;
    }

    public static boolean dfsRoundTripII(int node, int parent[], boolean inCurrentPath[],boolean vis[], ArrayList<ArrayList<Integer>> adj) throws IOException {
        vis[node] = true;
        inCurrentPath[node] = true;
        for(int nghAdj : adj.get(node)) {
            if(vis[nghAdj] == false) {
                parent[nghAdj] = node;
                if(dfsRoundTripII(nghAdj, parent, inCurrentPath, vis, adj)) {
                    return true;
                }
            } else if(vis[nghAdj] == true && inCurrentPath[nghAdj] == true) {
                cycleStart = nghAdj;
                cycleEnd = node;
                return true;
            }
        }
        inCurrentPath[node] = false;
        return false;
    }

    public static void RoundTripII() throws IOException {
        int n = sc.nextInt();
        int m = sc.nextInt();

        cycleStart = -1;
        cycleEnd = -1;

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0 ; i < n+1 ; i++){
            adj.add(new ArrayList<>());
        }
        for(int i = 1 ; i <= m ; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            adj.get(a).add(b);
        }

        int parent[] = new int[n+1];
        Arrays.fill(parent, -1);
        boolean vis[] = new boolean[n+1];
        boolean inCurrentPath[] = new boolean[n + 1];
        boolean found = false;
        for(int i = 1 ; i <= n ; i++) {
            if(!vis[i]) {
                if(dfsRoundTripII(i, parent, inCurrentPath, vis, adj)) {
                    found = true;
                    break;
                }
            }
        }
         if (!found || cycleStart == -1) {
            System.out.println("IMPOSSIBLE");
        } else {
            List<Integer> path = new ArrayList<>();
            path.add(cycleStart);
            for (int v = cycleEnd; v != cycleStart; v = parent[v]) {
                path.add(v);
            }
            path.add(cycleStart);
            Collections.reverse(path);
            if (path.size() >= 3) {
                System.out.println(path.size());
                for (int x : path) {
                    System.out.print(x + " ");
                }
            } else {
                System.out.println("IMPOSSIBLE");
            }
        }
    }

    public static void LongestFlightRoute() throws IOException {
        int n = sc.nextInt();
        int m = sc.nextInt();

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0 ; i < n+1 ; i++){
            adj.add(new ArrayList<>());
        }
        for(int i = 1 ; i <= m ; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            adj.get(a).add(b);
        }
        Queue<Integer> q = new LinkedList<>();
        int indg[] = new int[n+1];
        int dp[] = new int[n+1];
        int parent[] = new int[n+1];
        Arrays.fill(parent, -1);
        Arrays.fill(dp, Integer.MIN_VALUE);
        for(int i = 1 ; i < n+1 ; i++) {
            for(int ngh : adj.get(i)) {
                indg[ngh]++;
            }
        }
        for(int i = 1 ; i < n+1 ; i++) {
            if(indg[i] == 0) {
                q.add(i);
            }
        }
        dp[1] = 1;
        while(!q.isEmpty()) {
            int curNode = q.poll();
            for(int ngh : adj.get(curNode)) {
                if(dp[curNode] != Integer.MIN_VALUE) {
                    int cand = dp[curNode] + 1;
                    if(cand > dp[ngh]) {
                        dp[ngh] = cand;
                        parent[ngh] = curNode;
                    }
                }
                indg[ngh]--;
                if(indg[ngh] == 0) {
                    q.add(ngh);
                }
            }
        }
        if(dp[n] == Integer.MIN_VALUE) {
            out.write("IMPOSSIBLE");
            return;
        }
        List<Integer> path = new ArrayList<>();
        int cur = n;
        while(cur != -1) {
            path.add(cur);
            cur = parent[cur];
        }
        Collections.reverse(path);
        if(path.isEmpty() || path.get(0) != 1) {
            out.write("IMPOSSIBLE");
            return;
        }
        out.write(path.size() + "\n");
        for(int node : path) {
            out.write(node + " ");
        }
    }

    public static void GameRoutes() throws IOException {
        
    }

    public static void main(String args[]) throws IOException {

        // CountingRooms();
        // Labyrinth();
        // BuildingRoads();
        // MessageRoute();
        // BuildingTeams();
        // RoundTrip();
        // Monsters();
        // ShortestRoutesI();
        // ShortestRoutesII();
        // HighScore();
        // FlightDiscount();
        // CycleFinding();
        // FlightRoutes();
        // RoundTripII();
        // CourseSchedule();
        // LongestFlightRoute();
        GameRoutes();

        out.flush();
    }

}