package cs131.pa1.command.SystemNavigation;
import java.io.File;
import java.nio.file.*;
import cs131.pa1.filter.sequential.SequentialFilter;


public class LsFilter extends SequentialFilter {
	protected String processLine(String worthless) {
		File f = new File("."); // current directory
		String files = f.listFiles().toString();
		return files;
	}




	
	@Override
	public void process(){
		return;	
	}
}

