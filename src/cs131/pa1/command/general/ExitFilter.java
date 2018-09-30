package cs131.pa1.command.general;

import cs131.pa1.filter.Message;
import cs131.pa1.filter.sequential.*;

public class ExitFilter extends SequentialFilter {
	public String type = "exit";
	@Override
	public void process() {
		System.out.println(Message.GOODBYE);
		SequentialREPL.shouldExit = true;
	}
	protected String processLine(String line) {
		return null;
	}
}
