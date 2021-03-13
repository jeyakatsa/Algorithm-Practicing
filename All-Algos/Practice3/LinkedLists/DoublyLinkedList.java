public class DoublyLinkedList {
    public static void main (String[] args) {

        Employee janeJones = new Employee("Jane", "Jones", 123);
        Employee johnDoe = new Employee("John", "Doe", 456);

        DoublyLinkedList list = new DoublyLinkedList();
        list.addToFront(janeJones);
        list.addToFront(johnDoe);

        list.printList();
        System.out.println(list.getSize());

        Employee billEnd = new Employee("Bill", "Nye", 900);
        list.addToEnd(billEnd);

        list.printList();
        System.out.println(list.getSize());

        list.removeFromEnd();
        list.printList();
        System.out.println(list.getSize());


    }

    public static class Employee {

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
        private EmployeeNode previous;

        public EmployeeNode (Employee employee){
            this.employee = employee;
        }

        public Employee getEmployee() {return employee;}
        public void setEmployee (Employee employee) {this.employee = employee;}
        public EmployeeNode getNext() {return next;}
        public void setNext(EmployeeNode next) {this.next = next;}
        public String toString() {return employee.toString();}
        public EmployeeNode getPrevious() {return previous;}
        public void setPrevious(EmployeeNode previous) {this.previous = previous;}

    }




    //Begin Linked List Below

    private EmployeeNode head;
    private EmployeeNode tail;
    private int size;

    public void addToFront (Employee employee) {
        EmployeeNode node = new EmployeeNode(employee);
        node.setNext(head);

        if (head == null) {
            tail = node;
        }
        else {
            head.setPrevious(node);
        }

        head = node;
        size++;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void addToEnd(Employee employee) {

        EmployeeNode node = new EmployeeNode(employee);

        if(tail == null) {
            head = node;
        }
        else {
            tail.setNext(node);
            node.setPrevious(tail);
        }

        tail = node;
        size++;
    }

    public EmployeeNode removeFromFront() {
        if(isEmpty()) {
            return null;
        }

        EmployeeNode removedNode = head;

        if (head.getNext() == null) {
            tail = null;
        }
        else {
            head.getNext().setPrevious(null);
        }

        head = head.getNext();
        size--;
        removedNode.setNext(null);
        return removedNode;    
    }

    public EmployeeNode removeFromEnd() {
        if(isEmpty()) {
            return null;
        }

        EmployeeNode removedNode = tail;

        if(tail.getPrevious() == null) {
            head = null;
        }
        else {
            tail.getPrevious().setNext(null);
        }

        tail = tail.getPrevious();
        size--;
        removedNode.setPrevious(null);
        return removedNode;    
    }

    public void printList() {
        EmployeeNode current = head;
        System.out.print("Head <=> ");
        while (current != null) {
            System.out.print(current);
            System.out.print(" <=> ");
            current = current.getNext();
        }
        System.out.println("null");
    }
}
