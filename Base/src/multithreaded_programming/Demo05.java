package multithreaded_programming;

/**
 * @description: 使用Thread继承的方式开发多线程应用程序是有局限的，
 *               Java是单继承，为了改变这种限抽，
 *               可以使用Runnable接口方式来实现多线程。
 * @author: Jonny
 * @time: 2022/3/1 8:03 上午
 */
public class Demo05 {
    /*
    * Runnable实现多线程步骤
    * 1。创建一个类，这个类需要实现Runnable接口
    * 2。重写Runnnable接口的run方法
    * 3。实例化创建的这个类
    * 4。实例化一个Thread对象，并把第三不创建的对象通过Thread的构造方法惊醒传递
    * 5。调用Thread对象的start方法
    * */
    public static void main(String[] args) {
        Runnable runnable = new Demo05Thread();
        Thread thread = new Thread(runnable);
        thread.start();
        System.out.println("运行了main方法");

        /*
        * 运行结果1：
        * 运行了run方法
        * 运行了main方法
        * */

        /*
         * 运行结果2：
         * 运行了main方法
         * 运行了run方法
         * */
    }
}

/*
* 使用Thread继承的方式开发多线程应用程序是有局限的，
* Java是单继承，为了改变这种限抽，可以使用Runnable接口方式来实现多线程。
* */
class Demo05Thread implements Runnable{
    @Override
    public void run() {
        System.out.println("运行了run方法");
    }
}