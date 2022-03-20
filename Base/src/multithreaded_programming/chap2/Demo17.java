package multithreaded_programming.chap2;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:    synchronized同步语句块
 * @author: Jonny
 * @time: 2022/3/18 11:29 上午
 */
public class Demo17 {

    /*
    * 2.2.7-3     synchronized(非this对象)解决脏读问题
    * */

    public static void main(String[] args) throws InterruptedException {
        Demo17List list = new Demo17List();
        Thread t1 = new Demo17ThreadA(list);
        Thread t2 = new Demo17ThreadB(list);
        t1.start();
        t2.start();
        Thread.sleep(5000);
        System.out.println("list size is "+list.getSize());

        /*
        * 执行结果 ：
        *   list size is 1
        * */

        /*
        * 结论：
        *
        *       ** 前提条件：多线程同时执行的syncrhonized(x)，这个x是同一个对象的x
        *
        *   synchronized(非this对象x)格式的写法是将x对象本身作为『对象锁』，这样得出以下的结论：

            a. 当多个线程同时执行syncrhonized(x)同步代码块时呈同步效果；

            b. 当其它 线程执行x对象中的synchronized同步方法时呈同步效果；

            c. 当其它线程执行x对象方法里的synchronize(this)代码块时也呈同步效果

            注意：如果其它 线程调用不加synchronized关键字的方法时，还是异步调用
        *
        * */

    }
}

class Demo17List {
    private List list = new ArrayList();
    synchronized public void add(Object object){
        list.add(object);
    }

    synchronized public int getSize(){
        return list.size();
    }
}

class Demo17Service{
    public void add(Demo17List list,Object object){
        try {
            synchronized (list){
                if (list.getSize() < 1){
                    Thread.sleep(2000); //模拟数据获取
                    list.add(object);
                }
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}



class Demo17ThreadA extends Thread {
    private Demo17List list;

    public Demo17ThreadA(Demo17List list) {
        this.list = list;
    }

    @Override
    public void run() {
        Demo17Service service = new Demo17Service();
        service.add(list,"a");
    }
}

class Demo17ThreadB extends Thread {
    private Demo17List list;

    public Demo17ThreadB(Demo17List list) {
        this.list = list;
    }

    @Override
    public void run() {
        Demo17Service service = new Demo17Service();
        service.add(list,"b");
    }
}