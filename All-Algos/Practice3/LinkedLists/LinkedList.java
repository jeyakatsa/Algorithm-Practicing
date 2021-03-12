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

    public class EmployeeNode {

        private Employee employee;
        private EmployeeNode next;

        public EmployeeNode (Employee employee){
            this.employee = employee;
        }

        public Employee getEmployee() {return employee;}
        public void setEmployee (Employee employee) {this.employee = employee;}
        public EmployeeNode getNext() {return next;}
        public void setNext(EmployeeNode next) {this.next = next;}

    }

    private EmployeeNode head;

    public void addToFront (Employee employee) {
        EmployeeNode node = new EmployeeNode(employee);
        node.setNext(head);
        head = node;
    }

    public void printList() {
        EmployeeNode current = head;
    }
}