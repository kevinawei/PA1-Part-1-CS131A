package cs131.pa1.filter.sequential;

import java.io.FileNotFoundException;
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
	
	private static SequentialFilter constructFilterFromSubCommand(String subCommand) throws FileNotFoundException{
		
		if(!C.parallelStream().anyMatch(subCommand::contains)) {
			System.out.println(Message.COMMAND_NOT_FOUND.toString());
			return null;
		}
		if(subCommand.contains("pwd")) {
			PwdFilter p = new PwdFilter();
			p.process();
			return p;
		}
		if(subCommand.contains("ls")) {
			LsFilter l = new LsFilter();
			l.process();
			return l;
		}
		if(subCommand.contains("cd")) {
			String[] parts = subCommand.split(" ");
			if (parts.length == 1) {
				System.out.println(Message.REQUIRES_PARAMETER.toString());
				return null;
			}
			CdFilter cd = new CdFilter();
			cd.processLine(parts[1]);
			return cd;
		}
		if(subCommand.contains("cat")) {
			String[] parts = subCommand.split(" ");
			if (parts.length == 1) {
				System.out.println(Message.REQUIRES_PARAMETER.with_parameter("CAT"));
				return null;
			}
			else {
				String[] files = Arrays.copyOfRange(parts, 1, (parts.length -1));
				CatFilter c = new CatFilter(files);
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
			WcFilter wc = new WcFilter();
			return wc;
		}
		if(subCommand.contains(">")) {
			String[] parts = subCommand.split(" ");
			if (parts.length == 1) {
				System.out.println(Message.REQUIRES_PARAMETER.toString());
				return null;
			}
			ImplyingCarrotFilter icf = new ImplyingCarrotFilter();
			icf.processLine(parts[1]);
			return icf;
		}
		if(subCommand.contains("exit")) {
			ExitFilter e = new ExitFilter();
			return e;
		}
		return null;
		
		
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
