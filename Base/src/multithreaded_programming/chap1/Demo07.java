package multithreaded_programming.chap1;

/**
 * @description:成员变量与线程安全
 *                  共享数据
 * @author: Jonny
 * @time: 2022/3/1 8:54 上午
 */
public class Demo07 {
    /**
     * 共享数据有概率出现不同线程使用相同的count的值，
     * 产生了『非线程安全问题』。
     */
    public static void main(String[] args) {
        Thread t = new Demo07Thread();
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        Thread t3 = new Thread(t);
        Thread t4 = new Thread(t);
        Thread t5 = new Thread(t);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        /*
        * 输出结果：
        *   Thread-3,count=2
            Thread-2,count=2
            Thread-5,count=0
            Thread-4,count=1
            Thread-1,count=2
        * */
    }
}

class Demo07Thread extends Thread{
    private int count = 5;
    @Override
    public void run() {
        count --;
        System.out.println(Thread.currentThread().getName()+",count="+count);
    }
}
