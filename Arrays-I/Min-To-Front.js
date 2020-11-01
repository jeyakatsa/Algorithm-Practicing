//Given an array of comparable values, move the lowest element to array’s front, 
//shifting backward any elements previously ahead of it. Do not otherwise change 
//the array’s order. Given [4,2,1,3,5], change it to [1,4,2,3,5] and return it. 
//As always, do this without using built-in functions.
var array = [6,5,4,3,2,1];
function minToFront(array){
    var min = array[0];
    var index = 0;
    for (var i = 1; i < array.length; i++){
        if (array[i] < min) {
            min = array[i];
            index = i;
        }
    }
    for (var x = index; x > 0; x--){
        var temp = array[x];
        array[x] = array[x-1];
        array[x-1] = temp;
    }
    return array;
}