package multithreaded_programming.chap2;

/**
 * @description:    Synchronized同步语句块
 * @author: Jonny
 * @time: 2022/3/9 6:23 下午
 */
public class Demo13 {

    /*
    *2.2.5  代码块间的同步性
    * */

    public static void main(String[] args) throws InterruptedException {
        Demo13Service service = new Demo13Service();
        Thread t1 = new Demo13ThreadA(service);
        t1.start();
        Thread.sleep(10);
        Thread t2 = new Demo13ThreadB(service);
        t2.start();

        /*
        * 输出结果i
        *   foo1开始于1646821941360
            foo1结束于于1646821943360
            foo2开始于1646821943360
            foo2结束于于1646821943360
        *       *结论
        *           使用同步synchronized(this)时需要注意，当一个线程访问object的一个synchronized（this）同步代码块时
        *           其他线程对这个object的其他synchronized（this）同步的访问会被阻塞，说明synchronized是用的对象锁是同一个
        *           都是：当前对象的锁
        *
        *
        * */
    }

}

class Demo13Service{
    public void foo1(){
        try {
            synchronized (this){
                System.out.println("foo1开始于"+System.currentTimeMillis());
                Thread.sleep(2000);
                System.out.println("foo1结束于于"+System.currentTimeMillis());
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void foo2(){
        synchronized (this){
            System.out.println("foo2开始于"+System.currentTimeMillis());
            System.out.println("foo2结束于于"+System.currentTimeMillis());
        }
    }
}

class Demo13ThreadA extends Thread{
    private Demo13Service service;

    public Demo13ThreadA(Demo13Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.foo1();
    }
}

class Demo13ThreadB extends Thread{
    private Demo13Service service;

    public Demo13ThreadB(Demo13Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.foo2();
    }
}