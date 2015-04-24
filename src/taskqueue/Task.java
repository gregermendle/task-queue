package taskqueue;

import java.io.*;
import java.util.UUID;

public class Task{

	private UUID GUID;			// The tasks unique identifier
	private String command;		// Command to be executed
	private String description; // A name or description for our task
	
	public Task(String description, String command){
		
		this.description = description;
		this.command = command;
		
		// Generate a unique identifier for our task
		
		this.GUID = UUID.randomUUID();
	}
	
	protected void execute(){
		// execute the command
		try {
			
			long start, end; // Will hold our start and end time
			
			// Get the string value of our GUID
			
			String guid = this.GUID.toString();
			
			String output = "", exception = "";
			
			// Get the current time to later calculate execution time
			start = System.currentTimeMillis();
			
			// Create a separate process to execute our command
			
			Process exec = Runtime.getRuntime().exec(this.command);
			
			// Create the initial TaskResult object
			
			TaskResult t = new TaskResult(guid);
			TaskResults.addResult(guid, t);
			
			/*
			 * Create two BufferedReaders to collect the input stream from our processes exec
			 */
			
			BufferedReader stdOut = new BufferedReader(
					new InputStreamReader(exec.getInputStream())
			);
			
			BufferedReader stdError = new BufferedReader(
					new InputStreamReader(exec.getErrorStream())
			);
			
			// Block the current thread until the process has terminated
			try{
				
			exec.waitFor();
			
			// Get the current time to later calculate execution time
			end = System.currentTimeMillis();
			
			/*
			 * Set our taskResult data
			 */
			t.setState(TaskState.FINISHED);
			t.setException(exception);
			t.setExecutionDuration(end-start);
			t.setOutput(output);
			t.setReturnCode(exec.exitValue());
			
			if(t.getReturnCode() == 0){ // exitValue will be 0 on successful execution
				t.setSucceeded(true);
			}
			
			}catch(InterruptedException e){
				
				// Print a stack trace in the event waitFor is interrupted by another thread
				
				e.printStackTrace();
				
				// Exit since this will cause unwarranted results
				
				System.exit(-1);
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/*
	 * Getters for our objects data members
	 */
	
	public String getGUID(){
		return this.GUID.toString();
	}
	
	public String getCommand(){
		return this.command;
	}
	
	public String getDescription(){
		return this.description;
	}
}
