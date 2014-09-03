package ich.planetSummary;

public class Commodity {

	private String name;
	private double credits;

	public Commodity(String name, double credits) {
		super();
		this.name = name;
		this.credits = credits;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getCredits() {
		return credits;
	}
	public void setCredits(double credits) {
		this.credits = credits;
	}
}
