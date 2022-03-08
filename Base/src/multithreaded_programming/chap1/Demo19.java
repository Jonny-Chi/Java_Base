package multithreaded_programming.chap1;

/**
 * @description: 暂停线程
 *                  （已废弃）suspend：暂停线程
 *                  （已废弃）resume：恢复线程,重启线程
 * @author: Jonny
 * @time: 2022/3/4 9:08 上午
 */
public class Demo19 {
    public static void main(String[] args) throws InterruptedException {
        Demo19Thread t = new Demo19Thread();
        System.out.println("启动线程：Demo19Thread ， "+System.currentTimeMillis());
        t.start();
        System.out.println("主线程第一次暂停1s");
        Thread.sleep(1000);
        System.out.println("暂停Demo19Thread线程");
        t.suspend();
        System.out.println("A="+System.currentTimeMillis()+" , i="+t.getI());
        System.out.println("主线程第二次暂停1s");
        Thread.sleep(1000);
        System.out.println("A="+System.currentTimeMillis()+" , i="+t.getI());
        System.out.println("恢复Demo19Thread线程");
        t.resume();
        System.out.println("主线程第三次暂停1s");
        Thread.sleep(1000);
        System.out.println("暂停Demo19Thread线程");
        t.suspend();
        System.out.println("B="+System.currentTimeMillis()+" , i="+t.getI());
        System.out.println("主线程第四次暂停1s");
        Thread.sleep(1000);
        System.out.println("B="+System.currentTimeMillis()+" , i="+t.getI());

        /*
        * 输出结果
        *
        *   启动线程：Demo19Thread ， 1646358360631
            主线程第一次暂停1s
            暂停Demo19Thread线程
            A=1646358361635 , i=692538143
            主线程第二次暂停1s
            A=1646358362639 , i=692538143
            恢复Demo19Thread线程
            主线程第三次暂停1s
            暂停Demo19Thread线程
            B=1646358363643 , i=1516628230
            主线程第四次暂停1s
            B=1646358364648 , i=1516628230

        * */

    }
}


class Demo19Thread extends Thread{

    private long i = 0;

    public long getI() {
        return i;
    }

    public void setI(long i) {
        this.i = i;
    }

    @Override
    public void run() {
        while (true){
            i++;
        }
    }
}