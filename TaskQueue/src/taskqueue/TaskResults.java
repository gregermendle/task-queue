package taskqueue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class TaskResults {
	
	private static Map<String, TaskResult> taskResults = new HashMap<String, TaskResult>(); 
	
	
	// private constructor prevents use of new
	private TaskResults(){
	}
	
	protected static void addResult(String guid, TaskResult taskResult){
		
		// Inserts our TaskResult into our taskResults map
		
		taskResults.put(guid, taskResult);
	}
	
	protected static TaskResult result(String guid){
		
		// Returns the taskResult for the specified guid or null if it does not exist
		
		return taskResults.get(guid);
		
	}
	
	protected static TaskResult[] results(String guid[]){
		
		// Return null if there are no task results
		
		if(taskResults.isEmpty()){
			return null;
		}
		
		/*
		 * Iterate through each guid and add the corresponding value to our temporary results list
		 */
		
		ArrayList<TaskResult> results = new ArrayList<TaskResult>();
		
		for(int i = 0; i<guid.length; i++){
			
			results.add(result(guid[i]));
			
		}
		
		// Convert arrayList to a regular array to maintain consistency
		
		return (TaskResult[])results.toArray();
		
	}
}
