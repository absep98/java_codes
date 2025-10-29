import java.util.*;

public class MaxHeap {
    List<Integer> heap = new ArrayList<>();

    public void insert(int value) {
        heap.add(value);
        int current = heap.size() - 1;

        // Bubble up to maintain max-heap property
        while (current > 0) {
            int parent = (current - 1) / 2;
            if (heap.get(current) > heap.get(parent)) {
                // Swap current and parent
                int temp = heap.get(current);
                heap.set(current, heap.get(parent));
                heap.set(parent, temp);

                current = parent; // Move up
            } else {
                break; // Heap property satisfied
            }
        }
    }

    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap();
        int[] arr = {10, 23, 45, 5, 67, 12};

        for (int val : arr) {
            maxHeap.insert(val);
        }

        System.out.println("Max Heap: " + maxHeap.heap);
    }
}