package chapter2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Calculator {

    /*
    * --- 성공 ---
    * 1. 쉼표
    * 2. 콜론
    * 3. 커스텀 구분자
    * 4. 커스텀 구분자에 - 넣는 경우
    * 5. 빈값이 주어지는 경우
    * 6. 숫자가 주어지지 않는 경우 -> 0
    *  --- 실패 ---
    * 1. 음수 전달하는 경우
   * */
    private static String equation;
    private static String PARSER = ",|:";
    private static String CUSTOM_PARSER_START = "//";
    private static String CUSTOM_PARSER_END   = "\n";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //String equation = br.readLine();
        //String str = "//;\n1;-2;3";
        //String str = "1;2;3";
        equation = "1:2,3";


        String[] NumberArr = getNumberArr(equation);
        System.out.println(calculate(NumberArr));
    }

    public static boolean hasCustomParser(String equation) {
        return equation.startsWith(CUSTOM_PARSER_START);
    }

    public static String[] getNumberArr(String equation) {
        if(hasCustomParser(equation)) {
            PARSER += ("|" + equation.substring(2,equation.indexOf("\n")));
            equation = equation.substring(equation.indexOf("\n")+1,equation.length());
        }
        return equation.split(PARSER);
    }//

    public static int calculate(String[] NumberArr) {
        int answer = 0 ;

        for(String number : NumberArr) {
            if(Integer.parseInt(number) < 0) throw new RuntimeException("음수는 안되요!");
            answer += Integer.parseInt(number);
        }
        return answer;
    }


}
