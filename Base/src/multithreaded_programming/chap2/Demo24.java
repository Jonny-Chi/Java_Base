package multithreaded_programming.chap2;

/**
 * @description: synchronized同步语句块
 * @author: Jonny
 * @time: 2022/3/20 11:52 上午
 */
public class Demo24 {

    /*
     * 2.2.12    锁对象内容的改变
     * */

    public static void main(String[] args) throws InterruptedException {
        Demo24Service service = new Demo24Service();
        Thread t1 = new Demo24Thread(service);
        t1.setName("A");
        t1.start();

        Thread.sleep(50);

        Thread t2 = new Demo24Thread(service);
        t2.setName("B");
        t2.start();

        /*
        * 执行结果：
        *
            A开始于1647748892183
            B开始于1647748892234
            A结束于1647748894185
            B结束于1647748894235
        *
        * */

        /*
        * 解析：
        *
        *   线程A的锁是123，50毫秒后线程得到的锁是456，所以线程之间是异步调用，如果没有sleep(50)线程A和线程B的锁都是123，
        *
        *   这里解释一下：如果不sleep（50），在线程A修改lockObject之前线程B已经启动了
        *
        *   虽然代码里面将锁改成了456但是结果还是同步，实际上争抢的锁是123而不是456
        * */
    }
}

class Demo24Service {
    private String lockObject = "123";

    public void foo() {
        try {
            synchronized (lockObject) {
                System.out.println(Thread.currentThread().getName() + "开始于" + System.currentTimeMillis());
                lockObject = "456";
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + "结束于" + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Demo24Thread extends Thread {
    private Demo24Service service;

    public Demo24Thread(Demo24Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.foo();
    }
}