package ich.statements;

public class Query implements Statements{

	private String query;
	private String answer;
	private boolean isAnswered=false;

	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public void setAnswerAndIsAnswered(String answer) {
		this.answer = answer;
		isAnswered=true;
	}
	public boolean isAnswered() {
		return isAnswered;
	}
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getStatement() {
		return getQuery();
	}
	public void setStatement(String str) {
		setQuery(str);
	}
}
