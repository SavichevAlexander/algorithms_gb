public class heapsort {
    public static void sort(int[] array) {
        // Building a heap (rearrange the array)
        for (int i = array.length/2 -1; i >= 0; i--) // We divide the array in half so that the traversal does not start from the last row of the tree
            heapify(array, array.length, i);   

        // Retrieving elements from the heap one by one
        for (int i = array.length - 1; i >= 0; i--) {
            // Move the current root to the end
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            // Calling the heapify procedure on the reduced heap
            heapify(array, i, 0);
        }

    }

    private static void heapify(int[] array, int heapSize, int rootIndex){
        int largest = rootIndex; // Initialize the largest element as the root
        int leftChild = 2 * rootIndex + 1; // Left = 2 * rootIndex + 1
        int rightChild = 2 * rootIndex + 2; // Right = 2 * rootIndex + 2
        

        // If the left element is greater than the root
        if (leftChild < heapSize && array[leftChild] > array[largest])
            largest = leftChild;

        // If the right element is greater than the largest element so far
        if (rightChild < heapSize && array[rightChild] > array[largest])

        largest = rightChild;
        
        // If the largest element is not the root
        
         if (largest != rootIndex) {
            int temp = array[rootIndex];
            array[rootIndex] = array[largest];
            array[largest] = temp;
            
            // Recursively convert the affected subtree into a binary heap

            heapify(array, heapSize, largest);
         }
    }   
}