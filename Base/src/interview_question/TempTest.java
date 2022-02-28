package interview_question;

/**
 * @description:
 * @author: Jonny
 * @time: 2022/2/15 10:48 下午
 */
public class TempTest {

    public static void main(String[] args) {
        //unicode 编码总长度为：2^16
        for (int i = 0; i < 65536; i++){
            char str = (char) i;
            if (str == '通' | str == '话'){
                System.out.println(i+":"+str);
            }
            if (str == '重' | str == '地'){
                System.out.println(i+":"+str);
            }
        }
    }

}
