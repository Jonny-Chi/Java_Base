package multithreaded_programming;

/**
 * @description: 如何用interrupt和isInterrupted停止线程
 * @author: Jonny
 * @time: 2022/3/2 7:03 下午
 */
public class Demo17 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Demo17Thread();
        t.start();
        Thread.sleep(2);
        t.interrupt();
    }
}

class Demo17Thread extends Thread{
    @Override
    public void run() {
        try {
            for (int i = 0; i < 10000; i++) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("已经是停止状态了，我要退出！");
                    //return;
                    //break;    当线程在执行的过程中，调用interrupt方法，当前线程并不会停止，只会打上停止的标记
                    throw new InterruptedException();
                }
                System.out.println("i=" + i);
            }
            System.out.println("这里是结束循环后的代码");
        }catch (InterruptedException e){
            System.out.println("进入了catch块");
            e.printStackTrace();
        }
    }
}

