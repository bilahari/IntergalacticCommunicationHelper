package ich.regularExpressions;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ich.planetSummary.Commodity;
import ich.planetSummary.PlanetSummary;
import ich.statements.Query;
import ich.statements.Statements;
import ich.utils.RomanNumeralUtils;
import ich.utils.Utils;

public class QueryRegEx2 extends AbstractRegularExpressionsImpl{

	private final static Logger logger = LoggerFactory.getLogger(QueryRegEx2.class);

	private String pattern = "How many credits is((\\s(%PLANETARY_SYMBOLS%))*)\\s(%COMMODITIES%)\\s?\\?*";
	private String actualPattern=new String();
	private String answerFormat="%PLANETARY_NUMBER% %COMMODITY% is %CREDITS% Credits";
	private static String type="Query";

	public boolean isMatching(String stmt, PlanetSummary planetSummary) {
		actualPattern=getActualPattern(planetSummary);
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
		actualPattern=pattern.replace("%PLANETARY_SYMBOLS%", symbolsStr.toString().subSequence(0, symbolsStr.length()-1));
		List<String> commodityNames=planetSummary.getAllCommodityNames();
		if(commodityNames.size()<=0){
			logger.info("No commodities found so returning null");
			return null;
		}
		StringBuffer commodityNamesStr=new StringBuffer();
		for(String commodityName:commodityNames){
			commodityNamesStr.append(commodityName+"|");
		}
		actualPattern=actualPattern.replace("%COMMODITIES%", commodityNamesStr.toString().subSequence(0, commodityNamesStr.length()-1));
		actualPattern= actualPattern.replace("%SYMBOLS%", symbolsStr.toString().subSequence(0, symbolsStr.length()-1));
		return actualPattern;
	}
	public void processStatement(Statements stmt, PlanetSummary planetSummary) {
		Query query = (Query)stmt;
		String stmtStr = query.getQuery();
		actualPattern=getActualPattern(planetSummary);
		Pattern pattern = Pattern.compile(actualPattern,Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(stmtStr);
		if(matcher.matches()){
			String nCommoditiesInPlanetaryNumber = matcher.group(1);
			String nCommoditiesInRomanNumber = planetSummary.convertPlanetaryNumberToRomanNumber(nCommoditiesInPlanetaryNumber);
			if(Utils.isEmptyString(nCommoditiesInRomanNumber)){
				logger.info("Aborting 'processStatement' as the planetary number : "
							+nCommoditiesInPlanetaryNumber+" tries to generate an invalid roman number");
				return;
			}
			int    nCommoditiesInDec = RomanNumeralUtils.convertRomanToDecimal(nCommoditiesInRomanNumber);	
			String commodityName = matcher.group(4);
			double credit = nCommoditiesInDec*planetSummary.getCommodityCredit(commodityName);
			String answer=new String();
			answer = answerFormat.replaceAll("%PLANETARY_NUMBER%", nCommoditiesInPlanetaryNumber);
			answer = answer.replaceAll("%COMMODITY%", commodityName);
			answer = answer.replaceAll("%CREDITS%", (int)Math.ceil(credit)+"");
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
