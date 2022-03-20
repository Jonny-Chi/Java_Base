package multithreaded_programming.chap2;

/**
 * @description:    synchronized同步语句块
 * @author: Jonny
 * @time: 2022/3/19 9:39 下午
 */
public class Demo22 {

    /*
    * 2.2.10 解决synchronized方法无限等待问题
    * */
    public static void main(String[] args) {
        Demo22Service service = new Demo22Service();
        Thread t1 = new Demo22ThreadA(service);
        t1.start();
        Thread t2 = new Demo22ThreadB(service);
        t2.start();

        /*
        * 运行结果
        *
            foo1方法开始执行
            foo2方法开始执行
            foo2方法执行结束
        *
        * */

        /*
        * 解析：
        *
        *   synchronized加在方法上，如果当前方法一直不执行完，那么当前对象锁一直不被释放，
        *   当前对象的其他synchronized方法将一直等待其执行完之后才会执行
        *   如果使用synchronized （new Object）锁的话，仅仅是锁住当前对象锁的当前方法
        * */
    }
}

class Demo22Service{
    private Object lockObject1 = new Object();
    /*synchronized */public void foo1(){
        synchronized (lockObject1) {
            System.out.println("foo1方法开始执行");
            boolean isContinue = true;
            while (isContinue) {

            }
            System.out.println("foo1方法执行结束");
        }
    }
    private Object lockObject2 = new Object();
    /*synchronized */public void foo2(){
        synchronized (lockObject2) {
            System.out.println("foo2方法开始执行");
            System.out.println("foo2方法执行结束");
        }
    }
}

class Demo22ThreadA extends Thread{
    private Demo22Service service;
    public Demo22ThreadA(Demo22Service service){
        this.service = service;
    }

    @Override
    public void run() {
        service.foo1();
    }
}

class Demo22ThreadB extends Thread{
    private Demo22Service service;
    public Demo22ThreadB(Demo22Service service){
        this.service = service;
    }

    @Override
    public void run() {
        service.foo2();
    }
}