package cs131.pa1.filter.sequential;

import java.util.Arrays;
import java.util.List;

import cs131.pa1.filter.Message;
import cs131.pa1.command.textmanip.*;
import cs131.pa1.command.general.*;

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
			PwdFilter p = new PwdFilter();
			return p;
		}
		if(subCommand.contains("ls")) {
			LsFilter l = new LsFilter();
			return l;
		}
		if(subCommand.contains("cd")) {
			CdFilter cd = new CdFilter();
			return cd;
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
			WcFilter wc = new WCFilter();
			return wc;
		}
		if(subCommand.contains(">")) {
			ImplyingCarrotFilter icf = new ImplyingCarrotFilter();
			return icf;
		}
		if(subCommand.contains("exit")) {
			ExitFilter e = new ExitFilter();
			return e;
		}
		
		
	}

	private static boolean linkFilters(List<SequentialFilter> filters){
		for (int i =0; i< filters.size(); i++) {
			SequentialFilter first = filters.get(i);
			SequentialFilter second = null;
			if (filters.size() > i+1) {
				second = filters.get(i+1);
			}
			first.setNextFilter(second);
		}
		return false;
	}
}
