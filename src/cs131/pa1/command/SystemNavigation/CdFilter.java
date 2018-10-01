package cs131.pa1.command.SystemNavigation;

import cs131.pa1.filter.Filter;
import cs131.pa1.filter.sequential.SequentialFilter;

public class CdFilter extends SequentialFilter {
	
	@Override
	public void process(){
		return;	
	}

	@Override
	public String processLine(String line) {
		String cwd = System.getProperty("user.dir");
		int slash = cwd.indexOf(Filter.FILE_SEPARATOR, -1);
		String dots = line.substring(0, slash);
		String afterDots = line.substring(slash, line.length()-1);
		if (dots == "..") {
			int endIndex = cwd.lastIndexOf(Filter.FILE_SEPARATOR);
		    if (endIndex != -1)  
		    {
		        cwd = cwd.substring(0, endIndex);
		    }
		    System.setProperty("user.dir",  cwd + afterDots);
		} else {
			System.setProperty("user.dir",  cwd + afterDots);			
		}
		
		return null;
	}
}
