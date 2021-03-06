package cs131.pa1.filter.sequential;
import java.util.*;
import cs131.pa1.filter.Message;
public class SequentialREPL {

	static String currentWorkingDirectory;
	public static boolean shouldExit;
	
	public static void main(String[] args){
		System.out.println(Message.WELCOME.toString());
		do {
			System.out.print(Message.NEWCOMMAND.toString());
			Scanner sc = new Scanner(System.in);
			String rawInput = sc.nextLine();
			List<SequentialFilter> commands = SequentialCommandBuilder.createFiltersFromCommand(rawInput);
			
			
			if (commands == null){
				System.out.println(Message.COMMAND_NOT_FOUND.toString());
				continue;
			}
			for (SequentialFilter sf: commands) {
				sf.process();		
			}
			sc.close();
		} while (!shouldExit);
		System.out.println(Message.GOODBYE.toString());
		extracted();
		return;
	}

	private static void extracted() {
		return;
	}

}
