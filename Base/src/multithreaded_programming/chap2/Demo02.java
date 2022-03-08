package multithreaded_programming.chap2;

/**
 * @description:    线程的同步机制
 *                      Java多线程中的同步，如何在Java语言中开发出线程安全的程序，
 *                      如何在Java语文中解决非线程安全所带来的相关问题。
 * @author: Jonny
 * @time: 2022/3/8 2:54 下午
 */
public class Demo02 {

    /*
     * 1.2成员变量是线程不安全的
     * */

    public static void main(String[] args) {
        Demo02Service service = new Demo02Service();
        Thread t1 = new Demo02ThreadA(service);
        Thread t2 = new Demo02ThreadB(service);
        t1.start();
        t2.start();

        /*
        * 输出结果：
        *   未加synchronized关键字
            *   a set over
                b set over
                userName = b , num = 200
                userName = a , num = 200
        *
        *   加了synchronized关键字之后
            *   a set over
                userName = a , num = 100
                b set over
                userName = b , num = 200
        *
        * */


        /*
         * 结论：
         *
         *   如果两个线程同时操作业务对象中的成员变量，可能会产生【非线程安全问题】
         *   需要在方法前加synchronized关键字修饰
         *      为什么加了synchroized锁就能线程安全？
         *          因为synchronized是对象锁，当多个线程访问同一个对象的时候，
         *          会根据线程启动的先后执行程序，若第一个线程未执行完，则后面访问此对象的线程都无法执行
         *
         * */
    }
}

class Demo02Service{
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

class Demo02ThreadA extends Thread{
    private Demo02Service service;

    public Demo02ThreadA(Demo02Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.add("a");
    }
}

class Demo02ThreadB extends Thread {
    private Demo02Service service;

    public Demo02ThreadB(Demo02Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.add("b");
    }
}
