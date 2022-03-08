package multithreaded_programming.chap1;

/**
 * @description: 继承Thread
 * @author: Jonny
 * @time: 2022/2/28 4:34 下午
 */
public class Demo02 {

    /*
    * 继承Thread方式创建多线程
    * 1. 创建一个类，这个类需要继承Thread
    * 2. 重写Thread类的run方法（业务代码）
    * 3. 实例化创建好的线程类
    * 4. 调用实例化对象的start方法启动线程
    * */

    public static void main(String[] args) {
        Thread t = new Demo02Thread();
        t.start();  //启动线程
        System.out.println("运行了main方法");
    }
}

class Demo02Thread extends Thread{
    @Override
    public void run() {
//        super.run();
        System.out.println("运行了run方法");
    }
}
