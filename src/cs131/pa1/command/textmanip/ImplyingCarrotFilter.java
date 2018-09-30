package cs131.pa1.command.textmanip;

import java.io.*;
import java.nio.file.Files;

import cs131.pa1.filter.Message;
import cs131.pa1.filter.sequential.SequentialFilter;

public class ImplyingCarrotFilter extends SequentialFilter {
		

	protected String processLine(String line){
		File f = new File(line);
		try {
			if (f.exists())	{
				Files.delete(f.toPath());
			}
			FileWriter Writer = new FileWriter(f);
			Writer.write(this.input.toString());
			Writer.close();
		} catch (IOException e) {
			System.out.println(Message.DIRECTORY_NOT_FOUND.with_parameter(">"));
		}			
		
		return null;
	}
}
	
