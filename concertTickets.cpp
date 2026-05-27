#include <iostream>
#include <set>

using namespace std;

int main() {
    // Fast I/O lines - essential for passing strict competitive programming limits
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m;
    if (!(cin >> n >> m)) return 0;

    // A multiset handles duplicates and stays sorted automatically (like TreeMap)
    multiset<int> tickets;
    for (int i = 0; i < n; i++) {
        int ticketPrc;
        cin >> ticketPrc;
        tickets.insert(ticketPrc);
    }

    for (int i = 0; i < m; i++) {
        int custWilling;
        cin >> custWilling;

        // upper_bound(X) returns an iterator to the first element GREATER than X.
        auto it = tickets.upper_bound(custWilling);

        // If upper_bound points to the very beginning, it means NO ticket is <= custWilling
        if (it == tickets.begin()) {
            cout << "-1\n";
        } else {
            // Step back by 1 iterator position to find the largest element <= custWilling
            it--; 
            
            cout << *it << "\n";
            
            // Erase by iterator rather than by value so C++ only removes ONE instance of the price
            tickets.erase(it); 
        }
    }

    return 0;
}