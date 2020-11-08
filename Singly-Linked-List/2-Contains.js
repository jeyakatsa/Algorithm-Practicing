class Node {
    constructor(value) {
        this.value = value;
        this.next = null
    }
}

class SLL {
    constructor() {
        this.head=null;
    }
    contains(value) {
        var runner = this.head;
        while(runner){
            if(runner.value === value) {
                return true;
            }
            runner = runner.next;
        }
        return false;
    }
}