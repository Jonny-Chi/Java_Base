package multithreaded_programming.chap2;

/**
 * @description:
 * @author: Jonny
 * @time: 2022/3/20 11:48 下午
 */
public class Demo26_1 {

    /*
    * 2.3 volatile关键字
    *
    *       volatile主要作用是使变量可以在多个线程间见。
    *
    * */
    public static void main(String[] args) {
        Demo26_1Service service = new Demo26_1Service();
        service.foo();
        System.out.println(Thread.currentThread().getName()+"准备结束运行foo方法");
        service.flag = false;

        /*
        * 执行结果
        *
        *       foo方法开始运行
        *
        * */

        /*
        * 解析：
        *
        * 程序开始运行之后停不下来，主要原因是main线程一直在处理while循环，导致程序不能继续执行后面代码
        * */
    }
}

class Demo26_1Service{
    //标志，控制循环
    public boolean flag = true;

    public void foo(){
        System.out.println("foo方法开始运行");
        while (flag){

        }
        System.out.println("foo方法结束运行");
    }
}

