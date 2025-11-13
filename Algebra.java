// Implements algebraic operations and the square root function without using 
// the Java operations a + b, a - b, a * b, a / b, a % b, and without calling 
// Math.sqrt. All the functions in this class operate on int values and
// return int values.

public class Algebra {
	public static void main(String args[]) {
	    // Tests some of the operations
	    System.out.println(plus(2,3));   // 2 + 3
	    System.out.println(minus(7,2));  // 7 - 2
   		System.out.println(minus(2,7));  // 2 - 7
 		System.out.println(times(3,4));  // 3 * 4
   		System.out.println(plus(2,times(4,2)));  // 2 + 4 * 2
   		System.out.println(pow(5,3));      // 5^3
   		System.out.println(pow(3,5));      // 3^5
   		System.out.println(div(12,3));   // 12 / 3    
   		System.out.println(div(5,5));    // 5 / 5  
   		System.out.println(div(25,7));   // 25 / 7
   		System.out.println(mod(25,7));   // 25 % 7
   		System.out.println(mod(120,6));  // 120 % 6    
   		System.out.println(sqrt(36));
		System.out.println(sqrt(263169));
   		System.out.println(sqrt(76123));
	}  

	// Returns x1 + x2
	public static int plus(int x1, int x2) {
		int res = x1;
		//positive second number
		if(x2 >= 0)
			for(int i = 1; i <= x2; i++)
				res++;
		//negative second number
		else
			for(int i = -1; i >= x2; i--)
				res--;

		return res;
	}

	// Returns x1 - x2
	public static int minus(int x1, int x2) {
		int res = x1;
		//positive second number
		if(x2 >= 0)
			for(int i = 1; i <= x2; i++)
				res--;
		//negative second number
		else
			for(int i = -1; i >= x2; i--)
				res++;

		return res;
	}

	// Returns x1 * x2
	public static int times(int x1, int x2) {
		int res = 0;
		//positive second number
		if(x2 >= 0)
			for(int i = 1; i <= x2; i++)
				res = plus(res,x1);
		//negative second number
		else
			for(int i = -1; i >= x2; i--)
				res = minus(res,x1);

		return res;
	}

	// Returns x^n (for n >= 0)
	public static int pow(int x, int n) {
		int res = 1;
		for(int i = 1; i <= n; i++)
			res = times(res,x);

		return res;
	}

	// Returns the integer part of x1 / x2 
	public static int div(int x1, int x2) {
		int posX1 = x1;
		int posX2 = x2;
		int count = 0;
		//flipping the sign of res if the first number is negative
		if(x1 < 0) 
			posX1 = minus(0,x1);
		//flipping the sign of posX2 if the second number is negative
		if(x2 < 0) 
			posX2 = minus(0,x2);
		//incrementing the divisor from the dividend until we can't anymore
		while(posX1 >= posX2) {
			posX1 = minus(posX1,posX2);
			count++;
		}

		//negative first XOR second number = negative result
		if((x1 < 0) ^ (x2 < 0))
			return minus(0,count);
		//both numbers positive or both negative = positive result
		else return count;
	}

	// Returns x1 % x2
	public static int mod(int x1, int x2) {
		int res = x1;
		while(res >= x2)
			res = minus(res,x2);
		
		return res;
	}	

	// Returns the integer part of sqrt(x) 
	public static int sqrt(int x) {
		if(x < 0) return -1;
		int squared;
		for(int i = 1; i <= x; i++) {
			squared = pow(i,2);
			if(squared > x)
				return --i;
			if(squared == x)
				return i;
		}
		return 0;
	}	  	  
}