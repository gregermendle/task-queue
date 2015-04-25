package taskqueue;

public class TaskResult {
	
	private String task_guid;					// Original task GUID
	private boolean succeeded;					// True if task execution was successful false otherwise
	private TaskState state;					// State of the current task, either PENDING or FINISHED
	private int return_code;				// The return code returned after execution
	private String output = "";					// Standard Output from task
	private String exception = "";				// Exceptions thrown by the task during execution
	private double execution_duration = 0.0;	// Duration of execution for task
	
	public TaskResult(String task_guid){
		
		this.task_guid = task_guid;
		
		/*
		 * TaskResult constructor is called at the beginning of the execution call
		 * Thus state is set to pending and succeeded to false since execution has not yet begun
		 */
		
		this.succeeded = false;
		this.state = TaskState.PENDING;
		
	}
	
	/*
	 * Getters for objects data members
	 */
	
	public String getTaskGUID(){
		return this.task_guid;
	}
	
	public Boolean getSucceeded(){
		return this.succeeded;
	}
	
	public TaskState getState(){
		return this.state;
	}
	
	public int getReturnCode(){
		return this.return_code;
	}
	
	public String getOutput(){
		return this.output;
	}
	
	public String getException(){
		return this.exception;
	}
	
	public double getExecutionDuration(){
		return this.execution_duration;
	}
	
	/*
	 * Setters for objects data members
	 */
	
	public void setTaskGUID(String task_guid){
		this.task_guid = task_guid;
	}
	
	public void setSucceeded(boolean succeeded){
		this.succeeded = succeeded;
	}
	
	public void setState(TaskState state){
		this.state = state;
	}
	
	public void setReturnCode(int return_code){
		this.return_code = return_code;
	}
	
	public void setOutput(String output){
		this.output = output;
	}
	
	public void setException(String exception){
		this.exception = exception;
	}
	
	public void setExecutionDuration(double execution_duration){
		this.execution_duration = execution_duration;
	}
	
}
