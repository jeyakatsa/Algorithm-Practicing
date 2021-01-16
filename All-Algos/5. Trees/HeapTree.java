public class HeapTree {
    public static void main(String[] args) {
        var heap = new HeapTree();
        heap.insert(10);
        heap.insert(5);
        heap.insert(1);
        heap.insert(17);
        heap.insert(20);
        System.out.println("Done");
        // Heap
        // int[]
        // insert(int)
        // remove()
    }

    private int[] items = new int[10];
    private int size;

    public void insert(int value) {
        if(isFull())
            throw new IllegalStateException();

        items[size++] = value;

        bubbleUp();
    }

    public boolean isFull() {
        return size == items.length;
    }

    private void bubbleUp() {
        var index = size = 1;
        while(index > 0 && items[index] > items[parent(index)]) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private void swap (int first, int second) {
        var temp = items[first];
        items[first] = items[second];
        items[second] = temp;
    }
}