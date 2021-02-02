public class BigO {

    public static void main(String[] args) {

        int n = 10;
        int sum = 0;
        double pie = 3.14;
        int var = 1;


        //O(n)
        for (int var = 0; var < n; var = var + 3) {
          System.out.println("Pie: " + pie);
          for (int j = 0; j < n; j = j + 2) {
            sum++;
            System.out.println("Sum = " + sum);
          }
        }


        //O(n^2)
        for (int var = n; var >= 1; var = var - 3) {
          System.out.println("Pie: " + pie);
          for (int j = n; j >= 0; j = j - 1) {
            sum++;
          }
        }
        System.out.println("Sum: " + sum);


        //O(n)
		int var = 1;
		while(var < n) {  // O(log n)
			System.out.println("Pie: " + pie); // O(log n)
			
			for (int j = 0; j < var; j++) {  // 2n
				sum++;  //  (2n-1)
			}
			var *= 2; // O(log n)
		} //end of while loop	
		System.out.println("Sum: " + sum); //O(1)

        
        //O(nlogn)		
		while(var < n) { // O(log3 n)
			System.out.println("Pie: " + pie); // O(log3 n)	
			for (int j = 1; j < n; j = j + 2) {  // O((log3 n)* (n/2)) 
				sum++;  // O((log3 n)* (n/2) * 2) 
			}
			var *= 3;  // O(log3 n)
		} //end of while loop
        System.out.println("Sum: " + sum); //O(1)
        

        //O(nlogn)
        for (int var = 1; var < n; var += 3) {   // O(n/3)
          System.out.println("Pie: " + pie);    // O(n/3)
          j = 1;  // O(n/3)
          while (j < n) { // O((n/3) * (log3 n))
            sum += 1;      // O((n/3) * (log3 n))
            j *= 3;        // O((n/3) * (log3 n))
          }
        }
        System.out.println("Sum: " + sum); //O(1)

        //O(log(n!))
        for (int var = 0; var < n; var++) {    //O(n)
            int j = 1;  //O(n)
                  System.out.println("Pie: " + pie); //O(n)
                  while(j < var) { // O((n) * (log2 var))
              sum += 1; // O((n) * (log2 var))  
              j *= 2;  // O((n) * (log2 var))
            }
          } //end of for loop
          System.out.println("Sum: " + sum); //O(1)
    }
}