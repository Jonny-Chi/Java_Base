package multithreaded_programming;

/**
 * @description:成员变量与线程安全
 *                  不共享数据
 * @author: Joe
 * @time: 2022/3/1 8:44 上午
 */
public class Demo06 {
    /*
    每个线程都有各自的count变量，
    自己减少自己的count变量的值，这种情况就是不共享
    * */
    public static void main(String[] args) {
        Thread t1 = new Demo06Thread();
        Thread t2 = new Demo06Thread();
        Thread t3 = new Demo06Thread();
        t1.start();
        t2.start();
        t3.start();
    }
}

class Demo06Thread extends Thread{

    private int count = 5;

    @Override
    public void run() {
        while (count > 0){
            count --;
            System.out.println(Thread.currentThread().getName()+"count="+count);
        }
    }
}
