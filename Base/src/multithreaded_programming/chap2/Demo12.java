package multithreaded_programming.chap2;

/**
 * @description:    Synchronized同步语句块
 * @author: Jonny
 * @time: 2022/3/9 5:02 下午
 */
public class Demo12 {

    /*
    * 2.2.4  半异步半同步
    * */
    public static void main(String[] args) {
        Demo12Service service = new Demo12Service();
        Thread t1 = new Demo12Thread(service);
        t1.setName("A");
        t1.start();
        Thread t2 = new Demo12Thread(service);
        t2.setName("B");
        t2.start();

        /*
        * 输出结果
        *
        *   A 非同步线程  i=0
            B 非同步线程  i=0
            A 非同步线程  i=1
            B 非同步线程  i=1
            B 非同步线程  i=2
            A 非同步线程  i=2
            B 非同步线程  i=3
            A 非同步线程  i=3
            B 非同步线程  i=4
            A 非同步线程  i=4
            B 非同步线程  i=5
            A 非同步线程  i=5
            B 非同步线程  i=6
            A 非同步线程  i=6
            B 非同步线程  i=7
            A 非同步线程  i=7
            B 非同步线程  i=8
            A 非同步线程  i=8
            B 非同步线程  i=9
            A 非同步线程  i=9
            B 同步线程  i=0
            B 同步线程  i=1
            B 同步线程  i=2
            B 同步线程  i=3
            B 同步线程  i=4
            B 同步线程  i=5
            B 同步线程  i=6
            B 同步线程  i=7
            B 同步线程  i=8
            B 同步线程  i=9
            A 同步线程  i=0
            A 同步线程  i=1
            A 同步线程  i=2
            A 同步线程  i=3
            A 同步线程  i=4
            A 同步线程  i=5
            A 同步线程  i=6
            A 同步线程  i=7
            A 同步线程  i=8
            A 同步线程  i=9
        *
        * */

        /*
        * 解析
        *   未在synchronized块中的代码都是交替执行的，
        *   在synchronized块中的代码都是全部执行完之后在执行其他线程调用次同步锁代码块的线程
        *
        *     结论
        *       * 不在synchronized块中的就是异步执行，在synchronized块中的代码就是同步执行的
        *
        * */

    }
}

class Demo12Service{
    public void foo(){
        try{
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+" 非同步线程 " + " i=" + i);
                Thread.sleep(10);
            }
            synchronized (this){
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName()+" 同步线程 " + " i=" + i);
                    Thread.sleep(10);
                }
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

class Demo12Thread extends Thread{
    private Demo12Service service;

    public Demo12Thread(Demo12Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.foo();
    }
}
