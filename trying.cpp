// Online C++ compiler to run C++ program online
#include <iostream>
using namespace std;
int main() {
    // Write C++ code here
    int n;
    cin>>n;
     while (true) {
        cout<<n<<" ";
        if (n == 1) break;
        n = (n % 2 == 0) ? n / 2 : n * 3 + 1;
    }
    return 0;
}