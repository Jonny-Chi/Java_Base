package multithreaded_programming;

/**
 * @description:    暂停线程
             *  *        supend和resume为什么会被废弃
             *  *           1。suspend如果独占公共的同步对象，使其它线程无法访问公共同步对象
 *                          2。例如 println方法
 * @author: Jonny
 * @time: 2022/3/7 9:52 上午
 */
public class Demo21 {
    /**
     * 当t1执行了    suspend方法之后导致 导致  println方法一直处于暂停状态，无法被释放
     *                  所以主线程的  "main结束了"无法输出
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("main开始了");
        Thread t1 = new Demo21Thread();
        t1.start();
        Thread.sleep(10);
        t1.suspend();
        Thread.sleep(10);
        System.out.println("main结束了");
    }
}

class Demo21Thread extends Thread{
    public long i =0;
    @Override
    public void run() {
        while (true){
            i++;
            System.out.println("i= "+ i);
        }
    }
}
