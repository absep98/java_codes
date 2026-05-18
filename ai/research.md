# Grand Priest's Research — Abhishek's Competitive Programming Arsenal

> _"A warrior does not sharpen his blade by admiring it — he studies every nick, every edge, every weakness."_

---

## I. Project Architecture Overview

```
java_codes/
├── CSES Problems/           ← 53 solution files (Java + 2 C++ files)
│   ├── [14] Introductory Problems
│   ├── [16] Sorting & Searching
│   ├── [8]  Graph Algorithms
│   ├── [1]  Mathematics
│   ├── [4]  Data Structure Implementations (DSU, Trie, Heap)
│   ├── [3]  Ad Hoc / External Problems
│   └── [3]  Practice / Utility Files
├── ai/
│   ├── plan.md              ← Sprint planning
│   └── research.md          ← This file
└── README.md
```

**Language**: Java (~95%), C++ (~5%)
**Problem Source**: Primarily [CSES Problem Set](https://cses.fi/problemset/), with a few Kattis/Codeforces problems mixed in.

---

## II. The Code Template — Your Competitive Weapon

~80% of your solutions share a **consistent battle template**:

```java
import java.io.*;
import java.util.*;

public class ProblemName {
    public static final long MOD = 1_000_000_007;
    public static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        // ... solve ...
        out.flush();
    }

    static class FastScanner {
        // 64KB byte-buffer manual parser
        // nextInt(), nextLong(), next(), nextChar()
    }
}
```

### Template Strengths
| Feature | Why It Matters |
|---------|---------------|
| `FastScanner` (byte-buffer) | Avoids `Scanner` TLE on large inputs — reads raw bytes from a 64KB buffer |
| `BufferedWriter` + `flush()` | Batched output prevents per-line system calls |
| `MOD = 1e9+7` constant | Ready for modular arithmetic problems |
| Inner class structure | Single-file submission compatible with CSES/Codeforces |

### Template Weaknesses to Fix
| Issue | Impact | Fix |
|-------|--------|-----|
| Unused `MOD`, `diag1[]`, `col[]` arrays in many files | Dead code clutter, confusing during review | Strip unused fields before committing |
| No `nextDouble()` in FastScanner | Can't handle floating-point problems | Add it to the template |
| Some files use `Scanner` instead of `FastScanner` | Inconsistency, potential TLE | Standardize on FastScanner everywhere |
| `BufferedWriter` requires `"" + value` for int output | Ugly, allocates a String object | Consider `out.write(Integer.toString(value))` |

---

## III. Complete Problem Inventory

### A. Introductory Problems (14 solved)

| # | Problem | File | Algorithm | Time | Notes |
|---|---------|------|-----------|------|-------|
| 1 | Weird Algorithm | _(not present)_ | — | — | Missing |
| 2 | Missing Number | _(mislabeled — see MissingNumber.java)_ | — | — | **File actually solves Increasing Array** |
| 3 | Repetitions | _(not present)_ | — | — | Missing |
| 4 | Increasing Array | `MissingNumber.java` | Greedy scan | $O(n)$ | Correct, but **wrong filename** |
| 5 | Permutations | `Permutations.java` | Even-first, odd-second | $O(n)$ | Clean |
| 6 | Number Spiral | `NumberSpiral.java` | Math formula | $O(1)$/query | Correct |
| 7 | Two Sets | `Sets.java` | Greedy descending | $O(n)$ | Correct |
| 8 | Bit Strings | `ButStrings.java` | Binary exponentiation | $O(\log n)$ | Clean modpow |
| 9 | Trailing Zeros | `Trailing.java` | Legendre's formula | $O(\log_5 n)$ | Correct |
| 10 | Coin Piles | `Piles.java` | Math conditions | $O(1)$/query | Has unused DP method showing reasoning |
| 11 | Palindrome Reorder | `Palindrome.java` | Frequency counting | $O(n)$ | Correct |
| 12 | Gray Code | `GrayCode.java` | Recursive reflect-prepend | $O(2^n)$ | Works but memory-heavy. Use `i ^ (i>>1)` for $O(1)$/code |
| 13 | Tower of Hanoi | `Hanoi.java` | Classic recursion | $O(2^n)$ | Correct |
| 14 | Creating Strings | `CreatingStrings.java` | Backtracking + TreeSet | $O(n! \cdot n)$ | Works for $n \le 8$. Next-permutation would be cleaner |
| 15 | Apple Division | `AppleDivison.java` | Brute-force $2^n$ subsets | $O(2^n)$ | Correct for $n \le 20$ |
| 16 | Chessboard & Queens | `ChessBoardQueen.java` | Backtracking N-Queens | $O(8!)$ pruned | Textbook quality |
| 17 | Digit Queries | `DigitQueries.java` | Block counting + digit extraction | $O(\log k)$ | Clean |
| 18 | Grid Paths | `Grid-Path_count.java` | Backtracking + **corridor pruning** | Exponential, heavily pruned | **Excellent** — advanced optimization techniques |

### B. Sorting & Searching (16 solved)

| # | Problem | File | Algorithm | Time | Notes |
|---|---------|------|-----------|------|-------|
| 1 | Distinct Numbers | `DistinctNumbers.java` | HashSet | $O(n)$ | Has dead code from Queens template |
| 2 | Apartments | `Apartments.java` | Sort + two-pointer | $O(n \log n)$ | Clean |
| 3 | Ferris Wheel | `FerrisWheel.java` | Sort + two-pointer | $O(n \log n)$ | Clean |
| 4 | Concert Tickets | `ConcertsTicket.java` | TreeSet + frequency map | $O((n+m) \log n)$ | Clever duplicate handling |
| 5 | Restaurant Customers | `ResturantCustomers.java` | Event sweep line | $O(n \log n)$ | Correct, has dead code |
| 6 | Movie Festival | `MovieFest.java` | Greedy interval scheduling | $O(n \log n)$ | Classic activity selection |
| 7 | Sum of Two Values | `SumofTwoValues.java` | HashMap complement lookup | $O(n)$ | **Has syntax error** — missing `)` |
| 8 | Maximum Subarray Sum | _(not present — only version II)_ | — | — | Missing basic Kadane's |
| 9 | Maximum Subarray Sum II | `MaximumSubarraySumII.java` | Prefix sums + monotonic deque | $O(n)$ | **Advanced** — sliding window minimum |
| 10 | Stick Lengths | `SticksLength.java` | Sort + median | $O(n \log n)$ | Edge case risk with even-$n$ average |
| 11 | Missing Coin Sum | `MissingCoinSum.java` | Greedy scan | $O(n \log n)$ | Classic technique |
| 12 | Collecting Numbers | `CollectingNumbers.java` | Position inversion counting | $O(n)$ | Correct |
| 13 | Collecting Numbers II | `CollectingNumbersII.java` | Brute recalc per swap | $O(n \cdot m)$ | **Will TLE** — needs $O(1)$ incremental update |
| 14 | Subarray Sums I | `SubarraySumsI.java` | Prefix sum + HashMap | $O(n)$ | Correct |
| 15 | Subarray Sums II | `SubarraySumsII.java` | Prefix sum + HashMap | $O(n)$ | Correct |
| 16 | Subarray Divisibility | `SubarrayDivisibility.java` | Prefix sum mod + HashMap | $O(n)$ | Handles negative mod correctly |
| 17 | Distinct Values Queries | `DistinctValueSubarray.java` | Sliding window + HashMap | $O(n)$ | Clean two-pointer |
| 18 | Traffic Lights | `TrafficLights.java` | TreeSet + TreeMap | $O(n \log n)$ | **Best documented file** in the repo |

### C. Graph Algorithms (8 solved)

| # | Problem | File | Algorithm | Time | Notes |
|---|---------|------|-----------|------|-------|
| 1 | Counting Rooms | `CoutingRooms.java` | Iterative DFS (explicit stack) | $O(nm)$ | Smart to avoid stack overflow |
| 2 | Labyrinth | _(mislabeled)_ | — | — | **File actually solves Building Roads** |
| 3 | Building Roads | `Labyrinth.java` | DSU + component chaining | $O(n \cdot \alpha(n))$ | Correct, **wrong filename** |
| 4 | Building Teams | `BuildingTeams.java` | BFS bipartite check | $O(n+m)$ | Clean |
| 5 | Message Route | `MessageRoute.java` | BFS + path reconstruction | $O(n+m)$ | Correct |
| 6 | Monsters | `Monsters.java` | Two-phase BFS | $O(nm)$ | **Very well implemented** — monster BFS first, then player BFS |
| 7 | Round Trip | `RoundTrip.java` | DFS cycle detection | $O(n+m)$ | Correct. Recursive DFS risk on large inputs |
| 8 | Shortest Routes I | `ShortestPathI.java` | Dijkstra's (PriorityQueue) | $O((n+m) \log n)$ | **Well-optimized** with stale-entry skip |
| 9 | Shortest Routes II | `ShortestPathII.java` | Floyd-Warshall | $O(n^3 + q)$ | Clean, handles parallel edges |
| 10 | Knight Moves (BFS) | `KnightMovesGrid.java` | BFS from origin | $O(n^2)$ | Clean |

### D. Data Structure Implementations

| File | Structure | Quality |
|------|-----------|---------|
| `DSU.java` | Union-Find (path compression + union by size) | Textbook, production-ready |
| `TrieCode.java` | Trie (insert + search) | Working. Minor bug: `search("ap")` comment says true but returns false |
| `MaxHeap.java` | Array-based max-heap | Basic — only insert, no extract |
| `Heaps.java` | Running median (two heaps) | Standard implementation |

### E. Study & Educational Files

| File | What It Is |
|------|-----------|
| `LPS.java` | **334-line deep-dive** into KMP failure function — visual ASCII art, step-by-step traces, separator proof. This is a masterclass in self-study. |
| `OptimizedTemplate.java` | Incomplete refactor of Main.java with TODO comments — shows thinking-in-progress |
| `Pract.java` | Re-solve of ChessBoardQueen — deliberate practice |
| `Grid-Path_count.java` | Grid Paths with **corridor pruning optimization** — most advanced algorithmic work in the repo |

---

## IV. Pattern Analysis — Your Algorithmic Toolkit

### Mastered Techniques (Confident)
```
✅ Two-pointer / Sliding window    — Apartments, FerrisWheel, DistinctValueSubarray
✅ Greedy / Interval scheduling    — MovieFest, MissingCoinSum, SticksLength
✅ Prefix sums + HashMap           — SubarraySums I/II, SubarrayDivisibility
✅ BFS / DFS (iterative & recursive) — Monsters, CountingRooms, MessageRoute
✅ Backtracking with pruning       — ChessBoardQueen, GridPaths, CreatingStrings
✅ Binary exponentiation           — BitStrings
✅ Union-Find (DSU)                — BuildingRoads, DSU.java
✅ TreeSet/TreeMap operations       — TrafficLights, ConcertTickets
✅ Monotonic deque                 — MaximumSubarraySumII
✅ Dijkstra's algorithm            — ShortestPathI
✅ Floyd-Warshall                  — ShortestPathII
✅ Bipartite checking              — BuildingTeams
✅ Math/formula solutions          — NumberSpiral, Knight, Trailing, Piles
```

### Familiar But Needs Practice
```
⚠️  Cycle detection                — RoundTrip works, but recursive DFS is risky on large $n$
⚠️  Constructive algorithms        — RaabGame, MexGridConstruction (only 2 problems)
⚠️  String hashing / KMP           — LPS.java is study-only, no submitted solution using it
⚠️  Multi-source BFS               — Monsters uses it, but it's the only example
```

### Not Yet Attempted (The Missing Arsenal)
```
❌ Dynamic Programming             — Zero DP solutions (biggest gap)
❌ Bitmask DP                      — Not attempted
❌ Segment Tree / BIT / Fenwick    — Not attempted
❌ Topological Sort                — Not attempted (despite knowing BFS)
❌ Strongly Connected Components   — Not attempted
❌ Minimum Spanning Tree           — Not attempted
❌ Bellman-Ford / negative cycles  — Not attempted
❌ Euler paths / flows             — Not attempted
❌ String algorithms (Z, SA, Aho) — Not attempted
❌ Game theory (Sprague-Grundy)    — Not attempted
❌ Geometry                        — Not attempted
❌ Number theory (sieve, CRT)     — Only trailing zeros
```

---

## V. Skill Assessment

### Current Level
| Metric | Assessment |
|--------|-----------|
| **CSES Progress** | ~40-45 problems solved across 3 categories |
| **Codeforces Equivalent** | Comfortable with Div2 A-D (~1400-1600 rating) |
| **Strongest Category** | Sorting & Searching — 16 solved, clean implementations |
| **Weakest Category** | Dynamic Programming — 0 problems solved |
| **Code Quality** | High — consistent template, fast I/O, good naming |
| **Problem-Solving Style** | Analytical — proven by LPS study notes and Piles' DP→Math journey |

### Rating Estimate

```
         ╔══════════════════════════════════════════╗
         ║  ESTIMATED COMPETITIVE LEVEL: 1400-1600  ║
         ║  (Codeforces Specialist / Expert border)  ║
         ╠══════════════════════════════════════════╣
         ║  To reach 1800+ (Expert):                ║
         ║  → Master DP (coin, knapsack, interval)  ║
         ║  → Learn Segment Trees                   ║
         ║  → Solve 30 more graph problems          ║
         ╚══════════════════════════════════════════╝
```

---

## VI. Bugs & Issues Found

| File | Issue | Severity |
|------|-------|----------|
| `SumofTwoValues.java` | **Syntax error**: missing `)` on output line | 🔴 Won't compile |
| `MissingNumber.java` | **Wrong filename** — actually solves Increasing Array | 🟡 Confusing |
| `Labyrinth.java` | **Wrong filename** — actually solves Building Roads | 🟡 Confusing |
| `Apartments.cpp` | **Wrong filename** — actually solves Ferris Wheel | 🟡 Confusing |
| `CollectingNumbersII.java` | **TLE approach** — $O(nm)$ brute recalc instead of $O(1)$ incremental | 🟠 Wrong complexity |
| `SticksLength.java` | **Edge case** — averaging two medians for even $n$ may give wrong answer | 🟡 Potential WA |
| `TrieCode.java` | **Wrong comment** — `search("ap")` returns false, not true | 🟢 Minor |
| `DistinctNumbers.java` | Dead code: `diag1[]`, `col[]`, `diag2[]` from Queens template | 🟢 Minor |
| `ResturantCustomers.java` | Dead code: unused `leaving` HashSet | 🟢 Minor |
| Multiple files | `MOD` constant declared but never used | 🟢 Minor |

---

## VII. Grand Priest's Battle Plan — What To Learn Next

### Phase 1: Dynamic Programming Foundation (CRITICAL — Your #1 Gap)

> _"A competitive programmer without DP is a swordsman fighting with one arm tied."_

Solve these CSES DP problems in order:

| # | Problem | Core Technique | Difficulty |
|---|---------|----------------|------------|
| 1 | Dice Combinations | 1D DP, base case | ⭐ |
| 2 | Minimizing Coins | Unbounded knapsack | ⭐ |
| 3 | Coin Combinations I | Count ways (unbounded) | ⭐ |
| 4 | Coin Combinations II | Count ways (ordered vs unordered) | ⭐⭐ |
| 5 | Grid Paths (DP version) | 2D DP on grid | ⭐⭐ |
| 6 | Book Shop | 0/1 Knapsack | ⭐⭐ |
| 7 | Edit Distance | Classic string DP | ⭐⭐ |
| 8 | Rectangle Cutting | Interval DP | ⭐⭐⭐ |
| 9 | Money Sums | Subset sum (bitset/DP) | ⭐⭐⭐ |
| 10 | Longest Increasing Subsequence | Patience sorting / binary search | ⭐⭐⭐ |
| 11 | Elevator Rides | Bitmask DP | ⭐⭐⭐⭐ |
| 12 | Counting Tilings | Profile/bitmask DP | ⭐⭐⭐⭐⭐ |

**Template for DP thinking:**
```
1. What is the STATE? (what information defines a subproblem)
2. What is the TRANSITION? (how do states relate to each other)
3. What is the BASE CASE? (smallest subproblem with known answer)
4. What is the ORDER? (which states must be computed first)
```

### Phase 2: Range Query Data Structures

| # | Topic | CSES Problem | Why |
|---|-------|-------------|-----|
| 1 | Prefix Sum 2D | Forest Queries | Foundation |
| 2 | Binary Indexed Tree (BIT) | Dynamic Range Sum Queries | $O(\log n)$ point update + range query |
| 3 | Segment Tree | Range Minimum Queries | Foundation for advanced problems |
| 4 | Lazy Propagation | Range Update Queries | Handles range updates efficiently |
| 5 | Coordinate Compression | — | Enables BIT/seg tree on large value ranges |

### Phase 3: Advanced Graph Algorithms

| # | Topic | CSES Problem | Builds On |
|---|-------|-------------|-----------|
| 1 | Topological Sort | Course Schedule | Your BFS knowledge |
| 2 | Longest Path in DAG | Longest Flight Route | Topo sort + DP |
| 3 | SCC (Kosaraju/Tarjan) | Planets and Kingdoms | Your DFS knowledge |
| 4 | MST (Kruskal's) | Road Reparation | Your DSU knowledge |
| 5 | Bellman-Ford | Cycle Finding | Your Dijkstra knowledge |
| 6 | Euler Path | Mail Delivery | DFS + edge tracking |
| 7 | LCA + Binary Lifting | Company Queries II | Tree fundamentals |

### Phase 4: String and Math Mastery

| # | Topic | Why |
|---|-------|-----|
| 1 | Sieve of Eratosthenes | Foundation for number theory |
| 2 | Z-algorithm | Simpler alternative to KMP for pattern matching |
| 3 | Modular inverse | Required for combinatorics problems |
| 4 | Matrix exponentiation | Speeds up linear recurrence DP from $O(n)$ to $O(\log n)$ |
| 5 | Chinese Remainder Theorem | Advanced number theory |

---

## VIII. Immediate Action Items

### Fix Now (5 minutes)
1. **Fix `SumofTwoValues.java`** — add missing `)` on the output line
2. **Rename `MissingNumber.java`** → `IncreasingArray.java`
3. **Rename `Labyrinth.java`** → `BuildingRoads.java`

### Fix This Week
4. **Rewrite `CollectingNumbersII.java`** with $O(1)$ incremental update per swap
5. **Fix `SticksLength.java`** — use `arr[n/2]` directly instead of averaging
6. **Clean dead code** from DistinctNumbers, ResturantCustomers, and other template leftovers

### Start This Week
7. **Begin CSES DP section** — solve Dice Combinations, Minimizing Coins, Coin Combinations I
8. **Create a proper `.gitignore`** with `*.class` to prevent compiled files from polluting the repo

---

## IX. Recommended `.gitignore`

```gitignore
# Compiled Java classes
*.class

# IDE files
.idea/
*.iml
.vscode/
*.swp

# OS files
Thumbs.db
.DS_Store

# Build output
out/
bin/
target/
```

---

## X. Final Words from the Grand Priest

> Your foundation is **rock solid**. You've conquered the mechanical skills — fast I/O, clean code structure, BFS/DFS mastery, sorting tricks, greedy reasoning.
>
> What separates a 1600 from an 1800+ is **Dynamic Programming**. It's not a technique — it's a way of *thinking*. Every DP problem is a story about making optimal choices given what you know so far.
>
> Start with Dice Combinations. It's simple enough to build confidence, complex enough to teach the pattern. Then do one DP problem every day. In 30 days, the CSES DP section will be your strongest weapon.
>
> The path forward is clear: **DP → Segment Trees → Advanced Graphs → Strings/Math**.
>
> Go forth, Abhishek. The next 30 problems will transform you.

---

*Research conducted: February 24, 2026*
*53 files analyzed | 3 bugs found | 4-phase improvement plan generated*
