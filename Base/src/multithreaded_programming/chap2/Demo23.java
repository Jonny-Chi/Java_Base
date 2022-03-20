package multithreaded_programming.chap2;

/**
 * @description:     synchronized同步语句块
 * @author: Jonny
 * @time: 2022/3/19 10:10 下午
 */
public class Demo23 {

    /*
    * 2.2.11 死锁
    * */
    public static void main(String[] args) throws InterruptedException {
        Demo23Thread t = new Demo23Thread();
        t.setFlag("a");
        Thread t1 = new Thread(t);
        t1.start();
        Thread.sleep(10);
        t.setFlag("b");
        Thread t2 = new Thread(t);
        t2.start();

        /*
        * 运行结果：
        *
            flag = a
            flag = b

        *       *
        *       *解析：
        *           当 flag 「a」被赋值输出后进行线程睡眠，此时lock1锁已经在使用，
        *           但是主线程10毫秒之后 「b」被赋值输出后进行线程睡眠，此时lock2锁已经在使用
        *           当flag 「a」睡眠结束后继续执行，需要等待lock2释放再执行但此时  flag「b」还没结束睡眠
        *           当flag 「b」睡眠结束后，需要等待lock1释放
        *
        *           此时就形成了flag「a」在等lock2释放，flag「b」在等lock1释放，结果一直互相等，形成了死锁
        * */

        /*
        * 死锁产生的原因：

                1. 系统资源的竞争

                系统资源的竞争会导致系统资源不足，以及资源分配不当，导致死锁。

                2. 线程运行的顺序不合适

                线程在运行过程中请求和释放资源的顺序不当，会导致死锁。

                产生死锁的必要条件：

                a. 互斥条件：线程要求对所分配的资源进行排它性控制，即在一段时间内某个资源仅为一个线程所占用。

                b. 请求和保持条件：当线程因请求资源而阻塞时，对已经获得的资源保持不放。

                c. 不剥夺条件：线程已经获得的资源在未使用之前，不能剥夺，只能在使用完时由自己来释放。

                d. 环路等待条件：在发生死锁时，必然存在一个线程等待另一个线程的环形链(lock1=>lock2=>lock1)
        * */
    }

}

class Demo23Thread extends Thread{
    private String flag;    //标志，控制代码块医生么样的方式运行
    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        try {
            if ("a".equals(flag)){
                synchronized (lock1){
                    System.out.println("flag = " + flag);
                    Thread.sleep(30000);
                    synchronized (lock2){
                        System.out.println("按lock1->lock2的顺序执行");
                    }
                }
            }else {
                synchronized (lock2){
                    System.out.println("flag = " + flag);
                    Thread.sleep(30000);
                    synchronized (lock1){
                        System.out.println("按lock1->lock2的顺序执行");
                    }
                }
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
