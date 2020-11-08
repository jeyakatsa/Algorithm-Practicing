var array = [1,2,3,4,5,6];
var value = 22;
var index = 0;

// Push Front
// Given an array and an additional value, insert this value at the beginning of the array. 
// Do this without using any built-in array methods.
function pushFront(array, value){
    for (var i = array.length; i > 0; i--)
        array[i] = array[i-1];
        
    array[0] = value; 
}


// Pop Front
// Given an array, remove and return the value at the beginning of the array. 
// Do this without using any built-in array methods except pop().
function popFront(array) {
	var val = array[0];
	for(let i = 0; i < array.length; i++)
		array[i] = array[i + 1];
	array.length = array.length - 1;
	return val;
}


// Insert At
// Given an array, index, and additional value, insert the value into array at given index. 
// Do this without using built-in array methods. 
// You can think of pushFront(arr,val) as equivalent to insertAt(arr,0,val).
function insertAt(array, index, value) {
	for(let i = array.length; i > index; i--)
		array[i] = array[i-1];
	
	array[index] = value;
}


// Remove At
// Given an array and an index into array, remove and return the array value at that index. 
//Do this without using built-in array methods except pop(). Think of popFront(arr) as 
// equivalent to removeAt(arr,0).
function removeAt(array, index) {
    toRemove = array[index];
    for(let i = index; i < array.length-1; i++) {
        array[i] = array[i+1];
    }
    array.length = array.length-1;
    return toRemove;
}



// Swap Pairs
// Swap positions of successive pairs of values of given array. If length is odd,
// do not change the final element. For [1,2,3,4], return [2,1,4,3]. For example,
// change input ["Brendan",true,42] to [true,"Brendan",42]. As with all array challenges,
// do this without using any built-in array methods.
function swapPairs(array) {
	for(let i = 0; i < array.length - 1; i = i + 2) {
		let temp = array[i];
		array[i] = array[i + 1];
		array[i + 1] = temp;
	}
}



// Remove Duplicates
// Sara is looking to hire an awesome web developer and has received applications from
// various sources. Her assistant alphabetized them but noticed some duplicates.
// Given a sorted array, remove duplicate values. Because array elements are already
// in order, all duplicate values will be grouped together. As with all these array
// challenges, do this without using any built-in array methods.

// Second: Solve this without using any nested loops.
function removeDupesUnnested(array) {
	let newArr = [];
	for(let i = 0; i < array.length; i++) {
		if(array[i] !== array[i+1]) 
			newArr.push(array[i])
	}
	return newArr;
}