package ich.planetSummary;

import ich.exceptions.PlanetaryNumberConversionException;
import ich.utils.RomanNumeralUtils;
import ich.utils.Utils;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Symbols {

	private final static Logger logger = LoggerFactory.getLogger(Symbols.class);

	private Hashtable<String, String> planetarySymbolToRomanSymbolHt=new Hashtable<String, String>();

	public void addPlanetarySymbolAndValue(String planetarySymbol,String romanSymbol){
		planetarySymbolToRomanSymbolHt.put(planetarySymbol, romanSymbol);
	}
	public String getRomanSymbolFromPlanetarySymbol(String planetarySymbol){
		return planetarySymbolToRomanSymbolHt.get(planetarySymbol);
	}
	public List<String> getAllSymbols(){
		return new ArrayList<String>(planetarySymbolToRomanSymbolHt.keySet());
	}
	public String convertPlanetaryNumberToRomanNumber(String planetaryNumber){
		StringBuffer romanNumber=null;
		logger.info("planetaryNumber got as : "+planetaryNumber);
		if(!Utils.isEmptyString(planetaryNumber)){
			StringTokenizer st = new StringTokenizer(planetaryNumber," ");
			try{
				romanNumber=new StringBuffer();
				while (st.hasMoreElements()) {
					String pSymbol=(String) st.nextElement();
					if(planetarySymbolToRomanSymbolHt.containsKey(pSymbol)){
						romanNumber.append(planetarySymbolToRomanSymbolHt.get(pSymbol));
					}else{
						throw new PlanetaryNumberConversionException();
					}
				}
			}catch(PlanetaryNumberConversionException pnce){
				logger.info(pnce.getMessage());
				return null;
			}
		}
		logger.info("Converted Roman number : "+romanNumber);
		if(!RomanNumeralUtils.isValidRomanNumber(romanNumber.toString())){
			logger.info("Converted roman number is invalid so returning null");
			romanNumber = new StringBuffer().append("");
		}
		return romanNumber.toString();
	}
}
