//merge arrays of num1 & num 2
var nums1 = [1,2,3,4,5];
var nums2 = [6,7,8,9,10];


var merge = function (nums1, m, nums2, n) {

    let a = m - 1, b = n - 1, c = m + n - 1;
    while (a >= 0 && b >= 0) {

        if (nums1[a] > nums2[b]) {
            nums1[c--] = nums1[a--];
        }
        else {
            nums1[c--] = nums2[b--];
        }
    }

    while (a >= 0) {
        nums1[c--] = nums1[a--];
    }

    while (b >= 0) {

        nums1[c--] = nums2[b--];
    }
};

merge;