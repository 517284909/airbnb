import java.util.*;

// + - ( )
class BasicCalculator {
    public int calculate(String s) {
        Stack<Object> stack = new Stack<>();
        Integer result = 0;
        Character operator = '+';
        int i = 0;
        while (i < s.length()) {
            if (Character.isDigit(s.charAt(i))) {
                int curr = s.charAt(i) - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    curr = 10 * curr + (int)(s.charAt(i + 1) - '0');
                    ++i;
                }
                result = doCalculate(result, curr, operator);
            } else if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                operator = s.charAt(i);
            } else if (s.charAt(i) == '(') {
                stack.push(result);
                stack.push(operator);
                result = 0;
                operator = '+';
            } else if (s.charAt(i) == ')') {
                Character operator_stack = (Character)stack.pop();
                Integer param_stack = (Integer)stack.pop();
                result = doCalculate(param_stack, result, operator_stack);
            }

            ++i;
        }

        return result;
    }

    private int doCalculate(int a, int b, char op) {
        if (op == '+')  return a + b;
        if (op == '-')  return a - b;
        return -1;
    }

    public final static void main(String[] args) {
        BasicCalculator calculator = new BasicCalculator();
        System.out.println(calculator.calculate("1 + 2"));
        System.out.println(calculator.calculate("10 + 2"));
        System.out.println(calculator.calculate("(10 + 2) + 5"));
        System.out.println(calculator.calculate("(((10 + 2) + 5))"));
        System.out.println(calculator.calculate("1 - (((10 + 2) + 5))"));
    }
}
