public class LinkedList {
    public static void main (String[] args) {

    }

    public class Employee {

        private String firstName;
        private String lastName;
        private int id;

        public Employee(String firstName, String lastName, int id){
            this.firstName = firstName;
            this.lastName = lastName;
            this.id = id;
        }

        public String getFirstName() {return firstName;}
        public void setFirstName(String firstName) {this.firstName = firstName;}
        public String getLastName() {return lastName;}
        public void setLastName(String lastName) {this.lastName = lastName;}
        public int getId() {return id;}
        public void setId(int id) {this.id = id;}

    }

    public class EmployeNode {

        Node head;
        Node nextNode;

        public Node (Node head){
            this.head = head;
        }

        this.head = null;
        this.nextNode = null;

    }
}