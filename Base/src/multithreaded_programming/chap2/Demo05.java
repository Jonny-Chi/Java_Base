package multithreaded_programming.chap2;

import java.beans.EventHandler;

/**
 * @description:    线程的同步机制
 *                      Java多线程中的同步，如何在Java语言中开发出线程安全的程序，
 *                      如何在Java语文中解决非线程安全所带来的相关问题。
 * @author: Jonny
 * @time: 2022/3/8 6:35 下午
 */
public class Demo05 {

    /*
    * 1.5`  脏读
    * */
    public static void main(String[] args) throws InterruptedException {
        Demo05User user = new Demo05User();
        Thread t1 = new Deme05Thread(user);
        t1.start();
        Thread.sleep(50);   //防止计算机效率太高还未给userName赋值就直接输出
        user.getVaule();


        /*
        * 输出结果
        *
        *   main执行了getVaule方法，userName = b, passWard = aa
            Thread-0，执行了setUsernameAndPassward方法,userName=b,passWard = bb
        *
        *
        *   * 解析
        *   **  当A线程调用Demo05User类中使用synchronized关键字修饰的方法时，A线程就获取到一个方法锁，准确来说是获取到Demo05User对象锁，
        *       所以其它线程必须等A线程执行完毕后才可以调用Demo05User中使用synchrnoized修饰的方法。这时A线程已经执行完一个完整任务，
        *       也就是说username和password两个成员变量已经被同时赋值，所以不存在脏读的。
        *
        *   **  但是调用非synchronized关键字修饰的方法时，若Demo05User类中使用synchronized方法未执行完就存在数据脏读
        *
        * */
    }
}


class Demo05User{
    private String userName = "a";
    private String passWard = "aa";

    synchronized public void setUsernameAndPassward(String username,String passward){
        this.userName = username;
        try {
            Thread.sleep(2000); //等待非synchronized关键字修饰的方法执行
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.passWard =passward;
        System.out.println(Thread.currentThread().getName()+"，执行了setUsernameAndPassward方法,userName="+username+",passWard = "+passward);
    }
    public void getVaule(){
        System.out.println(Thread.currentThread().getName()+"执行了getVaule方法，userName = "+userName+", passWard = "+passWard);
    }
}


class Deme05Thread extends Thread{
    Demo05User user = new Demo05User();

    public Deme05Thread(Demo05User user) {
        this.user = user;
    }

    @Override
    public void run() {
        user.setUsernameAndPassward("b","bb");
    }
}
