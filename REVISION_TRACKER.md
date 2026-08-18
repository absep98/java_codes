# Revision Tracker

Single source of truth for spaced-repetition review, replacing the old Google Sheet
(`Revision_sheet_UPGRADED`), which had duplicated/contradictory sections and didn't
match what's actually in this repo.

## Protocol
- **Day 0** — solve it, write the Key Idea in one line, set Confidence.
- **Day +1** — re-solve cold (no notes). If stuck, stays 🔴, still counts as attempted.
- **Day +4** — code it again and explain the approach out loud.
- **Day +7** — re-read your Key Idea note, trace through mentally, no code.
- **Day +14** — full cold, timed re-solve. Under 2× original time → 🟢 (owned).
- Never copy-paste your own old solution during a review — trace it manually first.

**Confidence:** 🟢 owned · 🟡 solved but shaky · 🔴 buggy/needs rewrite · ⬜ not yet self-assessed

Update `Last Reviewed` / `Next Review` yourself as you go — I'm not going to guess dates.

---

## Introductory Problems
Source: `CSES Problems/Introductory Problems/*.java` (21 files) + confidence carried over
from the one internally-consistent table in the old sheet (its "Phase 1A" journal, which summed
to 19 owned / 6 shaky — everything else in that sheet contradicted itself, so only this table
is trusted).

| Problem | File | Key Idea | Confidence | Last Reviewed | Next Review |
|---|---|---|---|---|---|
| Increasing Array | IncreasingArray.java | Greedy: bump v[i] up to v[i-1] if smaller, accumulate the diff | 🟢 | | |
| Permutations | Permutations.java | Adjacent-diff-1 pairs always have opposite parity — group evens then odds | 🟢 | | |
| Number Spiral | NumberSpiral.java | Value at (x,y) depends only on k=max(x,y) and its parity | 🟢 | | |
| Two Knights (Knight.java) | Knight.java | Total pairs C(k²,2) minus attacking pairs 4(k-1)(k-2) | 🟢 | | |
| Two Sets | TwoSets.java | Odd total sum → impossible; else greedily assign n..1 to hit half-sum | 🟢 | | |
| Bit Strings | BitStrings.java | Binary exponentiation: pow(a,n) = pow(a,n/2)² × (a if odd) | 🟢 | | |
| Trailing Zeros | Trailing.java | Count factors of 5 in n! (Legendre's formula) | 🟢 | | |
| Coin Piles | CoinPiles.java | (a+b)%3==0 AND max(a,b) ≤ 2×min(a,b) | 🟢 | | |
| Tower of Hanoi | Hanoi.java | solve(from,to,aux,n): move n-1 out of the way, move biggest, move n-1 back | 🟢 | | |
| Palindrome Reorder | PalindromeReorder.java | Freq count → build left half (freq/2 of each char) + mirror | 🟢 | | |
| Gray Code | GrayCode.java | Reflect previous list, prefix old half with 0, new half with 1 | 🟢 | | |
| Chessboard and Queens | ChessBoardQueen.java | Backtracking with col/diag boolean arrays, d1=r+c, d2=r-c+(n-1) | 🟢 | | |
| Knight Moves Grid | KnightMovesGrid.java | BFS, 8 directions, visited[][] | 🟢 | | |
| Mex Grid Construction | MexGridConstruction.java | i XOR j gives the mex value directly | 🟢 | | |
| Grid Coloring I | GridColoringI.java | Checkerboard pattern | 🟢 | | |
| Apple Division | AppleDivision.java | Recursive subset partition; bitmask alt for n≤20 | 🟡 | | |
| Digit Queries | DigitQueries.java | Digit-by-digit bucketing by number-length ranges | 🟡 | | |
| Grid Path Description | GridPath.java | Backtracking with pruning | 🟡 | | |
| Raab Game I | RaabGame.java | Game-theory greedy | 🟡 | | |
| String Reorder | StringReorder.java | Greedy: always place the currently-most-frequent char ≠ prev | 🟡 | | |
| Creating Strings | CreatingStrings.java | **Relearn properly**: sort chars → backtrack(used[]) → skip if `chars[i]==chars[i-1] && !used[i-1]`. Old solve used a TreeSet shortcut, not the real pattern. | 🟡 | | |

Not in this repo (only exist as a code snippet inside the old sheet's notes — worth
committing if you still want them tracked): **Weird Algorithm (Collatz)**, **Missing Number
(XOR)**, **Repetitions** (streak/sliding-window), **Sum of Digits 1 to n**.

---

## Sorting & Searching
Source: `CSES Problems/Sorting and Searching/*.java` (33 files). No reliable prior
confidence data exists for these — the old sheet claimed this phase "hadn't started,"
which is wrong (the code is already there). Treat every row as ⬜ until you do a first
cold pass and fill it in yourself.

Apartments, ArrayDivision, CollectingNumbers, CollectingNumbersII, DistinctNumbers,
DistinctValueSubarray, DistinctValuesSubarraysII, DistinctValuesSubsequences,
FactoryMachines, FerrisWheel, JosephusProblemI, JosephusProblemII, MaximumSubarraySum,
MaximumSubarraySumII, MissingCoinSum, MovieFest, NearestSmallerValues, NestedRangesCheck,
NestedRangesCount, Playlist, ResturantCustomers, RoomAllocation, StickLengths,
SubarrayDivisibility, SubarraySumsI, SubarraySumsII, SumofFourValues, SumofThreeValues,
SumofTwoValues, TasksandDeadlines, Towers, TrafficLights, ConcertsTicket

---

## Graph Algorithms
Source: `GraphAlgorithms.java` (root bundle — this is ahead of the mostly-empty
`CSES Problems/Graph Algorithms/` folder, which only has 10 of these). Currently active
in `main()`: `RoadConstruction`. Same as above — no confidence data exists yet, ⬜ until
you self-assess.

CountingRooms, Labyrinth, BuildingRoads, MessageRoute, BuildingTeams, RoundTrip, Monsters,
ShortestRoutesI, ShortestRoutesII, HighScore, FlightDiscount, CycleFinding, FlightRoutes,
RoundTripII, CourseSchedule, LongestFlightRoute, GameRoutes, Investigation, RoadConstruction

---

## Dynamic Programming
Source: `DynamicProgramming.java` (root bundle — the `CSES Problems/Dynamic Programming/`
folder is currently empty). Currently active in `main()`: `IncreasingSubsequenceII`. ⬜
until self-assessed.

DiceCombinations, MinimizingCoins, CoinCombinationsI, CoinCombinationsII, RemovingDigits,
GridPathsI, BookShop, ArrayDescription, MoneySums, RemovalGame, Projects, EditDistance,
TwoSetsII, LongestCommonSubsequence, IncreasingSubsequence, IncreasingSubsequenceII,
RectangleCutting

---

## Extra practice (not CSES, kept from old sheet's notes — no file in this repo)
Edges on Shortest Path (double Dijkstra), Number of Ways to Arrive at Destination
(Dijkstra + path counting), All Paths From Source to Target (DFS backtracking), Center
of Graph (degree counting), Substrings That Begin and End With the Same Letter (prefix
count of char occurrences), Count of Interesting Subarrays (prefix sum mod k).

---

## Today
1. Cold re-solve **Creating Strings** — no notes, focus on the real backtracking pattern above.
2. Cold re-solve one more 🟡: **Digit Queries** or **Apple Division**.
3. Pick one problem from Sorting & Searching or Graph Algorithms you haven't touched in
   a while, re-solve cold, and give it its first real Confidence rating in this file.
