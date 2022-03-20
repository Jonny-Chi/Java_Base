package multithreaded_programming.chap2;

/**
 * @description:
 * @author: Jonny
 * @time: 2022/3/20 11:48 下午
 */
public class Demo26_3 {

    /*
    * 2.3 volatile关键字
    *
    *       volatile主要作用是使变量可以在多个线程间可见。
    *
    * */
    public static void main(String[] args) throws InterruptedException {
        Demo26_3Service service = new Demo26_3Service();
//        service.foo();
        service.start();
        Thread.sleep(100);

        System.out.println(Thread.currentThread().getName()+"准备结束运行foo方法");
        service.flag = false;

        /*
        * 执行结果
        *
        *       foo方法开始运行
                main准备结束运行foo方法
                foo方法结束运行
        *
        * */

        /*
        * 解析：为什么在变量flag前面加了volatile就可以让服务器模式的Java虚拟机的线程停止
        *
        *   在启动线程的时候，变量public boolean flag = true;存在于公共堆栈和线程私有堆栈中。
        *   在服务器模式中，虚拟机为了线程的运行效率，线程一直在私有堆栈中获取对象的值，私有堆栈中，flag的值一直是true。
        *   当service.flag = false时，虽然已被执行，但是是在公共的堆栈中修改了flag的值，所以是死循环。
        *
        *   使用volatile关键字修饰变量之后，强制虚拟机从公共堆栈中获取变量的值，因此循环被结束，线程也就停止了
        * */

        /*
        * 深入理解：synchronize和volatile区别
        *
        * 1.volatile是线程同步的轻量级实现，他的性能要比synchronized要好，
        *   并且volatile只能修饰变量，而synchronized可以修饰方法以及代码块
        *   随着JDK的版本更新，synchronized 的执行效率也得到很大的提升，在开发中synchronized的使用率还是比教高
        *
        * 2.多线程访问volatile不会发生阻塞，而synchronized会出现阻塞。
        *
        * 3.volatile可以保证数据的可见性，但是不能保证数据的原子性，而synchronized可以保证数据的原子性，也可以间接的保证数据的可见性
        *   因为synchronized会将私有内存和公共内存中的数据作同步
        *
        * 4.volatile解决的是变量在多线程之间的可见性，而synchronized是解决多个线程访问资源的同步性。
        * */
    }
}

class Demo26_3Service extends Thread{
    //标志，控制循环
    volatile public boolean flag = true;

    public void foo(){
        System.out.println("foo方法开始运行");
        while (flag){

        }
        System.out.println("foo方法结束运行");
    }

    @Override
    public void run() {
        foo();
    }
}

