package multithreaded_programming.chap1;

/**
 * @description:    当线程在sleep中时，调用interrupt会怎么样呢？
 * @author: Jonny
 * @time: 2022/3/2 8:23 下午
 */
public class Demo18 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Demo18Thread();
        t.start();
        Thread.sleep(10);
        /*
        * 当线程中使用了sleep方法，
        * 并且在线程执行的过程中使用了interrupt方法才会抛出InterruptedException异常，
        * 并且当线程执行到sleep方法时才会抛出，没执行到sleep方法不会抛出
        * */
        t.interrupt();
    }
}


class Demo18Thread extends Thread{
    @Override
    public void run() {
        try {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                System.out.println("i="+i);
//                String s = new String();
                if (i == 21046)
                    break;
            }
            System.out.println("开始线程");
            Thread.sleep(2000);
            System.out.println("结束线程");
        } catch (InterruptedException e){
            System.out.println("进入到catch块");
            e.printStackTrace();
        }
    }
}