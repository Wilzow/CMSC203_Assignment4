import java.util.ArrayList;

public class ManagementCompany {
	public final int MAX_PROPERTY = 5;
	private final int MGMT_WIDTH = 10;
	private final int MGMT_DEPTH = 10;
	
	private String name;
	private String taxId;
	private Plot plot;
	private double mgmFee;
	private int numberOfProperties = 0;
	
	private ArrayList<Property> properties;

	ManagementCompany(){
		this.name = "";
		this.taxId = "";
		this.mgmFee = 0;
		this.plot = new Plot(0,0,MGMT_WIDTH,MGMT_DEPTH);
		this.properties = new ArrayList<Property>();
	}
	
	ManagementCompany(String name, String taxID, double mgmFee){
		this.name  = name;
		this.taxId = taxID;
		this.mgmFee = mgmFee;
		this.plot = new Plot(0,0,MGMT_WIDTH,MGMT_DEPTH);
		this.properties = new ArrayList<Property>();
	}

	ManagementCompany(String name, String taxID, double mgmFee, int x, int y, int width, int depth){
		this.name  = name;
		this.taxId = taxID;
		this.mgmFee = mgmFee;
		this.plot = new Plot(x,y,width,depth);
		this.properties = new ArrayList<Property>();
	}

	ManagementCompany(ManagementCompany otherCompany){
		this.name  = otherCompany.getName();
		this.taxId = otherCompany.getTaxID();
		this.mgmFee = otherCompany.getMgmFeePer();
		this.plot = new Plot(otherCompany.getPlot());
		this.properties =new ArrayList<>(otherCompany.getProperties());
	}

	public String getName() {
		return name;
	}
	
	public String getTaxID() {
		return taxId;
	}

	public double getMgmFeePer() {
		return mgmFee;
	}

	public int getNumberOfProperties() {
		return numberOfProperties;
	}
	
	public Plot getPlot() {
		return this.plot;
	}
	
	public ArrayList<Property> getProperties(){
		return properties;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public void setPlot(Plot plot) {
		this.plot = plot;
	}

	public void setMgmFee(double mgmFee) {
		this.mgmFee = mgmFee;
	}

	public void setNumberOfProperties(int numberOfProperties) {
		this.numberOfProperties = numberOfProperties;
	}

	public void setProperties(ArrayList<Property> properties) {
		this.properties = properties;
	}
	
	public int addProperty(String name, String city, double rent, String owner) {
		Property tempProperty = new Property(name,city,rent,owner);
		
		return propertyTest(tempProperty);
	}
	
	public int addProperty(String name, String city, double rent, String owner, int x, int y, int width, int depth) {
		Property tempProperty = new Property(name,city,rent,owner,x,y,width,depth);
		
		return propertyTest(tempProperty);
	}
	
	public int addProperty(Property property) {
		
		if(property == null) {
			return -2;
		}
		
		return propertyTest(property);
		
	}
	
	public int propertyTest(Property property) {
		
		if(properties.size() >= MAX_PROPERTY) {
			return -1;
		}
		
		if(!(plot.encompasses(property.getPlot()))) {
			return -3;
		}
		
		if(overLapsOthers(property.getPlot())) {
			return -4;
		}
		
		properties.add(new Property(property));
		
		return properties.size()-1;
	}
	
	public boolean overLapsOthers(Plot plot) {
		for(Property prop : properties) {
			if(plot.overlaps(prop.getPlot())) {
				return true;
			}
		}
		return false;
	}
	
	public Property getHighestRentPropperty() {
		Property curHigh = properties.get(0);
		
		for(int i = 0; i < properties.size(); i++) {
			if(curHigh.getRentAmount() < properties.get(i).getRentAmount()) {
				curHigh = properties.get(i);
			}
		}
		
		return curHigh;
	}
	
	public boolean isManagementFeeValid() {
		return (mgmFee > 0 && mgmFee < 100);
	}
	
	public boolean isPropertiesFull() {
		return (properties.size() <= 5);
	}
	
	public void removeLastProperty() {
		properties.remove(properties.size()-1);
	}
	
	public int getPropertiesCount() {
		return properties.size();
	}
	
	public double getTotalRent() {
		int total = 0;
		
		for(Property prop : properties) {
			total += prop.getRentAmount();
		}
		
		return total;
	}
	
	@Override
	public String toString() {
		
		String toReturn = "List of the properties for " + name + ", taxID: " + taxId + "\n"
				+ "______________________________________________________\n";
		
		for(Property prop : properties) {
			toReturn += prop.toString() + "\n";
		}
		
		toReturn += "______________________________________________________\n"
				+ "\n"
				+ " total management Fee: " + (this.getTotalRent() * (mgmFee/100.0));
		
		return toReturn;
	}
}