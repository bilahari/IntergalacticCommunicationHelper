package ich.regularExpressions;

import java.util.ArrayList;
import java.util.List;

public class RegExConfig {

	private static List<String> regularExpressions = new ArrayList<String>();

	public static List<String> getAllRegularExpressions() {
		return regularExpressions;
	}
	public static void addRegularExpression(String regularExpression){
		regularExpressions.add(regularExpression);
	}
}
