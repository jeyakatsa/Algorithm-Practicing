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

        System.out.println("Retrieve key Smith: " + ht.get("Smith"));

    }

    public class StoredEmployee {
        public String key;
        public Employee employee;

        public StoredEmployee(String key, Employee employee) {
            this.key = key;
            this.employee = employee;
        }
    }

    private StoredEmployee[] hashtable;

    public HashTable() {
        hashtable = new StoredEmployee[10];

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
            hashtable[hashedKey] = new StoredEmployee (key, employee);
        }
    }

    public Employee get(String key) {
        int hashedKey = findKey(key);
        if (hashedKey == -1) {
            return null;
        }
        return hashtable[hashedKey].employee;
    }

    private int hashKey(String key) {
        return key.length() % hashtable.length;
    }

    private int findKey (String key) {
        int hashedKey = hashKey(key);
        if (hashtable[hashedKey] != null &&
            hashtable[hashedKey].key.equals(key)) {
            return hashedKey;
        }

        int stopIndex = hashedKey;
        if (hashedKey == hashtable.length - 1) {
            hashedKey = 0;
        }
        else {
            hashedKey++;
        }

        while (hashedKey != stopIndex && 
            hashtable[hashedKey] != null &&
            !hashtable[hashedKey].key.equals(key)) {
            hashedKey = (hashedKey + 1) % hashtable.length;
        }

        if(stopIndex == hashedKey) {
            return -1;
        }
        else {
            return hashedKey;
        }
        
    }

    private boolean occupied (int index) {
        return hashtable[index] != null;
    }

    public void printHashtable() {
        for (int i = 0; i < hashtable.length; i++) {
            if (hashtable[i] == null) {
                System.out.println("empty");
            }
            else {
                System.out.println("Position " + i + ": " + hashtable[i].employee);
            }
        }
    }
}