package unl.soc.trucks;

/**
 * A Truck object
 *
 */
public class Truck {

	public static final int HORSEPOWER_MIN = 320;
	public static final int HORSEPOWER_MAX = 600;
	public static final int CARRYINGCAPACITY_MIN = 44000;
	public static final int CARRYINGCAPACITY_MAX = 48000;
	public static final String[] TRANSMISSION_VALUES = { "Manual", "Automatic" };
	public static final int WHEELBASE_MIN = 230;
	public static final int WHEELBASE_MAX = 280;
	public static final int WHEELCUT_MIN = 40;
	public static final int WHEELCUT_MAX = 50;

	/**
	 * An inner static class that supports the
	 * <a href="https://en.wikipedia.org/wiki/Builder_pattern">Builder Pattern</a>
	 * so that instances can be easily created
	 * 
	 */
	public static class Builder {
		private String licensePlate;
		private int carryingCapacity;
		private int horsePower;
		private String transmission;
		private int wheelBase;
		private int wheelCut;

		public Builder licensePlate(final String licensePlate) {
			this.licensePlate = licensePlate;
			return this;
		}

		public Builder carryingCapacity(final int carryingCapacity) {
			this.carryingCapacity = carryingCapacity;
			return this;
		}

		public Builder horsePower(final int horsePower) {
			this.horsePower = horsePower;
			return this;
		}

		public Builder transmission(final String transmission) {
			this.transmission = transmission;
			return this;
		}

		public Builder wheelBase(final int wheelBase) {
			this.wheelBase = wheelBase;
			return this;
		}

		public Builder wheelCut(final int wheelCut) {
			this.wheelCut = wheelCut;
			return this;
		}

		public Truck build() {
			return new Truck(this.licensePlate, this.carryingCapacity, this.horsePower, this.transmission,
					this.wheelBase, this.wheelCut);
		}
	}

	private final String licensePlate;
	private final int carryingCapacity;
	private final int horsePower;
	private final String transmission;
	private final int wheelBase;
	private final int wheelCut;

	private Truck(String licensePlate, int carryingCapacity, int horsePower, String transmission, int wheelBase,
			int wheelCut) {
		this.licensePlate = licensePlate;
		this.carryingCapacity = carryingCapacity;
		this.horsePower = horsePower;
		this.transmission = transmission;
		this.wheelBase = wheelBase;
		this.wheelCut = wheelCut;
	}

	/**
	 * Prints the information about the truck
	 */
	public void print() {
		System.out.println("----------------------------------------");
		System.out.println("Licence Plate: " + this.licensePlate);
		System.out.println("Carrying Capacity: " + this.carryingCapacity);
		System.out.println("Horse Power: " + this.horsePower);
		System.out.println("Transmission: " + this.transmission);
		System.out.println("Wheel Base: " + this.wheelBase);
		System.out.println("Wheel Cut: " + this.wheelCut);
		System.out.println("----------------------------------------");
		return;
	}

	/**
	 *
	 * @return a randomly generated Truck
	 */
	public static Truck createRandomTruck() {
		Truck.Builder randomTruck = new Truck.Builder();

		String licensePlate = RandomStringUtils.randomAlphabetic(3) + " " + RandomStringUtils.randomNumeric(3);
		java.util.Random randomNumberGenerator = new java.util.Random();

		int carryingCapacity = Truck.CARRYINGCAPACITY_MIN
				+ randomNumberGenerator.nextInt(Truck.CARRYINGCAPACITY_MAX - Truck.CARRYINGCAPACITY_MIN);

		int horsePower = Truck.HORSEPOWER_MIN
				+ randomNumberGenerator.nextInt(Truck.HORSEPOWER_MAX - Truck.HORSEPOWER_MIN);

		String transmission = Truck.TRANSMISSION_VALUES[randomNumberGenerator.nextInt(2)];

		int wheelBase = Truck.WHEELBASE_MIN + randomNumberGenerator.nextInt(Truck.WHEELBASE_MAX - Truck.WHEELBASE_MIN);

		int wheelCut = Truck.WHEELCUT_MIN + randomNumberGenerator.nextInt(Truck.WHEELCUT_MAX - Truck.WHEELCUT_MIN);

		randomTruck.carryingCapacity(carryingCapacity);
		randomTruck.horsePower(horsePower);
		randomTruck.licensePlate(licensePlate);
		randomTruck.wheelBase(wheelBase);
		randomTruck.wheelCut(wheelCut);
		randomTruck.transmission(transmission);

		return randomTruck.build();
	}

	/**
	 *
	 * @return carrying capacity
	 */
	public int getCarryingCapacity() {
		return carryingCapacity;
	}

	/**
	 *
	 * @return horsepower
	 */
	public int getHorsePower() {
		return horsePower;
	}

	/**
	 * @return License plate number
	 */
	public String getLicensePlate() {
		return licensePlate;
	}

	/**
	 *
	 * @return transmission
	 */
	public String getTransmission() {
		return transmission;
	}

	/**
	 *
	 * @return wheel base of the truck
	 */
	public int getWheelBase() {
		return wheelBase;
	}

	/**
	 *
	 * @return wheel cut of the truck
	 */
	public int getWheelCut() {
		return wheelCut;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Truck ");
		sb.append(licensePlate);
		sb.append(", capacity = " + carryingCapacity);
		sb.append(", horsePower = " + horsePower);
		sb.append(", transmission = " + transmission);
		sb.append(", wheelBase = " + wheelBase);
		sb.append(", wheelCut = " + wheelCut);
		return sb.toString();

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + carryingCapacity;
		result = prime * result + horsePower;
		result = prime * result + ((licensePlate == null) ? 0 : licensePlate.hashCode());
		result = prime * result + ((transmission == null) ? 0 : transmission.hashCode());
		result = prime * result + wheelBase;
		result = prime * result + wheelCut;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Truck other = (Truck) obj;
		if (carryingCapacity != other.carryingCapacity)
			return false;
		if (horsePower != other.horsePower)
			return false;
		if (licensePlate == null) {
			if (other.licensePlate != null)
				return false;
		} else if (!licensePlate.equals(other.licensePlate))
			return false;
		if (transmission == null) {
			if (other.transmission != null)
				return false;
		} else if (!transmission.equals(other.transmission))
			return false;
		if (wheelBase != other.wheelBase)
			return false;
		if (wheelCut != other.wheelCut)
			return false;
		return true;
	}

}
