package multithreaded_programming.chap2;

/**
 * @description:    线程的同步机制
 *                      Java多线程中的同步，如何在Java语言中开发出线程安全的程序，
 *                      如何在Java语文中解决非线程安全所带来的相关问题。
 * @author: Jonny
 * @time: 2022/3/8 5:48 下午
 */
public class Demo04 {

    /*
    * 1.4 synchronized方法锁的是整个对象
    * */

    public static void main(String[] args) {
        Demo04Service service = new Demo04Service();
        Thread t1 = new Demo04ThreadA(service);
        Thread t2 = new Demo04ThreadB(service);
        Thread t3 = new Demo04ThreadC(service);
        t1.start();
        t2.start();
        t3.start();

        /*
        * 运行结果
        *
        *       开始运行foo1方法,ThreadName:Thread-0
                开始运行foo3方法,ThreadName:Thread-2
                foo3方法运行结束
                foo1方法运行结束
                开始运行foo2方法,ThreadName:Thread-1
                foo2方法运行结束
        *
        * */

        /*
        * 结论与解析
        *
        *   A线程先持有Object对象的对象锁，B线程就不可以异步方式调用Object对象使用Synchronize修饰的方法，
        *
        *      * 但是可以异步调用未被Synchronized修饰的方法（例如线程C执行的foo3），未被synchronized关键字修饰的方法可以被异步调用，
        *      * 不用等synchronized关键字修饰的方法执行完
        *
        *   线程B只能等线程A的方法执行完成释放对象锁才能够执行，也就是同步执行。
        *   B线程可以以异步的方式调用Object对象没有使用synchronized修改的方法。
        *
        * */

    }

}

class Demo04Service{
    synchronized public void foo1(){
        System.out.println("开始运行foo1方法"+",ThreadName:"+Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("foo1方法运行结束");
    }
    synchronized public void foo2(){
        System.out.println("开始运行foo2方法"+",ThreadName:"+Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("foo2方法运行结束");
    }
    public void foo3(){
        System.out.println("开始运行foo3方法"+",ThreadName:"+Thread.currentThread().getName());
//        try {
//            Thread.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("foo3方法运行结束");
    }
}

class Demo04ThreadA extends Thread{
    private Demo04Service service;

    public Demo04ThreadA(Demo04Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.foo1();
    }
}

class Demo04ThreadB extends Thread {
    private Demo04Service service;

    public Demo04ThreadB(Demo04Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.foo2();
    }
}

class Demo04ThreadC extends Thread {
    private Demo04Service service;

    public Demo04ThreadC(Demo04Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.foo3();
    }
}
