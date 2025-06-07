package synchronous;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

public class BankAccount {

  private int balance;
  private Lock lock = new ReentrantLock();

  public BankAccount(int balance) {
    this.balance = balance;
  }

  public void increment(int amount) {
    try {
      if(lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
        try {
          System.out.println(Thread.currentThread().getName() + " lock acq");
          Thread.sleep(3000);
          balance += amount;
        } catch (InterruptedException e) {
          System.out.println(e);
          Thread.currentThread().interrupt();
        } finally {
          lock.unlock();
        }
      } else {
        System.out.println(Thread.currentThread().getName() + " could not acq lock");
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public int getBalance() {
    return balance;
  }
  
}
