public class Bitwise {
  public static int findSingleNumber(int[] arr) {
    //base case
    //sort array
    //if arr[i] != arr[i++]
    //return arr[i++]
    
    int num = 0;
    for (int i=0; i < arr.length; i++) {
        num = num ^ arr[i];
    }
    return num;
  }
  public static int[] findSingleNumbers(int[] nums) {
    // get the XOR of the all the numbers
    int n1xn2 = 0;
    for (int num : nums) {
      n1xn2 ^= num;
    }

    // get the rightmost bit that is '1'
    int rightmostSetBit = 1;
    while ((rightmostSetBit & n1xn2) == 0) {
      rightmostSetBit = rightmostSetBit << 1;
    }
    int num1 = 0, num2 = 0;
    for (int num : nums) {
      if ((num & rightmostSetBit) != 0) // the bit is set
        num1 ^= num;
      else // the bit is not set
        num2 ^= num;
    }
    return new int[] { num1, num2 };
  }

  public static int bitwiseComplement(int num) {
    // count number of total bits in 'num'
    int bitCount = 0;
    int n = num;
    while (n > 0) {
      bitCount++;
      n = n >> 1;
    }

    // for a number which is a complete power of '2' i.e., it can be written as pow(2, n), if we
    // subtract '1' from such a number, we get a number which has 'n' least significant bits set to '1'.
    // For example, '4' which is a complete power of '2', and '3' (which is one less than 4) has a binary 
    // representation of '11' i.e., it has '2' least significant bits set to '1' 
    int all_bits_set = (int) Math.pow(2, bitCount) - 1;

    // from the solution description: complement = number ^ all_bits_set
    return num ^ all_bits_set;
  }    



  public static void main(String[] args) {
  int[] arr = new int[] { 1, 4, 2, 1, 3, 5, 6, 2, 3, 5 };
  int[] result = findSingleNumbers(arr);
  System.out.println("Single numbers are: " + result[0] + ", " + result[1]);

  arr = new int[] { 2, 1, 3, 2 };
  result = findSingleNumbers(arr);
  System.out.println("Single numbers are: " + result[0] + ", " + result[1]);
  }   
}