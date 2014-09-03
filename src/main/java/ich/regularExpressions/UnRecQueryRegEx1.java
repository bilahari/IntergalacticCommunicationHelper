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

public class UnRecQueryRegEx1 extends AbstractRegularExpressionsImpl{

	private final static Logger logger = LoggerFactory.getLogger(UnRecQueryRegEx1.class);

	private String pattern = "(How|What).*\\?*";
	private String actualPattern=new String();
	private String answerFormat="I have no idea what you are talking about";
	private static String type="UnrecognizedQuery";

	public boolean isMatching(String stmt, PlanetSummary planetSummary) {
		actualPattern=getActualPattern(planetSummary);
		logger.info("actual pattern\t: "+actualPattern);
		if (Utils.isEmptyString(actualPattern)) return false;
		Pattern pattern = Pattern.compile(actualPattern,Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(stmt);
		return matcher.matches();
	}
	public String getActualPattern(PlanetSummary planetSummary){
		actualPattern=pattern;
		return pattern;
	}
	public void processStatement(Statements stmt, PlanetSummary planetSummary) {
		Query query = (Query)stmt;
		String stmtStr = query.getQuery();
		actualPattern=getActualPattern(planetSummary);
		Pattern pattern = Pattern.compile(actualPattern,Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(stmtStr);
		if(matcher.matches()){
			for(String regExClassName:RegExConfig.getAllRegularExpressions()){
				try{
					RegularExpressions regEx=(RegularExpressions) Class.forName(regExClassName).newInstance();
					if(!regEx.getType().equalsIgnoreCase("UnrecognizedQuery")&&regEx.isMatching(query.getQuery(), planetSummary)){
						logger.info("This query is not belongs to unrecognised category so aborting process");
						return;
					}
				}catch(ClassNotFoundException e){
					logger.info("Class for regular expression not found : "+e.getMessage());
				} catch (InstantiationException e) {
					logger.info(e.getMessage());
				} catch (IllegalAccessException e) {
					logger.info(e.getMessage());
				}
			}
			query.setAnswerAndIsAnswered(answerFormat.trim());
		}
	}
	public String getType() {
		return type;
	}
	public String getPattern() {
		return pattern;
	}
}
