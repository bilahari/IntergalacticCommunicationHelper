package ich.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	public static void main(String[] args) {


		/** Grouping
		String s1="Hello hi   . HHH dd .";
		String pattern = "(\\w)(\\s+)([\\.,])";
		System.out.println(s1.replaceAll(pattern, "$1$3"));
		 */ 

		/*String s="How many apples do you have ?";
		String regex1="How many (\\w+\\s*)+\\?*";
		*//*
		System.out.println(s.matches("\\bHow many \\b(\\w+\\s*)+\\?*"));

		System.out.println(s.matches("How many (\\w+\\s*)+\\?*"));

		
		Pattern pattern = Pattern.compile(regex1);
		Matcher matcher = pattern.matcher(s);

		while (matcher.find()) {
			System.out.println("Start index: " + matcher.start());
			System.out.println("End index: " + matcher.end() + " ");
			System.out.println(matcher.group());
		}
 */




/*
		String s1 = "how much is pish tegj glob glob ?";

		List<String> symbols=new ArrayList<String>();
		symbols.add("glob");
		symbols.add("prok");
		symbols.add("pish");
		symbols.add("tegj");

		StringBuffer regex2=new StringBuffer();
		regex2.append("How much is ");
		regex2.append("(((");
		for(String symbol:symbols){
			regex2.append(symbol+"|");
		}
		regex2.append(")\\s)*)");
		regex2.append("\\?*");
		System.out.println(regex2);
		
		Pattern pattern = Pattern.compile(regex2.toString(),Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(s1);
		matcher.matches();
		//while (matcher.find()) {
			System.out.println("Start index: " + matcher.start());
			System.out.println("End index: " + matcher.end() + " ");
			System.out.println(matcher.group());
			System.out.println(matcher.groupCount());
			for(int i=0;i<=matcher.groupCount();i++){
			System.out.println("Group "+i+" : "+matcher.group(i));
			}
		//}
		
*///glob is I
		String regExx="(\\w*)\\sis\\s(I|V|X|L|C|D|M)";

		String s1 = "how many Credits is glob prok Silver ?";

		List<String> symbols=RomanNumeralUtils.getAllRomanNumeralSymbols();

		StringBuffer regex2=new StringBuffer();
		regex2.append("How many credits is (((glob|prok|pish|tegj)\\s)*)(Silver)\\s?\\?*");
		/*regex2.append("(");
		for(String symbol:symbols){
			regex2.append(symbol+"|");
		}
		regex2.append(")");*/
		System.out.println(regex2);
		
		Pattern pattern = Pattern.compile(regex2.toString(),Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(s1);
		matcher.matches();
		//while (matcher.find()) {
		if(matcher.matches()){
			System.out.println("Start index: " + matcher.start());
			System.out.println("End index: " + matcher.end() + " ");
			System.out.println(matcher.group());
			System.out.println(matcher.groupCount());
			for(int i=0;i<=matcher.groupCount();i++){
			System.out.println("Group "+i+" : "+matcher.group(i));
			}
		}
		//}
		


	}

}
