package multithreaded_programming;

/**
 * @description:
 * @author: Joe
 * @time: 2022/2/28 3:41 下午
 */
public class Demo01 {
    public static void main(String[] args) throws InterruptedException {
        while (true){
            System.out.println(System.currentTimeMillis());
            Thread.sleep(1000);
        }
    }
}