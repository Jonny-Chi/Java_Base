package multithreaded_programming.chap2;

/**
 * @description:    synchronized同步语句块
 * @author: Jonny
 * @time: 2022/3/9 7:23 下午
 */
public class Demo14 {

    /*
    * 2.2.6 synchronized(this)代码块是锁定的当前对象
    * */
    public static void main(String[] args) throws InterruptedException {
        Demo14Service service = new Demo14Service();
        Thread t1 = new Demo14ThreadA(service);
        Thread t2 = new Demo14ThreadB(service);
        t2.start();
        Thread.sleep(10);
        t1.start();

        /*
        * 运行结果：
        *   foo2开始
            foo2结束
            foo1正在运行
        * */

        /*
        * 结论
        *
        *   synchronized(this)代码块与synchronized方法一样，
        *   synchronized(this)代码块也是锁定当前的对象
        *
        * */
    }
}


class Demo14Service{
    synchronized public void foo1(){
        System.out.println("foo1正在运行");
    }

    public void foo2(){
        try {
            synchronized (this) {
                System.out.println("foo2开始");
                Thread.sleep(2000);
                System.out.println("foo2结束");
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

class Demo14ThreadA extends Thread{
    private Demo14Service service;
    public Demo14ThreadA(Demo14Service service){
        this.service = service;
    }

    @Override
    public void run() {
        service.foo1();
    }
}

class Demo14ThreadB extends Thread{
    private Demo14Service service;
    public Demo14ThreadB(Demo14Service service){
        this.service = service;
    }

    @Override
    public void run() {
        service.foo2();
    }
}