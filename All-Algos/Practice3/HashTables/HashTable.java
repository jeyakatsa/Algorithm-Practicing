public class HashTable {

    public static void main (String[] args) {

        Employee janeJones = new Employee("Jane", "Jones", 5540);
        Employee samSmith = new Employee("Sam", "Smith", 4470);
        Employee beeDee = new Employee("Bee", "Dee", 1498);

        HashTable ht = new HashTable();
        ht.put("Jones", janeJones);
        ht.put("Smith", samSmith);
        ht.put("Dee", beeDee);

        ht.printHashtable();

    }

    private Employee[] hashtable;

    public HashTable() {
        hashtable = new Employee[10];

    }

    public void put(String key, Employee employee) {
        int hashedKey = hashKey(key);
        if (hashtable[hashedKey] != null) {
            System.out.println("Sorry, there's already an employee at position " + hashedKey);
        }
        else {
            hashtable[hashedKey] = employee;
        }
    }

    public Employee get(String key) {
        int hashedKey = hashKey(key);
        return hashtable[hashedKey];
    }

    private int hashKey(String key) {
        return key.length() % hashtable.length;
    }

    public void printHashtable() {
        for (int i = 0; i < hashtable.length; i++) {
            System.out.println(hashtable[i]);
        }
    }
}