import java.lang.Thread;

public class Test extends Thread {

  public Test(String name) {
    super(name);
  }

  @Override
  public void run() {
    int i=0;
    while(i < 10) {
      System.out.println(Thread.currentThread().getName() + " is running " + i);

      // .yield() provides a hint to the scheduler that the current thread is willing to give the CPU time to other threads running.
      // It is just a hint and not mandatory that it will happen that other threads get the CPU time.
      Thread.yield();
      
      i++;
    }
  }

  public static void main(String[] args) throws Exception {
    System.out.println("Hello world from Main Thread!");
    
    // Creating new Threads using extending Thread class
    Test t1 = new Test("T1");
    Test t2 = new Test("T2");
    
    // .setDaemon(true) marks the thread as a Daemon thread. Which means the JVM will not wait for this Thread to finish executing. It might terminate the program before that.
    // But for regular (User) Threads, which is the default, JVM waits till all these User Threads and the Main Thread are finished executing before terminating the program.
    t1.setDaemon(true);
    
    // .start() calls the overriden run() method in the subclass of Thread class
    t1.start();
    t2.start();
    
    // .setName() to change the name of the thread running
    t1.setName("T1x");

    // .setPriority() to set the priority of the thread.
    // MIN_PRIORITY -> 1, NORM_PRIORITY -> 5 (DEFAULT), MAX_PRIORITY -> 10
    // setting a priority is just a hint to the scheduler to prioritize the threads with higher priority.
    // But it is not guranteed that threads with higher priority will always run first. 
    t1.setPriority(Thread.MIN_PRIORITY);
    t2.setPriority(Thread.MAX_PRIORITY);

    // .getState() -> returns the current state of the Thread
    // 1. NEW -> Thread is created but not run yet
    // 2. RUNNABLE -> Thread is either ready to be executed or is currently running
    // 3. BLOCKED -> Thread is currently waiting for another Thread to finish executing or waiting for some resource
    // 4. TIMED_WAITING -> Thread is waiting for a set amount of time
    // 5. TERMINATED -> Thread has finished executing and has been terminated
    
    Thread.sleep(5);
    System.out.println(t1.getState());
    System.out.println(t2.getState());

    
    // .join() stops the current Thread from executing further until the spun Thread completes executing and it joins back to the current Thread.
    // t1.join();
    // t2.join();

    System.out.println(t1.getState());
    System.out.println(t2.getState());
  }
}