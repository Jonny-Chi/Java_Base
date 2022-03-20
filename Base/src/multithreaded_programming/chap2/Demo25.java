package multithreaded_programming.chap2;

/**
 * @description:     synchronized同步语句块
 * @author: Jonny
 * @time: 2022/3/20 12:15 下午
 */
public class Demo25 {

    public static void main(String[] args) throws InterruptedException {
        Demo25Service service = new Demo25Service();
        Demo25User user = new Demo25User();

        Thread t1 = new Demo25Thread(service,user);
        t1.setName("A");
        t1.start();
        Thread.sleep(10);
        Thread t2 = new Demo25Thread(service,user);
        t2.setName("B");
        t2.start();

        /*
        * 执行结果
        *
            A开始于1647752238729
            A结束于1647752240730
            B开始于1647752240730
            B结束于1647752242735
        * */

        /*
        * 解析：
        *
        *   只要对象不变，即使对象的属性被改变，运行的结果 还是同步。
        * */
    }
}

class Demo25User {
    public String userName;
    public String passward;
}

class Demo25Service{
    public void foo(Demo25User user){
        try {
            synchronized (user){
                System.out.println(Thread.currentThread().getName()+"开始于"+System.currentTimeMillis());
                user.userName="a";
                user.passward="aa";
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName()+"结束于"+System.currentTimeMillis());
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

class Demo25Thread extends Thread{
    private Demo25Service service;
    private Demo25User user;

    public Demo25Thread(Demo25Service service, Demo25User user) {
        this.service = service;
        this.user = user;
    }

    @Override
    public void run() {
        service.foo(user);
    }
}
