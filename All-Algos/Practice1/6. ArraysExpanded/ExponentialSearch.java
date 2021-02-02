public class ExponentialSearch {
    public static void main (String[] args) {
        int[] numbers = {1,2,3,4,5,6,7};
        var search = new ExponentialSearch();
        var index = search.exponentialSearch(numbers, 5);
        System.out.println(index);
    }

    public int exponentialSearch(int[] array, int target) {
        int bound = 1;
        while (bound < array.length && array[bound] < target)
            bound *= 2;

        int left = bound / 2;
        int right = Math.min(bound, array.length - 1);    

        return binarySearchRec(array, target, left, right);
    }

    private int binarySearchRec(
        int[] array, int target, int left, int right) {
        
        if (right < left)
            return -1;

        int middle = (left + right) / 2;    
        
        if (array[middle] == target)
            return middle;

        if (target < array[middle])
            return binarySearchRec(array, target, left, middle - 1);
            
        return binarySearchRec(array, target, middle + 1, right);
        
        
    }
}
