package CalculatorLv3;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ArithmeticCalculatorLv3<T extends Number> {   // calculate 메서드를 구현할 때, doubleValue 메서드를 사용하기 위해 Number 클래스를 상속받음

    // 속성
    private List<T> results;

    // 생성자
    public ArithmeticCalculatorLv3() {
        this.results = new ArrayList<T>();
    }

    // 메서드
    // getter 메서드 ( 최근에 계산한 값 )
    public List<T> getResults() {
        return results;
    }

    // setter 메서드 ( 최근에 계산한 값 )
    public void setResults(List<T> results) {
        this.results = results;
    }

    public T calculate(T num1, T num2, OperatorTypeLv3 operator) {
        // 정밀한 소수 계산을 위해 BigDecimal 활용
        // num1 과 num2 를 생성자 + 문자열 방식으로 BigDecimal 값으로 초기화
        BigDecimal firstNum = new BigDecimal(num1.toString());
        BigDecimal secondNum = new BigDecimal(num2.toString());
        // 계산 결과를 담을 answer
        BigDecimal answer = BigDecimal.ZERO;
        // answer 값을 다시 T 자료형으로 변환한 값을 담을 realAnswer
        T realAnswer = null;

        // BigDecimal 연산
        switch (operator) {
            case ADD:
                answer = firstNum.add(secondNum);
                break;
            case SUBTRACT:
                answer = firstNum.subtract(secondNum);
                break;
            case MULTIPLY:
                answer = firstNum.multiply(secondNum);
                break;
            case DIVIDE:
                // 소수점 6자리까지 + 반올림으로 계산
                answer = firstNum.divide(secondNum, 6, RoundingMode.HALF_UP);
                break;
        }

        // 불필요한 0 제거
        answer = answer.stripTrailingZeros();

        // BigDecimal 을 T 로 변환하는 castToT 메서드 호출
        realAnswer = castToT(answer, num1, num2);

        // 계산한 값 반환
        return realAnswer;
    }

    // 제네릭에서 강제로 형변환을 실행할 때 발생하는 컴파일러 경고 무시
    // BigDecimal 을 T 로 변환하는 메서드
    @SuppressWarnings("unchecked")
    private T castToT(BigDecimal value, T num1, T num2) {
        if (num1 instanceof Integer && num2 instanceof Integer) {               // 계산할 두 값이 Integer 형이면 value 를 Integer 값으로 형변환
            return (T) Integer.valueOf(value.intValue());
        } else if (num1 instanceof Double || num2 instanceof Double) {          // 계산할 두 값 중 하나가 Double 형이면 value 를 Double 값으로 형변환
            return (T) Double.valueOf(value.doubleValue());
        } else {
            throw new UnsupportedOperationException("지원되지 않는 타입입니다.");   // 나머지 경우에 예외 발생
        }
    }

    // App 클래스의 main 메서드에서 results 에 calculate 메서드로 계산한 값을 추가하기 위한 메서드
    // main 메서드에서는 T ( 제네릭 ) 을 사용할 수 없기 때문에, addResult 메서드를 따로 구현함
    // main 메서드에서 이 메서드를 호출하면 setter 를 간접적으로 호출할 수 있음
    public void addResult(T result) {
        List<T> updatedResults = new ArrayList<>(this.results);
        updatedResults.add(result);
        setResults(updatedResults);
    }

    // Calculator 클래스에 저장된 연산 결과들 중 가장 먼저 저장된 데이터를 삭제하는 기능
    public void removeResult() {
        if (!results.isEmpty()) {
            results.remove(0);
        } else {
            System.out.println("삭제할 결과가 없습니다.");
        }
    }

}
