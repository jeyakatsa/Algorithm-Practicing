public class BigO {

    public static void main(String[] args) {

        //O(n)
        int n = 10;
        int sum = 0;
        double pie = 3.14;
        
        for (int var = 0; var < n; var = var + 3) {
          System.out.println("Pie: " + pie);
          for (int j = 0; j < n; j = j + 2) {
            sum++;
            System.out.println("Sum = " + sum);
          }
        }

        //O(n^2)
        int n = 10;
        int sum = 0; 
        double pie = 3.14;
        
        for (int var = n; var >= 1; var = var - 3) {
          System.out.println("Pie: " + pie);
          for (int j = n; j >= 0; j = j - 1) {
            sum++;
          }
        }
        System.out.println("Sum: " + sum);

        //O(n)
        int n = 10;
		int sum = 0; 
		double pie = 3.14;
		int var = 1;

		while(var < n) {
			System.out.println("Pie: " + pie); 
			for (int j = 0; j < var; j++) {
				sum++;
			}
			var *= 2; 
		}
		System.out.println("Sum: " + sum);
    }
}