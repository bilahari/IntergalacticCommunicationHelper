package ich.inputCollector;

import ich.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class BasicInputCollector implements InputCollectable{

	public List<String> collect() {
		List<String> inputs = new ArrayList<String>();
		System.out.println("Enter the statements one after another. Enter EOS(End Of Statements) to finish");
		String line=Utils.readLine();
		while(!line.equalsIgnoreCase("EOS")){
			inputs.add(line);
			line=Utils.readLine();
		}
		return inputs;
	}
}
