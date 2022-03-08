package multithreaded_programming.chap1;

/**
 * @description:    停止线程
 *                      使用interrupt方法中断线程
 * @author: Jonny
 * @time: 2022/3/2 10:55 上午
 */
public class Demo15 {
    public static void main(String[] args) {
        Thread t = new Demo15Thread();
        t.start();
        /*调用interrupt不会真正结束线程，只会在当前线程中打上一个停止的标记
        * */
        t.interrupt();
        System.out.println("是否停止1？"+t.isInterrupted());
        System.out.println("是否停止2？"+Thread.interrupted());

        /*
        *   t.isInterrupted方法是检查Demo15Thread是否被打上停止的标记，
        *   Thread.interrupted方法是检查主线程是否被打上停止的标记。
        * */
    }
}

class Demo15Thread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("i="+i);
        }
    }
}