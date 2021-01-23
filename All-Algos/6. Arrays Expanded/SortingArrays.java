public class SortingArrays {
    public static void main(String[] args) {
        var sort = new SortingArrays();
        var sorted = sort.bubbleSort(4, 5, 6, 7, 1, 0);
        System.out.println(sorted);

    }

    public int[] bubbleSort (int[] list) {
        int i, j, temp = 0;
        for(i=0; i<list.length - 1; i++) {
            for(j=0; j<list.length -1 - i; j++) {
                if(list[j] > list[j+1]) {
                    temp = list[j];
                    list[j] = list[j+1];
                    list[j+1] = temp;
                }
            }
        }
        return list;
    }
}