package cs131.pa1.command.textmanip;


import cs131.pa1.filter.sequential.SequentialFilter;

public class GrepFilter extends SequentialFilter {
	public String search;
	
	@Override
	protected String processLine(String line) {
		if (line.contains(search)) {
			return line;
		}
		return null;
	}
}
