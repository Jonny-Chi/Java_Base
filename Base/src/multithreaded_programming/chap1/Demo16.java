package multithreaded_programming.chap1;

/**
 * @description:    interrupted调用的特殊情况
 * @author: Jonny
 * @time: 2022/3/2 1:29 下午
 */
public class Demo16 {
    public static void main(String[] args) {
        Thread.currentThread().interrupt();
        /*
        * 测试当前线程是否已经中断，线程的中断状态由方法清除。
        * 如果 连接两次调用该方法，第二次调用将返回false。
        * */
        System.out.println("是否打上停止标记1？"+Thread.interrupted());
        System.out.println("是否打上停止标记2？"+Thread.interrupted());
    }
}
