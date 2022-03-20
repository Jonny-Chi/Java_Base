package multithreaded_programming.chap2;

/**
 * @description:    synchronized同步语句块
 * @author: Jonny
 * @time: 2022/3/19 12:08 下午
 */
public class Demo18 {

    /*
    * 2.2.8     静态同步synchronized方法与synchronized(class)代码块
    *                   关键字synchronized还可以应用在静态方法上，
    *                   如果 这样就是对象当前的*.java文件对应的Class进行加锁
    * */

    public static void main(String[] args) {
        Thread t1 = new Demo18ThreadA();
        t1.setName("A");
        t1.start();

        Thread t2 = new Demo18ThreadB();
        t2.setName("B");
        t2.start();

        /*
        * 运行结果
        *
        *
            A进入方法foo1在1647663331617
            A结束方法foo1在1647663333620
            B进入方法foo2在1647663333620
            B结束方法foo2在1647663335621
        *
        * */

        /*
        * 结论：
        *
        *   从运行结果来看，在静态方法上使用synchronized修饰和非静态方法上使用的结果是一致的，都是同步运行。
        *
        *   从本质上来讲，他们是有区别的，在静态方法上使用synchronized修饰是给Class类上锁,
        *                           非静态方法上使用synchronized修饰是给当前类new出来的对象上锁
        *
        * */
    }
}

class Demo18Service{
    synchronized public static void foo1(){
        System.out.println(Thread.currentThread().getName() + "进入方法foo1在" + System.currentTimeMillis());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "结束方法foo1在" + System.currentTimeMillis());
    }
    synchronized public static void foo2(){
        System.out.println(Thread.currentThread().getName() + "进入方法foo2在" + System.currentTimeMillis());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "结束方法foo2在" + System.currentTimeMillis());
    }
}

class Demo18ThreadA extends Thread{
    @Override
    public void run() {
        Demo18Service.foo1();
    }
}

class Demo18ThreadB extends Thread{
    @Override
    public void run() {
        Demo18Service.foo2();
    }
}