package ich.utils;

import ich.exceptions.BadArgumentException;
import ich.planetSummary.Symbols;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RomanNumeralUtils {

	private final static Logger logger = LoggerFactory.getLogger(Symbols.class);

	private static String validRomanNumberRegEx = "^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";
	public static int convertRomanToDecimal(String romanNumber){
		romanNumber = romanNumber.toUpperCase();
		try{
			if(isValidRomanNumber(romanNumber)){
				if(Utils.isEmptyString(romanNumber)) return 0;
				if (romanNumber.startsWith("M")) return 1000 + convertRomanToDecimal(romanNumber.substring(1,romanNumber.length()));
				if (romanNumber.startsWith("CM")) return 900 + convertRomanToDecimal(romanNumber.substring(2,romanNumber.length()));
				if (romanNumber.startsWith("D")) return 500 + convertRomanToDecimal(romanNumber.substring(1,romanNumber.length()));
				if (romanNumber.startsWith("CD")) return 400 + convertRomanToDecimal(romanNumber.substring(2,romanNumber.length()));
				if (romanNumber.startsWith("C")) return 100 + convertRomanToDecimal(romanNumber.substring(1,romanNumber.length()));
				if (romanNumber.startsWith("XC")) return 90 + convertRomanToDecimal(romanNumber.substring(2,romanNumber.length()));
				if (romanNumber.startsWith("L")) return 50 + convertRomanToDecimal(romanNumber.substring(1,romanNumber.length()));
				if (romanNumber.startsWith("XL")) return 40 + convertRomanToDecimal(romanNumber.substring(2,romanNumber.length()));
				if (romanNumber.startsWith("X")) return 10 + convertRomanToDecimal(romanNumber.substring(1,romanNumber.length()));
				if (romanNumber.startsWith("IX")) return 9 + convertRomanToDecimal(romanNumber.substring(2,romanNumber.length()));
				if (romanNumber.startsWith("V")) return 5 + convertRomanToDecimal(romanNumber.substring(1,romanNumber.length()));
				if (romanNumber.startsWith("IV")) return 4 + convertRomanToDecimal(romanNumber.substring(2,romanNumber.length()));
				if (romanNumber.startsWith("I")) return 1 + convertRomanToDecimal(romanNumber.substring(1,romanNumber.length()));
				throw new BadArgumentException("Not a proper Roman Number");
			}else{
				throw new BadArgumentException("Not a proper Roman Number");
			}
		}catch(BadArgumentException bae){
			logger.info("Exception in converting roman number so returning 0"+bae.getMessage());
			return 0;
		}
	}

	public static boolean isValidRomanNumber(String romanNumber){
		romanNumber = romanNumber.toUpperCase();
		Pattern pattern = Pattern.compile(validRomanNumberRegEx,Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(romanNumber);
		return matcher.matches();
	}

	public static List<String> getAllRomanNumeralSymbols(){
		List<String> romanNumeralSymbols = new ArrayList<String>();
		romanNumeralSymbols.add("I");
		romanNumeralSymbols.add("V");
		romanNumeralSymbols.add("X");
		romanNumeralSymbols.add("L");
		romanNumeralSymbols.add("C");
		romanNumeralSymbols.add("D");
		romanNumeralSymbols.add("M");
		return romanNumeralSymbols;
	}
}
