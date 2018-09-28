package cs131.pa1.filter.sequential;

import java.util.Arrays;
import java.util.List;

public class SequentialCommandBuilder {
	private static final List<String> C = Arrays.asList("grep", "cat", "wc", "uniq", "exit", "pwd", "ls", "cd", ">");
	private static List<SequentialFilter> fList;
	public static final String PIPE = "|";
	
	public static List<SequentialFilter> createFiltersFromCommand(String command){
		
		if (command.contains(PIPE)){
			String[] commands = command.split(PIPE);
			
			for(int i = 0; i < commands.length ;i++) {
				fList.add(constructFilterFromSubCommand(commands[i]));
			}
			linkFilters(fList);
		} 
		else {
			fList.add(constructFilterFromSubCommand(command));
		}
		
		return fList;
	}
	
	private static SequentialFilter determineFinalFilter(String command){
		return null;
	}
	
	private static String adjustCommandToRemoveFinalFilter(String command){
		return null;
	}
	
	private static SequentialFilter constructFilterFromSubCommand(String subCommand){
		
		return null;
		
	}

	private static boolean linkFilters(List<SequentialFilter> filters){
		return false;
	}
}
