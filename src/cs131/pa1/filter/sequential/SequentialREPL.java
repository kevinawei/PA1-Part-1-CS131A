package cs131.pa1.filter.sequential;
import java.util.ArrayList;
import java.util.Scanner;

public class SequentialREPL {

	static String currentWorkingDirectory;
	
	public static void main(String[] args){
		System.out.println("Welcome to the Unix-ish command line.");
		do {
			Scanner sc = new Scanner(System.in);
			String rawInput = sc.nextLine();
			ArrayList<String> input = new ArrayList<String>();
			for(String S: rawInput.split(" ")) {
				input.add(S);
			}
			//Send input to somewhere it's a linked list of strings
		} while () ;
		
	}

}
