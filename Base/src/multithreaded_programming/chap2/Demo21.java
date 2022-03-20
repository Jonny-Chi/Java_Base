package multithreaded_programming.chap2;

/**
 * @description:    synchronized同步语句块
 * @author: Jonny
 * @time: 2022/3/19 9:07 下午
 */
public class Demo21 {

    /*
    * 2.2.9 synchronized(String)会有什么问题
    * */
    public static void main(String[] args) {
        Thread t1 = new Demo21ThreadA();
        t1.setName("A");
        t1.start();

        Thread t2 = new Demo21ThreadB();
        t2.setName("B");
        t2.start();

        /*
        * 执行结果：
        *
            线程A
            线程A
            线程A
            线程A
            线程A
            线程A
            线程A
            线程A
        *
        * */

        /*
        * 结论：
        *
        *   出现 只有线程A在运行的原因是，两个线程的对象锁都是使用AA，两个线程持有的相同的锁，
        *   所以造成线程B不能运行。这就是String常量所带来的问题。不应该使用字符串类型作为对象锁，
        *   而应该使用其它类型，例如new Object()
        *
        * */
    }
}

class Demo21Service{
    public static void foo1(String lockObject){
        try {
            synchronized (lockObject) {
                while (true) {
                    System.out.println("线程" + Thread.currentThread().getName());
                    Thread.sleep(1000);
                }
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public static void foo2(Object lockObject){
        try {
            synchronized (lockObject) {
                while (true) {
                    System.out.println("线程" + Thread.currentThread().getName());
                    Thread.sleep(1000);
                }
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

class Demo21ThreadA extends Thread{
    @Override
    public void run() {
        Demo21Service.foo1("AA");
    }
}

class Demo21ThreadB extends Thread{
    @Override
    public void run() {
        Demo21Service.foo2("AA");
    }
}