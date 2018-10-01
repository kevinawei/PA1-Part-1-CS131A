package cs131.pa1.command.textmanip;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import cs131.pa1.filter.Message;
import cs131.pa1.filter.sequential.SequentialFilter;

public class CatFilter extends SequentialFilter {
	boolean donezo = false;
	
	
	@Override
	public boolean isDone() {
		return donezo;
	}
			
	
	@Override
	protected String processLine(String line) {
		int count = 0;
		do {
			File f = new File(line);
		    if(f.isFile()){
		    	Scanner reader;
				try {
					reader = new Scanner(f);
					while (reader.hasNextLine())
			    	{
			    	   this.output.add(reader.nextLine());
			    	} 
			    	reader.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
		    	
			} else	{
				System.out.println(Message.INVALID_PARAMETER.with_parameter("CAT"));
			}	
		    count ++;
		} while (count<line.length());
		donezo = true;
		return null;
	}
	
}