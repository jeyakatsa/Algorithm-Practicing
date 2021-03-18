public class HashTable {

    public static void main (String[] args) {

        Employee janeJones = new Employee("Jane", "Jones", 5540);
        Employee samSmith = new Employee("Sam", "Smith", 4470);
        Employee beeDee = new Employee("Bee", "Dee", 1498);
        Employee marySmith = new Employee("Mary", "Smith", 983);
        Employee billEnd = new Employee("Bill", "End", 38);

        HashTable ht = new HashTable();
        ht.put("Jones", janeJones);
        ht.put("Smith", samSmith);
        ht.put("Dee", beeDee);
        ht.put("Mary", marySmith);

        ht.printHashtable();

        System.out.println("Retrieve key Dee: " + ht.get("Dee"));

    }

    private Employee[] hashtable;

    public HashTable() {
        hashtable = new Employee[10];

    }

    public void put(String key, Employee employee) {
        int hashedKey = hashKey(key);
        if (occupied(hashedKey)) {
            int stopIndex = hashedKey;
            if (hashedKey == hashtable.length - 1) {
                hashedKey = 0;
            }
            else {
                hashedKey++;
            }

            while (occupied(hashedKey) && hashedKey != stopIndex) {
                hashedKey = (hashedKey + 1) % hashtable.length;
            }
        }
        if (occupied(hashedKey)) {
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

    private boolean occupied (int index) {
        return hashtable[index] != null;
    }

    public void printHashtable() {
        for (int i = 0; i < hashtable.length; i++) {
            System.out.println(hashtable[i]);
        }
    }
}