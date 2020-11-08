class Node {
    constructor(value) {
        this.value=value;
        this.next = null;
    }
}

class SSL {
    constructor() {
        this.head = null;
    }

    // max(){

    // }
    // min(){

    // }
    // avg(){

    // }
    maxMinAvg() {
    var sum = 0;
    var max = this.head.value;
    var min = this.head.value;
    var runner = this.head;
    while(runner) {
        sum += runner.value;
        if(runner.value > max) {
            max = runner.value;
        }
        else if(runner.value < min) {
            min = runner.value;
        }
        runner = runner.next
    }
    return `max: ${max}, min: ${min}, avg: ${sum/this.length()}`
}
}