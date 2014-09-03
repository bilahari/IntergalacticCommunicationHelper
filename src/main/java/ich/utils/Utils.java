package ich.utils;

import ich.configs.ConfigManager;
import ich.configs.Constants;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class Utils {
	
	public static String readLine(){
		String line=null;
		BufferedReader bufRead = new BufferedReader(new InputStreamReader(System.in)) ;	
		try {
			line = bufRead.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return line;
	}
	
	public static List<String> readFileLineByLine(){
		List<String> inputs=new ArrayList<String>();
		BufferedReader br = null;
		try {
			String sCurrentLine;
			System.out.println(ConfigManager.getInstance().getProperty(Constants.INPUT_FILE_PATH));
			br = new BufferedReader(new FileReader(ConfigManager.getInstance().getProperty(Constants.INPUT_FILE_PATH)));
			while ((sCurrentLine = br.readLine()) != null) {
				inputs.add(sCurrentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return inputs;
	}
	public static boolean isEmptyString(String str){
		return str==null||str.trim().length()<=0;
	}
}
