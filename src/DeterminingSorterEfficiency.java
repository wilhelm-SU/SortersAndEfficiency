import java.util.Random;
public class DeterminingSorterEfficiency {

    public int[] selectionSort(int[] inputArray){
        for(int i = 0; i < inputArray.length; i++){
            int smallestElement = inputArray[i];
            int smallestElementIndex = i;
            for(int j = i + 1; j < inputArray.length; j++){
                if(smallestElement > inputArray[j]){
                    smallestElement = inputArray[j];
                    smallestElementIndex = j;
                }
            }
            inputArray[smallestElementIndex] = inputArray[i];
            inputArray[i] = smallestElement;
        }
        return inputArray;
    }

    public int[] bubbleSort(int[] inputArray){
        boolean done = false;
        while(!done){
            boolean swapped = false;
            for(int i = 0; i < inputArray.length-1; i++){
                if(inputArray[i] > inputArray[i+1]){
                    int temp = inputArray[i];
                    inputArray[i] = inputArray[i+1];
                    inputArray[i+1] = temp;
                    swapped = true;
                }
            }
            if(!swapped){
                done = true;
            } else{
                done = false;
            }
        }
        return inputArray;
    }

    public int[] insertionSort(int[] inputArray){
        for(int i = 1; i < inputArray.length; i++){
            int temp = inputArray[i];
            int j = i - 1;

                while (j >= 0 && inputArray[j] > temp) {
                    inputArray[j + 1] = inputArray[j];
                    j = j - 1;
                }
            inputArray[j+1] = temp;
        }
        return inputArray;
    }

    //Code for the mergeSort method and the mergeHelper method was inherited from Bro Code on YouTube and implemented accordingly
    //https://www.youtube.com/watch?v=3j0SWDX4AtU&ab_channel=BroCode
    public int[] mergeSort(int[] inputArray){
        int arrayLength = inputArray.length;

        if(arrayLength  <= 1){
            return inputArray;
        }
        else {

            int midpoint = arrayLength / 2;
            int[] leftArray = new int[midpoint];
            int[] rightArray = new int[arrayLength - midpoint];

            int j = 0;
            for (int i = 0; i < arrayLength; i++) {
                if (i < midpoint) {
                    leftArray[i] = inputArray[i];
                } else {
                    rightArray[j] = inputArray[i];
                    j++;
                }
            }
            mergeSort(leftArray);
            mergeSort(rightArray);
            mergeHelper(leftArray, rightArray, inputArray);
        }
        return inputArray;
    }

    private void mergeHelper(int[] leftArray, int[] rightArray, int[]inputArray){
        int leftArraySize = inputArray.length / 2;
        int rightArraySize = inputArray.length - leftArraySize;
        int i = 0;
        int left = 0;
        int right = 0;

        while(left < leftArraySize && right < rightArraySize){
            if(leftArray[left] < rightArray[right]){
                inputArray[i] = leftArray[left];
                i++;
                left++;
            }
            else{
                inputArray[i] = rightArray[right];
                i++;
                right++;
            }
        }
        while(left < leftArraySize){
            inputArray[i] = leftArray[left];
            i++;
            left++;
        }
        while(right < rightArraySize){
            inputArray[i] = rightArray[right];
            i++;
            right++;
        }
    }

    //Code for the shellSort method was inherited from geeksforgeeks.org and implemented accordingly
    //https://www.geeksforgeeks.org/shell-sort/
    public int[] shellSort(int[] inputArray){
        int arrayLength = inputArray.length;
        for(int gap = arrayLength / 2; gap > 0; gap /= 2){
            for(int i = gap; i < arrayLength; i += 1){
                int temp = inputArray[i];
                int j;
                for(j = i; j >= gap && temp < inputArray[j - gap]; j -= gap){
                    inputArray[j] = inputArray[j - gap];
                }
                inputArray[j] = temp;
            }
        }
        return inputArray;
    }

    //Code for the quickSort method and the partition method was inherited from Bro Code on YouTube and implemented accordingly
    //https://www.youtube.com/watch?v=Vtckgz38QHs&ab_channel=BroCode
    public int[] quickSort(int[] inputArray, int start, int end){
        if(end <= start){
            return inputArray;
        }
        else {
            int pivot = partition(inputArray, start, end);
            quickSort(inputArray, start, pivot - 1);
            quickSort(inputArray, pivot + 1, end);
        }
        return inputArray;
    }

    private int partition(int[] inputArray, int start, int end){
        int pivot = inputArray[end];
        int j = start;
        int i = start - 1;
        int temp;

        for (; j <= end - 1; j++) {
            if (inputArray[j] < pivot) {
                i++;
                temp = inputArray[i];
                inputArray[i] = inputArray[j];
                inputArray[j] = temp;
            }
        }

        //Pivot is now in proper place
        i++;
        temp = inputArray[i];
        inputArray[i] = inputArray[end];
        inputArray[end] = temp;
        return i;
    }

