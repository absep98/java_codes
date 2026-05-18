# Competitive Programming — Master Plan

> Based on analysis of 53 solved problems, existing sprint plans, and identified gaps.
> Generated: February 24, 2026

---

## Current Standing

```
Problems Solved: ~45 (CSES) + a few ad hoc
Categories Hit:  Introductory ✅  |  Sorting & Searching ✅  |  Graphs ✅ (partial)
Categories Missed: DP ❌  |  Range Queries ❌  |  Trees ❌  |  Strings ❌  |  Math ❌ (mostly)
Estimated Rating: Codeforces ~1400-1600 (Specialist)
Target Rating:    1800+ (Expert)
```

### What the 12-Day Sprint Accomplished
Your original sprint plan (the "Perfect timing, Abhishek 👊" plan) was designed for 3 phases:
- **Phase 1 (Recursion/Backtracking)** → ✅ Completed — Apple Division, Queens, Gray Code, Creating Strings, Grid Paths all solved
- **Phase 2 (Sorting/DP)** → ⚠️ Half done — sorting problems solved, but **DP was skipped entirely**
- **Phase 3 (Graphs/Advanced DP)** → ⚠️ Graphs done, DP on graphs not started

**Bottom line:** The sprint built excellent foundations in greedy/sorting/graphs but left the single most important topic untouched — **Dynamic Programming**.

---

## Housekeeping — Fix Before Moving Forward

These take 15 minutes total and will clean up your repo:

