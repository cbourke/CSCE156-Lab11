package unl.soc.trucks;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Random;

/**
 * A JUnit test suite of test cases for the {@link TruckList} linked list
 * implementation.
 *
 */
public class TruckListTests {

	private TruckList testList;

	/**
	 * Initializes a new {@link TruckList}
	 */
	@BeforeEach
	public void setUp() {
		testList = new TruckList();
	}

	/**
	 * Check you're actually doing the assignment...
	 */
	@Test
	public void noCheatingTest() {
		// Prevent hiding the list by creating on add
		testList.addToEnd(Truck.createRandomTruck());
		testList.addToEnd(Truck.createRandomTruck());

		Field[] fields = testList.getClass().getDeclaredFields();
		boolean collectionField = false;
		boolean nodeField = false;
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				collectionField |= field.get(testList) instanceof Collection;
			} catch (IllegalAccessException | IllegalArgumentException ignored) {
			}

			nodeField |= field.getType() == TruckListNode.class;
		}
		assertTrue(nodeField, "You need to have at least one node in your class's objects");
		assertFalse(collectionField, "You may not use Java's Collections!");
	}

	/**
	 * Tests that the {@link TruckList#getSize()} is correct after inserting a new
	 * {@link Truck} with the {@link TruckList#addToStart(Truck)} method.
	 */
	@Test
	public void getSizeAddToStart() {
		Truck testElement = Truck.createRandomTruck();
		for (int i = 0; i < 10; i++) {
			assertEquals(i, testList.getSize(), String.format(
					"Added %d elements to the start of the truck list and size() returned %d", i, testList.getSize()));
			testList.addToStart(testElement);
		}
	}

	/**
	 * Tests that the {@link TruckList#getSize()} is correct after inserting a new
	 * {@link Truck} with the {@link TruckList#addToEnd(Truck)} method.
	 */
	@Test
	public void getSizeAddToEnd() {
		Truck testElement = Truck.createRandomTruck();
		for (int i = 0; i < 10; i++) {
			assertEquals(i, testList.getSize(), String.format(
					"Added %d elements to the end of the truck list and size() returned %d", i, testList.getSize()));
			testList.addToEnd(testElement);
		}
	}

	/**
	 * Tests that the {@link TruckList#getSize()} is correct after removing a
	 * {@link Truck} with the {@link TruckList#remove(int)} method.
	 */
	@Test
	public void getSizeRemove() {
		Truck testElement = Truck.createRandomTruck();
		for (int i = 0; i < 10; i++) {
			testList.addToStart(testElement);
		}

		Random randomRemover = new Random();
		for (int i = 10; i > 0; i--) {
			assertEquals(i, testList.getSize(),
					String.format("Removed elements from a truck list leaving %d elements and size() returned %d", i,
							testList.getSize()));
			testList.remove(randomRemover.nextInt(i));
		}
	}

	/**
	 * Tests that the {@link TruckList#getSize()} is correct after clearing out the
	 * list using the {@link TruckList#clear()} method.
	 */
	@Test
	public void clear() {
		assertEquals(0, testList.getSize(), "Lists should start empty");
		testList.clear();
		assertEquals(0, testList.getSize(), "Clearing should empty a list");
		testList.addToEnd(Truck.createRandomTruck());
		testList.addToStart(Truck.createRandomTruck());
		testList.clear();
		assertEquals(0, testList.getSize(), "Clearing should empty a list");
	}

	/**
	 * Tests that the {@link TruckList#addToStart(Truck)} is correct.
	 */
	@Test
	public void addToStart() {
		for (int i = 0; i < 10; i++) {
			Truck testElement = Truck.createRandomTruck();
			testList.addToStart(testElement);
			assertEquals(testElement, testList.getTruck(0), String.format(
					"Added %d elements to the start of the truck list and size() returned %d", i, testList.getSize()));
		}
	}

	/**
	 * Tests that the {@link TruckList#addToEnd(Truck)} is correct.
	 */
	@Test
	public void addToEnd() {
		for (int i = 0; i < 10; i++) {
			Truck testElement = Truck.createRandomTruck();
			testList.addToEnd(testElement);
			assertEquals(testElement, testList.getTruck(i), String.format(
					"Added %d elements to the end of the truck list and size() returned %d", i, testList.getSize()));
		}
	}

	/**
	 * Tests that the {@link TruckList#remove(int)} is correct.
	 */
	@Test
	public void remove() {
		Truck[] testElements = { Truck.createRandomTruck(), Truck.createRandomTruck(), Truck.createRandomTruck(),
				Truck.createRandomTruck(), Truck.createRandomTruck() };

		for (Truck t : testElements) {
			testList.addToEnd(t);
		}

		assertEquals(testElements[0], testList.getTruck(0), "remove() is causing trucks to be in an unexpected order");
		assertEquals(testElements[1], testList.getTruck(1), "remove() is causing trucks to be in an unexpected order");
		assertEquals(testElements[2], testList.getTruck(2), "remove() is causing trucks to be in an unexpected order");
		assertEquals(testElements[3], testList.getTruck(3), "remove() is causing trucks to be in an unexpected order");
		assertEquals(testElements[4], testList.getTruck(4), "remove() is causing trucks to be in an unexpected order");
		assertEquals(5, testList.getSize(),
				String.format("size() returned %d for a list with %d elements", testList.getSize(), 5));

		testList.remove(0);
		assertEquals(testElements[1], testList.getTruck(0), "remove() is causing trucks to be in an unexpected order");
		assertEquals(testElements[2], testList.getTruck(1), "remove() is causing trucks to be in an unexpected order");
		assertEquals(testElements[3], testList.getTruck(2), "remove() is causing trucks to be in an unexpected order");
		assertEquals(testElements[4], testList.getTruck(3), "remove() is causing trucks to be in an unexpected order");
		assertEquals(4, testList.getSize(),
				String.format("size() returned %d for a list with %d elements", testList.getSize(), 4));

		testList.remove(3);
		assertEquals(testElements[1], testList.getTruck(0), "remove() is causing trucks to be in an unexpected order");
		assertEquals(testElements[2], testList.getTruck(1), "remove() is causing trucks to be in an unexpected order");
		assertEquals(testElements[3], testList.getTruck(2), "remove() is causing trucks to be in an unexpected order");
		assertEquals(3, testList.getSize(),
				String.format("size() returned %d for a list with %d elements", testList.getSize(), 3));

		testList.remove(1);
		assertEquals(testElements[1], testList.getTruck(0), "remove() is causing trucks to be in an unexpected order");
		assertEquals(testElements[3], testList.getTruck(1), "remove() is causing trucks to be in an unexpected order");
		assertEquals(2, testList.getSize(),
				String.format("size() returned %d for a list with %d elements", testList.getSize(), 2));

		testList.remove(0);
		assertEquals(testElements[3], testList.getTruck(0), "remove() is causing trucks to be in an unexpected order");
		assertEquals(1, testList.getSize(),
				String.format("size() returned %d for a list with %d elements", testList.getSize(), 1));

		testList.remove(0);
		assertEquals(0, testList.getSize(),
				String.format("size() returned %d for a list with %d elements", testList.getSize(), 0));
	}

	/**
	 * Tests that the {@link TruckList#getTruck(int)} is correct.
	 */
	@Test
	public void getTruck() {
		Truck[] testElements = { Truck.createRandomTruck(), Truck.createRandomTruck(), Truck.createRandomTruck(),
				Truck.createRandomTruck(), Truck.createRandomTruck() };

		for (Truck t : testElements) {
			testList.addToEnd(t);
		}

		assertEquals(testElements[0], testList.getTruck(0), "getTruck() is returning an unexpected element");
		assertEquals(testElements[1], testList.getTruck(1), "getTruck() is returning an unexpected element");
		assertEquals(testElements[2], testList.getTruck(2), "getTruck() is returning an unexpected element");
		assertEquals(testElements[3], testList.getTruck(3), "getTruck() is returning an unexpected element");
		assertEquals(testElements[4], testList.getTruck(4), "getTruck() is returning an unexpected element");
	}

}
