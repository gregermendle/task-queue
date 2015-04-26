package taskqueue;

public class Driver {

	public static void main(String args[]){
		TaskQueue simple_tasks = new TaskQueue();
		simple_tasks.push(new Task("My first task","echo Hey"));
		simple_tasks.push(new Task("My second task","ping -c 3 google-public-dns-b.google.com"));
		
		while(simple_tasks.count() > 0){
			Task t = simple_tasks.pop();
			
			// Place print before execution since it is blocking in this case
			// The blocking issue could in turn be fixed using threads
			
			System.out.println("Running task "+t.getGUID());
			t.execute();
			
			TaskResult result = TaskResults.result(t.getGUID());
			System.out.println("Task Succeeded: "+result.getSucceeded());
			System.out.println("Task Execution Time: "+result.getExecutionDuration()+" Milliseconds");
			System.out.println("Task State: "+result.getState());
			System.out.println("Task Return Code: "+result.getReturnCode());
		}
		
		
	}
	
}
