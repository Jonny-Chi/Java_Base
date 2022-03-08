package multithreaded_programming.chap1;

/**
 * @description:
 * @author: Jonny
 * @time: 2022/2/28 4:53 下午
 */
public class Demo03 {
    /*
    *在多线程编程中，代码的执行结果与代码的执行顺序或调用顺序是无关的。
    * 线程是一个子任务，CPU以不确定的方式或者是以随机的时间来调用线程中的run方法。
    * */
    public static void main(String[] args) {

        //Start的执行顺序和线程的启动顺序是不一致的
        Thread t = new Demo03Thread();

        t.start();
        //t.run();

        /*
        * 如果直接调用线程的run方法，不是启动线程，
        * 而是由main主线程来调用run方法
        * */

        try {
            for (int i = 0; i < 10 ; i++) {
                System.out.println("运行了main方法");
                Thread.sleep(100);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        
    }
}
class Demo03Thread extends Thread{
    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("运行了run方法");
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
