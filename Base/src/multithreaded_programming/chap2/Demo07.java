package multithreaded_programming.chap2;

import java.util.Random;

/**
 * @description:    线程的同步机制
 *                      Java多线程中的同步，如何在Java语言中开发出线程安全的程序，
 *                      如何在Java语文中解决非线程安全所带来的相关问题。
 * @author: Jonny
 * @time: 2022/3/8 7:42 下午
 */
public class Demo07 {

    /*
    * 1.7 锁的自动释放
    * */
    public static void main(String[] args) {
        Demo07Service service = new Demo07Service();
        Thread t1 = new Demo07Thread(service);
        Thread t2 = new Demo07Thread(service);
        t1.setName("A");
        t1.start();
        t2.start();

        /*
        * 输出结果
        *
        *   线程A结束于1646740818407
            Exception in thread "A" java.lang.NumberFormatException: For input string: "A"
                at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
                at java.lang.Integer.parseInt(Integer.java:580)
                at java.lang.Integer.parseInt(Integer.java:615)
                at multithreaded_programming.chap2.Demo07Service.foo(Demo07.java:33)
                at multithreaded_programming.chap2.Demo07Thread.run(Demo07.java:51)
            线程B开始于：1646740824204
        *
        * */

        /*
        * 结论
        *
        *      当一个线程执行的代码出现了异常，其所持有的锁会自动释放。
        *
        * */
    }
}

class Demo07Service{
     synchronized public void foo(){
         if ("A".equals(Thread.currentThread().getName())){
             System.out.println("线程A结束于"+System.currentTimeMillis());
             while (true){
                 if ((""+ Math.random()).substring(0,9).equals("0.1234567")){
                     Integer.parseInt("A");
                 }
             }
         }else {
             System.out.println("线程B开始于："+System.currentTimeMillis());
         }
     }
}

class Demo07Thread extends Thread{
    private Demo07Service service;

    public Demo07Thread(Demo07Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.foo();
    }
}
