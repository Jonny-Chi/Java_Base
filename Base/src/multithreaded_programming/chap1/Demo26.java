package multithreaded_programming.chap1;

/**
 * @description: 守护线程
 * @author: Jonny
 * @time: 2022/3/8 9:32 上午
 */
public class Demo26 {
    /*
    *   在Java线程中有两种线程，一种是用户线程，另一种是守护线程。
        守护线程是一种特殊的线程，特殊指的是当进程中不存在用户线程时，守护线程会自动销毁。
        典型的守护线程的例子就是垃圾回收线程，当进程中没有用户线程，垃圾回收线程就没有存在的必要了，会自动销毁。
    * */
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Demo26Thread();
        t.setDaemon(true);  //将指定的的线程设置为守护线程
        t.start();
        Thread.sleep(5000);
        System.out.println("主线程结束");

        /*
        * 输出结果
        *
        *   time = 1646703459478
            time = 1646703460482
            time = 1646703461484
            time = 1646703462484
            time = 1646703463489
            主线程结束
        *
        *
        * * 结论
        *       当主线程执行完后，守护线程会随着主线程的消亡而消亡
        * */
    }
}


class Demo26Thread extends Thread{
    @Override
    public void run() {
        try {
            while (true){
                System.out.println("time = "+System.currentTimeMillis());
                Thread.sleep(1000);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}