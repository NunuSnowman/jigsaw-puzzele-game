package util;

import java.util.ArrayList;
import java.util.Random;

public class VerificationCodeUtil {
    public static void main(String[] args) {
        String s = getcode();
        //System.out.println(s);
    }

    /**
     * generate the verification code
     *
     * @return
     */
    public static String getcode() {
        /** step1: build an ArrayList to keep 52 letters
         * step2: generate 4 random letter
         * step3: append a random digit to the String
         * step4: shuffle the sequence of the 4th Character of the String
         * step5: return the final String
         */
        ArrayList<Character> letterList = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            letterList.add((char)('a' + i));
            letterList.add((char)('A' + i));
        }
        Random random = new Random();
        String str = "";
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(letterList.size());
            str += letterList.get(index);
            //System.out.println(str);
        }
        str += random.nextInt(10);
        int randomIndex = random.nextInt(5);
        char[] charArr = str.toCharArray();
        char temp = charArr[randomIndex];
        charArr[randomIndex] = charArr[4];
        charArr[4] = temp;
        String result = new String(charArr);
        return result;
    }
}
