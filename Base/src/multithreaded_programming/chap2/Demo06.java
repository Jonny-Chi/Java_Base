package multithreaded_programming.chap2;

/**
 * @description:    线程的同步机制
 *                      Java多线程中的同步，如何在Java语言中开发出线程安全的程序，
 *                      如何在Java语文中解决非线程安全所带来的相关问题。
 * @author: Jonny
 * @time: 2022/3/8 7:13 下午
 */
public class Demo06 {

    /*
    * 1.6   锁重入
    * */
    public static void main(String[] args) {
//        Thread t1 = new Demo06ThreadA();
//        t1.start();
        Thread t2 = new Demo06ThreadB();
        t2.start();

        /*
        * 输出结果：
        *
        *   Demo06ThreadA
        *       Demo06ThreadA开始执行
                foo1方法
                foo2方法
                foo3方法
        *
        *
        *   Demo06ThreadB开始执行
            foo4方法
            foo1方法
            foo2方法
            foo3方法
        * */

        /*
        *   结论
        *       关键字synchronized拥有锁重入的功能，就是说在使用synchronized时，
        *       当一个线程得到一个对象锁后，再次请求些对象锁时是可以再次得到该对象锁，而不是重新获取锁。
        *
        *       并且可重入锁也支持父子类继承的环境中。
        * */

    }
}

class Demo06ServiceA{
    synchronized public void foo1(){
        System.out.println("foo1方法");
        foo2();
    }
    synchronized public void foo2(){
        System.out.println("foo2方法");
        foo3();
    }
    synchronized public void foo3(){
        System.out.println("foo3方法");
    }
}

class Demo06ServiceB extends Demo06ServiceA{
    synchronized public void foo4() {
        System.out.println("foo4方法");
        super.foo1();
    }
}

class Demo06ThreadA extends Thread{
    @Override
    public void run() {
        System.out.println("Demo06ThreadA开始执行");
        Demo06ServiceA serviceA = new Demo06ServiceA();
        serviceA.foo1();
    }
}

class Demo06ThreadB extends Thread{
    @Override
    public void run() {
        System.out.println("Demo06ThreadB开始执行");
        Demo06ServiceB serviceB = new Demo06ServiceB();
        serviceB.foo4();
    }
}