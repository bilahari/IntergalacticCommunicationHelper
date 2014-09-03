package ich.regularExpressions;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ich.planetSummary.PlanetSummary;
import ich.statements.Query;
import ich.statements.Statements;
import ich.utils.RomanNumeralUtils;
import ich.utils.Utils;

public class QueryRegEx1 extends AbstractRegularExpressionsImpl{

	private final static Logger logger = LoggerFactory.getLogger(QueryRegEx1.class);

	private String pattern = "How much is((\\s(%SYMBOLS%))*)\\s?\\?*";
	private String actualPattern=new String();
	private String answerFormat="%PLANETARY_NUMBER% is %DECIMAL_NUMBER%";
	private static String type="Query";

	public boolean isMatching(String stmt, PlanetSummary planetSummary) {
		actualPattern = getActualPattern(planetSummary);
		logger.info("actual pattern\t: "+actualPattern);
		if (Utils.isEmptyString(actualPattern)) return false;
		Pattern pattern = Pattern.compile(actualPattern,Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(stmt);
		return matcher.matches();
	}
	public String getActualPattern(PlanetSummary planetSummary){
		List<String> symbols=planetSummary.getSymbols().getAllSymbols();
		if(symbols.size()<=0){
			logger.info("No planetary symbols found so returning null");
			return null;
		}
		StringBuffer symbolsStr=new StringBuffer();
		for(String symbol:symbols){
			symbolsStr.append(symbol+"|");
		}
		actualPattern=pattern.replace("%SYMBOLS%", symbolsStr.toString().subSequence(0, symbolsStr.length()-1));
		return actualPattern;
	}
	public void processStatement(Statements stmt, PlanetSummary planetSummary) {
		Query query = (Query)stmt;
		String stmtStr = query.getQuery();
		actualPattern=getActualPattern(planetSummary);
		Pattern pattern = Pattern.compile(actualPattern,Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(stmtStr);
		if(matcher.matches()){
			String planetaryNumber = matcher.group(1);
			planetaryNumber=planetaryNumber.trim();
			String romanNumber = planetSummary.convertPlanetaryNumberToRomanNumber(planetaryNumber);
			if(Utils.isEmptyString(romanNumber)){
				logger.info("Aborting 'processStatement' as the planetary number : "
							+planetaryNumber+" tries to generate an invalid roman number");
				return;
			}
			int decimalNumber = 0;
			if(RomanNumeralUtils.isValidRomanNumber(romanNumber)){
				decimalNumber = RomanNumeralUtils.convertRomanToDecimal(romanNumber);
				logger.info("Planetary Number : "+planetaryNumber+" | Roman Number : "+romanNumber+" | Decimal Number : "+decimalNumber);
			}
			String answer=new String();
			answer = answerFormat.replaceAll("%PLANETARY_NUMBER%", planetaryNumber);
			answer = answer.replaceAll("%DECIMAL_NUMBER%", decimalNumber+"");
			logger.info("Setting answer as : "+answer);
			query.setAnswerAndIsAnswered(answer.trim());
		}
	}

	public String getType() {
		return type;
	}

	public String getPattern() {
		return pattern;
	}
}
