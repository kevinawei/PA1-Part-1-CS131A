package cs131.pa1.command.SystemNavigation;
import java.nio.file.*;
import cs131.pa1.filter.sequential.SequentialFilter;

public class PwdFilter extends SequentialFilter {
	protected String processLine(String worthless) {
		String path = FileSystems.getDefault().getPath(".").toString();
		return path;
	}
	@Override
	public void process(){
		return;	
	}
}

