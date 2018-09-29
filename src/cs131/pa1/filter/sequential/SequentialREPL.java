package cs131.pa1.filter.sequential;
import java.util.ArrayList;
import java.util.Scanner;
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
			ArrayList<String> input = new ArrayList<String>();
			for(String S: rawInput.split(" ")) {
				input.add(S);
			}
			//Send input to somewhere it's a linked list of strings
		} while (!shouldhExit) ;
		
	}

}
