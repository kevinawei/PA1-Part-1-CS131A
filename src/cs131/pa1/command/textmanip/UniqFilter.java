package cs131.pa1.command.textmanip;

import cs131.pa1.filter.sequential.SequentialFilter;
import java.util.HashSet;

public class UniqFilter extends SequentialFilter {
	
	private HashSet<String> lines;

	protected String processLine(String line) {
		if (lines.contains(line)) {
			return null;
		}
		else {
			lines.add(line);
			return line;
		}
	}
	
}
