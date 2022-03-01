package multithreaded_programming;

/**
 * @description: 线程常用API - getId
 *  *                      获取当前线程的唯一标识
 * @author: Joe
 * @time: 2022/3/1 9:23 下午
 */
public class Demo11 {
    public static void main(String[] args) {
        Thread t = Thread.currentThread();
        System.out.println(t.getName()+","+t.getId());
    }
}
