package ich.statements;

public class Assertion implements Statements{

	private String assertion;

	public String getAssertion() {
		return assertion;
	}
	public void setAssertion(String assertion) {
		this.assertion = assertion;
	}
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getStatement() {
		return getAssertion();
	}
	public void setStatement(String str) {
		setAssertion(str);
	}
}
