package unl.soc.trucks;

/**
 * An ad-hoc testing class for you to use to troubleshoot and debug your
 * implementation.
 *
 */
public class ListTester {

	public static void main(String[] args) {

		// TODO: Ad-hoc test cases should be made here
		TruckList tl = new TruckList();
		Truck t = new Truck.Builder().licensePlate("ABC 123").build();
		tl.addToEnd(t);
		tl.print();

	}
}
