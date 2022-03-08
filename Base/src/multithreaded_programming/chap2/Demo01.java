package multithreaded_programming.chap2;

/**
 * @description:    线程的同步机制
 *                      Java多线程中的同步，如何在Java语言中开发出线程安全的程序，
 *                      如何在Java语文中解决非线程安全所带来的相关问题。
 * @author: Jonny
 * @time: 2022/3/8 10:34 上午
 */
public class Demo01 {
    /*
    * 1.synchronized同步方法
    *   『线程安全』与『非线程安全』是学习多线程技术时一定会遇到的经典问题。
    *   『非线程安全』其实当多个线程访问同一个对象中的成员变量时产生的，
    *    产生的后果就是『脏读』，就是取到的数据其实是被更改过的。
    *    而『线程安全』就是以获取的成员变量的值是经过同步处理的，不会出现脏读的现象。
    * */

    /*
    * 1.1局部变量是线程安全的
    * */

    public static void main(String[] args) {
        Demo01Service service = new Demo01Service();
        Thread t1 = new Demo01ThreadA(service);
        Thread t2 = new Demo01ThreadB(service);
        t1.start();
        t2.start();

        /*
        * 输出结果：
        *
        *   a set over
            b set over
            userName = b , num = 200
            userName = a , num = 100
        *
        * */


        /*
        * 结论：
        *
        *   局部变量不存在非线程安全问题，永远都是线程安全的。
        *   这是由局部变量是私有的特征造成的
        *
        * */
    }
}

class Demo01Service{
    public void add(String userName){
        int num = 0;
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

class Demo01ThreadA extends Thread{
    private Demo01Service service;

    public Demo01ThreadA(Demo01Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.add("a");
    }
}

class Demo01ThreadB extends Thread {
    private Demo01Service service;

    public Demo01ThreadB(Demo01Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.add("b");
    }
}
