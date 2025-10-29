import java.util.*;

class Main {

    public static int partition(int arr[], int st, int ed){
        int j = st, i = st;
        int pivot = arr[ed];
        while(j < ed){
            if(arr[j] < pivot){
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
            }
            j++;
        }
        int temp = arr[i];
        arr[i] = arr[ed];
        arr[ed] = temp;
        return i;
    }

    public static void solve(int arr[], int st, int ed){
        if(st >= ed){
            return;
        }
        int partitionIdx = partition(arr, st, ed);
        solve(arr, st, partitionIdx-1);
        solve(arr, partitionIdx+1, ed);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        int arr[] = new int[n];
        for(int i = 0 ; i < n ; i++){
            arr[i] = sc.nextInt();
        }

        solve(arr, 0, n-1);
        
        for(int num : arr){
            System.out.print(num + " ");
        }

    }
}
