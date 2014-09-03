package ich.core;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ich.inputCollector.BasicInputCollector;
import ich.inputCollector.FileInputCollector;
import ich.inputCollector.InputCollectable;
import ich.planetSummary.Commodity;
import ich.planetSummary.PlanetSummary;
import ich.regularExpressions.QueryRegEx1;
import ich.statements.Query;
import ich.statements.Assertion;
import ich.statements.UncategorizedStatement;
import ich.utils.Utils;

public class PlanetConversationProcessor {

	private final static Logger logger = LoggerFactory.getLogger(PlanetConversationProcessor.class);

	private List<String> inputs;
	private ConversationProcessor conversationProcessor=new ConversationProcessor();
	private List<Assertion> assertions = new ArrayList<Assertion>();
	private List<Query> queries=new ArrayList<Query>();
	private List<UncategorizedStatement> uncategorizedStatements=new ArrayList<UncategorizedStatement>();
	private PlanetSummary planetSummary=new PlanetSummary();

	public void processConversation(){
		conversationProcessor.process(inputs, assertions, queries, uncategorizedStatements, planetSummary);
	}

	public void startRecording(){
		System.out.print("\nEnter planet name\t: ");
		planetSummary.setName(Utils.readLine());
		readInputs();
	}

	public void readInputs(){
		System.out.print("Is input from file (if 'y' specify the filename in config.properties) ?(Y/N)\t: ");
		String isFromFile=Utils.readLine();
		InputCollectable inputCollector;
		if(isFromFile.equalsIgnoreCase("y")){
			inputCollector = new FileInputCollector();
		}else{
			inputCollector = new BasicInputCollector();
		}
		inputs = inputCollector.collect();
	}
	public void printInputs(){
		for(String str:inputs){
			System.out.println(str);
		}
	}
	public List<String> getAllAnswers(){
		List<String> answers=null;
		if(queries!=null&&queries.size()>0){
			answers=new ArrayList<String>();
			for(Query query:queries){
				if(query.isAnswered()){
					answers.add(query.getAnswer());
				}
			}
			logger.info("No of answers found : "+answers.size());
		}else{
			logger.info("No answers present");
		}
		return answers;
	}
	public void printAssertions(){
		for(Assertion assertion:assertions){
			System.out.println(assertion.getAssertion());
		}
	}
	public void printAnswers(){
		for(String answerStr:getAllAnswers()){
			System.out.println(answerStr);
		}
	}
}
