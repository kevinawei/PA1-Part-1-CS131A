package cs131.pa1.filter.sequential;
import java.util.*;
import cs131.pa1.filter.Message;
import java.lang.Object;
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
			
			//Input is now a ArrayList of strings, feed it to sequential command builder
			//Send input to somewhere it's a linked list of strings
		} while (!shouldExit) ;
		
	}

}
