package multithreaded_programming.chap1;

/**
 * @description:    停止线程
 *                      停止一个线程意味着在线程处理完成任务之前结束正在执行的操作。
 *                      方法一：
 *                          使用退出标志，使线程正常退出，也就是当run方法完成后线程终止。
 * @author: Jonny
 * @time: 2022/3/2 8:53 上午
 */
public class Demo12 {
    public static void main(String[] args) throws InterruptedException {
        Demo12Thread t = new Demo12Thread();
        t.start();
        Thread.sleep(2000);
        t.stopThread();
    }
}

class Demo12Thread extends Thread{

    private boolean flag = true;

    @Override
    public void run() {
        while (flag){
            System.out.println("时间："+System.currentTimeMillis());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("线程执行结束");
    }

    public void stopThread(){
        flag = false;
    }
}
