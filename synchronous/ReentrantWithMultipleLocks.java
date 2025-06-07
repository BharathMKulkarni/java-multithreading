package synchronous;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;

public class ReentrantWithMultipleLocks extends Thread {
  // Reentrant Lock allows a thread to acquire the same Lock multiple times without causing a Deadlock.
  Lock lock = new ReentrantLock();

  @Override
  public void run() {
    outer();
  }

  public void outer() {
    try {
      // if a lock is already acquired n times, the current thread will wait until all the instances of the lock are released.
      lock.lock();
      System.out.println("Outer Method");
      inner();
    } catch (Exception e) {
      System.out.println(e);
    } finally {
      // cannot unlock if a lock is already unlocked. Will throw IllegalMonitorStateException.
      lock.unlock();
    }
  }

  public void inner() {
    try {
      lock.lock();
      System.out.println("Inner Method");
    } catch (Exception e) {
      System.out.println(e);
    } finally {
      lock.unlock();
    }
  }

  public static void main(String[] args) {
    ReentrantWithMultipleLocks t1 = new ReentrantWithMultipleLocks();
    ReentrantWithMultipleLocks t2 = new ReentrantWithMultipleLocks();  
    t1.start();
    t2.start();
  }
}
