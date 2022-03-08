package multithreaded_programming;

import java.util.Random;

/**
 * @description: 高优先级和低优先级有什么不同以及特点
 * @author: Jonny
 * @time: 2022/3/7 3:02 下午
 */
public class Demo25 {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread t1 = new Demo25Thread();
            t1.setName("A"+i);
            t1.setPriority(Thread.MAX_PRIORITY);
            t1.start();
            Thread t2 = new Demo25Thread();
            t2.setName("B"+i);
            t2.setPriority(Thread.MIN_PRIORITY);
            t2.start();
        }

        /*
        *执行结果
        *
        *   线程A1优先级：10执行完成使用了：887毫秒
            线程A7优先级：10执行完成使用了：914毫秒
            线程B9优先级：1执行完成使用了：899毫秒
            线程A0优先级：10执行完成使用了：925毫秒
            线程B0优先级：1执行完成使用了：937毫秒
            线程A8优先级：10执行完成使用了：930毫秒
            线程B1优先级：1执行完成使用了：2048毫秒
            线程B7优先级：1执行完成使用了：909毫秒
            线程B5优先级：1执行完成使用了：3597毫秒
            线程A6优先级：10执行完成使用了：3664毫秒
            线程A5优先级：10执行完成使用了：2745毫秒
            线程B4优先级：1执行完成使用了：2499毫秒
            线程B6优先级：1执行完成使用了：3517毫秒
            线程B8优先级：1执行完成使用了：1748毫秒
            线程A2优先级：10执行完成使用了：1959毫秒
            线程B3优先级：1执行完成使用了：1153毫秒
            线程A9优先级：10执行完成使用了：1739毫秒
            线程B2优先级：1执行完成使用了：1109毫秒
            线程A4优先级：10执行完成使用了：1172毫秒
            线程A3优先级：10执行完成使用了：1073毫秒
        *
        * */


        /*
        * 结论：
        *
        *   高优先级的线程总是大部分先执行完，但不代表高优先级的线程全部执行完。
        *   当线程优先级的等级差距很大时，谁先执行完和代码的调用顺序无关。
        *   线程的优先还有『随机性』，也就是说优先级高的线程不一定每一次都先执行完成。
        *
        * */
    }
}


class Demo25Thread extends Thread{
    @Override
    public void run() {
        long start = System.currentTimeMillis();
        long count = 0;
        Random r = new Random();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 500000; j++) {
                count += i * j + r.nextInt() ;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("线程"+Thread.currentThread().getName()+"优先级："+Thread.currentThread().getPriority()+"执行完成使用了："+(end-start)+"毫秒");
    }
}