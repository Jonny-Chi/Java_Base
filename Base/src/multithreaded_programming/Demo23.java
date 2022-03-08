package multithreaded_programming;

/**
 * @description:    yield方法作用
 *                      放弃当前CPU资源。将CPU资源让给其他任务去占用并执行
 *                      但是放弃的时间不确定，有可能刚刚放弃马上又获取CPU资源
 * @author: Jonny
 * @time: 2022/3/7 10:47 上午
 */
public class Demo23 {
    public static void main(String[] args) {
        Thread thread = new Demo23Thread();
        thread.start();
    }
}

class Demo23Thread extends Thread{
    @Override
    public void run() {
        int copunt =0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            Thread.yield();
            copunt += i+1;
        }
        long end = System.currentTimeMillis();
        System.out.println("用时： " + (end - start)+"毫秒");
    }
}
