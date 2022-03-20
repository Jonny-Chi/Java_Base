package multithreaded_programming.chap2;

/**
 * @description:    synchronized同步语句块
 * @author: Jonny
 * @time: 2022/3/19 7:41 下午
 */
public class Demo20 {
    public static void main(String[] args) {
        Demo20Service service = new Demo20Service();
        Thread t1 = new Demo20ThreadA(service);
        t1.setName("A");
        t1.start();

        Thread t2 = new Demo20ThreadB(service);
        t2.setName("B");
        t2.start();

        /*
        * 执行结果：
        *
            A进入foo1方法在1647693211367
            A结束foo1方法在1647693213367
            B进入foo1方法在1647693213367
            B结束foo1方法在1647693215368
        *
        * */

        /*
        * 结论
        *
        * 同步synchronized(class)代码块的作用其实和synchronized static方法的作用是一样的
        *
        * */
    }
}

class Demo20Service{
    synchronized public static void foo1(){
        System.out.println(Thread.currentThread().getName() + "进入foo1方法在" + System.currentTimeMillis());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "结束foo1方法在" + System.currentTimeMillis());
    }
    public static void foo2(){
        synchronized (Demo20Service.class) {
            System.out.println(Thread.currentThread().getName() + "进入foo2方法在" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "结束foo2方法在" + System.currentTimeMillis());
        }
    }
}

class Demo20ThreadA extends Thread{
    private Demo20Service service;
    public Demo20ThreadA(Demo20Service service){
        this.service = service;
    }

    @Override
    public void run() {
        service.foo1();
    }
}

class Demo20ThreadB extends Thread{
    private Demo20Service service;
    public Demo20ThreadB(Demo20Service service){
        this.service = service;
    }

    @Override
    public void run() {
        service.foo2();
    }
}