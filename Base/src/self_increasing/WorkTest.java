package self_increasing;

/**
 * @description: 字符串根据字符拆分成数组
 * @author: Jonny
 * @time: 2022/2/17 10:35 上午
 */
public class WorkTest {
//    public static void main(String[] args) {
//        //当String类型被拆分时没有匹配的拆分字符是否还会被存到数组中去
//        String costCenterCode = "BGPVABAB,BGPVABAC,BGPVABBA";
//        String costCenterName = "熔铸单元-熔炼-50T";
//
//        String[] costCenterCodeArry = costCenterCode.split(",");
//        String[] costCenterNameArry = costCenterName.split(",");
//
//        for (int i = 0;i < costCenterCodeArry.length; i++){
//            System.out.println("costCenterCodeArry"+i+":"+costCenterCodeArry[i]);
//        }
//
//        for (int i = 0;i < costCenterNameArry.length; i++){
//            System.out.println("costCenterNameArry"+i+":"+costCenterNameArry[i]);
//        }
//
//    }

    public static void main(String[] args) {
        String a = "123";
        a = "456";
        System.out.println();

        Demo25User user = new Demo25User();
        user.userName = "a";
        user.userName = "b";
        System.out.println();
    }


}
class Demo25User {
    public String userName;
    public String passward;
}
