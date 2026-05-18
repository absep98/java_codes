#include <iostream>
#include <string>
#include <cmath>

using namespace std;

int total_paths = 0;
// 9x9 grid gives us a 1-layer protective true border around our 7x7 playable grid
bool visited[9][9];
string path_desc;

void gridPath(int r, int c, int indx) {
    // 1. Destination check: Lower-left corner is now (7,1) due to padding
    if (r == 7 && c == 1) {
        if (indx == 48) total_paths++;
        return;
    }

    // 2. Ran out of moves
    if (indx == 48) return;

    // 3. Pruning: Manhattan Distance check
    if (abs(7 - r) + abs(1 - c) > 48 - indx) return;

    // 4. Pruning: Wall Split (Up/Down blocked, Left/Right open)
    if (visited[r-1][c] && visited[r+1][c] && !visited[r][c-1] && !visited[r][c+1]) return;

    // 5. Pruning: Wall Split (Left/Right blocked, Up/Down open)
    if (visited[r][c-1] && visited[r][c+1] && !visited[r-1][c] && !visited[r+1][c]) return;

    // Mark current cell as visited
    visited[r][c] = true;

    char ch = path_desc[indx];

    // Try directions only if the target cell is unvisited
    if (ch == 'D' || ch == '?') {
        if (!visited[r+1][c]) gridPath(r+1, c, indx+1);
    }
    if (ch == 'U' || ch == '?') {
        if (!visited[r-1][c]) gridPath(r-1, c, indx+1);
    }
    if (ch == 'L' || ch == '?') {
        if (!visited[r][c-1]) gridPath(r, c-1, indx+1);
    }
    if (ch == 'R' || ch == '?') {
        if (!visited[r][c+1]) gridPath(r, c+1, indx+1);
    }

    // Backtrack and unmark
    visited[r][c] = false;
}

int main() {
    // Optimize standard I/O operations for competitive programming speed
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    if (!(cin >> path_desc)) return 0;

    // Initialize the outermost borders of the 9x9 grid as permanently true (blocked)
    for (int i = 0; i < 9; i++) {
        visited[0][i] = true; // Top wall
        visited[8][i] = true; // Bottom wall
        visited[i][0] = true; // Left wall
        visited[i][8] = true; // Right wall
    }

    // Start execution at the top-left playable square (1,1) at step 0
    gridPath(1, 1, 0);

    cout << total_paths << "\n";
    return 0;
}