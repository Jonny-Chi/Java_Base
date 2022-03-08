package multithreaded_programming.chap1;

/**
 * @description: 线程常用API - isAlive
 *                      判断当前的线程是否处于活动状态
 * @author: Jonny
 * @time: 2022/3/1 4:32 下午
 */
public class Demo09 {
    /*
    * 活动状态就是线程已经启动且运行没有结束。
    * 线程处于正在运行或准备开始运行的状态，就认为线程是『存活』的状态。
    * */
    public static void main(String[] args) {
        Thread t = new Demo09thread();
        System.out.println("准备开始启动线程："+t.isAlive());
        t.start();  //启动线程
        System.out.println("启动线程结束后："+t.isAlive());

    }
}

class Demo09thread extends Thread{
    public Demo09thread(){
        System.out.println("构造方法开始");
        System.out.println("构造方法："+Thread.currentThread().isAlive());
        System.out.println(this.isAlive());
        System.out.println("构造方法结束");
    }
    @Override
    public void run() {
        System.out.println("run方法开始");
        System.out.println(Thread.currentThread().isAlive());
        System.out.println("run方法运行状态："+this.isAlive());
        System.out.println("run方法结束");
    }
}
