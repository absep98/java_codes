import java.io.*;
import java.util.*;
public class GridPathDescription {

    static boolean[][] onPath = new boolean[9][9];
    // added border to all four sides so a 7x7 becomes a 9x9
    static int[] dr = {-1, 0, 1, 0};  // transitions to up, right, down, left
    static int[] dc = {0, 1, 0, -1};  // for row and column, respectively
    static int[] p = new int[48];     // all possible paths have length 48

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        // convert path to ints
        for (int i = 0; i < p.length; i++) {
            char cur = line.charAt(i);

            if (cur == 'U') p[i] = 0;
            else if (cur == 'R') p[i] = 1;
            else if (cur == 'D') p[i] = 2;
            else if (cur == 'L') p[i] = 3;
            else p[i] = 4;  // cur == '?'
        }

        // set borders of grid
        for (int i = 0; i < 9; i++) {
            onPath[0][i] = true;
            onPath[8][i] = true;
            onPath[i][0] = true;
            onPath[i][8] = true;
        }

        int ans = tryPath(0, 1, 1);
        System.out.println(ans);
    }

    public static int tryPath(int pathIdx, int curR, int curC) {
        // Early termination: check if current position creates an impossible path
        if (wouldCreateIsolatedCorridor(curR, curC)) {
            return 0;
        }

        // Base cases: check if we've reached the end or used all moves
        if (hasReachedEndpoint(curR, curC)) {
            return hasUsedAllMoves(pathIdx) ? 1 : 0;
        }
        if (hasUsedAllMoves(pathIdx)) {
            return 0; // Used all moves but didn't reach endpoint
        }

        // Mark current position as visited
        onPath[curR][curC] = true;
        int totalPaths = 0;

        if (isMovePredetermined(pathIdx)) {
            // Move is fixed (not '?'), try that specific direction
            totalPaths += tryPredeterminedMove(pathIdx, curR, curC);
        } else {
            // Move is '?', use optimization to avoid dead ends first
            totalPaths += tryOptimalMove(pathIdx, curR, curC);
        }

        // Backtrack: unmark current position
        onPath[curR][curC] = false;
        return totalPaths;
    }

    // Helper methods for better readability and maintainability

    private static boolean wouldCreateIsolatedCorridor(int row, int col) {
        // Optimization 3: Avoid creating isolated horizontal or vertical corridors
        boolean horizontallyClosed = onPath[row][col - 1] && onPath[row][col + 1];
        boolean verticallyOpen = !onPath[row - 1][col] && !onPath[row + 1][col];
        
        boolean verticallyClosed = onPath[row - 1][col] && onPath[row + 1][col];
        boolean horizontallyOpen = !onPath[row][col - 1] && !onPath[row][col + 1];
        
        return (horizontallyClosed && verticallyOpen) || (verticallyClosed && horizontallyOpen);
    }

    private static boolean hasReachedEndpoint(int row, int col) {
        return row == 7 && col == 1; // Target endpoint
    }

    private static boolean hasUsedAllMoves(int pathIndex) {
        return pathIndex == p.length;
    }

    private static boolean isMovePredetermined(int pathIndex) {
        return p[pathIndex] < 4; // 0-3 are U,R,D,L; 4 is '?'
    }

    private static int tryPredeterminedMove(int pathIdx, int curR, int curC) {
        int direction = p[pathIdx];
        int nextR = curR + dr[direction];
        int nextC = curC + dc[direction];
        
        if (onPath[nextR][nextC]) {
            return 0; // Can't move to already visited cell
        }
        return tryPath(pathIdx + 1, nextR, nextC);
    }

    private static int tryOptimalMove(int pathIdx, int curR, int curC) {
        // Optimization 4: Prioritize moves that prevent dead ends
        int totalPaths = 0;
        
        // Check for potential dead ends and handle them first
        int deadEndMove = findPotentialDeadEndMove(curR, curC);
        if (deadEndMove != -1) {
            int nextR = curR + dr[deadEndMove];
            int nextC = curC + dc[deadEndMove];
            return tryPath(pathIdx + 1, nextR, nextC);
        }
        
        // No urgent dead end to handle, try all possible moves
        for (int direction = 0; direction < 4; direction++) {
            int nextR = curR + dr[direction];
            int nextC = curC + dc[direction];
            
            if (!onPath[nextR][nextC]) {
                totalPaths += tryPath(pathIdx + 1, nextR, nextC);
            }
        }
        
        return totalPaths;
    }

    private static int findPotentialDeadEndMove(int curR, int curC) {
        // Check left direction for potential dead end
        if (isPotentialDeadEnd(curR, curC, 3)) { // 3 = LEFT
            return 3;
        }
        
        // Check right direction for potential dead end
        if (isPotentialDeadEnd(curR, curC, 1)) { // 1 = RIGHT
            return 1;
        }
        
        // Check up direction for potential dead end (simplified version)
        if (isPotentialDeadEnd(curR, curC, 0)) { // 0 = UP
            return 0;
        }
        
        return -1; // No urgent dead end found
    }

    private static boolean isPotentialDeadEnd(int curR, int curC, int direction) {
        if (direction == 3) { // LEFT
            return (curC > 2) && 
                   onPath[curR][curC - 2] &&
                   (onPath[curR - 1][curC - 1] || onPath[curR + 1][curC - 1]) &&
                   !onPath[curR][curC - 1];
        } else if (direction == 1) { // RIGHT
            return (curC < 6) && 
                   onPath[curR][curC + 2] &&
                   (onPath[curR - 1][curC + 1] || onPath[curR + 1][curC + 1]) &&
                   !onPath[curR][curC + 1];
        } else if (direction == 0) { // UP
            return (curR > 2) && 
                   onPath[curR - 2][curC] && 
                   onPath[curR - 1][curC - 1] &&
                   !onPath[curR - 1][curC];
        }
        return false;
    }
}