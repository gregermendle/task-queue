# TaskQueue
TaskQueue is a simple FIFO shell command task queue.

**It should be noted that TaskQueue is a rudimetary queueing system intended for learning and testing purposes only.**

# Building TaskQueue
---
The TaskQueue repository contains both the source code and an Eclipse project file. 
The easiest way to build TaskQueue is to import the supplied Eclipse project file into the Eclipse IDE, and use Eclipse to run, debug, test, and make changes to TaskQueue

# Using Task Queue
---
TaskQueue is a FIFO shell command queuing system that uses Java's Process and Runtime classes to execute shell commands. TaskQueue is equipped with tools to help manage and execute queued tasks. Below are some examples of use-cases for TaskQueue.

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
TaskQueue comes with the TaskResults class. This class is concerned with gathering results after the execution of a given task. TaskResults contains static data members and therefore does not need to be instantiated with `new`.

You can query the results for a given task by using the `result(String GUID)` method. This method takes the GUID of a given task that has been executed and returns a TaskResult object.

```java
TaskResult result = TaskResults.result(t.getGUID()); // returns the TaskResult object for task T

```
