package multithreaded_programming.chap2;

/**
 * @description:    线程的同步机制
 *                      Java多线程中的同步，如何在Java语言中开发出线程安全的程序，
 *                      如何在Java语文中解决非线程安全所带来的相关问题。
 * @author: Jonny
 * @time: 2022/3/8 3:37 下午
 */
public class Demo03 {

    /*
     * 1.3多个对象使用多个对象锁
     * */

    public static void main(String[] args) {
        Demo03Service service1 = new Demo03Service();
        Demo03Service service2 = new Demo03Service();
        Thread t1 = new Demo03ThreadA(service1);
        Thread t2 = new Demo03ThreadB(service2);
        t1.start();
        t2.start();

        /*
        * 输出结果：
        *   未加synchronized关键字
            *   a set over
                b set over
                userName = b , num = 200
                userName = a , num = 100
        *
        *   加了synchronized关键字之后
            *   a set over
                b set over
                userName = b , num = 200
                userName = a , num = 100
        *
        * */


        /*
         * 结论：
         *
            *  因为synchronized是对象锁，当多个线程访问同一个对象的时候，
            *  会根据线程启动的先后执行程序，若第一个线程未执行完，则后面访问此对象的线程都无法执行
            *
            * synchronized取得的锁都是对象锁，而不是把一段代码或方法作为锁，
            * 所以哪个线程先执行带synchronized关键字修饰的方法，哪个方法就持有该方法所属对象的锁，
            * 其它线程只能呈等待状态，前提是多个线程访问的是同一个对象。如果多个线程访问多个对象，
            * JVM会创建出多个对象锁。
         *
         * */
    }
}

class Demo03Service{
    int num = 0;
    synchronized public void add(String userName){
        if ("a".equals(userName)){
            num = 100;
            System.out.println("a set over");
            try {
                Thread.sleep(2000); //等待其他线程修改num的值
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else if ("b".equals(userName)) {
            num = 200;
            System.out.println("b set over");
        }
        System.out.println("userName = " + userName + " , num = " + num);
    }
}

class Demo03ThreadA extends Thread{
    private Demo03Service service;

    public Demo03ThreadA(Demo03Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.add("a");
    }
}

class Demo03ThreadB extends Thread {
    private Demo03Service service;

    public Demo03ThreadB(Demo03Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.add("b");
    }
}
