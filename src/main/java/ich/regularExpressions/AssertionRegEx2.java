package ich.regularExpressions;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ich.planetSummary.Commodity;
import ich.planetSummary.PlanetSummary;
import ich.statements.Assertion;
import ich.statements.Query;
import ich.statements.Statements;
import ich.utils.RomanNumeralUtils;
import ich.utils.Utils;

public class AssertionRegEx2 extends AbstractRegularExpressionsImpl{

	private final static Logger logger = LoggerFactory.getLogger(AssertionRegEx2.class);

	private String pattern = "(((%PLANETARY_SYMBOLS%)\\s)*)(\\w*)\\sis\\s(\\d*)\\sCredits";
	private String actualPattern=new String();
	private static String type="Assertion";

	public boolean isMatching(String stmt, PlanetSummary planetSummary) {
		actualPattern=getActualPattern(planetSummary);
		logger.info("actual pattern\t: "+actualPattern);
		if (Utils.isEmptyString(actualPattern)) return false;
		Pattern pattern = Pattern.compile(actualPattern,Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(stmt);
		return matcher.matches();
	}
	public String getActualPattern(PlanetSummary planetSummary) {
		List<String> planetaryNumeralSymbols=planetSummary.getSymbols().getAllSymbols();
		if(planetaryNumeralSymbols.size()<=0){
			logger.info("No planetary symbols present so returning null");
			return null;
		}
		StringBuffer symbolsStr=new StringBuffer();
		for(String symbol:planetaryNumeralSymbols){
			symbolsStr.append(symbol+"|");
		}
		actualPattern=pattern.replace("%PLANETARY_SYMBOLS%", symbolsStr.toString().subSequence(0, symbolsStr.length()-1));
		return actualPattern;
	}
	public void processStatement(Statements stmt, PlanetSummary planetSummary) {
		actualPattern=getActualPattern(planetSummary);
		Pattern pattern = Pattern.compile(actualPattern,Pattern.CASE_INSENSITIVE);
		Assertion assertion = (Assertion)stmt;
		String assertionStr=assertion.getAssertion();
		Matcher matcher = pattern.matcher(assertionStr);
		if(matcher.matches()){
			String noOfCommoditiesInPlanetaryNumber=matcher.group(1);
			noOfCommoditiesInPlanetaryNumber=noOfCommoditiesInPlanetaryNumber.trim();
			String noOfCommoditiesInRomanNumber=planetSummary.convertPlanetaryNumberToRomanNumber(noOfCommoditiesInPlanetaryNumber);
			if(Utils.isEmptyString(noOfCommoditiesInRomanNumber)){
				logger.info("Aborting 'processStatement' as the planetary number : "
							+noOfCommoditiesInPlanetaryNumber+" tries to generate an invalid roman number");
				return;
			}
			int    noOfCommoditiesInDec = RomanNumeralUtils.convertRomanToDecimal(noOfCommoditiesInRomanNumber);
			String commodityName=matcher.group(4);
			double creditsForN=Integer.parseInt(matcher.group(5));
			double creditsPerCommodity=creditsForN/noOfCommoditiesInDec;
			planetSummary.addCommodity(commodityName, creditsPerCommodity);
		}
	}
	public String getType() {
		return type;
	}
	public String getPattern() {
		return pattern;
	}
}
