public class LinkedList {
    public static void main (String[] args) {

        Employee janeJones = new Employee("Jane", "Jones", 123);
        Employee johnDoe = new Employee("John", "Doe", 456);

        LinkedList list = new LinkedList();
        list.addToFront(janeJones);
        list.addToFront(johnDoe);

        list.printList();

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

        public boolean equals (Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Employee employee = (Employee) o;

            if (id != employee.id) return false;
            if (!firstName.equals(employee.firstName)) return false;
            return lastName.equals(employee.lastName);
        }

        @Override
        public int hashCode() {
            int result = firstName.hashCode();
            result = 31 * result + lastName.hashCode();
            result = 31 * result + id;
            return result;
        }

        @Override
        public String toString() {
            return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id=" + id +
                "}";
        }

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
        public String toString() {return employee.toString();}

    }

    private EmployeeNode head;

    public void addToFront (Employee employee) {
        EmployeeNode node = new EmployeeNode(employee);
        node.setNext(head);
        head = node;
    }

    public void printList() {
        EmployeeNode current = head;
        System.out.print("Head -> ");
        while (current != null) {
            System.out.print(current);
            System.out.print(" -> ");
            current = current.getNext();
        }
        System.out.println("null");
    }
}