| # | Task | Time |
|---|------|------|
| 1 | Fix `SumofTwoValues.java` — missing `)` on output line (won't compile) | 1 min |
| 2 | Rename `MissingNumber.java` → `IncreasingArray.java` (wrong filename) | 1 min |
| 3 | Rename `Labyrinth.java` → `BuildingRoads.java` (wrong filename) | 1 min |
| 4 | Fix `CollectingNumbersII.java` — rewrite with O(1) incremental swap update | 10 min |
| 5 | Add `.gitignore` with `*.class` | 1 min |
| 6 | Remove dead code (unused arrays in DistinctNumbers, unused HashSet in ResturantCustomers) | 5 min |

---

## The Plan — 8 Weeks to Expert Level

### Overview

| Week | Focus | Goal | Problems |
|------|-------|------|----------|
| 1-2 | Dynamic Programming (Basics) | Build DP intuition from zero | 14 |
| 3 | Dynamic Programming (Intermediate) | Knapsack, intervals, strings | 8 |
| 4 | Dynamic Programming (Advanced) | Bitmask DP, DP on trees | 6 |
| 5 | Range Queries + Data Structures | Segment tree, BIT, lazy prop | 8 |
| 6 | Advanced Graphs | Topo sort, SCC, MST, LCA | 10 |
| 7 | Strings + Number Theory | KMP, Z-algo, sieve, modular inverse | 8 |
| 8 | Contest Practice + Review | Virtual contests, revisit weak spots | 10+ |

**Daily commitment:** 2-3 hours focused solving + 30 min review

---

## Week 1-2: Dynamic Programming — The Foundation

> This is the #1 priority. Every competitive programming contest has at least one DP problem.

### The DP Framework (memorize this)

```
For every DP problem, answer four questions:

1. STATE    → What variables define a subproblem?
             Example: dp[i] = answer using first i items

2. TRANSITION → How does the current state relate to previous states?
                Example: dp[i] = dp[i-1] + dp[i-2]

3. BASE CASE → What's the smallest subproblem with a known answer?
               Example: dp[0] = 1, dp[1] = 1

4. ORDER     → Which states must be computed first?
               Example: left to right, or smaller subsets first
```

### Week 1: 1D and 2D DP

| Day | Problem (CSES) | Technique | Hint |
|-----|----------------|-----------|------|
| 1 | **Dice Combinations** | 1D DP, base case | `dp[i] = sum of dp[i-1]..dp[i-6]` |
| 2 | **Minimizing Coins** | Unbounded knapsack | `dp[x] = min coins to make sum x` |
| 3 | **Coin Combinations I** | Count ways (order matters) | Like Dice but with custom denominations |
| 4 | **Coin Combinations II** | Count ways (order doesn't matter) | Iterate coins in outer loop — this is the key insight |
| 5 | **Removing Digits** | DP with digit choices | `dp[n] = 1 + min(dp[n - digit])` for each digit of n |
| 6 | **Grid Paths** (DP version) | 2D DP on grid | `dp[i][j] = dp[i-1][j] + dp[i][j-1]`, skip traps |
| 7 | **Book Shop** | 0/1 Knapsack | Classic — take or skip each book |

**After each problem, write in a comment block at the top:**
```java
/*
 * STATE: dp[i] = ...
 * TRANSITION: dp[i] = ...
 * BASE: dp[0] = ...
 * TIME: O(...)
 * PATTERN: [knapsack / prefix / interval / ...]
 */
```

### Week 2: Intermediate DP

| Day | Problem (CSES) | Technique | Builds On |
|-----|----------------|-----------|-----------|
| 1 | **Edit Distance** | String DP (2D) | Grid Paths logic on strings |
| 2 | **Rectangle Cutting** | Interval DP | Try every horizontal/vertical cut |
| 3 | **Money Sums** | Subset sum with DP | Variation of 0/1 knapsack |
| 4 | **Removal Game** | Game DP (minimax) | `dp[l][r]` = best score for range |
| 5 | **Two Sets II** | Counting subsets with target sum | Knapsack counting variant |
| 6 | **Increasing Subsequence** | LIS with binary search | Not standard DP — uses patience sorting for O(n log n) |
| 7 | **Projects** | Interval scheduling + DP | Sort by end time, binary search for compatible previous |

---

## Week 3: Advanced DP

| Day | Problem (CSES) | Technique |
|-----|----------------|-----------|
| 1 | **Elevator Rides** | Bitmask DP — `dp[mask]` = (rides, weight) |
| 2 | **Counting Tilings** | Profile DP / broken profile |
| 3 | **Array Description** | DP with constraints on adjacent elements |
| 4 | **Counting Numbers** | Digit DP — count numbers with property in range [a,b] |
| 5 | **Counting Towers** | DP with tile states |
| 6 | **High Score** (CSES Graphs) | DP on DAG with negative cycle detection (Bellman-Ford + DP) |

### Bitmask DP Template
```java
// Elevator Rides pattern:
// dp[mask] = {minRides, lastRideWeight} for subset of people represented by mask
for (int mask = 0; mask < (1 << n); mask++) {
    for (int i = 0; i < n; i++) {
        if ((mask & (1 << i)) != 0) continue; // already included
        int newMask = mask | (1 << i);
        // transition: add person i to current ride or start new ride
    }
}
```

---

## Week 4: DP on Trees + Consolidation

| Day | Problem (CSES) | Technique |
|-----|----------------|-----------|
| 1 | **Tree Matching** | DP on tree (take/skip edge) |
| 2 | **Tree Diameter** | Two BFS or DP on tree |
| 3 | **Tree Distances I** | Rerooting technique — DP down + DP up |
| 4 | **Subordinates** | Subtree size DP (warmup) |
| 5-7 | **Revisit 3 hardest DP problems** from weeks 1-3 | Re-solve without looking at notes |

### Rerooting DP Template
```java
// Phase 1: Root at node 0, compute dp_down[v] for all v
void dfs_down(int v, int parent) {
    dp_down[v] = base;
    for (int u : adj[v]) {
        if (u == parent) continue;
        dfs_down(u, v);
        dp_down[v] = combine(dp_down[v], dp_down[u]);
    }
}

// Phase 2: Compute dp_up[v] = answer considering parent's subtree
void dfs_up(int v, int parent) {
    // dp[v] = combine(dp_down[v], dp_up[v])
    for (int u : adj[v]) {
        if (u == parent) continue;
        dp_up[u] = combine(dp_up[v], dp_down[v] WITHOUT dp_down[u]);
        dfs_up(u, v);
    }
}
```

---

## Week 5: Range Queries & Data Structures

| Day | Problem (CSES) | Structure | Time |
|-----|----------------|-----------|------|
| 1 | **Static Range Sum Queries** | Prefix sums (review) | O(1) query |
| 2 | **Static Range Minimum Queries** | Sparse Table | O(1) query, O(n log n) build |
| 3 | **Dynamic Range Sum Queries** | BIT (Fenwick Tree) | O(log n) update + query |
| 4 | **Dynamic Range Minimum Queries** | Segment Tree | O(log n) update + query |
| 5 | **Range Xor Queries** | Prefix XOR or BIT | O(1) or O(log n) |
| 6 | **Range Update Queries** | Segment Tree + Lazy Propagation | O(log n) |
| 7 | **Forest Queries** | 2D Prefix Sums | O(1) query |
| 8 | **Salary Queries** | Segment Tree + Coordinate Compression | Combine techniques |

### Segment Tree Template
```java
int[] tree;
int n;

void build(int[] arr) {
    n = arr.length;
    tree = new int[4 * n];
    build(arr, 1, 0, n - 1);
}

void build(int[] arr, int node, int start, int end) {
    if (start == end) { tree[node] = arr[start]; return; }
    int mid = (start + end) / 2;
    build(arr, 2 * node, start, mid);
    build(arr, 2 * node + 1, mid + 1, end);
    tree[node] = Math.min(tree[2 * node], tree[2 * node + 1]); // or sum, max, etc.
}

void update(int node, int start, int end, int idx, int val) {
    if (start == end) { tree[node] = val; return; }
    int mid = (start + end) / 2;
    if (idx <= mid) update(2 * node, start, mid, idx, val);
    else update(2 * node + 1, mid + 1, end, idx, val);
    tree[node] = Math.min(tree[2 * node], tree[2 * node + 1]);
}

int query(int node, int start, int end, int l, int r) {
    if (r < start || end < l) return Integer.MAX_VALUE; // identity element
    if (l <= start && end <= r) return tree[node];
    int mid = (start + end) / 2;
    return Math.min(query(2 * node, start, mid, l, r),
                    query(2 * node + 1, mid + 1, end, l, r));
}
```

---

## Week 6: Advanced Graph Algorithms

You already have strong BFS/DFS/Dijkstra foundations. Now build on them:

| Day | Problem (CSES) | Technique | Builds On |
|-----|----------------|-----------|-----------|
| 1 | **Course Schedule** | Topological Sort (Kahn's) | Your BFS experience |
| 2 | **Longest Flight Route** | DP on DAG (topo order) | Topo sort + DP |
| 3 | **Game Routes** | Count paths in DAG | Same structure as above |
| 4 | **Road Reparation** | MST (Kruskal's) | Your DSU.java! |
| 5 | **Planets and Kingdoms** | SCC (Kosaraju's) | Your DFS experience |
| 6 | **Cycle Finding** | Bellman-Ford negative cycle | Your Dijkstra experience |
| 7 | **Mail Delivery** | Euler circuit (Hierholzer's) | New technique |
| 8 | **Company Queries I** | Binary Lifting for LCA | Tree fundamentals |
| 9 | **Company Queries II** | LCA queries | Binary lifting |
| 10 | **Distance Queries** | LCA + depth-based distance | Combine LCA + tree DP |

### Topological Sort Template (Kahn's BFS)
```java
int[] inDegree = new int[n];
for (int u = 0; u < n; u++)
    for (int v : adj[u]) inDegree[v]++;

Queue<Integer> queue = new LinkedList<>();
for (int i = 0; i < n; i++)
    if (inDegree[i] == 0) queue.add(i);

List<Integer> order = new ArrayList<>();
while (!queue.isEmpty()) {
    int u = queue.poll();
    order.add(u);
    for (int v : adj[u])
        if (--inDegree[v] == 0) queue.add(v);
}
// if order.size() < n → cycle exists
```

---

## Week 7: Strings & Number Theory

| Day | Topic | Problem / Exercise |
|-----|-------|--------------------|
| 1 | **Sieve of Eratosthenes** | Counting Divisors (CSES) |
| 2 | **Modular inverse (Fermat's)** | Distributing Apples (CSES) — needs nCr mod p |
| 3 | **Z-algorithm** | String Matching (CSES) |
| 4 | **KMP** | Apply your LPS.java study — actually submit Finding Patterns |
| 5 | **Hashing** | String Hashing (CSES) — polynomial rolling hash |
| 6 | **Matrix exponentiation** | Fibonacci (n up to 10^18) |
| 7 | **Euler's totient** | Exponentiation II (CSES) |
| 8 | **Combinatorics (nCr table)** | Bracket Sequences I (CSES) |

### Modular Inverse Template
```java
// Fermat's little theorem: a^(-1) ≡ a^(p-2) mod p  (p prime)
static long modInverse(long a, long mod) {
    return modPow(a, mod - 2, mod);
}

static long modPow(long base, long exp, long mod) {
    long result = 1;
    base %= mod;
    while (exp > 0) {
        if ((exp & 1) == 1) result = result * base % mod;
        base = base * base % mod;
        exp >>= 1;
    }
    return result;
}

// Precompute factorials and inverse factorials for nCr
long[] fact = new long[MAXN], inv_fact = new long[MAXN];
fact[0] = 1;
for (int i = 1; i < MAXN; i++) fact[i] = fact[i-1] * i % MOD;
inv_fact[MAXN-1] = modInverse(fact[MAXN-1], MOD);
for (int i = MAXN-2; i >= 0; i--) inv_fact[i] = inv_fact[i+1] * (i+1) % MOD;

long nCr(int n, int r) {
    if (r < 0 || r > n) return 0;
    return fact[n] % MOD * inv_fact[r] % MOD * inv_fact[n-r] % MOD;
}
```

---

## Week 8: Contest Mode + Gap Filling

| Day | Activity |
|-----|----------|
| 1-2 | **Codeforces Virtual Contest** — Div 2, full 2-hour attempt |
| 3 | Review contest: analyze every unsolved problem, identify which technique was needed |
| 4-5 | **CSES Revisit** — re-solve 5 problems from memory (no notes) |
| 6 | **Second Virtual Contest** — aim for 3 problems solved |
| 7 | Identify weakest remaining topic → solve 3 more problems from it |

---

## Daily Routine Template

```
┌─────────────────────────────────────────────────┐
│           DAILY CP SESSION (2.5 hrs)            │
├─────────────────────────────────────────────────┤
│                                                 │
│  [0:00 - 0:10]  Read problem, understand fully  │
│  [0:10 - 0:25]  Think on paper — no code yet    │
│                  → Identify: STATE, TRANSITION   │
│                  → Draw examples                 │
│  [0:25 - 1:00]  Code the solution               │
│  [1:00 - 1:15]  Test with examples + edge cases │
│  [1:15 - 1:30]  Submit & debug if needed        │
│                                                 │
│  [1:30 - 2:00]  Problem #2 (same cycle)         │
│                                                 │
│  [2:00 - 2:30]  REVIEW                          │
│    → Write STATE/TRANSITION/BASE/ORDER comment  │
│    → Add to this plan: what pattern was it?     │
│    → If stuck > 45 min, read editorial, then    │
│      re-solve from scratch next day             │
│                                                 │
└─────────────────────────────────────────────────┘
```

---

## Progress Tracker

### DP Problems
- [ ] Dice Combinations
- [ ] Minimizing Coins
- [ ] Coin Combinations I
- [ ] Coin Combinations II
- [ ] Removing Digits
- [ ] Grid Paths (DP)
- [ ] Book Shop
- [ ] Edit Distance
- [ ] Rectangle Cutting
- [ ] Money Sums
- [ ] Removal Game
- [ ] Two Sets II
- [ ] Increasing Subsequence
- [ ] Projects
- [ ] Elevator Rides
- [ ] Counting Tilings
- [ ] Array Description
- [ ] Counting Numbers
- [ ] Counting Towers
- [ ] High Score

### Range Queries
- [ ] Static Range Sum Queries
- [ ] Static Range Minimum Queries
- [ ] Dynamic Range Sum Queries
- [ ] Dynamic Range Minimum Queries
- [ ] Range Xor Queries
- [ ] Range Update Queries
- [ ] Forest Queries
- [ ] Salary Queries

### Advanced Graphs
- [ ] Course Schedule
- [ ] Longest Flight Route
- [ ] Game Routes
- [ ] Road Reparation
- [ ] Planets and Kingdoms
- [ ] Cycle Finding
- [ ] Mail Delivery
- [ ] Company Queries I
- [ ] Company Queries II
- [ ] Distance Queries

### Strings & Math
- [ ] Counting Divisors
- [ ] Distributing Apples
- [ ] String Matching (Z-algo)
- [ ] Finding Patterns (KMP)
- [ ] String Hashing
- [ ] Fibonacci (matrix exp)
- [ ] Exponentiation II
- [ ] Bracket Sequences I

---

## Key Principles

1. **Think before you code.** 15 minutes on paper saves 45 minutes debugging.
2. **One topic at a time.** Don't jump to graphs while DP is still shaky.
3. **The 45-minute rule.** If stuck for 45 minutes, read the editorial. Then close it and re-solve from scratch. No shame — the goal is learning the pattern.
4. **Write the DP comment block** at the top of every DP solution. This builds pattern recognition over time.
5. **Revisit solved problems.** Can you re-solve Monday's problem on Friday without looking at your code? If not, you didn't learn it.
6. **Contest regularly.** Starting week 4, do one virtual Codeforces contest per week. Real time pressure reveals what you actually know vs. what you think you know.

---

## Milestone Checkpoints

| Checkpoint | When | You Should Be Able To |
|------------|------|-----------------------|
| **CP-1** | End of Week 2 | Solve any 1D/2D DP problem given 30 minutes |
| **CP-2** | End of Week 4 | Solve CSES DP section (~15 problems), handle bitmask DP |
| **CP-3** | End of Week 6 | Solve CSES Graph section fully, know MST/SCC/LCA |
| **CP-4** | End of Week 8 | Solve Codeforces Div2 A-D consistently in contest time (2 hrs) |

**Target by Week 8: Codeforces 1700-1800+ (Expert)**

---

*Plan created: February 24, 2026*
*Based on analysis of 53 existing solutions — see ai/research.md for full audit*