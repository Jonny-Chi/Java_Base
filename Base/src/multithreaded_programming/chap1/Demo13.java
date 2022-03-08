package multithreaded_programming.chap1;

/**
 * @description:    停止线程
 *                      停止一个线程意味着在线程处理完成任务之前结束正在执行的操作。
 *                      方法二：
 *                          使用stop方法强制结束线程
 * @author: Jonny
 * @time: 2022/3/2 9:10 上午
 */
public class Demo13 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Demo13Thread();
        t.start();
        Thread.sleep(2000);
        t.stop();
    }
    /*
    * 输出结果：
    *   time=1646184301014
        time=1646184302018
        进入ThreadDeath的catch
        java.lang.ThreadDeath
            at java.lang.Thread.stop(Thread.java:853)
            at multithreaded_programming.chap1.Demo13.main(Demo13.java:16)
    * */
}

class Demo13Thread extends Thread{
    @Override
    public void run() {
        try {
            while (true){
                System.out.println("time="+System.currentTimeMillis());
                Thread.sleep(1000);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        } catch (ThreadDeath e){
            System.out.println("进入ThreadDeath的catch");
            e.printStackTrace();
        }
    }
}

