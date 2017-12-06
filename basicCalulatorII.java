import java.util.*;

//
class BasicCalculatorII {

    // Using stack
    // + - * /
    public int calculate(String s) {
        Stack<Object> stack = new Stack<>();
        int i = 0;
        while (i < s.length()) {
            if (Character.isDigit(s.charAt(i))) {
                int num = s.charAt(i) - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    num = 10 * num + s.charAt(i + 1) - '0';
                    ++i;
                }
                if (!stack.isEmpty() && ((char)stack.peek() == '*' || (char)stack.peek() == '/')) {
                    char op = (char)stack.pop();
                    int a = (int)stack.pop();
                    stack.push(doCalculate(a, num, op));
                } else {
                    stack.push(num);
                }
            } else if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                if (stack.size() == 3) {
                    int b = (int)stack.pop();
                    char op = (char)stack.pop();
                    int a = (int)stack.pop();
                    stack.push(doCalculate(a, b, op));
                }
                stack.push(s.charAt(i));
            } else if (s.charAt(i) == '*' || s.charAt(i) == '/') {
                stack.push(s.charAt(i));
            }
            ++i;
        }
        while (stack.size() > 1) {
            int b = (int)stack.pop();
            char op = (char)stack.pop();
            int a = (int)stack.pop();
            stack.push(doCalculate(a, b, op));
        }
        if (!stack.isEmpty()) {
            return (int)stack.pop();
        }

        return -1;
    }

    private int doCalculate(int a, int b, char op) {
        if (op == '+')  return a + b;
        if (op == '*')  return a * b;
        if (op == '-')  return a - b;
        if (op == '/')  return a / b;
        return -1;
    }

    public final static void main(String[] args) {
        BasicCalculatorII calculator = new BasicCalculatorII();
        System.out.println(calculator.calculate("1 + 2 * 3"));
    }
}

