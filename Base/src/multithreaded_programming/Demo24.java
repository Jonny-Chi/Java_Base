package multithreaded_programming;

/**
 * @description:    线程的优先级
 * @author: Jonny
 * @time: 2022/3/7 2:39 下午
 */
public class Demo24 {

    /*
    * 在操作系统中，线程可以划分优先级，优先级较高的线程得到更多的CPU资源，
    * 也就CPU会优先执行优先级较高的线程对象中的任务。
    * 设置线程优先有助于帮助『线程调度器』确定在下一次选择哪个线程优先执行。
    * 设置线程的优先级使用setPriority方法，优级分为1~10个级别，
    * 如果设置优先级小于1或大于10，JDK抛出IllegalArgumentException。
    * JDK默认设置3个优先级常量，
    * MIN_PRIORITY=1(最小值)，NORM_PRIORITY=5(中间值，默认)，MAX_PRIORITY=10(最大值)。
    * */
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+"线程默认运行的优先级是："+Thread.currentThread().getPriority());
        Thread.currentThread().setPriority(9);
        System.out.println(Thread.currentThread().getName()+"线程当前的优先级是："+Thread.currentThread().getPriority());

        Thread t1 = new Demo24Thread();
        t1.start();

        Thread.sleep(10);

        System.out.println("此时将"+Thread.currentThread().getName()+"由"+Thread.currentThread().getPriority()+"改成3");
        Thread.currentThread().setPriority(3);

        System.out.println(Thread.currentThread().getName()+"线程当前的优先级是："+Thread.currentThread().getPriority());

        Demo24Thread t2 = new Demo24Thread();
        t2.start();

        Thread t3 = new Thread(t1);
        t3.start();

        /*
        * 输出结果为
        *
        *   main线程默认运行的优先级是：5
            main线程当前的优先级是：9
            Thread-0线程的运行优先级是9
            此时将main由9改成3
            main线程当前的优先级是：3
            Thread-1线程的运行优先级是3
            Thread-2线程的运行优先级是3
            *
        * */

        /*
        * 结论：
        *       线程具有继承性，比如线程A启动线程B，
        *       线程B的优先级是通线程A一致的，线程A的优先级改变，线程B的优先级也会跟着改变
        * */

    }
}



class Demo24Thread extends Thread{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"线程的运行优先级是"+Thread.currentThread().getPriority());
    }
}