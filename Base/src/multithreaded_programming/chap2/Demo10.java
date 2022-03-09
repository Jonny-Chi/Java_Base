package multithreaded_programming.chap2;

/**
 * @description:    Synchronized同步语句块
 * @author: Jonny
 * @time: 2022/3/9 4:04 下午
 */
public class Demo10 {

    /*
    * 2.2.2   synchronized代码块的使用
    *           2.2.1的缺陷任然存在，synchronized代码块的使用
    *           和synchronized加在方法上是一样的
    * */
    public static void main(String[] args) {
        Demo10Service service = new Demo10Service();
        Thread t1 = new Demo10Thread(service);
        t1.setName("A");
        t1.start();
        Thread t2 = new Demo10Thread(service);
        t2.setName("B");
        t2.start();

        /*
        * 输出结果
        *
        *   A 开始于：1646813523719
            A 结束于：1646813525721
            B 开始于：1646813525721
            B 结束于：1646813527725
        *
        * */

    }

}

class Demo10Service{
    public void foo(){
        try {
            synchronized (this) {
                System.out.println(Thread.currentThread().getName() + " 开始于：" + System.currentTimeMillis());
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + " 结束于：" + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Demo10Thread extends Thread{
    Demo10Service service = new Demo10Service();

    public Demo10Thread(Demo10Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.foo();
    }
}

