package multithreaded_programming.chap2;

/**
 * @description:    Synchronized同步语句块
 * @author: Jonny
 * @time: 2022/3/9 3:20 下午
 */
public class Demo09 {

    /*
    * 2.2.1 synchronized方法缺陷
    *   在实际项目中，当有多个线程同时调用加了synchronized的同步锁方法时，
    *   只能一个一个处理，加长了实际运行时间
    * */

    public static void main(String[] args) throws InterruptedException {
        Demo09Service service = new Demo09Service();
        Thread t1 = new Demo09ThreadA(service);
        t1.setName("A");
        Thread t2 = new Demo09ThreadB(service);
        t2.setName("B");
        t1.start();
        t2.start();
        Thread.sleep(11000);
        long start = Demo09Utils.start1 > Demo09Utils.start2 ? Demo09Utils.start2 : Demo09Utils.start1;
        long end = Demo09Utils.end1 > Demo09Utils.end2 ? Demo09Utils.end1 : Demo09Utils.end2;
        System.out.println("总耗时 ： "+(end-start) / 1000 + "秒");

        /*
        * 输出结果
        *
        *   A开始执行任务
            A，长时间处理任务完成
            A，任务结束
            B开始执行任务
            B，长时间处理任务完成
            B，任务结束
            总耗时 ： 10秒
        *
        * */
    }
}

class Demo09Utils{
    static long start1;
    static long start2;
    static long end1;
    static long end2;
}

class Demo09Service{
    synchronized public void foo(){
        System.out.println(Thread.currentThread().getName()+"开始执行任务");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"，长时间处理任务完成");
        System.out.println(Thread.currentThread().getName()+"，任务结束");
    }
}

class Demo09ThreadA extends Thread{
    Demo09Service service = new Demo09Service();

    public Demo09ThreadA(Demo09Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        Demo09Utils.start1 = System.currentTimeMillis();
        service.foo();
        Demo09Utils.end1 = System.currentTimeMillis();
    }
}

class Demo09ThreadB extends Thread{
    Demo09Service service = new Demo09Service();

    public Demo09ThreadB(Demo09Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        Demo09Utils.start2 = System.currentTimeMillis();
        service.foo();
        Demo09Utils.end2 = System.currentTimeMillis();
    }
}