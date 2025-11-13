// Computes the periodical payment necessary to pay a given loan.
public class LoanCalc {
	
	static double epsilon = 0.001;  // Approximation accuracy
	static int iterationCounter;    // Number of iterations 
	
	// Gets the loan data and computes the periodical payment.
    // Expects to get three command-line arguments: loan amount (double),
    // interest rate (double, as a percentage), and number of payments (int).  
	public static void main(String[] args) {		
		// Gets the loan data
		double loan = Double.parseDouble(args[0]);
		double rate = Double.parseDouble(args[1]);
		int n = Integer.parseInt(args[2]);
		System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);
		
		// Computes the periodical payment using brute force search
		System.out.print("\nPeriodical payment, using brute force: ");
		System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);

		// Computes the periodical payment using bisection search
		System.out.print("\nPeriodical payment, using bi-section search: ");
		System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter); 

	}

	// Computes the ending balance of a loan, given the loan amount, the periodical
	// interest rate (as a percentage), the number of periods (n), and the periodical payment.
	private static double endBalance(double loan, double rate, int n, double payment) {	
		double balance = loan;
		for(int i = 0; i < n; i++){
			balance -= payment;
			balance *= 1 + rate / 100;
		}
		return balance;
	}
	
	// Uses sequential search to compute an approximation of the periodical payment
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
		double guess = loan / n;
		double balance = loan;
		iterationCounter = 0;
		
		//increasing the monthly payment value (=guess) until the endBalance is is lower than 0
		while (balance > 0) {
			guess += epsilon;
			balance = endBalance(loan, rate, n, guess);
			iterationCounter++;
		}
		return guess;
    }
    
    // Uses bisection search to compute an approximation of the periodical payment 
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {  
        double highPay = loan / n; //low monthly payment, high end balance
		double lowPay = loan; //high monthly payment, low end balance
		double payment = (highPay + lowPay) / 2;
		double balance;
		iterationCounter = 0;

		//keep pulling the low and high limits toward each other (and 0) 
		//until they are really close (practically the same)
		while ((lowPay - highPay) > epsilon) {
			payment = (highPay + lowPay) / 2;
			balance = endBalance(loan, rate, n, payment);
			if(balance > 0) highPay = payment; //if we are too short on payment, increase the high limit
			else lowPay = payment; //if we are paying too much, decrease the low limit
			iterationCounter++;
		}
		return payment;
    }
}