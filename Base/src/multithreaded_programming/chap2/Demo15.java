package multithreaded_programming.chap2;

/**
 * @description:     synchronized同步语句块
 * @author: Jonny
 * @time: 2022/3/9 7:44 下午
 */
public class Demo15 {

    /*
    * 2.2.7-1     使用任意对象作为对象锁
    *               除了可以使用synchronized(this)来同步代码块，Java还支持，【任意对象】作为【对象锁】来实现同步功能
    *               这个【任意对象】大多数情况下是成员变量或者方法参数
    *                   使用格式synchronized(非this对象)
    * */

    public static void main(String[] args) {
        Demo15Service service = new Demo15Service();
        Thread t1 = new Demo15Thread(service);
        t1.setName("A");
        t1.start();
        Thread t2 = new Demo15Thread(service);
        t2.setName("B");
        t2.start();

        /*
        * 输出结果
        *   使用同一个对象锁
        *       A开始于1646826688801
                A结束于1646826690805
                B开始于1646826690805
                B结束于1646826692809
            使用不同的对象锁
            *
            *   A开始于1646826988262
                B开始于1646826988262
                A结束于1646826990263
                B结束于1646826990263

        *
        * */

        /*
        * 结论
        *
        *     在多个线程持有『对象锁』为同一个对象的情况下，
        *     同一时间只有一个线程可以执行synchronized(非this对象)同步代码块中的代码。
        *     如果使用不是同一个对象锁，运行的结果就是异步调用，会交叉运行。
        *
        * */

    }
}

class Demo15Service{
    private Object lockObject = new Object();

    public void foo(){
        try {
//            synchronized (lockObject) {
            synchronized (new Object()) {
                System.out.println(Thread.currentThread().getName() + "开始于" + System.currentTimeMillis());
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + "结束于" + System.currentTimeMillis());
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

class Demo15Thread extends Thread{
    private Demo15Service service;
    public Demo15Thread(Demo15Service service){
        this.service = service;
    }

    @Override
    public void run() {
        service.foo();
    }
}
