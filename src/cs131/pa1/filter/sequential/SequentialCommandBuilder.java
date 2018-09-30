package cs131.pa1.filter.sequential;

import java.util.Arrays;
import java.util.List;

import cs131.pa1.filter.Message;
import cs131.pa1.command.textmanip.*;
import cs131.pa1.command.SystemNavigation.LsFilter;
import cs131.pa1.command.SystemNavigation.PwdFilter;
import cs131.pa1.command.general.*;

public class SequentialCommandBuilder {
	private static final List<String> C = Arrays.asList("grep", "cat", "wc", "uniq", "exit", "pwd", "ls", "cd", ">");
	private static final List<String> T = Arrays.asList("grep", "uniq", ">", "wc");
	private static List<SequentialFilter> fList;
	public static final String PIPE = "|";
	
	public static List<SequentialFilter> createFiltersFromCommand(String command){
		
		if (command.contains(PIPE)){
			String[] commands = command.split(PIPE);
		
			for(int i = 0; i < commands.length ;i++) {
				fList.add(constructFilterFromSubCommand(commands[i]));
			}
			if (!linkFilters(fList)) {
				SequentialREPL.shouldExit = true;
				return null;
			}
			return fList;
		} 
		else {
			fList.add(constructFilterFromSubCommand(command));
			return fList;
		}
		
	
	}
	
	private static SequentialFilter determineFinalFilter(String command){
		return null;
	}
	
	private static String adjustCommandToRemoveFinalFilter(String command){
		return null;
	}
	
	private static SequentialFilter constructFilterFromSubCommand(String subCommand){
		
		if(!C.parallelStream().anyMatch(subCommand::contains)) {
			System.out.println(Message.COMMAND_NOT_FOUND.toString());
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
			String[] parts = subCommand.split(" ");
			if (parts.length == 1) {
				System.out.println(Message.REQUIRES_PARAMETER.toString());
				return null;
			}
			CdFilter cd = new CdFilter(parts[1]);
			return cd;
		}
		if(subCommand.contains("cat")) {
			String[] parts = subCommand.split(" ");
			if (parts.length == 1) {
				System.out.println(Message.REQUIRES_PARAMETER.toString());
				return null;
			}
			else {
			CatFilter c = new CatFilter(parts[1]);
			return c;
			}
		}
		if(subCommand.contains("grep")) {
			String[] parts = subCommand.split(" ");
			if (parts.length == 1) {
				System.out.println(Message.REQUIRES_PARAMETER.toString());
				return null;
			}
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
			ImplyingCarrotFilter icf = new ImplyingCarrotFilter();
			return icf;
		}
		if(subCommand.contains("exit")) {
			ExitFilter e = new ExitFilter();
			return e;
		}
		
		
	}

	private static boolean linkFilters(List<SequentialFilter> filters){
		if (T.contains(filters.get(0).type)) {
			System.out.println(Message.REQUIRES_INPUT.toString());
			return false;
		}
		for (int i =0; i< filters.size(); i++) {
			SequentialFilter first = filters.get(i);
			SequentialFilter second = null;
			if (filters.size() > i+1) {
				second = filters.get(i+1);
			}
			first.setNextFilter(second);
		}
		return true;
	}
}
