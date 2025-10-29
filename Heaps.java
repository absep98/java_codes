import java.util.*;
class Heaps {
    public static void main(String[] args) {
        int arr[]={100 , 20, 30, 40, 90};

        PriorityQueue<Integer> low = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> high = new PriorityQueue<>();

        double median = 0;
        for(int i = 0 ; i < arr.length ; i++){
            if(low.isEmpty() || arr[i] <= low.peek()){
                low.add(arr[i]);
            } else {
                high.add(arr[i]);
            }

            if(high.size() > low.size()){
                low.add(high.poll());
            } else if (low.size()-high.size()>1){
                high.add(low.poll());
            }

            if(low.size() == high.size()){
                median = (low.peek() + high.peek())/2.0;
            } else {
                median = low.peek();
            }

            System.out.println("current median : "+median+" ");
        }

    }   
}
