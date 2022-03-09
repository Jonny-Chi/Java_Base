package multithreaded_programming.chap2;

/**
 * @description:    线程的同步机制
 *                      Java多线程中的同步，如何在Java语言中开发出线程安全的程序，
 *                      如何在Java语文中解决非线程安全所带来的相关问题。
 * @author: Jonny
 * @time: 2022/3/9 10:31 上午
 */
public class Demo08 {

    /*
    * 1.8   同步锁不具有继承性
    * */

    public static void main(String[] args) {
        Demo08ServiceB serviceB = new Demo08ServiceB();
        Thread t1 = new Demo08Thread(serviceB);
        t1.setName("A");
        t1.start();

        Thread t2 = new Demo08Thread(serviceB);
        t2.setName("B");
        t2.start();

        /*
        * 输出结果
        *
        *   子类：A，开始于1646806810574
            子类：B，开始于1646806810574
            子类：A，结束于1646806815575
            子类：B，结束于1646806815575
            父类：A，开始于1646806815575
            父类：A，结束于1646806820575
            父类：B，开始于1646806820575
            父类：B，结束于1646806825580
        *
        * */

        /*
        * 分析
        *   子类重写的foo方法未加synchronized同步锁，存在异步执行的情况
        *   当调用父类的foo时，只能同步的一个线程执行完，再开始执行下一个线程
        * */
    }
}

class Demo08ServiceA{
    synchronized public void foo(){
        try {
            System.out.println("父类：" + Thread.currentThread().getName() + "，开始于" + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println("父类：" + Thread.currentThread().getName() + "，结束于" + System.currentTimeMillis());
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

class Demo08ServiceB extends Demo08ServiceA{
    public void foo(){
        try {
            System.out.println("子类：" + Thread.currentThread().getName() + "，开始于" + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println("子类：" + Thread.currentThread().getName() + "，结束于" + System.currentTimeMillis());
            super.foo();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

class Demo08Thread extends Thread{
    private Demo08ServiceB service;
    public Demo08Thread(Demo08ServiceB service){
        this.service = service;
    }

    @Override
    public void run() {
        service.foo();
    }
}
