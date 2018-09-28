package cs131.pa1.filter.sequential;

import java.util.Arrays;
import java.util.List;

import cs131.pa1.filter.Message;
import cs131.pa1.command.textmanip.*;

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
		
		if(!C.parallelStream().anyMatch(subCommand::contains)) {
			System.out.println("Message.COMMAND_NOT_FOUND");
		return null;
		}
		if(subCommand.contains("pwd")) {
			
		}
		if(subCommand.contains("ls")) {
			
		}
		if(subCommand.contains("cd")) {
			
		}
		if(subCommand.contains("cat")) {
			CatFilter c = new CatFilter();
			return c;
		}
		if(subCommand.contains("grep")) {
			String[] parts = subCommand.split(" ");
			GrepFilter g = new GrepFilter();
			g.search = parts[1];
			return g;
			
		}
		if(subCommand.contains("uniq")) {
			UniqFilter u = new UniqFilter();
			return u;
		}
		if(subCommand.contains("wc")) {
			WCFilter wc = new WCFilter();
			return wc;
		}
		if(subCommand.contains(">")) {
			
		}
		if(subCommand.contains("exit")) {
			
		}
		
		
	}

	private static boolean linkFilters(List<SequentialFilter> filters){
		return false;
	}
}