    //Code for the heapSort method and helper methods was inherited from geeksforgeeks.org and implemented accordingly
    //https://www.geeksforgeeks.org/heap-sort/
    public int[] heapSort(int[] inputArray){
        int arrayLength = inputArray.length;
        int temp;

        for(int i = (arrayLength / 2) - 1; i >= 0; i--){
            heapify(inputArray, arrayLength, i);
        }

        for(int i = arrayLength - 1; i >= 0; i--){
            temp = inputArray[0];
            inputArray[0] = inputArray[i];
            inputArray[i] = temp;
            heapify(inputArray, i, 0);
        }
        return inputArray;
    }

    private void heapify(int[] inputArray, int end, int root){
        int largest = root;
        int temp;
        int leftIndex = (2*root) + 1;
        int rightIndex = (2*root) + 2;

        if(leftIndex < end && inputArray[leftIndex] > inputArray[largest]){
            largest = leftIndex;
        }
        if(rightIndex < end && inputArray[rightIndex] > inputArray[largest]){
            largest = rightIndex;
        }
        if(largest != root){
            temp = inputArray[root];
            inputArray[root] = inputArray[largest];
            inputArray[largest] = temp;

            heapify(inputArray, end, largest);
        }
    }

    Random RNG = new Random();
    int[] theArrayToBeSorted;

    public void graphingData(){
        int[] differentArrayLengths = {10000, 20000, 30000, 40000, 50000, 60000, 70000, 80000, 90000, 100000, 110000, 120000, 130000, 140000, 150000, 160000, 170000, 180000, 190000, 200000};

        for(int i = 0; i < differentArrayLengths.length; i++) {
            theArrayToBeSorted = new int[differentArrayLengths[i]];
            for (int j = 0; j < differentArrayLengths[i]; j++) {
                theArrayToBeSorted[j] = RNG.nextInt(100);
            }

            int[] selectionSortArray = theArrayToBeSorted.clone();
            int[] bubbleSortArray = theArrayToBeSorted.clone();
            int[] insertionSortArray = theArrayToBeSorted.clone();
            int[] mergeSortArray = theArrayToBeSorted.clone();
            int[] quickSortArray = theArrayToBeSorted.clone();
            int[] heapSortArray = theArrayToBeSorted.clone();
            int[] shellSortArray = theArrayToBeSorted.clone();


            System.out.println("Array length: " + differentArrayLengths[i] + ":");

            System.out.println("Selection Sort:");
            long selectionSortBeforeTime = System.currentTimeMillis();
            selectionSort(selectionSortArray);
            System.out.println(System.currentTimeMillis() - selectionSortBeforeTime);

            System.out.println("Bubble Sort:");
            long bubbleSortBeforeTime = System.currentTimeMillis();
            bubbleSort(bubbleSortArray);
            System.out.println(System.currentTimeMillis() - bubbleSortBeforeTime);

            System.out.println("Insertion Sort:");
            long insertionSortBeforeTime = System.currentTimeMillis();
            insertionSort(insertionSortArray);
            System.out.println(System.currentTimeMillis() - insertionSortBeforeTime);

            System.out.println("Merge Sort:");
            long mergeSortBeforeTime = System.currentTimeMillis();
            mergeSort(mergeSortArray);
            System.out.println(System.currentTimeMillis() - mergeSortBeforeTime);

            System.out.println("Quick Sort:");
            long quickSortBeforeTime = System.currentTimeMillis();
            quickSort(quickSortArray, 0, quickSortArray.length - 1);
            System.out.println(System.currentTimeMillis() - quickSortBeforeTime);

            System.out.println("Heap Sort:");
            long heapSortBeforeTime = System.currentTimeMillis();
            heapSort(heapSortArray);
            System.out.println(System.currentTimeMillis() - heapSortBeforeTime);

            System.out.println("Shell Sort:");
            long shellSortBeforeTime = System.currentTimeMillis();
            shellSort(shellSortArray);
            System.out.println(System.currentTimeMillis() - shellSortBeforeTime);
        }
    }

    public String toString(int[] inputArray){
        String x = "";
        for(int i = 0; i < inputArray.length; i++){
            x = x + inputArray[i] + ", ";
        }
        return x;
    }

    public void validateSorters(){
        int[] x = {3, 6, 9, 1, 3, 5, 7};
        System.out.println("Array to be sorted: " + toString(x));

        int[] selectionSortedArray = selectionSort(x.clone());
        int[] bubbleSortedArray = bubbleSort(x.clone());
        int[] insertionSortedArray = insertionSort(x.clone());
        int[] mergeSortedArray = mergeSort(x.clone());
        int[] quickSortedArray = quickSort(x.clone(), 0, x.length - 1);
        int[] heapSortedArray = heapSort(x.clone());
        int[] shellSortedArray = shellSort(x.clone());

        System.out.println(toString(selectionSortedArray));
        System.out.println(toString(bubbleSortedArray));
        System.out.println(toString(insertionSortedArray));
        System.out.println(toString(mergeSortedArray));
        System.out.println(toString(quickSortedArray));
        System.out.println(toString(heapSortedArray));
        System.out.println(toString(shellSortedArray));
        System.out.println();
    }
}
