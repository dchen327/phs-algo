
/**
 * Use your ArrayStackOfIntegers ADT for the following assignment:
 * Design and implement a client to calculate the following postfix expression: 
 * 8 4 -3 * 1 5 + / *
 * 
 * @David Chen
 * @Java 1.8.0 - 11/11/20
 */

public class ArrayStackOfIntegersCalculator_DC {
    public static void main(String[] args) {
        System.out.println(calculatePostfix("8 4 -3 * 1 5 + / *"));
    }

    // calculate an expression provided in postfix notation
    public static int calculatePostfix(String expression) {
        ArrayStackOfIntegers_DC stack = new ArrayStackOfIntegers_DC(1000);
        String[] splitBySpaces = expression.split(" ");
        for (String token : splitBySpaces) {
            try { // check if token is integer
                int num = Integer.parseInt(token);
                stack.push(num);
            } catch (NumberFormatException e) { // if not integer, run an operation
                int num1 = stack.pop();
                int num2 = stack.pop();
                stack.push(calculate(num1, num2, token));
            }
        }
        return stack.pop(); // the last value left will be the result of the expression
    }

    public static int calculate(int num1, int num2, String operation) {
        // given an operation and two numbers, execute the operation
        switch (operation) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                return num2 / num1;
        }
        return -1000000000;
    }
}
