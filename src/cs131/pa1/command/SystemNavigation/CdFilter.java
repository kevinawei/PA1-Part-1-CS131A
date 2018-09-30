package cs131.pa1.command.SystemNavigation;
import java.nio.file.*;
import cs131.pa1.filter.sequential.SequentialFilter;

public class CdFilter extends SequentialFilter {
	
	@Override
	public void process(){
		return;	
	}

	@Override
	protected String processLine(String line) {
		String cwd = System.getProperty("user.dir");
		int slash = cwd.indexOf("\\", -1);
		String dots = cwd.substring(0, slash);
		if (dots == "..") {
			
		} else if (dots == ".") {
			
		}
		
		return null;
	}
}
