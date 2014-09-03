package ich.core;

import ich.planetSummary.PlanetSummary;
import ich.regularExpressions.QueryRegEx1;
import ich.regularExpressions.RegExConfig;
import ich.regularExpressions.RegularExpressions;
import ich.statements.Assertion;
import ich.statements.Query;
import ich.statements.UncategorizedStatement;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConversationProcessor {

	private final static Logger logger = LoggerFactory.getLogger(ConversationProcessor.class);

	public void process(List<String> inputs,
			List<Assertion> assertions,
			List<Query> queries,
			List<UncategorizedStatement> uncategorizedStatements,
			PlanetSummary planetSummary
			){
		for(String input:inputs){
			logger.info("input line : "+input);
			for(String regExClassName:RegExConfig.getAllRegularExpressions()){
				try{
					RegularExpressions regEx=(RegularExpressions) Class.forName(regExClassName).newInstance();
					logger.info("Regular expression currently evaluating: "+regEx.getPattern());
					if(regEx.isMatching(input, planetSummary)){
						logger.info("Matching Regular Expression found");
						String regExType=regEx.getType();
						if(regExType.equalsIgnoreCase("Assertion")){
							assertions.add(getProcessedAssertion(input, regEx, planetSummary));
						}else if(regExType.equalsIgnoreCase("Query")){
							queries.add(getProcessedQuery(input, regEx, planetSummary));
						}else if(regExType.equalsIgnoreCase("UnrecognizedQuery")){
							queries.add(getProcessedQuery(input, regEx, planetSummary));
						}else{

						}
						break;
					}else{
						logger.info("Regular Expression not matching");
					}
				}catch(ClassNotFoundException e){
					logger.info("Class for regular expression not found : "+e.getMessage());
				} catch (InstantiationException e) {
					logger.info(e.getMessage());
				} catch (IllegalAccessException e) {
					logger.info(e.getMessage());
				}
			}
		}
	}
	public Query getProcessedQuery(String input,RegularExpressions regEx,PlanetSummary planetSummary){
		logger.info("Processing query : "+input);
		Query query = new Query();
		query.setQuery(input);
		regEx.processStatement(query, planetSummary);
		return query;
	}
	public Assertion getProcessedAssertion(String input,RegularExpressions regEx,PlanetSummary planetSummary){
		logger.info("Processing assertion : "+input);
		Assertion assertion = new Assertion();
		assertion.setAssertion(input);
		regEx.processStatement(assertion, planetSummary);
		return assertion;
	}
}
