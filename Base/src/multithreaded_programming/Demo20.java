package multithreaded_programming;

/**
 * @description: 暂停线程
 *                  supend和resume为什么会被废弃
 *                      1。suspend如果独占公共的同步对象，使其它线程无法访问公共同步对象
 * @author: Jonny
 * @time: 2022/3/4 9:50 上午
 */
public class Demo20 {
    //当suspend独占公共的同步对象时
    public static void main(String[] args) throws InterruptedException {
        Demo20Service service = new Demo20Service();
        Thread t1 = new Thread(){
            @Override
            public void run() {
                service.printSytring();
            }
        };
        t1.setName("A"); // 将线程名从Thread-0改成A
        System.out.println("启动线程");
        t1.start();
        System.out.println("主线程睡眠10毫秒");
        Thread.sleep(10);
        Thread t2 = new Thread(){
            @Override
            public void run() {
                service.printSytring();
            }
        };
        t2.start();
        /*
        * Demo20Service的printSytring不加synchronized关键字输出结果
        *
        *   启动线程
            主线程睡眠10毫秒
            打印线程开始
            A线程永远supend了
            打印线程开始
            线程结束
        * */

        /*
        * Demo20Service的printSytring加synchronized关键字输出结果
        *
        *   启动线程
            主线程睡眠10毫秒
            打印线程开始
            A线程永远supend了
        * */

        /*
        * t1线程启动的过程中，执行了suspend，
        * 导致使用了同步锁的的println资源未被释放一直处于暂停状态，so，t2线程启动时无任何输出
        * */

    }
}


class Demo20Service{
    synchronized public void printSytring(){
        System.out.println("打印线程开始");
        if ("A".equals(Thread.currentThread().getName())){
            System.out.println("A线程永远suspend了");
            Thread.currentThread().suspend();
        }
        System.out.println("线程结束");
    }
}

