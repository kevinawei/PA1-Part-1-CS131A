package cs131.pa1.command.SystemNavigation;
import java.io.File;
import cs131.pa1.filter.sequential.SequentialFilter;


public class LsFilter extends SequentialFilter {
	protected String processLine(String worthless) {
		return null;
	}

	boolean donezo = false;


	
	@Override
	public void process(){
			File f = new File("."); // current directory
			String files = f.listFiles().toString();
			output.add(files);
			donezo = true;
			return;
	}
	

	@Override
	public boolean isDone() {
		return donezo;
	}
}

