package ich.inputCollector;

import ich.utils.Utils;

import java.util.List;

public class FileInputCollector implements InputCollectable{

	public List<String> collect() {
		return Utils.readFileLineByLine();
	}
}
