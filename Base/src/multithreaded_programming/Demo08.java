package multithreaded_programming;

/**
 * @description: 线程常用API - currentThread方法
*                       返回代码正在那个线程调用的详细信息
 * @author: Jonny
 * @time: 2022/3/1 9:09 上午
 */
public class Demo08 {
    public static void main(String[] args) {
        Thread t = new Demo08thread();
        t.start();
        System.out.println("main方法："+Thread.currentThread().getName());
    }
}

/*
 *main方法被命名为main的线程调用，线程类的构造方法是被main线程调用，
 * run方法是被Thread-0的线程调用，run方法是自动被调用的方法。
 * */
class Demo08thread extends Thread{
    public Demo08thread(){
        System.out.println("构造方法开始");
        System.out.println("构造方法："+Thread.currentThread().getName());
        System.out.println(this.getName());
        System.out.println("构造方法结束");

    }
    @Override
    public void run() {
        System.out.println("run方法开始");
        System.out.println("run方法："+Thread.currentThread().getName());
        System.out.println(this.getName());
        System.out.println("run方法结束");
    }
}
