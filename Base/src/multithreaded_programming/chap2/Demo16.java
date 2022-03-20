package multithreaded_programming.chap2;

/**
 * @description:     synchronized同步语句块
 * @author: Jonny
 * @time: 2022/3/18 10:04 上午
 */
public class Demo16 {

    /*
     * 2.2.7-2     使用任意对象(非this)作为对象锁的优先级
    * */

    public static void main(String[] args) throws InterruptedException {
        Demo16Service service = new Demo16Service();
        Thread t1 = new Demo16ThreadA(service);
        Thread t2 = new Demo16ThreadB(service);
        t1.start();
        t2.start();

        /*
        * 运行结果
        *
            foo2开始于：1647570855121
            foo1开始于：1647570855121
            foo2结束于：1647570856125
            foo1结束于：1647570857125
        *
        * */

        /*
        * 结论
        *
        *   *   一个类有很多个synchronized方法，虽然可以实现同步但是会收到阻塞，所以会影响运行效率。如果使用同步代码块，非（this）对象
        *   *   则synchronized的非this对象的代码中的程序与同步方法是异步的，不与其他this锁争抢当前对象锁，大大提高运行效率
        * */
    }
}

class Demo16Service {
    private Object lockObject = new Object();
    public void foo1() {
        try {
            synchronized (lockObject){
                System.out.println("foo1开始于："+System.currentTimeMillis());
                Thread.sleep(2000);
                System.out.println("foo1结束于："+System.currentTimeMillis());
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    synchronized public void foo2(){
        System.out.println("foo2开始于："+System.currentTimeMillis());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("foo2结束于："+System.currentTimeMillis());
    }
}

class Demo16ThreadA extends Thread{
    public Demo16Service service;

    public Demo16ThreadA(Demo16Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.foo1();
    }
}

class Demo16ThreadB extends Thread{
    public Demo16Service service;

    public Demo16ThreadB(Demo16Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.foo2();
    }
}