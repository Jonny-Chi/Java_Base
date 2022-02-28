package multithreaded_programming;

/**
 * @description:
 * @author: Joe
 * @time: 2022/2/28 4:53 下午
 */
public class Demo03 {
    public static void main(String[] args) {
        Thread t = new Demo03Thread();
        t.start();
        for (int i = 0; i < 10 ; i++) {
            System.out.println("运行了main方法");
        }
        
    }
}
class Demo03Thread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 10 ; i++) {
            System.out.println("运行了run方法");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
