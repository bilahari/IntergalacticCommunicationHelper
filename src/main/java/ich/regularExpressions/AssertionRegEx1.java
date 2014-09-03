package ich.regularExpressions;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ich.planetSummary.PlanetSummary;
import ich.statements.Assertion;
import ich.statements.Query;
import ich.statements.Statements;
import ich.utils.RomanNumeralUtils;
import ich.utils.Utils;

public class AssertionRegEx1 extends AbstractRegularExpressionsImpl{

	private final static Logger logger = LoggerFactory.getLogger(AssertionRegEx1.class);

	private String pattern = "(\\w*)\\sis\\s(%ROMAN_SYMBOLS%)";
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
	public String getActualPattern(PlanetSummary planetSummary){
		List<String> romanNumeralSymbols=RomanNumeralUtils.getAllRomanNumeralSymbols();
		StringBuffer symbolsStr=new StringBuffer();
		for(String symbol:romanNumeralSymbols){
			symbolsStr.append(symbol+"|");
		}
		actualPattern = pattern.replace("%ROMAN_SYMBOLS%", symbolsStr.toString().subSequence(0, symbolsStr.length()-1));
		return actualPattern;
	}
	public void processStatement(Statements stmt, PlanetSummary planetSummary) {
		actualPattern = getActualPattern(planetSummary);
		Pattern pattern = Pattern.compile(actualPattern,Pattern.CASE_INSENSITIVE);
		Assertion assertion = (Assertion)stmt;
		String assertionStr=assertion.getAssertion();
		Matcher matcher = pattern.matcher(assertionStr);
		if(matcher.matches()){
			String planetarySymbol=matcher.group(1);
			String romanSymbol=matcher.group(2);
			planetSummary.getSymbols().addPlanetarySymbolAndValue(planetarySymbol, romanSymbol);
		}
	}
	public String getType() {
		return type;
	}

	public String getPattern() {
		return pattern;
	}
}
