package multithreaded_programming.chap1;

/**
 * @description: 不建议使用stop方法
 *  *               1.stop方法已经被作废，如果强制让线程停止有可能使一些清理性的工作得不到完成
 *  *               2.另外一个原因是对锁定的对象进行『解锁』，导致数据得不到同步的处理，出现数据不一致性的问题
 * @author: Jonny
 * @time: 2022/3/2 9:26 上午
 */
public class Demo14 {
    public static void main(String[] args) {
        try {
            Demo14User user = new Demo14User();
            Thread t = new Demo14Thread(user);
            t.start();
            Thread.sleep(500);
            t.stop();
            System.out.println("username="+user.getUsername()+" , passward="+user.getPassward());
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    /*
    * 输出结果为：
    *   username=b , passward=aa
    * */
}




class Demo14Thread extends Thread{

    Demo14User user ;

    public Demo14Thread(Demo14User user) {
        this.user = user;
    }

    @Override
    public void run() {
        user.updateUsernameAndPassward("b","bb");
    }
}

class Demo14User{

    private String username = "a";
    private String passward = "aa";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassward() {
        return passward;
    }

    public void setPassward(String passward) {
        this.passward = passward;
    }

    synchronized public void updateUsernameAndPassward(String username,String passward){
        try {
            this.username = username;
            Thread.sleep(1000);
            this.passward = passward;
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
