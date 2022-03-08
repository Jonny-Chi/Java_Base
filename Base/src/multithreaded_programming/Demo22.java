package multithreaded_programming;

/**
 * @description:    暂停线程
 *  *        supend和resume为什么会被废弃
 *  *           1。suspend如果独占公共的同步对象，使其它线程无法访问公共同步对象
 *              2。例如 println方法
 *              3。suspend 造成共享数据不同步
 * @author: Jonny
 * @time: 2022/3/7 10:10 上午
 */
public class Demo22 {
    public static void main(String[] args) throws InterruptedException {
        Demo22user user = new Demo22user();
        Thread t1 = new Thread(){
            @Override
            public void run() {
                user.updateUsernameAndPassward("b","bb");
            }
        };
        t1.setName("A");
        t1.start();
        Thread.sleep(10);

        Thread t2 = new Thread(){
            @Override
            public void run() {
                user.printUsernameAndPassward();
            }
        };
        t2.start();

        /*
        * 输出结果：
        *   停止线程A
            username = b , passward = aa
        * */

        /*
        * 结论 当suspen方法执行之后会让当前线程直接停止，后面代码也不执行，
        *   即.suspend();执行完毕之后，后面说有的代码都不会执行
        * */
    }
}


class Demo22user{
    private String username = "a";
    private String passward = "aa";

    public void updateUsernameAndPassward(String username,String passward){
        this.username = username;
        if ("A".equals(Thread.currentThread().getName())){
            System.out.println("停止线程A");
            Thread.currentThread().suspend();
        }
        this.passward = passward;
        System.out.println("username = "+username+" , passward = "+passward);

    }

    public void printUsernameAndPassward(){
        System.out.println("username = "+username+" , passward = "+passward);
    }
}
