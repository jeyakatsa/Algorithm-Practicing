public class LinearSearch {
    public static void main (String[] args){
        int[] numbers = {4,3,2,6,7,1,0,9};
        var search = new LinearSearch();
        var index = search.linearSearch(numbers, 7);
        System.out.println(index);
    }

    public int linearSearch(int[] array, int target) {
        for(var i = 0; i < array.length; i++)
            if (array[i] == target)
                return i;        
        return -1;        
    }
}
