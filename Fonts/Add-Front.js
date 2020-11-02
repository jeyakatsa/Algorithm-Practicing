// Remove Blanks
// Create a function that, given a string, returns all of that string’s contents, 
// but without blanks. 
// ---
// If given the string " Pl ayTha tF u nkyM usi c ", return "PlayThatFunkyMusic".
function removeBlanks(str) {
    return str.split(" ").join("");
}


// Get Digits
// Create a JavaScript function that given a string, returns the integer made 
// from the string’s digits. Given "0s1a3y5w7h9a2t4?6!8?0", the function should 
// return the number 1357924680.
function getDigits(str) {
    var stringArr = str.split("");
    var intStr = "";
    for (var ind = 0; ind < stringArr.length; ind++) {
        if (stringArr[ind]%1 == 0) {
            intStr += stringArr[ind];
        }
    }
    return intStr/1;
}


// Acronyms
// Create a function that, given a string, returns the string’s acronym 
// (first letters only, capitalized). 
// ---
// Given " there's no free lunch - gotta pay yer way. ", return "TNFL-GPYW". 
// ---
// Given "Live from New York, it's Saturday Night!", return "LFNYISN".
function acronyms(str) {
    var strArr = str.split(" ");
    var retStr = "";
    for (var ind = 0; ind < strArr.length; ind++) {
        if (strArr[ind] == false) {
            continue;
        } else {
            retStr += strArr[ind][0].toUpperCase();
        }
    }
    return retStr;
}


// Count Non-Spaces
// Accept a string and return the number of non-space characters found 
// in the string. 
// ---
// For example, given "Honey pie, you are driving me crazy", return 29
// (not 35).
function countNS(str) {
    var strArr = str.split("");
    var count = 0;
    for (var ind = 0; ind < strArr.length; ind++) {
        if (strArr[ind] == false) {
            continue;
        } else {
            count++;
        }
    }
    return count;
}


// Remove Shorter Strings
// Given a string array and value (length), remove any strings shorter than the 
// length from the array.
function removeShort(strArr, val) {
    for (var ind = strArr.length-1; ind >= 0; ind--) {
        if (strArr[ind].length < val) {
            for (var i = ind; i < strArr.length-1; i++){
                var temp = strArr[i];
                strArr[i] = strArr[i+1];
                strArr[i+1] = temp;
            }
            strArr.pop();
        }
    }
    return strArr;
}