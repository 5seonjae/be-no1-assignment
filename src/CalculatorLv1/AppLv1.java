package CalculatorLv1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AppLv1 {

    public static void main(String[] args) {

        // 반복문을 사용하되, 반복의 종료를 알려주는 "exit" 문자열을 입력하기 전까지 무한으로 계산을 진행할 수 있도록 소스 코드를 수정하기

        Scanner scanner = new Scanner(System.in);
        int num1;
        int num2;
        String input;
        char operator = ' ';
        int result = 0;
        String command = "";

        while (!command.equals("exit")) {

            // 양의 정수 ( 0 포함 ) 를 입력받기

            while (true) {
                System.out.print("첫 번째 숫자를 입력하세요 ( 0 보다 크거나 같은 정수 ) : ");

                try {
                    num1 = scanner.nextInt();

                    if (num1 < 0) {
                        System.out.println("0 보다 크거나 같은 정수를 입력하세요.");
                    } else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("0 보다 크거나 같은 정수를 입력하세요.");
                    scanner.nextLine();  // 잘못된 입력 제거
                }
            }

            while (true) {
                System.out.print("두 번째 숫자를 입력하세요 ( 0 보다 크거나 같은 정수 ) : ");

                try {
                    num2 = scanner.nextInt();

                    if (num2 < 0) {
                        System.out.println("0 보다 크거나 같은 정수를 입력하세요.");
                    } else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("0 보다 크거나 같은 정수를 입력하세요.");
                    scanner.nextLine();  // 잘못된 입력 제거
                }
            }



            // 사칙연산 기호 ( +, -, *, / ) 입력받기

            while (true) {
                System.out.print("사칙연산 기호를 입력하세요 ( +, -, *, / ) : ");
                input = scanner.next();

                if (input.length() == 1) {
                    if (input.charAt(0) == '+' || input.charAt(0) == '-' || input.charAt(0) == '*' || input.charAt(0) == '/') {
                        operator = input.charAt(0);
                        break;
                    } else {
                        System.out.println("사칙연산 기호가 아닙니다.");
                    }
                } else {
                    System.out.println("한 글자만 입력하세요.");
                }
            }

            // 나눗셈의 경우 두 번째 숫자가 0 보다 커야 함

            if (operator == '/' && num2 == 0) {
                System.out.println("0 은 나눗셈의 두 번째 숫자로 사용할 수 없습니다.");
                while (true) {
                    System.out.print("두 번째 숫자를 다시 입력하세요 ( 양의 정수 ) : ");

                    try {
                        num2 = scanner.nextInt();

                        if (num2 > 0) {
                            break;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("양의 정수를 입력하세요.");
                        scanner.nextLine();
                    }

                }
            }



            // 위에서 입력받은 양의 정수 2개와 사칙연산 기호를 사용하여 연산을 진행한 후 결과값을 출력하기

            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    result = num1 / num2;
                    break;
            }
            System.out.println("결과 : " + result);

            System.out.print("더 계산하시겠습니까? ( exit 입력 시 종료 ) ");
            scanner.nextLine();     // 앞의 nextInt() 로 인해 남아있는 줄바꿈 문자 ('\n') 제거
            command = scanner.nextLine();
        }

    }

}
