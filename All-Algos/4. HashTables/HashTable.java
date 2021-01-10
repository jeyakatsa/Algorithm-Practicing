import java.util.LinkedList;

public class HashTable {

    public class Entry {
        private int key;
        private String value;

        // Constructor
        public Entry(int key, String value) {
            this.key = key;
            this.value = value; 
        }
    }

    private LinkedList<Entry>[] entries = new LinkedList[5];

    // put(k, v)
    public void put (int key, String value) {
        var index = hash(key);
        if (entries[index] == null)
            entries[index] = new LinkedList<>();
        
        var bucket = entries[index];
        for (var entry : bucket)
            if (entry.key == key) {
                entry.value = value;
                return;
            }
        
        bucket.addLast(new Entry(key, value));    
    }

    // get(k) : v
    public String get(int key) {
        var index = hash(key);
        var bucket = entries[index];
        if (bucket != null) {
            for (var entry : bucket)
                if (entry.key == key)
                return entry.value;
        }
        return null;
    }

    // remove(k)
    public void remove (int key) {
        var index = hash(key);
        var bucket = entries[index];
        if (bucket == null)
            throw new IllegalStateException();
        for (var entry : bucket) {
            if (entry.key == key) {
                bucket.remove(entry);
                return;
            }
        }
        throw new IllegalStateException(); 
    }

    private int hash(int key) {
        return key % entries.length;
    }

    public static void main(String[] args) {
        // HashTable
        HashTable table = new HashTable();
        table.put(6, "A"); 
        table.put(8, "B");
        table.put(11, "C");
        table.remove(60);
        System.out.println(table.get(6));
    }
}