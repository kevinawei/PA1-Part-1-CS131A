package cs131.pa1.filter.sequential;

import java.util.Arrays;
import java.util.List;

import cs131.pa1.filter.Message;
import cs131.pa1.command.textmanip.*;
import cs131.pa1.command.SystemNavigation.CdFilter;
import cs131.pa1.command.SystemNavigation.LsFilter;
import cs131.pa1.command.SystemNavigation.PwdFilter;
import cs131.pa1.command.general.*;

public class SequentialCommandBuilder {
	private static final List<String> C = Arrays.asList("grep", "cat", "wc", "uniq", "exit", "pwd", "ls", "cd", ">");
	private static final List<String> T = Arrays.asList("grep", "uniq", ">", "wc");
	private static List<SequentialFilter> fList;
	public static final String PIPE = "|";
	
	public static List<SequentialFilter> createFiltersFromCommand(String command){
		
		if (!command.isEmpty()) {
			String newCom = adjustCommandToRemoveFinalFilter(command);
			if (newCom == null) {
				return null;
			}
			if (newCom.contains(PIPE)){
				String[] commands = newCom.split(PIPE);
				
				for(int i = 0; i < commands.length ;i++) {
					if (constructFilterFromSubCommand(commands[i]) == null) {
						System.out.println(Message.COMMAND_NOT_FOUND.toString());
						return null;
					}
					fList.add(constructFilterFromSubCommand(commands[i]));
				}
				if (!linkFilters(fList)) {
					SequentialREPL.shouldExit = true;
					return null;
				}
				return fList;
			} 
			else {
				fList.add(constructFilterFromSubCommand(newCom));
				return fList;
			}
		}
		return null;
	}
	
	private static String adjustCommandToRemoveFinalFilter(String command){
		if (!command.contains(">")) {
			return command;
		} 
		else {
			String[] checkFilter = command.split(">");
			String badCom;
			
			if(command.indexOf(">") == 0) {
				System.out.printf(Message.REQUIRES_INPUT.toString(), command);
				return null;
				} 
			else if (command.indexOf(">") == command.length()-1) {
				System.out.printf(Message.REQUIRES_PARAMETER.toString(), ">");
				return null;
			} 
			else if (checkFilter.length>2) {
				System.out.printf(Message.CANNOT_HAVE_OUTPUT.toString(), checkFilter[0].trim());
				return null;
			} 
			else if (checkFilter.length == 2 && checkFilter[1].contains("|")) {
				if (checkFilter[0].contains("|")) {
					badCom = checkFilter[0].substring(checkFilter[0].lastIndexOf('|')).trim();
				} else {
					badCom = "> "+checkFilter[1].substring(0,checkFilter[1].indexOf('|')).trim();
				}
				
				System.out.printf(Message.CANNOT_HAVE_OUTPUT.toString(), badCom);
				return null;
			}
			
			return checkFilter[0].trim();
		}
	}
	
	private static SequentialFilter constructFilterFromSubCommand(String subCommand){
		
		if(!C.parallelStream().anyMatch(subCommand::contains)) {
			System.out.println(Message.COMMAND_NOT_FOUND.toString());
			return null;
		} 
		if(subCommand.startsWith("pwd")) {
			PwdFilter p = new PwdFilter();
			return p;
		}
		if(subCommand.startsWith("ls")) {
			LsFilter l = new LsFilter();
			return l;
		}
		if(subCommand.startsWith("cd")) {
			String[] parts = subCommand.split(" ");
			if (parts.length == 1) {
				System.out.printf(Message.REQUIRES_PARAMETER.with_parameter("cd"));
				return null;
			}
			CdFilter cd = new CdFilter();
			return cd;
		}
		if(subCommand.startsWith("cat")) {
			String[] parts = subCommand.split(" ");
			if (parts.length == 1) {
				System.out.println(Message.REQUIRES_PARAMETER.with_parameter("cat"));
				return null;
			}
			else {
				CatFilter c = new CatFilter();
				return c;
			}
		}
		if(subCommand.startsWith("grep")) {
			String[] parts = subCommand.split(" ");
			if (parts.length == 1) {
				System.out.println(Message.REQUIRES_PARAMETER.with_parameter("grep"));
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
			WcFilter wc = new WcFilter();
			return wc;
		}
		if(subCommand.contains(">")) {
			String[] parts = subCommand.split(" ");
			if (parts.length == 1) {
				System.out.println(Message.REQUIRES_PARAMETER.with_parameter(">"));
				return null;
			}
			ImplyingCarrotFilter icf = new ImplyingCarrotFilter();
			return icf;
		}
		if(subCommand.contains("exit")) {
			ExitFilter e = new ExitFilter();
			return e;
		}
		return null;
		
		
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
		return true;
	}
}
