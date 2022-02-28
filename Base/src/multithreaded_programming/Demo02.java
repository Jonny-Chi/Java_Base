package multithreaded_programming;

/**
 * @description:  Start的执行顺序和线程的启动顺序是不一致的
 * @author: Joe
 * @time: 2022/2/28 4:34 下午
 */
public class Demo02 {
    public static void main(String[] args) {
        Thread t = new Demo02Thread();
        t.start();  //启动线程
        System.out.println("运行了main方法");
    }
}

class Demo02Thread extends Thread{
    @Override
    public void run() {
//        super.run();
        System.out.println("运行了run方法");
    }
}
