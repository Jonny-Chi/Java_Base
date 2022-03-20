package multithreaded_programming.chap2;

/**
 * @description:
 * @author: Jonny
 * @time: 2022/3/20 11:48 下午
 */
public class Demo26_2 {

    /*
    * 2.3 volatile关键字
    *
    *       volatile主要作用是使变量可以在多个线程间可见。
    *
    * */
    public static void main(String[] args) throws InterruptedException {
        Demo26_2Service service = new Demo26_2Service();
//        service.foo();
        service.start();
        Thread.sleep(100);

        System.out.println(Thread.currentThread().getName()+"准备结束运行foo方法");
        service.flag = false;

        /*
        * 执行结果
        *
        *       foo方法开始运行
                main准备结束运行foo方法
        *
        * */

        /*
        * 解析：为什么设置了变量值还是一直在死循环呢？
        *
        *   如果上面的代码运行在JDK1.8以及以上的版本，那么JVM默认使用的是服务器模式，上面的代码会出现死循环
        *   如果上面的代码在JDK1.7及以下版本运行，JVM采用的是客户端模式，确实会停止运行，让线程停止
        * */
    }
}

class Demo26_2Service extends Thread{
    //标志，控制循环
    public boolean flag = true;

    public void foo(){
        System.out.println("foo方法开始运行");
        while (flag){

        }
        System.out.println("foo方法结束运行");
    }

    @Override
    public void run() {
        foo();
    }
}

