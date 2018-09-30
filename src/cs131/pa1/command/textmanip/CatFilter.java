package cs131.pa1.command.textmanip;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import cs131.pa1.filter.Message;
import cs131.pa1.filter.sequential.SequentialFilter;

public class CatFilter extends SequentialFilter {
	boolean donezo = false;
	
	public CatFilter(String[] files) throws FileNotFoundException{
		int count = 0;
		do {
			File f = new File(files[count]);
		    if(f.isFile()){
		    	Scanner reader = new Scanner(f);				
		    	while (reader.hasNextLine())
		    	{
		    	   this.output.add(reader.nextLine());
		    	} 
		    	reader.close();
			} else	{
				System.out.println(Message.INVALID_PARAMETER.with_parameter("CAT"));
			}	
		    count ++;
		} while (count<files.length);
		donezo = true;
	}
	
	@Override
	public boolean isDone() {
		return donezo;
	}
			
	
	@Override
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		return null;
	}
	
}