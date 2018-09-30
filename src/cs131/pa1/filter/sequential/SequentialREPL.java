package cs131.pa1.filter.sequential;
import java.util.*;
import cs131.pa1.filter.Message;
public class SequentialREPL {

	static String currentWorkingDirectory;
	public static boolean shouldExit;
	
	public static void main(String[] args){
		shouldExit = false;
		System.out.println(Message.WELCOME.toString());
		do {
			System.out.print(Message.NEWCOMMAND.toString());
			Scanner sc = new Scanner(System.in);
			String rawInput = sc.nextLine();
			SequentialCommandBuilder.createFiltersFromCommand(rawInput);
			sc.close();
		} while (!shouldExit) ;
		
	}

}
