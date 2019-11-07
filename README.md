# TaskQueue
TaskQueue is a simple FIFO shell command task queue.

**It should be noted that TaskQueue is a rudimetary queueing system intended for learning and testing purposes only.**

# Building TaskQueue
---
The TaskQueue repository contains both the source code and an Eclipse project file. 
The easiest way to build TaskQueue is to import the supplied Eclipse project file into the Eclipse IDE, and use Eclipse to run, debug, test, and make changes to TaskQueue

# Using Task Queue
---
TaskQueue is a FIFO shell command queuing system that uses Java's Process and Runtime classes to execute shell commands. TaskQueue is equipped with tools to help manage and execute queued tasks. Below is an outline of TaskQueue's tools accompanied with the corresponding syntax.

### First Things First

Before you can use TaskQueue you must create a new TaskQueue object. This will allow us to add, execute, and remove tasks from our task queue.

```java
TaskQueue myTaskQueue = new TaskQueue();  // create a new TaskQueue instance
```
_Further examples will use myTaskQueue as the TaskQueue instance_

### Creating A Task
A task can be created by calling `new Task(String Description, String CommandLine)`.

```java
Task myTask = new Task("My First Task", "echo 'Hello World!'"); // create a new Task object
```

Now that the task has been created, it contains your description and your command line along with a Globaly Unique Identifier (GUID).

These Fields can be accessed, and changed (except for GUID) using getters and setters.

Once a task has been created, you can then execute it using the `execute()` method. During execution data from the executed process is loaded into a TaskResult object. This will be covered later in this document.

```java
myTask.execute(); // executes the task echo 'Hello World!'
```

```java
myTask.getGUID(); // return GUID
myTask.getDescription(); // return Description
myTask.getCommand(); // return Command Line

myTask.setDescription(String Description); // set Description
myTask.setCommand(String CommandLine); // set Command Line
```

### Using The Task Queue
Tasks can be added to the task queue by using the `push( Task T )` method. This method excepts a Task object and returns the the Task object that was added to the queue.

```java
myTaskQueue.push(myTask); // add our new task to the task queue
```
##### Queue Operations
Since our tasks are held in a FIFO (First In First Out) queue, it's important to be able to retrieve the task at the front or the head of the queue. This can be done in two different ways:

You can `peek_next()`, which will return the element at the head of the queue and *not* remove it or you can `pop()` which will return and *will* remove the element at the head of the queue.

```java
Task t = myTaskQueue.peek_next(); // retrieve the head, do not remove

// or

Task t = myTaskQueue.pop(); // retrieve/remove the head
```

You can view all of the elements currently in the queue by using the `peek_all()` method. This returns and array of Task elements that are in the queue.

```java
Task t[] = myTaskQueue.peek_all(); // return array of task objects in the queue
```

##### Queue Count
The number of elements that are current in the queue can be found using the `count()` method. This returns and integer value of the number of elements in the task queue.

```java
int size = myTaskQueue.count(); // count elements in task queue
```
## Getting Results
The TaskResults class is concerned with storing results for executed tasks. TaskResults contains static data members and therefore does not need to be instantiated with `new`.

You can query the results for a given task by using the `result(String GUID)` method. This method takes the GUID of a given task that has been executed and returns a TaskResult object containing information about the processes execution.

You can get an array containing all process object by using the `results()` method. This returns an array of TaskResult objects for all tasks that have been executed.

```java
TaskResult result = TaskResults.result(t.getGUID()); // returns the TaskResult object for task T
TaskResult results[] = TaskResults.results(); // all results for all executed tasks
```

1. `task_guid` The GUID of the task
2. A boolean `succeeded` that determines if the task terminated without error
3. The `return_code` for the completed task
4. A String `output` that contains the concatenated output stream from the process
5. A string `exception` that contains the concatenated error stream from the process
6. `state`, which contains a value corresponding to the Enumerated Type TaskState. The value can be PENDING for tasks that are still executing and FINISHED for tasks that have terminated.
7. And lastly an integer `execution_duration` that contains the time in milliseconds it took for the given task to complete

All of these values, as seen before, can be accessed using getters.

```java
result.getTaskGUID(); // returns the guid for the given task
result.getSucceeded(); // returns whether the task succeeded
result.getReturnCode(); // returns the return code for the executed process
result.getOutput(); // returns a string of the output stream from the process
result.getException(); // returns a string of the error stream from the process
result.getState(); // returns a TaskState of the processes state
result.getExecutionDuration(); // returns the milliseconds it took for the process to execute
```

# Notes
During task execution, Java's Process and Runtime classes are used to create a sub process that executes a given task's command line. Enable to gather results after this sub processes execution, a thread block must be put in place to wait for the process to terminate. If TaskQueue is run on a single thread, the main thread running the application will be blocked while the sub process executes. To remedy this issue, a thread pool can be created using Java's Threads and Runnable classes in your application to create a new thread for each execution. The wait statement will then block the newly created thread instead of blocking the main thread.
