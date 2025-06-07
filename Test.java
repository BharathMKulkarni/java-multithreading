import java.lang.Thread;

public class Test extends Thread {

  public Test(String name) {
    super(name);
  }

  @Override
  public void run() {
    int i=0;
    while(i < 20) {
      System.out.println(Thread.currentThread().getName() + " is running");
      i++;
    }
  }

  public static void main(String[] args) {
    System.out.println("Hello world from Main Thread!");
    
    // Creating new Threads using extending Thread class
    Test t1 = new Test("T1");
    Test t2 = new Test("T2");
    
    // .start() calls the overriden run() method in the subclass of Thread class
    t1.start();
    t2.start();
    
    // .setName() to change the name of the thread running
    t1.setName("T1x");

    // .setPriority() to set the priority of the thread.
    // MIN_PRIORITY -> 1, NORM_PRIORITY -> 5 (DEFAULT), MAX_PRIORITY -> 10
    // setting a priority is just a hint to the scheduler to prioritize the threads with higher priority.
    // But it is not guranteed that threads with higher priority will always run first. 
    t1.setPriority(Thread.NORM_PRIORITY);
    t2.setPriority(Thread.MAX_PRIORITY);

    // .getState() -> returns the current state of the Thread
    // 1. NEW -> Thread is created but not run yet
    // 2. RUNNABLE -> Thread is either ready to be executed or is currently running
    // 3. BLOCKED -> Thread is currently waiting for another Thread to finish executing or waiting for some resource
    // 4. TIMED_WAITING -> Thread is waiting for a set amount of time
    // 5. TERMINATED -> Thread has finished executing and has been terminated
    System.out.println(t1.getState());
    System.out.println(t2.getState());
  }
}