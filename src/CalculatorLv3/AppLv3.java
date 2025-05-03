package CalculatorLv3;

import java.util.Scanner;

public class AppLv3 {

    public static void main(String[] args) {

        // ArithmeticCalculator 인스턴스 생성
        ArithmeticCalculatorLv3 arithmeticCalculatorLv3 = new ArithmeticCalculatorLv3();

        // 반복문을 사용하되, 반복의 종료를 알려주는 "exit" 문자열을 입력하기 전까지 무한으로 계산을 진행할 수 있도록 소스 코드를 수정하기
        // 입력값을 받기 위해 Scanner 객체 생성
        Scanner scanner = new Scanner(System.in);
        // 사칙연산에 사용될 변수 num1, num2 생성 ( Number 형으로 생성해서 나중에 Integer 또는 Double 형으로 초기화 )
        Number num1;
        Number num2;
        // 입력값을 받을 문자열 변수 input
        String input;
        // 연산자 타입을 저장할 OperatorType 변수 operator
        OperatorTypeLv3 operator = null;
        // 더 계산할지 판단하기 위해 문자열 변수 command 생성
        String command = "";

        // command 값이 "exit" 이면 반복문 종료
        while (!command.equals("exit")) {

            // 양의 정수 ( 0 포함 ) 를 입력받기
            // 첫 번째 숫자를 받아서 num1 에 초기화
            while (true) {
                System.out.print("첫 번째 숫자를 입력하세요 ( 정수 또는 실수 ) : ");
                input = scanner.nextLine();
                try {
                    if (input.contains(".")) {              // '.' 이 포함되면 실수값일 가능성이 있음
                        num1 = Double.parseDouble(input);   // 입력값이 실수 형태면 num1 에 실수값으로 저장 ( 나머지는 예외 처리 )
                        break;
                    } else {
                        num1 = Integer.parseInt(input);     // 입력값이 정수 형태면 num1 에 정수값으로 저장
                        break;
                    }
                } catch (NumberFormatException e) {         // 정수나 실수가 아니면 예외 처리
                    System.out.print("정수 또는 실수를 입력하세요.");
                    scanner.nextLine();     // 잘못된 입력 제거
                }
            }

            // 위의 방식과 동일하게 두 번째 숫자를 받아서 num2 에 초기화
            while (true) {
                System.out.print("두 번째 숫자를 입력하세요 ( 정수 또는 실수 ) : ");
                input = scanner.nextLine();
                try {
                    if (input.contains(".")) {
                        num2 = Double.parseDouble(input);
                        break;
                    } else {
                        num2 = Integer.parseInt(input);
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("정수 또는 실수를 입력하세요.");
                    scanner.nextLine();     // 잘못된 입력 제거
                }
            }



            // 사칙연산 기호 ( +, -, *, / ) 입력받기
            while (true) {
                System.out.print("사칙연산 기호를 입력하세요 ( +, -, *, / ) : ");
                input = scanner.nextLine();

                if (input.length() == 1) {
                    switch (input.charAt(0)) {                  // switch 문에 입력값을 사용하기 위해 입력값을 char 형태로 바꿈
                        case '+':
                            operator = OperatorTypeLv3.ADD;        // operator 를 각 연산자에 맞는 OperatorType 으로 초기화
                            break;
                        case '-':
                            operator = OperatorTypeLv3.SUBTRACT;
                            break;
                        case '*':
                            operator = OperatorTypeLv3.MULTIPLY;
                            break;
                        case '/':
                            operator = OperatorTypeLv3.DIVIDE;
                            break;
                        default:
                            System.out.println("사칙연산 기호를 입력하세요 ( +, -, *, / ) : ");
                            continue;
                    }
                    break;  // 입력 성공 시 반복문 종료
                } else {
                    System.out.println("한 글자만 입력하세요.");
                    scanner.nextLine();     // 잘못된 입력 제거
                }
            }

            // 나눗셈의 경우 두 번째 숫자가 0 보다 커야 함
            if (operator == OperatorTypeLv3.DIVIDE && (num2.doubleValue() == 0.0)) {       // num2 의 타입이 Integer 일수도 있고, Double 일수도 있기 때문에, doubleValue() 메서드를 활용해서 0 인지 확인
                System.out.println("0 은 나눗셈의 두 번째 숫자로 사용할 수 없습니다.");
                while (true) {
                    System.out.print("두 번째 숫자를 다시 입력하세요 ( 0 이 아닌 수 ) : ");
                    input = scanner.nextLine();

                    try {
                        if (input.contains(".")) {
                            num2 = Double.parseDouble(input);
                        } else {
                            num2 = Integer.parseInt(input);
                        }

                        if (num2.doubleValue() != 0.0) {    // 입력받은 두 번째 숫자가 0 이 아니면 반복문 종료
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("0 이 아닌 정수 또는 실수를 입력하세요.");
                    }
                }
            }



            // 위에서 입력받은 양의 정수 2개와 사칙연산 기호를 사용하여 연산을 진행한 후 결과값을 출력
            System.out.println("결과 : " + arithmeticCalculatorLv3.calculate(num1, num2, operator));

            // ArithmeticCalculator 의 getter, setter 활용
            // 지금까지 계산한 결과들을 출력
            // addResult 메서드를 통해 setter 를 간접적으로 활용
            arithmeticCalculatorLv3.addResult(arithmeticCalculatorLv3.calculate(num1, num2, operator));
            System.out.println("지금까지 계산한 결과 : " + arithmeticCalculatorLv3.getResults());

            System.out.print("더 계산하시겠습니까? ( exit 입력 시 종료 ) ");
            scanner.nextLine();     // 앞의 nextInt() 로 인해 남아있는 줄바꿈 문자 ('\n') 제거
            command = scanner.nextLine();
        }

        // 가장 먼저 저장된 데이터를 삭제
        arithmeticCalculatorLv3.removeResult();

        // 삭제 후 계산 결과들 출력
        // Calculator 클래스의 results 변수에 대한 getter 활용
        System.out.println("가장 먼저 저장된 데이터 삭제 후 나머지 계산 결과들 : " + arithmeticCalculatorLv3.getResults());

    }

}
