package cs131.pa1.command.SystemNavigation;
import java.nio.file.*;
import cs131.pa1.filter.sequential.SequentialFilter;

public class PwdFilter extends SequentialFilter {
	protected String processLine(String worthless) {
		return null;
	}
	boolean donezo = false;
	
	@Override
	public void process(){
		String path = FileSystems.getDefault().getPath(".").toString();
		this.output.add(path);
		donezo = true;
		return;
	}
	
	@Override
	public boolean isDone() {
		return donezo;
	}
}

