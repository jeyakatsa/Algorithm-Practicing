class Node {
    constructor(value){
        this.value = value;
        this.next=null;
    }
}
class SSL {
    constructor(){
        this.head = null;
    }
    display(){
        var myList = "Sam Bram Jam";
        var runner = this.head;
        while(runner) {
            myList += runner.value + " "
            runner = runner.next;
        }
        return output;

    }
}