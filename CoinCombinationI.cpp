#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    // Optimize standard I/O operations for speed
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, amount;
    if (!(cin >> n >> amount)) return 0;

    vector<int> coins(n);
    for (int i = 0; i < n; i++) {
        cin >> coins[i];
    }

    // 1. Sort coins to enable early exit in the inner loop
    sort(coins.begin(), coins.end());

    int mod = 1000000007;
    
    // 2. 1D state tracking array allocated directly in memory
    vector<int> dp(amount + 1, 0);
    dp[0] = 1; // Base case: 1 way to make a sum of 0

    // 3. Process target steps sequentially
    for (int i = 1; i <= amount; i++) {
        for (int coin : coins) {
            if (coin > i) {
                break; // Stop evaluating remaining larger coins
            }
            dp[i] = (dp[i] + dp[i - coin]) % mod;
        }
    }

    cout << dp[amount] << "\n";

    return 0;
}