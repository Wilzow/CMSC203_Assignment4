
public class Property {
	private String name;
	private String city;
	private String owner;
	private Plot plot;
	private double rentAmount;
	
	Property(){
		this.name = "";
		this.city = "";
		this.owner = "";
		this.plot = new Plot();
		this.rentAmount = 0;
	}
	
	Property(String propertyName, String city, double rentAmount, String owner){
		this.name = propertyName;
		this.city = city;
		this.owner = owner;
		this.plot = new Plot();
		this.rentAmount = rentAmount;
	}
	
	Property(String propertyName, String city, double rentAmount, String owner, int x, int y, int width, int depth){
		this.name = propertyName;
		this.city = city;
		this.owner = owner;
		this.plot = new Plot(x,y,width,depth);
		this.rentAmount = rentAmount;
	}
	
	Property(Property otherProperty){
		this.name = otherProperty.getPropertyName();
		this.city = otherProperty.getCity();
		this.owner = otherProperty.getOwner();
		this.plot = new Plot(otherProperty.getPlot());
		this.rentAmount = otherProperty.getRentAmount();
		
	}

	public double getRentAmount() {
		return this.rentAmount;
	}

	public Plot getPlot() {
		return this.plot;
	}

	public String getOwner() {
		return this.owner;
	}

	public String getCity() {
		return this.city;
	}

	public String getPropertyName() {
		return this.name;
	}
	
	@Override
	public String toString(){
		return name + "," + city + "," + owner + "," + rentAmount;
	}
}
