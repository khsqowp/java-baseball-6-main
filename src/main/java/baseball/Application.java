package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class Application {
    private static final int minimum = 1;
    private static final int maximum = 9;
    private static final int lengthNum = 3;
    static Integer[] youNum;

    //랜덤넘버 만들기
    static List<Integer> computer() {

        List<Integer> computer;

        computer = new ArrayList<>();
        while (computer.size() < 3) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!computer.contains(randomNumber)) {
                computer.add(randomNumber);
            }
        }
        return computer;
    }


    static Integer[] computer2Array() {
        youNum = computer().toArray(computer().toArray(new Integer[3]));
        return youNum;
    }


    public static void main(String[] args) {
        // TODO: 프로그램 구현
        computer2Array();
        boolean restart = false;
        System.out.println("숫자 야구 게임을 시작합니다.");
        do {
            int strike = 0;
            int ball = 0;

            System.out.print("숫자를 입력해주세요 : ");
            System.out.println(Arrays.toString(youNum));
            int input_num = Integer.parseInt(Console.readLine());
            // 입력 받아오기
            try {
                int number = Integer.parseInt(String.valueOf(input_num));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("정수가 아닌 문자열이 입력되었습니다.");
            }
            int length = (int) (Math.log10(input_num) + 1);
            if (length > 3) {
                System.out.println("3자리수만 입력해주세요");
                restart = true;
            }

            //세자리 숫자를 배열화
            Integer[] my_num = new Integer[3];

            //input_number -> num2str -> my_num[]
            String num2str = Integer.toString(input_num);
            String[] str2arr = num2str.split("");
            for (int i = 0; i < 3; i++) {
                my_num[i] = Integer.parseInt(str2arr[i]);
            }

            for (int i = 0; i < 3; i++) {
                if (Objects.equals(my_num[i], youNum[i])) {
                    strike += 1;
                }
            }

            for (int i = 0; i < 3; i++) {
                for (int ii = 0; ii < 3; ii++) {
                    if (Objects.equals(my_num[i], youNum[ii])) {
                        ball += 1;
                    }
                }
            }
            ball -= strike;

            if (ball == 0 && strike == 0) {
                System.out.println("낫싱");
            } else {
                if (strike == 3) {
                    System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임종료\n 게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
                    String restartnum = Console.readLine();

                    if (restartnum.equalsIgnoreCase("1")) {
                        computer2Array();
                    }
                    restart = restartnum.equalsIgnoreCase("1");
                }
                if (ball > 0) {
                    System.out.print(ball + "볼");
                    restart = true;
                }
                if (strike > 0) {
                    System.out.println(strike + "스트라이크");
                    restart = true;
                }

            }

        } while (restart);


    }
}
