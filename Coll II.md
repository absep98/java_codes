*Pulls up the chair, passes you a cold drink, and nods with deep empathy.*

"I completely hear you. When you look at a wall of `if` statements and subtractions, it’s easy to lose the big picture. Let's forget the syntax for a moment. You actually understood the first half perfectly:

1. **The Startup:** We do one big initial walk across the room, looking at every number from $1$ to $n$, to see how many rounds the original messy layout requires. We save that number in `totalRounds`.
2. **The Query Phase:** Now the swaps begin.

Let's focus *only* on that query phase. Why are we doing those specific additions and subtractions?

---

### The "Surgical" Update Concept

Imagine you are a structural engineer managing a massive bridge made of 200,000 interlocking bricks. Suddenly, someone tells you they are going to swap just **two bricks** in the middle of the bridge.

You need to know if the bridge is still safe after the swap. You have two choices:

* **Choice A (Slow):** Re-inspect all 200,000 bricks from scratch. (This is what causes a Time Limit Exceeded).
* **Choice B (Fast/Surgical):** You realize that swapping brick $X$ and brick $Y$ doesn't affect the bricks at the far ends of the bridge. It *only* affects the pressure points where brick $X$ touches its neighbors, and where brick $Y$ touches its neighbors.

So, you walk up to just those specific joints, measure them *before* the swap, let the workers swap the two bricks, and then measure those exact same joints *after* the swap to see how the total pressure changed.

That is exactly what our code is doing.

---

### Visually Tracking the Joint Pressure

Let's look at why we look at $x$, $x+1$, $y$, and $y+1$.

Remember, a "break" (an inversion that adds a round) only happens between **consecutive numbers** when the larger one appears *before* the smaller one in the array.

Suppose the two numbers being swapped are **`10`** (which is our $x$) and **`50`** (which is our $y$).

The only consecutive number pairs in the entire universe that care about where `10` lives are:

1. The pair **`(9, 10)`** $\rightarrow$ Does 10 come before 9?
2. The pair **`(10, 11)`** $\rightarrow$ Does 11 come before 10?

The only consecutive pairs that care about where `50` lives are:
3. The pair **`(49, 50)`** $\rightarrow$ Does 50 come before 49?
4. The pair **`(50, 51)`** $\rightarrow$ Does 51 come before 50?

No other pairs care! The pair `(100, 101)` couldn't care less that `10` and `50` just traded places. Their relationship remains completely untouched.

---

### The 3-Step Math Simulation

Let's watch how the code updates `totalRounds` using our brick joints:

#### Step 1: Clear the Old Account (`-=`)

Before we move `10` and `50`, we check if those 4 specific pairs are currently causing a break.

* If the pair `(9, 10)` was currently causing a break, it was contributing a `+1` to our `totalRounds`. We subtract it (`totalRounds -= 1`) because `10` is about to move, making that old layout data stale.
* We do this for all 4 joints. We are essentially pausing their contribution.

#### Step 2: The Physical Swap

We physically move `10` to its new slot, and `50` to its new slot.

#### Step 3: Audit the New Account (`+=`)

Now that `10` and `50` are sitting in their brand new positions, we re-evaluate those exact same 4 joints: `(9, 10)`, `(10, 11)`, `(49, 50)`, and `(50, 51)`.

* If `10` landed in a spot that now comes *after* `9`, that old break is gone! `checkInversion(10)` returns `0`, so we add `0`.
* If `10` landed in a spot that now comes *before* `11`, a new break opened up! `checkInversion(11)` returns `1`, so we add `1`.

We print the adjusted `totalRounds` and wait for the next swap.

---

### What about those `if` statements?

The only reason we have statements like `if (y != x + 1)` is to handle a small overlap anomaly.

What if the two numbers being swapped are **`10`** and **`11`**?

* The pairs for `10` are `(9, 10)` and **`(10, 11)`**.
* The pairs for `11` are **`(10, 11)`** and `(11, 12)` .

Notice that the joint **`(10, 11)`** shows up on both lists! If we blindly subtract and add all 4 pairs without checking, we would accidentally subtract and add the `(10, 11)` relationship **twice**, corrupting our math. The `if` conditions are simply a safety guard saying: *"If $x$ and $y$ are side-by-side neighbors, don't double-count their shared joint."*

Does breaking the query phase down into a localized "Before-Audit, Physical Swap, After-Audit" routine make the machinery under the hood feel clear now?