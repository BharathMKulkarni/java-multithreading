package synchronous;

import java.util.concurrent.locks.*;

public class ReentrantReadWriteLockExample {
  private static int count;
  private static final ReadWriteLock lock = new ReentrantReadWriteLock();  

  private static final Lock readLock = lock.readLock();
  private static final Lock writeLock = lock.writeLock();

  public int getCount() {
    readLock.lock();
    try {
      return count;
    } finally {
      readLock.unlock();
    }
  }

  public void incrementCount() {
    writeLock.lock();
    try {
      count++;
    } finally {
      writeLock.unlock();
    }
  }


public static void main(String[] args) {
  ReentrantReadWriteLockExample test = new ReentrantReadWriteLockExample();
  Runnable r1 = new Runnable() {
    @Override
    public void run() {
      for(int i=0; i<10; i++) {
        System.out.println(test.getCount());
      }
    }
  };
  Thread t1 = new Thread(r1);
  
  Runnable r2 = new Runnable() {
    @Override
    public void run() {
      for(int i=0; i<10; i++) {
        test.incrementCount();
      }
    }
  };
  Thread t2 = new Thread(r2);

  t1.start();
  t2.start();


  try {
    t1.join();
    t2.join();
  } catch (InterruptedException e) {
    System.out.println(e);
  } finally {
    test.getCount();
  }
}
}
