import java.util.Hashtable;

public class HashTable {
    public static void main(String[] args) {
        // HashTable
        Hashtable<Integer, String> ht = new Hashtable<>();
        // put(k, v)
        ht.put(1, "one");
        ht.put(2, "two");
        h1.put(3, "three");
        // get(k) : v
        // remove(k)
        // k: int
        // v: string
        // Collisions: chaining
        // LinkedList<Entry>[]
        // [ LL, LL, LL ]

    }

    //Node of chains
    public class HashNode<K, V> {
        K key;
        V value;

        // Reference to next node
        HashNode<K, V> next;

        // Constructor
        public HashNode (K key, V value){
            this.key = key;
            this.value = value; 
        }
    
    }





    
}