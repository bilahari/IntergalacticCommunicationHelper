package ich.planetSummary;

import java.util.ArrayList;
import java.util.List;

public class PlanetSummary {

	private String name;
	private Symbols symbols=new Symbols();
	private List<Commodity> commodities=new ArrayList<Commodity>();

	public String convertPlanetaryNumberToRomanNumber(String planetaryNumber){
		return symbols.convertPlanetaryNumberToRomanNumber(planetaryNumber);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Symbols getSymbols() {
		return symbols;
	}
	public void setSymbols(Symbols symbols) {
		this.symbols = symbols;
	}
	public List<Commodity> getCommodities() {
		return commodities;
	}
	public void addCommodity(String name,double credits) {
		commodities.add(new Commodity(name, credits));
	}
	public List<String> getAllCommodityNames(){
		List<String> commityNames = new ArrayList<String>();
		for(Commodity commodity:commodities){
			commityNames.add(commodity.getName());
		}
		return commityNames;
	}
	public double getCommodityCredit(String commodityName){
		for(Commodity commodity:commodities){
			if(commodity.getName().equalsIgnoreCase(commodityName)){
				return commodity.getCredits();
			}
		}
		return 0.0;
	}
}
