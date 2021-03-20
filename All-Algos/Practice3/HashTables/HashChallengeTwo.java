import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class HashChallengeTwo {
    //remove duplicate items from linked list
    //solution must use JDK linkedList class
    //Solution must use HashMap
    public static void main (String[] args) {
        LinkedList<Employee> employees = new LinkedList<>();
        employees.add(new Employee("Jane", "Jones", 123));
        employees.add(new Employee("John", "Doe", 5678));
        employees.add(new Employee("Mike", "Wilson", 45));
        employees.add(new Employee("Mary", "Smith", 5555));
        employees.add(new Employee("John", "Doe", 5678));
        employees.add(new Employee("Bill", "End", 3948));
        employees.add(new Employee("Jane", "Jones", 123));

        Map<String, Employee> map = new HashMap<String, Employee>();

        

        employees.forEach(e -> System.out.println(e));
    }

    public static boolean hash(int id) {
        return false;
    }

}
