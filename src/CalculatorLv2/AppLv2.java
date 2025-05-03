package CalculatorLv2;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AppLv2 {

    public static void main(String[] args) {

        // Calculator 인스턴스 생성
        CalculatorLv2 calculatorLv2 = new CalculatorLv2();

        // 반복문을 사용하되, 반복의 종료를 알려주는 "exit" 문자열을 입력하기 전까지 무한으로 계산을 진행할 수 있도록 소스 코드를 수정하기
        // 입력값을 받기 위해 Scanner 객체 생성
        Scanner scanner = new Scanner(System.in);
        // 사칙연산에 사용될 변수 num1, num2 생성
        int num1;
        int num2;
        // 입력값을 받을 문자열 변수 input
        String input;
        // 연산자 타입을 저장할 char 변수 operator
        char operator = ' ';
        // 더 계산할지 판단하기 위해 문자열 변수 command 생성
        String command = "";

        // command 값이 "exit" 이면 반복문 종료
        while (!command.equals("exit")) {

            // 양의 정수 ( 0 포함 ) 를 입력받기
            // 첫 번째 숫자를 받아서 num1 에 초기화
            while (true) {
                System.out.print("첫 번째 숫자를 입력하세요 ( 0 보다 크거나 같은 정수 ) : ");

                try {
                    num1 = scanner.nextInt();

                    if (num1 < 0) {
                        System.out.println("0 보다 크거나 같은 정수를 입력하세요.");
                    } else {
                        break;
                    }
                } catch (InputMismatchException e) {        // 정수 값이 아니면 예외 처리
                    System.out.println("0 보다 크거나 같은 정수를 입력하세요.");
                    scanner.nextLine();     // 잘못된 입력 제거
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
                    System.out.print("0 보다 크거나 같은 정수를 입력하세요.");
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



            // 위에서 입력받은 양의 정수 2개와 사칙연산 기호를 사용하여 연산을 진행한 후 결과값을 출력
            System.out.println("결과 : " + calculatorLv2.calculate(num1, num2, operator));

            // 지금까지 계산한 결과들을 출력
            // Calculator 클래스의 results 변수에 대한 getter 와 setter 활용
            // Calculator 클래스의 results 를 getter 로 가져와서 계산한 값을 추가한 다음 다시 setter 로 results 를 갱신
            List<Integer> results = calculatorLv2.getResults();
            results.add(calculatorLv2.calculate(num1, num2, operator));
            calculatorLv2.setResults(results);
            System.out.println("지금까지 계산한 결과 : " + calculatorLv2.getResults());

            System.out.print("더 계산하시겠습니까? ( exit 입력 시 종료 ) ");
            scanner.nextLine();     // 앞의 nextInt() 로 인해 남아있는 줄바꿈 문자 ('\n') 제거
            command = scanner.nextLine();
        }

        // 가장 먼저 저장된 데이터를 삭제
        calculatorLv2.removeResult();

        // 삭제 후 계산 결과들 출력
        // Calculator 클래스의 results 변수에 대한 getter 활용
        System.out.println("가장 먼저 저장된 데이터 삭제 후 나머지 계산 결과들 : " + calculatorLv2.getResults());

    }

}
