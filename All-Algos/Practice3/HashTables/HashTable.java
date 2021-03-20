import java.util.LinkedList;

public class HashTable {

    public static void main (String[] args) {

        Employee janeJones = new Employee("Jane", "Jones", 5540);
        Employee johnDoe = new Employee("John", "Doe", 4470);
        Employee marySmith = new Employee("Mary", "Smith", 1498);
        Employee mikeWilson = new Employee("Mike", "Wilson", 983);
        Employee billEnd = new Employee("Bill", "End", 38);

        HashTable ht = new HashTable();
        ht.put("Jones", janeJones);
        ht.put("Doe", johnDoe);
        ht.put("Wilson", mikeWilson);
        ht.put("Smith", marySmith);

        ht.printHashtable();

        System.out.println("Retrieve key Wilson: " + ht.get("Wilson"));
        System.out.println("Retrieve key Smith: " + ht.get("Smith"));

        ht.remove("Wilson");
        ht.remove("Jones");
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

    public class ChainedHashtable {
        private LinkedList<StoredEmployee>[] hashtable;

        public ChainedHashtable() {
            hashtable = new LinkedList[10];
            for(int i = 0; i < hashtable.length; i++) {
                hashtable[i] = new LinkedList<StoredEmployee>();
                
            }
        }

        public void put(String key, Employee employee) {
            int hashedKey = hashKey(key);
            hashtable[hashedKey].add(new StoredEmployee(key, employee));

        }

        private int hashKey(String key) {
            return key.length() % hashtable.length;
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
            !hashtable[hashedKey].key.equals(key)) 
            {
            hashedKey = (hashedKey + 1) % hashtable.length;
        }

        if(hashtable[hashedKey] != null &&
            hashtable[hashedKey].key.equals(key)) {
            return hashedKey;
        }
        else { 
            return -1;
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

    public Employee remove(String key) {
        int hashedKey = findKey(key);
        if (hashedKey == -1) {
            return null;
        }

        Employee employee = hashtable[hashedKey].employee;
        hashtable[hashedKey] = null;

        StoredEmployee[] oldHashtable = hashtable;
        hashtable = new StoredEmployee[oldHashtable.length];
        for (int i = 0; i < oldHashtable.length; i++) {
            if (oldHashtable[i] != null) {
                put(oldHashtable[i].key, oldHashtable[i].employee);
            }
        }
        return employee;
    }
}