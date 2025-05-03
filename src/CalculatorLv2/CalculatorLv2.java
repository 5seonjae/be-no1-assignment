package CalculatorLv2;

import java.util.ArrayList;
import java.util.List;

public class CalculatorLv2 {

    // 속성
    private List<Integer> results;

    // 생성자
    public CalculatorLv2() {
        this.results = new ArrayList<Integer>();
    }

    // 메서드
    // getter 메서드 ( 최근에 계산한 값 )
    public List<Integer> getResults() {
        return results;
    }

    // setter 메서드 ( 최근에 계산한 값 )
    public void setResults(List<Integer> results) {
        this.results = results;
    }

    public int calculate(int num1, int num2, char operator) {
        int answer = 0;

        switch (operator) {
            case '+':
                answer = num1 + num2;
                break;
            case '-':
                answer = num1 - num2;
                break;
            case '*':
                answer = num1 * num2;
                break;
            case '/':
                answer = num1 / num2;
                break;
        }

        return answer;
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
