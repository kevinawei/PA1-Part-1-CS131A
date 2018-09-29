package cs131.pa1.command.textmanip;

import cs131.pa1.filter.sequential.SequentialFilter;

public class WcFilter extends SequentialFilter {
	int lines;
	int words;
	int chars;

	//Slightly different process from SequentialFilter, because we need to output 
	//the number of lines, words, chars instead of the processed lines.
	@Override
	public void process() {
		super.process();
		output.add(lines+" "+words+" "+chars);
	}
	@Override
	protected String processLine(String line) {
		lines++;
		words += line.split("\\s+").length;
		chars += line.replace("\n", "").length();
		return null;
	}
}
