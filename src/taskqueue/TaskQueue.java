package taskqueue;

import java.util.LinkedList;
import java.util.Queue;

public class TaskQueue {
	private Queue<Task> tasks;  
	
	public TaskQueue(){
		
		// Instantiates a new linked list in our Queue interface to store our tasks
		
		this.tasks = new LinkedList<Task>();
	}
	
	protected Task push(Task task) {
		
		// Attempt to add our task to the task queue
		
		if(!this.tasks.offer(task)){
			
			// Return error if task queue is at capacity (System Limitations)
			
			System.out.println("Task queue is over capacity, "+task.getDescription()+" cannot be added to the queue.");
		}
		return task;
	}
	
	protected Task pop() {
		
		// Returns and removes the head of the queue or null if empty
		
		return this.tasks.poll();
	}
	
	protected Task peek_next(){
		
		// Returns the head of the queue or null if empty
		
		return this.tasks.peek();
	}
	
	protected Task[] peek_all(){
		
		// Return null if our task queue is empty
		if(this.tasks.isEmpty()){
			
			return null;
		}
		
		// Return an array of task objects
		
		return (Task[])this.tasks.toArray();
	}
	
	protected int count(){
		
		// Returns the size of our queue
		
		return this.tasks.size();
	}
	

}
