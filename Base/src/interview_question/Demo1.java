package interview_question;

/**
 * @description:
 * @author: Jonny
 * @time: 2021/11/28 1:26 下午
 */
public class Demo1 {
    public static void main(String[] args) {

        /**
         * 两个对象的hashcode（）相同，则equals（）一定相同对么？
         */

        String str1 = "通话";//String str1 = new String("通话);
        String str2 = "重地";
        String str3 = "你好";
        System.out.println("str1的hashcode是："+str1.hashCode());
        System.out.println("str2的hashcode是："+str2.hashCode());
        System.out.println("str3的hashcode是："+str3.hashCode());
        System.out.println("a的hashcode是："+"a".hashCode());
        System.out.println("A的hashcode是："+"A".hashCode());
        System.out.println("Aa的hashcode是："+"Aa".hashCode());
        System.out.println("哀悼的hashcode是："+"哀悼".hashCode());

        for (int i = 0; i < 65536; i++) {
            char str = (char) i;
//            if (str =='哀' | str =='悼'){
//                System.out.println(i+":"+str);
//            }
//            if (str == '\u0000'){
//                System.out.println(i);
//            }
            System.out.println(i+":"+str);
//            if (i == 65537){
//                System.out.println(str);
//            }
        }



    }

}
