package synchronous;

public class Main implements Runnable {

  private static BankAccount sbi;

  @Override
  public void run() {
    System.out.println(Thread.currentThread().getName() + " is attempting to increment balance");
    sbi.increment(100);
  }

  public static void main(String[] args) {
    sbi = new BankAccount(0);
    Runnable main = new Main();
    Thread t1 = new Thread(main);
    Thread t2 = new Thread(main);
    t2.setPriority(Thread.MAX_PRIORITY);
    t1.start();
    t2.start();

    try {
      t1.join();
      t2.join();
    } catch (Exception e) {
      // TODO: handle exception
    }

    System.out.println("Ending balance is: " + sbi.getBalance());
  }
  
}
