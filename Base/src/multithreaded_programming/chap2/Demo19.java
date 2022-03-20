package multithreaded_programming.chap2;

/**
 * @description:    synchronized同步语句块
 * @author: Jonny
 * @time: 2022/3/19 12:34 下午
 */
public class Demo19 {
    /*
    *   2.2.8     静态同步synchronized方法与synchronized(class)代码块
    * */

    public static void main(String[] args) {
        Demo19Service service = new Demo19Service();
        Thread t1 = new Demo19ThreadA(service);
        t1.setName("A");
        t1.start();
        Thread t2 = new Demo19ThreadB(service);
        t2.setName("B");
        t2.start();

        /*
        * 运行结果：
        *
            A进入foo1方法在1647665063051
            B进入foo2方法在1647665063051
            A结束foo1方法在1647665065052
            B结束foo2方法在1647665065052
        *
        * */

        /*
        * 结论：
        *
        *   以异步方式运行的原因是：持有的锁不一样，非静态方法持有的是对象锁，而静态方法持有的是Class锁，
        *   Class锁可以对类的所有实例对象起作用
        *
        *   很多人可能会有疑惑，既然Class锁可以对类的所有实例对象起作用，那为什么还可以异步执行呢？
        *
        *   这里解释一下
        *   类锁和对象锁是两个不一样的锁，控制着不同的区域，它们是互不干扰的。同样，线程获得对象锁的同时，也可以获得该类锁，即同时获得两个锁，这是允许的。
        *
        *   类锁：可以理解为当前类所有实例出来的对象的锁，通俗点点讲就是 把所有new出来的对象的锁
        *   对象锁：可以理解为 当前类 new 出来的对象的锁，新new 对象是一把新锁，但是这把锁的性质是和前面new 出来的对象的锁是一样的
        * */
    }
}

class Demo19Service{
    synchronized public static void foo1(){
        System.out.println(Thread.currentThread().getName() + "进入foo1方法在" + System.currentTimeMillis());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "结束foo1方法在" + System.currentTimeMillis());
    }

    synchronized public void foo2(){
        System.out.println(Thread.currentThread().getName() + "进入foo2方法在" + System.currentTimeMillis());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "结束foo2方法在" + System.currentTimeMillis());
    }
}

class Demo19ThreadA extends Thread{
    private Demo19Service service;
    public Demo19ThreadA(Demo19Service service){
        this.service = service;
    }

    @Override
    public void run() {
        service.foo1();
    }
}

class Demo19ThreadB extends Thread{
    private Demo19Service service;
    public Demo19ThreadB(Demo19Service service){
        this.service = service;
    }

    @Override
    public void run() {
        service.foo2();
    }
}